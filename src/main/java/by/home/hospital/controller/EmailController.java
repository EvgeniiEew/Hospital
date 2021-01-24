package by.home.hospital.controller;

import by.home.hospital.converter.UserDischarsergePDFExporter;
import by.home.hospital.domain.Diagnosis;
import by.home.hospital.domain.Epicrisis;
import by.home.hospital.dto.AppointmentDischarsergesDto;
import by.home.hospital.dto.UserDischarsergeDto;
import by.home.hospital.service.impl.EmailService;
import by.home.hospital.service.impl.EpicrisisService;
import by.home.hospital.service.impl.UserService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class EmailController {
    @Autowired
    private EpicrisisService epicrisisService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @PostMapping("user/export/{id}")
    public String exportToEmail(@PathVariable("id") Integer id, HttpServletRequest request) throws DocumentException, IOException {
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/");

        UserDischarsergeDto userDischarsergeDto = this.userService.generateHospitalDischarge(id);
        List<Diagnosis> diagnosisList = userDischarsergeDto.getDiagnosisNameAndDate();
        List<AppointmentDischarsergesDto> appointmentDischarsergesDtoList = userDischarsergeDto.getListDischarserge();
        List<Epicrisis> epicrisisList = this.epicrisisService.getEpicrisisToDiscargeList(id);

        UserDischarsergePDFExporter userDischarsergePDFExporter =
                new UserDischarsergePDFExporter(
                        userDischarsergeDto,
                        diagnosisList,
                        appointmentDischarsergesDtoList,
                        epicrisisList,
                        userService);
        userDischarsergePDFExporter.export(path);
        this.emailService.sendmail(this.userService.getEmailByIdUser(id), path);
        return "Email sent successfully";
    }
}
