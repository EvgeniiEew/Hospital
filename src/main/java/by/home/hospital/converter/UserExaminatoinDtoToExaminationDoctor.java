package by.home.hospital.converter;

import by.home.hospital.dto.AppointmentDto;
import by.home.hospital.domain.ExaminationDoctor;
import by.home.hospital.dto.UserExaminationDto;
import by.home.hospital.enums.Type;
import by.home.hospital.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static java.lang.Integer.parseInt;

@Component
public class UserExaminatoinDtoToExaminationDoctor implements Converter<UserExaminationDto, ExaminationDoctor> {

    @Override
    public ExaminationDoctor convert(UserExaminationDto userExaminationDto) {
        ExaminationDoctor examinationDoctor = new ExaminationDoctor(
                parseInt(userExaminationDto.getIdPatient()),
                userExaminationDto.getAuthenticationDoctorId(),
                userExaminationDto.getDiagnosisDto(),
                new AppointmentDto(userExaminationDto.getNameApointment(),
                        Type.valueOf(userExaminationDto.getName())),
                userExaminationDto.getEpicrisis());
        return examinationDoctor;
    }
}
