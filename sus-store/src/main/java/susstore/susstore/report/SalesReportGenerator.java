package susstore.susstore.report;

import java.util.ArrayList;
import java.util.List;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import susstore.susstore.models.Barang;
import susstore.susstore.models.Customer;
import susstore.susstore.models.FixedBill;
import susstore.susstore.models.TemporaryBill;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SalesReportGenerator implements ReportGenerator {
    List<? extends Printable> allSalesData;

    public SalesReportGenerator(List<? extends Printable> allSalesData) {
        this.allSalesData = allSalesData;
    }

    @Override
    public void GenerateReport(String filePath) throws Exception {
        String report = getReportFormat();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            document.add(new Paragraph(report));
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            throw new Exception("Gagal membuat laporan penjualan");
        }
    }

    private String getReportFormat() {
        StringBuffer buffer = new StringBuffer("Berikut adalah laporan penjualan BNMO:\n");
        int i = 1;
        for (Printable data: allSalesData) {
            buffer.append(i + ". " + data.toText() + "\n");
            i++;
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        Customer testcust = new Customer();
        TemporaryBill tempbill = new TemporaryBill(testcust.getUserID());
        tempbill.addProduct(new Barang("barang1", 7, "bbbb", "D:\\University\\__Tubes2OOP\\sus-store\\sus-store\\target\\912138.jpg", 300, 400), 2);
        tempbill.addProduct(new Barang("barang2", 10, "bbbb", "D:\\University\\__Tubes2OOP\\sus-store\\sus-store\\target\\912138.jpg", 300, 400), 2);
        FixedBill fixbill = new FixedBill(tempbill);
        List<FixedBill> listbill = new ArrayList<>();
        listbill.add(fixbill);
        listbill.add(fixbill);
        SalesReportGenerator test = new SalesReportGenerator(listbill);
        test.GenerateReport("report.pdf");
    }
}
