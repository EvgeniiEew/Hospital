package by.home.hospital.controller;

import by.home.hospital.service.IFileExport;
import by.home.hospital.service.impl.EmailService;
import by.home.hospital.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
public class EmailController {
    private final String INFO = "infoList";
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private IFileExport pdfFileExportService;

    @PostMapping("user/export/{id}")
    public String exportToEmail(@PathVariable("id") Integer id, HttpServletRequest request) {
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/");
        String fileName = this.pdfFileExportService.pdfFileExport(path, id);
        this.emailService.sendmail(this.userService.getEmailByIdUser(id), path, fileName);
        return this.INFO;
    }
}
