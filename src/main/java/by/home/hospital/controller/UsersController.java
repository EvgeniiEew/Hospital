package by.home.hospital.controller;


import by.home.hospital.domain.User;
import by.home.hospital.dto.*;
import by.home.hospital.service.StorageService;
import by.home.hospital.service.impl.PatientDetailsService;
import by.home.hospital.service.impl.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

// todo CRUD methods
@Controller
public class UsersController {

    private final  int pageSize = 5;
    private final String INDEX = "index";
    private final String VIEW = "myViewList";
    private final String DISCHARGES = "dischargesList";

    @Autowired
    private PatientDetailsService patientDetailsService;
    @Autowired
    private ConversionService conversionService;
    @Autowired
    private StorageService imgService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String homePage() {
        return this.INDEX;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/myaccount")
    public String getMyView(Authentication authentication, Model model) {

        if (authentication == null) {
            return "redirect:/login";
        }
        String email = authentication.getName();
        MyViewDto view = (conversionService.convert(email, MyViewDto.class));
        model.addAttribute("view", view);
        model.addAttribute("email", email);
        return this.VIEW;
    }

    @PostMapping("/users/{id}/img")
    public String handleFileUpload(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file) throws IOException {
        imgService.store(id, file);
        return "redirect:/myaccount";
    }

    @GetMapping("/users/{id}/img")
    public void getImmage(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        Avatar file = this.imgService.getFile(id);
        if (file != null) {
            try (InputStream is = file.getData()) {
                IOUtils.copy(is, response.getOutputStream());
            }
        }
    }
    //!
    @GetMapping("/patient/{id}/img")
    public void getImmagePatient(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
      Integer idUser = this.patientDetailsService.getUserByIdPatientDetaisl(id).getId();
       Avatar file = this.imgService.getFile(idUser);
        if (file != null) {
            try (InputStream is = file.getData()) {
                IOUtils.copy(is, response.getOutputStream());
            }
        }
    }
    //в форме доктора регистрации добавить радиобаттон для регистрации доктора или медсесты с разным пост urlom
    @PostMapping("/nurse/create")
    public String registerNurse(NurseRegisterDto nurseRegisterDto) {
        this.userService.saveNurse(nurseRegisterDto);
        return "redirect:/";
    }
    @GetMapping("/user/discharges")
    public String dischargesUser(Model model){
        return dischargesUserFindPaginated(1,model);
    }

    @GetMapping("/user/discharges/{pageNo}")
    public String dischargesUserFindPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        Page<User> usersPage = this.userService.findAllActiveUsersNative(pageNo, pageSize);
        List<User> usersList = usersPage.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", usersPage.getTotalPages());
        model.addAttribute("totalItems", usersPage.getTotalElements());
        model.addAttribute("usersList", usersList);
        return this.DISCHARGES;
    }
    @GetMapping("/patient/{id}/discharges")
    public void dischargeUser(@PathVariable("id") Integer id,Model model){
//        UserDischarsergeDto userDischarsergeDto = this.userService.;
//       List<AppointmentDischarsergesDto> list= userDischarsergeDto.getListDischarserge();
//        model.addAttribute("list" ,list);
//        model.addAttribute();
//        return;
    }

    @GetMapping("user/{id}/overwrite")
    public String overwriteUser(@PathVariable("id") Integer id) {
        this.patientDetailsService.resetPatientDetaislStatusFromIdUser(id);
        return "redirect:/";
    }
}
