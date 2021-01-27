package by.home.hospital.controller;


import by.home.hospital.dto.NurseRegisterDto;
import by.home.hospital.domain.User;
import by.home.hospital.dto.*;
import by.home.hospital.service.StorageService;
import by.home.hospital.service.impl.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

// todo CRUD methods
@Controller
public class UsersController {

    private final int pageSize = 5;
    private final String INDEX = "index";
    private final String VIEW = "myViewList";
    private final String DISCHARGES = "dischargesList";
    private final String DISCHARGE = "dischargeList";
    private final String EDIT_USER_LIST = "editUserList";

    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    private CredentialAuthService credentialAuthService;
    @Autowired
    private EpicrisisService epicrisisService;
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

    @PostMapping("/users/{id}/edit/")
    @PreAuthorize("@editUserVouter.checkUserId(authentication,#id) or hasRole('ADMIN')")
    public String editUser(Authentication authentication, @PathVariable("id") Integer id, Model model) {
       String email = this.userService.getEmailByIdUser(id);
        MyViewDto view = (conversionService.convert(email, MyViewDto.class));
        UserEditDto userEditDto = this.userService.getUserEditById(id);
        model.addAttribute("userEditDto", userEditDto);
        model.addAttribute("view", view);
        return this.EDIT_USER_LIST;
    }

    @PostMapping("/user/edit/{id}/")
    public String editUser(@PathVariable("id") Integer id, @Valid UserEditDto userEditDto,
                           BindingResult bindingResult,
                           HttpServletRequest request,
                           Model model) {
        if (bindingResult.hasErrors()) {
            userEditDto.setId(id);
            String email = this.userService.getEmailByIdUser(id);
            MyViewDto view = (conversionService.convert(email, MyViewDto.class));
            model.addAttribute("userEditDto", userEditDto);
            model.addAttribute("view", view);
            return this.EDIT_USER_LIST;
        }
        userEditDto.setId(id);
        this.userService.userEdit(userEditDto);
        if (request.isUserInRole("ADMIN")) {
            return "redirect:/credanchials";
        }
        return "redirect:/myaccount";
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
    public String handleFileUpload(@PathVariable("id") Integer id, @RequestParam("files") MultipartFile file) throws IOException {
        imgService.store(id, file);
        return "redirect:/myaccount";
//        return  "redirect:/users/"+ id +"/edit/";
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
        Integer idUser = this.patientDetailsService.getUserByIdPatientDetails(id).getId();
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
    public String dischargesUser(Model model) {
        return dischargesUserFindPaginated(1, model);
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

    @PostMapping("/patient/{id}/discharges")
    public String dischargeUser(@PathVariable("id") Integer id, Model model) {
        UserDischarsergeDto userDischarsergeDto = this.userService.generateHospitalDischarge(id);
        model.addAttribute("listAppointment", userDischarsergeDto.getListDischarserge());
        model.addAttribute("userDischarsergeDto", userDischarsergeDto);
        model.addAttribute("listDiagnosis", userDischarsergeDto.getDiagnosisNameAndDate());
        model.addAttribute("epicrisisList", this.epicrisisService.getEpicrisisToDiscargeList(id));
        return this.DISCHARGE;
    }

    @GetMapping("user/overwrite")
    public String overwriteUser(Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        this.patientDetailsService.resetPatientDetaislStatusFromIdUser(this.credentialAuthService.getIdAutUser());
        return "redirect:/";
    }
}
