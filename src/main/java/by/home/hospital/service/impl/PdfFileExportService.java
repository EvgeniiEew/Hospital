package by.home.hospital.service.impl;

import by.home.hospital.domain.Diagnosis;
import by.home.hospital.domain.Epicrisis;
import by.home.hospital.dto.AppointmentDischarsergesDto;
import by.home.hospital.dto.UserDischarsergeDto;
import by.home.hospital.service.IFileExport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfFileExportService implements IFileExport {
    private final EpicrisisService epicrisisService;
    private final UserService userService;

    @Override
    public String pdfFileExport(String path, Integer id) {
        UserDischarsergeDto userDischarsergeDto = this.userService.generateHospitalDischarge(id);
        List<Diagnosis> diagnosisList = userDischarsergeDto.getDiagnosisNameAndDate();
        List<AppointmentDischarsergesDto> appointmentDischarsergesDtoList = userDischarsergeDto.getListDischarserge();
        List<Epicrisis> epicrisisList = this.epicrisisService.getEpicrisisToDiscargeList(id);
        UserDischarsergePDFExporterService userDischarsergePDFExporterService =
                new UserDischarsergePDFExporterService(
                        userDischarsergeDto,
                        diagnosisList,
                        appointmentDischarsergesDtoList,
                        epicrisisList,
                        userService);
        try {
            String fileName = userDischarsergePDFExporterService.export(path);
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "fileName";
    }

}
