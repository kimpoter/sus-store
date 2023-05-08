package susstore.chart1;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
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
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();

        XYSeriesCollection lineDataset = new XYSeriesCollection();

        XYSeries series = new XYSeries("Data");

        Thread thread = new Thread(() -> {
           while (true)
           {
               try {
                   int index = 0;
                   for (Barang b : barangController.getBarangs())
                   {
                       System.out.println(b.getNama() +  b.getStok());
                       barDataset.addValue(b.getStok(), "", b.getNama());
                       series.addOrUpdate(index, b.getStok());
                       index += 10;
                   }

                   Thread.sleep(2000);

                    barDataset.clear();
                    series.clear();
               }
               catch (Exception e) {
                   e.printStackTrace();
               }
           }
        });
        thread.start();

        lineDataset.addSeries(series);
        // Create a bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Product Sales",
                "Product",
                "Stok",
                barDataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Create a chart viewer and set the chart panel as its content
        ChartViewer barViewer = new ChartViewer(barChart);

        // Create a JFreeChart object
        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "Line Chart Example",
                "X Axis Label",
                "Y Axis Label",
                lineDataset
        );

        ChartViewer lineViewer = new ChartViewer(lineChart);

        this.pageRootLayout.getItems().addAll(barViewer, lineViewer);
    }
}