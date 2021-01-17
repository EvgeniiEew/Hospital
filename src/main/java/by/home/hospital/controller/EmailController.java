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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class EmailController {
    @Autowired
    private EpicrisisService epicrisisService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @RequestMapping(value = "/sendemail")
    public String sendEmail(String pasword) throws AddressException, MessagingException, IOException {
        this.emailService.sendmail(pasword);
        return "Email sent successfully";
    }
//    localhost:8090/user/export/?id=5
    @GetMapping("user/export/")
    public void exportToEmail(HttpServletResponse response , Integer id) throws DocumentException,IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_"+ currentDateTime+".pdf";
        response.setHeader(headerKey, headerValue);


        UserDischarsergeDto userDischarsergeDto = this.userService.generateHospitalDischarge(id);
        List<Diagnosis> diagnosisList = userDischarsergeDto.getDiagnosisNameAndDate();
        List<AppointmentDischarsergesDto> appointmentDischarsergesDtoList =  userDischarsergeDto.getListDischarserge();
        List<Epicrisis> epicrisisList = this.epicrisisService.getEpicrisisToDiscargeList(id);

        UserDischarsergePDFExporter userDischarsergePDFExporter = new UserDischarsergePDFExporter(userDischarsergeDto,
                diagnosisList, appointmentDischarsergesDtoList, epicrisisList);
        userDischarsergePDFExporter.export(response);
    }
}
