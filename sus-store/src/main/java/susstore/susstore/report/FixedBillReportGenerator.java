package susstore.susstore.report;

import susstore.susstore.models.Barang;
import susstore.susstore.models.Customer;
import susstore.susstore.models.FixedBill;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import susstore.susstore.models.TemporaryBill;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FixedBillReportGenerator implements ReportGenerator {
    FixedBill fixedBill;

    public FixedBillReportGenerator(FixedBill fixedBill) {
        this.fixedBill = fixedBill;
    }

    @Override
    public void GenerateReport(String filePath) throws Exception {
        String report = fixedBill.toText();
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

    public static void main(String[] args) throws Exception {
        Customer testcust = new Customer();
        TemporaryBill tempbill = new TemporaryBill(testcust.getUserID());
        tempbill.addProduct(new Barang("barang1", 7, "bbbb", "D:\\University\\__Tubes2OOP\\sus-store\\sus-store\\target\\912138.jpg", 300, 400), 2);
        tempbill.addProduct(new Barang("barang2", 10, "bbbb", "D:\\University\\__Tubes2OOP\\sus-store\\sus-store\\target\\912138.jpg", 300, 400), 2);
        FixedBill fixbill = new FixedBill(tempbill);
        FixedBillReportGenerator test = new FixedBillReportGenerator(fixbill);
        test.GenerateReport("report.pdf");
    }
}
