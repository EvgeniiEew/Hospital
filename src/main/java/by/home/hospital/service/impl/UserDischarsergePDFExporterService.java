package by.home.hospital.service.impl;

import by.home.hospital.domain.Diagnosis;
import by.home.hospital.domain.Epicrisis;
import by.home.hospital.dto.AppointmentDischarsergesDto;
import by.home.hospital.dto.UserDischarsergeDto;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserDischarsergePDFExporterService {
    private UserDischarsergeDto userDischarsergeDto;
    private List<Diagnosis> diagnosisList;
    private List<AppointmentDischarsergesDto> appointmentDischarsergesDtoList;
    private List<Epicrisis> epicrisisList;
    private UserService userService;

    public UserDischarsergePDFExporterService(UserDischarsergeDto userDischarsergeDto, List<Diagnosis> diagnosisList, List<AppointmentDischarsergesDto> appointmentDischarsergesDtoList, List<Epicrisis> epicrisisList, UserService userService) {
        this.userDischarsergeDto = userDischarsergeDto;
        this.diagnosisList = diagnosisList;
        this.appointmentDischarsergesDtoList = appointmentDischarsergesDtoList;
        this.epicrisisList = epicrisisList;
        this.userService = userService;
    }

    private void writeTableHeaderDiagnosisList(PdfPTable tableDiagnosisList) {
        for (Diagnosis diagnosis : diagnosisList) {
            tableDiagnosisList.addCell(String.valueOf(diagnosis.getName()));
            tableDiagnosisList.addCell(String.valueOf(diagnosis.getDate()));
        }
    }

    private void writeTableHeaderEpicrisis(PdfPTable tableEpicrisis) {
        for (Epicrisis epicrisis : epicrisisList) {
            tableEpicrisis.addCell(String.valueOf(epicrisis.getInfo()));
        }
    }

    private void writeTableHeaderAppointmentDischarsergesDto(PdfPTable tableAppointmentDischarsergesDto) {
        for (AppointmentDischarsergesDto appointmentDischarsergesDto : appointmentDischarsergesDtoList) {
            tableAppointmentDischarsergesDto.addCell(String.valueOf(appointmentDischarsergesDto.getAppointmentName()));
            tableAppointmentDischarsergesDto.addCell(String.valueOf(appointmentDischarsergesDto.getAppointmentType()));

            tableAppointmentDischarsergesDto.addCell(String.valueOf(appointmentDischarsergesDto.getAppointmentCompletionDate()));
            tableAppointmentDischarsergesDto.addCell(String.valueOf(appointmentDischarsergesDto.getDoctorPosition()));

            tableAppointmentDischarsergesDto.addCell(String.valueOf(appointmentDischarsergesDto.getDoctorType()));
            tableAppointmentDischarsergesDto.addCell(String.valueOf(appointmentDischarsergesDto.getDoctorFirstName()));
            tableAppointmentDischarsergesDto.addCell(String.valueOf(appointmentDischarsergesDto.getDoctorLastName()));
        }
    }

    private void writeTableDataAppointmentDischarsergesDto(PdfPTable tableAppointmentDischarsergesDto) {
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setBackgroundColor(Color.gray);
        pdfPCell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        pdfPCell.setPhrase(new Phrase("Name appointment", font));
        tableAppointmentDischarsergesDto.addCell(pdfPCell);

        pdfPCell.setPhrase(new Phrase("Type appointment", font));
        tableAppointmentDischarsergesDto.addCell(pdfPCell);

        pdfPCell.setPhrase(new Phrase("Appointment completion  date ", font));
        tableAppointmentDischarsergesDto.addCell(pdfPCell);

        pdfPCell.setPhrase(new Phrase("Doctor position", font));
        tableAppointmentDischarsergesDto.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("doctor's specialization", font));
        tableAppointmentDischarsergesDto.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("FirstName", font));
        tableAppointmentDischarsergesDto.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("LastName", font));
        tableAppointmentDischarsergesDto.addCell(pdfPCell);
    }

    private void writeTableDataDiagnosisList(PdfPTable tableDiagnosisList) {
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setBackgroundColor(Color.gray);
        pdfPCell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        pdfPCell.setPhrase(new Phrase("complete diagnosis in a year", font));
        tableDiagnosisList.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("Diagnosed", font));
        tableDiagnosisList.addCell(pdfPCell);

    }

    private void writeTableHeaderFromUserDischarserge(PdfPTable table) {
        try {
            Image image = setAvatarPdf();
            table.addCell(image);
        } catch (Exception e) {
            table.addCell("photo");
        }
        table.addCell(String.valueOf(userDischarsergeDto.getIdPatientUser()));
        table.addCell(String.valueOf(userDischarsergeDto.getFirstNamePatient()));
        table.addCell(String.valueOf(userDischarsergeDto.getLastNamePatient()));
    }

    private void writeTableDataUserDischarsergeDto(PdfPTable table) {
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setBackgroundColor(Color.gray);
        pdfPCell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        pdfPCell.setPhrase(new Phrase("Patient photo", font));

        table.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("Number Patient", font));

        table.addCell(pdfPCell);

        pdfPCell.setPhrase(new Phrase("FirstNamePatient", font));
        table.addCell(pdfPCell);

        pdfPCell.setPhrase(new Phrase("LastNamePatient", font));
        table.addCell(pdfPCell);
    }

    public String export(String path) throws DocumentException, IOException {
        Document document = new Document(PageSize.A3);
        String fileName = generateFileNameByUser();
        PdfWriter.getInstance(document, new FileOutputStream(path + "/resources/" + fileName + ".pdf"));
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);
        font.setSize(18);
        Paragraph title = new Paragraph("Extract from the patient's medical record", font);

        document.add(title);
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        writeTableDataUserDischarsergeDto(table);
        writeTableHeaderFromUserDischarserge(table);
        document.add(table);

        Paragraph titleDiagnosis = new Paragraph("Diagnisis", font);
        document.add(titleDiagnosis);
        PdfPTable tableDiagnosisList = new PdfPTable(2);
        tableDiagnosisList.setWidthPercentage(100);
        tableDiagnosisList.setSpacingBefore(15);
        writeTableDataDiagnosisList(tableDiagnosisList);
        writeTableHeaderDiagnosisList(tableDiagnosisList);
        document.add(tableDiagnosisList);

        Paragraph titleAppointmentDischarsergesDto = new Paragraph("Appointment Discharserges", font);
        document.add(titleAppointmentDischarsergesDto);
        PdfPTable tableAppointmentDischarsergesDto = new PdfPTable(7);
        tableAppointmentDischarsergesDto.setWidthPercentage(100);
        tableAppointmentDischarsergesDto.setSpacingBefore(15);
        writeTableDataAppointmentDischarsergesDto(tableAppointmentDischarsergesDto);
        writeTableHeaderAppointmentDischarsergesDto(tableAppointmentDischarsergesDto);
        document.add(tableAppointmentDischarsergesDto);

        Paragraph titleEpicris = new Paragraph("Brief history and recommendations:", font);
        document.add(titleEpicris);
        PdfPTable tableEpicrisis = new PdfPTable(1);
        tableEpicrisis.setWidthPercentage(100);
        tableEpicrisis.setSpacingBefore(15);
        writeTableHeaderEpicrisis(tableEpicrisis);
        document.add(tableEpicrisis);
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        document.addTitle(currentDateTime);
        document.close();
        return fileName;
    }

    private String generateFileNameByUser() {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String currentDateTime = dateFormatter.format(new Date());
        String patientFirstName = userDischarsergeDto.getFirstNamePatient();
        String patientLastName = userDischarsergeDto.getLastNamePatient();
        return patientFirstName.concat("_" + patientLastName).concat("_" + currentDateTime);

    }

    private Image setAvatarPdf() throws IOException, NullPointerException {
        String name = this.userService.getUserById(userDischarsergeDto.getIdPatientUser()).getAvatarFileName();
        if (name == null) {
            throw new NullPointerException("File name not found");
        }
        Path path = Paths.get(name);
        return Image.getInstance(path.toAbsolutePath().toString());
    }
}
