package susstore.chart1;


import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import susstore.susstore.view.PageType;
import susstore.susstore.view.page.Page;

public class LineView extends Page
{
    private final BorderPane pageRootLayout;

    public LineView() {
        super(PageType.EditCustomerPage);
        this.pageRootLayout = new BorderPane();
        loadUI();
        this.tab.setContent(this.pageRootLayout);
    }

    private void loadUI() {
        XYSeries series = new XYSeries("Data");

        // Add some data points to the series
        series.add(1.0, 1.0);
        series.add(2.0, 2.0);
        series.add(3.0, 3.0);
        series.add(4.0, 4.0);
        series.add(5.0, 5.0);

        // Put the series in a collection
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        // Create a JFreeChart object
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Line Chart Example",
                "X Axis Label",
                "Y Axis Label",
                dataset
        );

        ChartViewer viewer = new ChartViewer(chart);

        this.pageRootLayout.setCenter(viewer);
    }
}