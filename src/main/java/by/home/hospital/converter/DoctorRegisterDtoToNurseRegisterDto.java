package by.home.hospital.converter;

import by.home.hospital.dto.DoctorRegisterDto;
import by.home.hospital.dto.NurseRegisterDto;
import org.springframework.core.convert.converter.Converter;

public class DoctorRegisterDtoToNurseRegisterDto implements Converter<DoctorRegisterDto, NurseRegisterDto> {

    @Override
    public NurseRegisterDto convert(DoctorRegisterDto source) {
        NurseRegisterDto nurseRegisterDto = new NurseRegisterDto(
                source.getFirstName(),
                source.getLastName(),
                source.getLogin(),
                source.getPassword());
        return nurseRegisterDto;
    }
}