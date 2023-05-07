package susstore.chart1;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import susstore.susstore.view.PageType;
import susstore.susstore.view.page.Page;

public class BarView extends Page
{
    private final BorderPane pageRootLayout;

    public BarView() {
        super(PageType.EditCustomerPage);
        this.pageRootLayout = new BorderPane();
        loadUI();
        this.tab.setContent(this.pageRootLayout);
    }

    private void loadUI() {
        // Create a dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(500, "Sales", "Product 1");
        dataset.addValue(300, "Sales", "Product 2");
        dataset.addValue(200, "Sales", "Product 3");


        // Create a bar chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Product Sales",
                "Product",
                "Sales",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Create a chart viewer and set the chart panel as its content
        ChartViewer viewer = new ChartViewer(chart);

        this.pageRootLayout.setCenter(viewer);
    }
}