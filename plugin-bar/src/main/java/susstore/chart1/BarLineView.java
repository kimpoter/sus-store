package susstore.chart1;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import susstore.susstore.controller.BarangController;
import susstore.susstore.models.Barang;
import susstore.susstore.view.PageType;
import susstore.susstore.view.page.Page;

public class BarLineView extends Page
{
    private SplitPane pageRootLayout;

    private BarangController barangController;

    public BarLineView(
            BarangController    barangController
    ) {
        super(PageType.PluginPage);
        this.pageRootLayout = new SplitPane();

        this.barangController = barangController;

        loadUI();
        this.tab.setContent(this.pageRootLayout);

    }

    private void loadUI() {

        // Create a dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        XYSeries series = new XYSeries("Data");

        Thread thread = new Thread(() -> {
           while (true)
           {
               try {
                   for (Barang b : barangController.getBarangs())
                   {
                       dataset.addValue(b.getStok(), "", b.getNama());
                   }

                   Thread.sleep(2000);

                    dataset.clear();
               }
               catch (Exception e) {
                   e.printStackTrace();
               }
           }
        });
        thread.start();

        // Create a bar chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Product Sales",
                "Product",
                "Stok",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Create a chart viewer and set the chart panel as its content
        ChartViewer barViewer = new ChartViewer(chart);

        this.pageRootLayout.getItems().addAll(barViewer);
    }
}