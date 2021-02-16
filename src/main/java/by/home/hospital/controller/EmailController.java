package by.home.hospital.controller;

import by.home.hospital.service.IFileExport;
import by.home.hospital.service.impl.EmailService;
import by.home.hospital.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class EmailController {
    private final String INFO = "infoList";
    private final UserService userService;
    private final EmailService emailService;
    private final IFileExport pdfFileExportService;

    @PostMapping("user/export/{id}")
    public String exportToEmail(@PathVariable("id") Integer id, HttpServletRequest request) {
        ServletContext context = request.getServletContext();
        String path = context.getRealPath("/");
        String fileName = pdfFileExportService.pdfFileExport(path, id);
        emailService.sendmail(userService.getEmailByIdUser(id), path, fileName);
        return INFO;
    }
}
