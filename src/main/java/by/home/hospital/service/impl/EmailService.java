package by.home.hospital.service.impl;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {

    public void sendmail(String password) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = javax.mail.Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("evgenii1900@gmail.com", password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("slabysh_yauhen@mail.ru", true));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("slabysh_yauhen@mail.ru"));
            msg.setSubject("Выписка из больницы");
            msg.setContent("Выписка из больницы", "text/html");
            msg.setSentDate(new Date());
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(getMessage(), "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
             MimeBodyPart attachPart = new MimeBodyPart();

            attachPart.attachFile("E:\\Projects\\ResaulProject\\src\\main\\resources\\templates\\dischargeList.html");
            multipart.addBodyPart(attachPart);
            msg.setContent(multipart);

            Transport.send(msg);
        } catch (MessagingException mex) {
            throw new RuntimeException(mex);
        }
    }

    private String getMessage() {
        return new StringBuilder("Hello ") //.append(user.getFirstName()).append("! ")
                .append("To  your account discharge").toString(); //.append(emailConfig.getDomainHost())
//                .append("/activate/").toString();//.append(user.getId()).toString();
    }
}
