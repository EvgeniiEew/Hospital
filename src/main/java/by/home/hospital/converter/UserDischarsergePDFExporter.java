package by.home.hospital.converter;

import by.home.hospital.domain.Diagnosis;
import by.home.hospital.domain.Epicrisis;
import by.home.hospital.dto.AppointmentDischarsergesDto;
import by.home.hospital.dto.UserDischarsergeDto;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class UserDischarsergePDFExporter {
    UserDischarsergeDto userDischarsergeDto;
    private List<Diagnosis> diagnosisList;
    private List<AppointmentDischarsergesDto> appointmentDischarsergesDtoList;
    private List<Epicrisis> epicrisisList;

    public UserDischarsergePDFExporter(UserDischarsergeDto userDischarsergeDto, List<Diagnosis> diagnosisList, List<AppointmentDischarsergesDto> appointmentDischarsergesDtoList, List<Epicrisis> epicrisisList) {
        this.userDischarsergeDto = userDischarsergeDto;
        this.diagnosisList = diagnosisList;
        this.appointmentDischarsergesDtoList = appointmentDischarsergesDtoList;
        this.epicrisisList = epicrisisList;
    }
//         private void writeTableHeader(PdfPTable table) {
//        for (User user: lsitUSeer){
//            table.addCell(String.valueOf().user.getId());
//        }
//    }


    private void writeTableHeader(PdfPTable table) {
        table.addCell(String.valueOf(userDischarsergeDto.getIdPatientUser()));
        table.addCell(String.valueOf(userDischarsergeDto.getFirstNamePatient()));
        table.addCell(String.valueOf(userDischarsergeDto.getLastNamePatient()));
    }

    private void writeTableData(PdfPTable table) {
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setBackgroundColor(Color.BLUE);
        pdfPCell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        pdfPCell.setPhrase(new Phrase("Number Patient", font));

        table.addCell(pdfPCell);

        pdfPCell.setPhrase(new Phrase("FirstNamePatient", font));
        table.addCell(pdfPCell);

        pdfPCell.setPhrase(new Phrase("LastNamePatient", font));
        table.addCell(pdfPCell);
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLUE);
        font.setSize(18);

        Paragraph title = new Paragraph("Extract from the patient's medical record", font);
        document.add(title);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
//        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f, 1.5f});


        writeTableData(table);
        writeTableHeader(table);

        document.add(table);

        document.close();
        //ссылка для отправки ответа хттр
    }
}
