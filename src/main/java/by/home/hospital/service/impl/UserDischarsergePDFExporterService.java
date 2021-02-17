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
    private final String NAME_APPOINTMENT_FIELD = "Name appointment";
    private final String TYPE_APPOINTMENT_FIELD = "Type appointment";
    private final String APPOINTMENT_COMPLETION_DATE_FIELD = "Appointment completion  date ";
    private final String DOCTOR_POSITION_FIELD = "Doctor position";
    private final String DOCTOR_SPECIALIZATION_FIELD = "doctor's specialization";
    private final String FIRST_NAME_FIELD = "FirstName";
    private final String LAST_NAME_FIELD = "LastName";
    private final String COMPLETE_DIAGNOSIS_FIELD = "complete diagnosis in a year";
    private final String DIAGNOSED_FIELD = "Diagnosed";
    private final String PATIENT_PHOTO_FIELD = "Patient photo";
    private final String NUMBER_PATIENT_FIELD = "Number Patient";
    private final String FIRST_NAME_PATIENT_FIELD = "FirstNamePatient";
    private final String LAST_NAME_PATIENT_FIELD = "LastNamePatient";
    private final String EXTRACT_FIELD = "Extract from the patient's medical record";
    private final String DIAGNISIS_FIELD = "Diagnisis";
    private final String APPOINTMENT_DISCHARSERGES_FIELD = "Appointment Discharserges";
    private final String BRIEF_HISTORY_FIELD = "Brief history and recommendations:";
    private final String DATE_FORMAT_FIELD = "yyyy_MM_dd_HH_mm_ss";
    private final String FILE_NAME_NOTFOUND_FIELD = "File name not found";


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
        writeTableDisharserges(tableAppointmentDischarsergesDto, pdfPCell, font, NAME_APPOINTMENT_FIELD, TYPE_APPOINTMENT_FIELD, APPOINTMENT_COMPLETION_DATE_FIELD);
        writeTableDisharserges(tableAppointmentDischarsergesDto, pdfPCell, font, DOCTOR_POSITION_FIELD, DOCTOR_SPECIALIZATION_FIELD, FIRST_NAME_FIELD);
        pdfPCell.setPhrase(new Phrase(LAST_NAME_FIELD, font));
        tableAppointmentDischarsergesDto.addCell(pdfPCell);
    }

    private void writeTableDisharserges(PdfPTable tableAppointmentDischarsergesDto, PdfPCell pdfPCell, Font font, String name_appointment, String type_appointment, String appointment_completion_date) {
        pdfPCell.setPhrase(new Phrase(name_appointment, font));
        tableAppointmentDischarsergesDto.addCell(pdfPCell);

        pdfPCell.setPhrase(new Phrase(type_appointment, font));
        tableAppointmentDischarsergesDto.addCell(pdfPCell);

        pdfPCell.setPhrase(new Phrase(appointment_completion_date, font));
        tableAppointmentDischarsergesDto.addCell(pdfPCell);
    }

    private void writeTableDataDiagnosisList(PdfPTable tableDiagnosisList) {
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setBackgroundColor(Color.gray);
        pdfPCell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        pdfPCell.setPhrase(new Phrase(COMPLETE_DIAGNOSIS_FIELD, font));
        tableDiagnosisList.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase(DIAGNOSED_FIELD, font));
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
        pdfPCell.setPhrase(new Phrase(PATIENT_PHOTO_FIELD, font));

        table.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase(NUMBER_PATIENT_FIELD, font));

        table.addCell(pdfPCell);

        pdfPCell.setPhrase(new Phrase(FIRST_NAME_PATIENT_FIELD, font));
        table.addCell(pdfPCell);

        pdfPCell.setPhrase(new Phrase(LAST_NAME_PATIENT_FIELD, font));
        table.addCell(pdfPCell);
    }

    public String export(String path) throws DocumentException, IOException {
        Document document = new Document(PageSize.A3);
        String fileName = generateFileNameByUser();
        PdfWriter.getInstance(document, new FileOutputStream(path + "/resources/" + fileName + ".pdf"));
        document.open();
        Paragraph title = new Paragraph(EXTRACT_FIELD, getFont());

        document.add(title);
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        writeTableDataUserDischarsergeDto(table);
        writeTableHeaderFromUserDischarserge(table);
        document.add(table);

        Paragraph titleDiagnosis = new Paragraph(DIAGNISIS_FIELD, getFont());
        document.add(titleDiagnosis);
        PdfPTable tableDiagnosisList = new PdfPTable(2);
        tableDiagnosisList.setWidthPercentage(100);
        tableDiagnosisList.setSpacingBefore(15);
        writeTableDataDiagnosisList(tableDiagnosisList);
        writeTableHeaderDiagnosisList(tableDiagnosisList);
        document.add(tableDiagnosisList);

        Paragraph titleAppointmentDischarsergesDto = new Paragraph(APPOINTMENT_DISCHARSERGES_FIELD, getFont());
        document.add(titleAppointmentDischarsergesDto);
        PdfPTable tableAppointmentDischarsergesDto = new PdfPTable(7);
        tableAppointmentDischarsergesDto.setWidthPercentage(100);
        tableAppointmentDischarsergesDto.setSpacingBefore(15);
        writeTableDataAppointmentDischarsergesDto(tableAppointmentDischarsergesDto);
        writeTableHeaderAppointmentDischarsergesDto(tableAppointmentDischarsergesDto);
        document.add(tableAppointmentDischarsergesDto);

        Paragraph titleEpicris = new Paragraph(BRIEF_HISTORY_FIELD, getFont());
        document.add(titleEpicris);
        PdfPTable tableEpicrisis = new PdfPTable(1);
        tableEpicrisis.setWidthPercentage(100);
        tableEpicrisis.setSpacingBefore(15);
        writeTableHeaderEpicrisis(tableEpicrisis);
        document.add(tableEpicrisis);
        DateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT_FIELD);
        String currentDateTime = dateFormatter.format(new Date());
        document.addTitle(currentDateTime);
        document.close();
        return fileName;
    }

    private String generateFileNameByUser() {
        DateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT_FIELD);
        String currentDateTime = dateFormatter.format(new Date());
        String patientFirstName = userDischarsergeDto.getFirstNamePatient();
        String patientLastName = userDischarsergeDto.getLastNamePatient();
        return patientFirstName.concat("_" + patientLastName).concat("_" + currentDateTime);

    }

    private Image setAvatarPdf() throws IOException, NullPointerException {
        String name = userService.getUserById(userDischarsergeDto.getIdPatientUser()).getAvatarFileName();
        if (name == null) {
            throw new NullPointerException(FILE_NAME_NOTFOUND_FIELD);
        }
        Path path = Paths.get(name);
        return Image.getInstance(path.toAbsolutePath().toString());
    }

    private Font getFont() {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);
        font.setSize(18);
        return font;
    }
}
