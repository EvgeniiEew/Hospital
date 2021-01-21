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
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class EmailController {
    @Autowired
    private EpicrisisService epicrisisService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

//    @GetMapping("/sendemail")
////    public String sendEmail() throws MessagingException, IOException {
////        this.emailService.sendmail("" );
////        return "Email sent successfully";
////    }

    //    localhost:8090/user/export/?id=5
//    @PostMapping("user/export/{id}")
//    public void exportToEmail(@PathVariable("id") Integer id, HttpServletResponse response) throws DocumentException, IOException {
//        response.setContentType("application/pdf");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
//        response.setHeader(headerKey, headerValue);
//
//        UserDischarsergeDto userDischarsergeDto = this.userService.generateHospitalDischarge(id);
//        List<Diagnosis> diagnosisList = userDischarsergeDto.getDiagnosisNameAndDate();
//        List<AppointmentDischarsergesDto> appointmentDischarsergesDtoList = userDischarsergeDto.getListDischarserge();
//        List<Epicrisis> epicrisisList = this.epicrisisService.getEpicrisisToDiscargeList(id);
//
//        UserDischarsergePDFExporter userDischarsergePDFExporter = new UserDischarsergePDFExporter(userDischarsergeDto,
//                diagnosisList, appointmentDischarsergesDtoList, epicrisisList);
//        userDischarsergePDFExporter.export(response);
//    }
    @PostMapping("user/export/{id}")
    public String exportToEmail(@PathVariable("id") Integer id, HttpServletRequest request) throws DocumentException, IOException, MessagingException, URISyntaxException {
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/");
        UserDischarsergeDto userDischarsergeDto = this.userService.generateHospitalDischarge(id);
        List<Diagnosis> diagnosisList = userDischarsergeDto.getDiagnosisNameAndDate();
        List<AppointmentDischarsergesDto> appointmentDischarsergesDtoList = userDischarsergeDto.getListDischarserge();
        List<Epicrisis> epicrisisList = this.epicrisisService.getEpicrisisToDiscargeList(id);
        UserDischarsergePDFExporter userDischarsergePDFExporter = new UserDischarsergePDFExporter(userDischarsergeDto,
                diagnosisList, appointmentDischarsergesDtoList, epicrisisList, userService);
        userDischarsergePDFExporter.export(path);
        this.emailService.sendmail(this.userService.getEmailByIdUser(id), path);
        return "Email sent successfully";
    }
}
