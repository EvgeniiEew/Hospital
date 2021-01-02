//package by.home.hospital.converter;
//
//import by.home.hospital.dto.AppointmentDto;
//import by.home.hospital.dto.ExaminationDoctorDto;
//import by.home.hospital.dto.UserExaminationDto;
//import by.home.hospital.enums.Type;
//import by.home.hospital.service.impl.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import static java.lang.Integer.parseInt;
//
//@Component
//public class UserExaminatoinDtoToExaminationDoctorDto implements Converter<UserExaminationDto, ExaminationDoctorDto> {
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public ExaminationDoctorDto convert(UserExaminationDto userExaminationDto) {
//        ExaminationDoctorDto examinationDoctorDto = new ExaminationDoctorDto(
//                parseInt(userExaminationDto.getIdPatient()),
//                this.userService.getUserIdByCredentials_login(userExaminationDto.getAuthentication().getName()),
//                userExaminationDto.getDiagnosisDto(),
//                new AppointmentDto(userExaminationDto.getNameApointment(),
//                        Type.valueOf(userExaminationDto.getName())),
//                userExaminationDto.getEpicrisis());
//        return examinationDoctorDto;
//    }
//}
