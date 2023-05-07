package susstore.susstore.report;

import java.util.List;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
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
        }
        return buffer.toString();
    }
}
