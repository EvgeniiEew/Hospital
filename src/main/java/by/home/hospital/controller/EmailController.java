package by.home.hospital.controller;

import by.home.hospital.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;
    @RequestMapping(value = "/sendemail")
    public String sendEmail(String pasword) throws AddressException, MessagingException, IOException {
        this.emailService.sendmail(pasword);
        return "Email sent successfully";
    }

}
