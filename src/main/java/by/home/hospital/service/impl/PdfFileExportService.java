package by.home.hospital.service.impl;

import by.home.hospital.service.UserDischarsergePDFExporterService;
import by.home.hospital.domain.Diagnosis;
import by.home.hospital.domain.Epicrisis;
import by.home.hospital.dto.AppointmentDischarsergesDto;
import by.home.hospital.dto.UserDischarsergeDto;
import by.home.hospital.service.IFileExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PdfFileExportService implements IFileExport {
    @Autowired
    private EpicrisisService epicrisisService;
    @Autowired
    private UserService userService;
    @Override
    public void pdfFileExport(String path, Integer id) {
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
           userDischarsergePDFExporterService.export(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
