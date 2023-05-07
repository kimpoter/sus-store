package susstore.susstore.report;

import susstore.susstore.models.FixedBill;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FixedBillReportGenerator implements ReportGenerator {
    FixedBill fixedBill;

    public FixedBillReportGenerator(FixedBill fixedBill) {
        this.fixedBill = fixedBill;
    }

    @Override
    public void GenerateReport(String filePath) throws Exception {
        //String report = fixedBill.toText();
        String report="";
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            document.add(new Paragraph(report));
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            throw new Exception("Gagal membuat laporan fixed bill");
        }
    }
}
