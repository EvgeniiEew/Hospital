package by.home.hospital.controller;


import by.home.hospital.domain.Position;
import by.home.hospital.domain.User;
import by.home.hospital.dto.*;
import by.home.hospital.service.StorageService;
import by.home.hospital.service.impl.CredentialAuthService;
import by.home.hospital.service.impl.EpicrisisService;
import by.home.hospital.service.impl.PatientDetailsService;
import by.home.hospital.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsersController {

    private final int pageSize = 5;
    private final String INDEX = "index";
    private final String VIEW = "myViewList";
    private final String DISCHARGES = "dischargesList";
    private final String DISCHARGE = "dischargeList";
    private final String EDIT_USER_LIST = "editUserList";

    private final CredentialAuthService credentialAuthService;
    private final EpicrisisService epicrisisService;
    private final PatientDetailsService patientDetailsService;
    private final ConversionService conversionService;
    private final StorageService imgService;
    private final UserService userService;

    @GetMapping("/")
    public String homePage(Model model) {
        Integer userId = credentialAuthService.getIdAutUser();
        if (userId != 0 && userService.getUserById(userId).getPosition().equals(Position.PATIENT)) {
            model.addAttribute("patientStatus", patientDetailsService.getStatusByUserId(userId).toString());
        }
        return INDEX;
    }

    @PostMapping("/users/{id}/edit/")
    @PreAuthorize("@editUserVouter.checkUserId(authentication,#id) or hasRole('ADMIN')")
    public String editUser(Authentication authentication, @PathVariable("id") Integer id, Model model) {
        UserEditDto userEditDto = userService.getUserEditById(id);
        getEditModel(id, model, userEditDto);
        return EDIT_USER_LIST;
    }

    private Model getEditModel(Integer id, Model model, UserEditDto userEditDto) {
        String email = userService.getEmailByIdUser(id);
        MyViewDto view = (conversionService.convert(email, MyViewDto.class));
        model.addAttribute("userEditDto", userEditDto);
        model.addAttribute("view", view);
        return model;
    }

    @PostMapping("/user/edit/{id}/")
    public String editUser(@PathVariable("id") Integer id, @Valid UserEditDto userEditDto,
                           BindingResult bindingResult,
                           HttpServletRequest request,
                           Model model) {
        if (bindingResult.hasErrors()) {
            userEditDto.setId(id);
            getEditModel(id, model, userEditDto);
            return EDIT_USER_LIST;
        }
        userEditDto.setId(id);
        userService.userEdit(userEditDto);
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
        return VIEW;
    }

    @PostMapping("/users/{id}/img")
    public String handleFileUpload(@PathVariable("id") Integer id, @RequestParam("files") MultipartFile file) throws IOException {
        imgService.store(id, file);
        return "redirect:/myaccount";
    }

    @GetMapping("/users/{id}/img")
    public void getImmage(@PathVariable("id") Integer id, HttpServletResponse response, HttpServletRequest request) throws IOException {
        getUserImgage(id, response, request);
    }

    @GetMapping("/patient/{idUser}/img")
    public void getImmagePatient(@PathVariable("idUser") Integer idUser, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Integer id = patientDetailsService.getUserByIdPatientDetails(idUser).getId();
        getUserImgage(id, response, request);
    }

    private void getUserImgage(Integer id, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Avatar file = imgService.getFile(id);
        if (file != null) {
            try (InputStream is = file.getData()) {
                IOUtils.copy(is, response.getOutputStream());
            }
        } else {
            ServletContext context = request.getServletContext();
            String path = context.getRealPath("/");
            String fullFilePath = path.concat("/resources/photo/no_avatar.png");
            IOUtils.copy(new FileInputStream(new File(fullFilePath)), response.getOutputStream());
        }
    }

    @PostMapping("/nurse/create")
    public String registerNurse(NurseRegisterDto nurseRegisterDto) {
        userService.saveNurse(nurseRegisterDto);
        return "redirect:/";
    }

    @GetMapping("/user/discharges")
    public String dischargesUser(Model model) {
        return dischargesUserFindPaginated(1, model);
    }

    @GetMapping("/user/discharges/{pageNo}")
    public String dischargesUserFindPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        Page<User> usersPage = userService.findAllActiveUsersNative(pageNo, pageSize);
        List<User> usersList = usersPage.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", usersPage.getTotalPages());
        model.addAttribute("totalItems", usersPage.getTotalElements());
        model.addAttribute("usersList", usersList);
        return DISCHARGES;
    }

    @PostMapping("/patient/{id}/discharges")
    public String dischargeUser(@PathVariable("id") Integer id, Model model) {
        UserDischarsergeDto userDischarsergeDto = userService.generateHospitalDischarge(id);
        model.addAttribute("listAppointment", userDischarsergeDto.getListDischarserge());
        model.addAttribute("userDischarsergeDto", userDischarsergeDto);
        model.addAttribute("listDiagnosis", userDischarsergeDto.getDiagnosisNameAndDate());
        model.addAttribute("epicrisisList", epicrisisService.getEpicrisisToDiscargeList(id));
        return DISCHARGE;
    }

    @GetMapping("user/overwrite")
    public String overwriteUser(Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        patientDetailsService.resetPatientDetailslStatusFromIdUser(credentialAuthService.getIdAutUser());
        return "redirect:/";
    }
}
