package susstore.chart2;

import javafx.scene.layout.BorderPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.data.general.DefaultPieDataset;
import susstore.susstore.controller.BarangController;
import susstore.susstore.controller.UserController;
import susstore.susstore.models.Barang;
import susstore.susstore.view.PageType;
import susstore.susstore.view.page.Page;

import java.util.HashMap;
import java.util.Map;

public class PieView extends Page
{
    private final BorderPane pageRootLayout;

    private BarangController barangController;

    public PieView(
            BarangController barangController
    ) {
        super(PageType.PluginPage);
        this.pageRootLayout = new BorderPane();

        this.barangController = barangController;

        loadUI();
        this.tab.setContent(this.pageRootLayout);
    }

    private void loadUI() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        Map<String, Integer> data = new HashMap<>();
        int totalStok = 0;

        for (Barang b : barangController.getBarangs())
        {
            if (data.containsKey(b.getKategori()))
                data.put(b.getKategori(), data.get(b.getKategori()) + b.getStok());
            else
                data.put(b.getKategori(), b.getStok());
            totalStok += b.getStok();
        }

        for (HashMap.Entry<String, Integer> entry : data.entrySet())
        {
            dataset.setValue(entry.getKey(), (int)(entry.getValue() / totalStok));
        }

        // Create a JFreeChart pie chart from the dataset
        JFreeChart chart = ChartFactory.createPieChart(
                "Stok Berdasarkan Kategori",  // Chart title
                dataset,              // Dataset
                true,                 // Legend
                true,                 // Tooltips
                false                 // URLs
        );

        ChartViewer viewer = new ChartViewer(chart);

        this.pageRootLayout.setCenter(viewer);
    }
}
