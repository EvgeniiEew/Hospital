package by.home.hospital.service.impl;

import by.home.hospital.config.EmailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;


@Service
public class EmailService {
    @Autowired
    private EmailProperties emailConfig;

    public void sendmail(String address, String path) throws IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", emailConfig.getHost());
        props.put("mail.smtp.port", emailConfig.getPort());
        Session session = javax.mail.Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailConfig.getUsername(), emailConfig.getPassword());
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(address, true));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(address));
            msg.setSubject("Выписка из больницы");
            msg.setContent("Выписка из больницы", "text/html");
            msg.setSentDate(new Date());
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(getMessage(), "text/html");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            MimeBodyPart attachPart = new MimeBodyPart();
//            url('/files/Extract.pdf')
            attachPart.attachFile(path+"/resources/Extract.pdf");
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
