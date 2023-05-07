package susstore.chart2;

import javafx.scene.layout.BorderPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartViewer;
import org.jfree.data.general.DefaultPieDataset;
import susstore.susstore.view.PageType;
import susstore.susstore.view.page.Page;

public class PieView extends Page
{
    private final BorderPane pageRootLayout;

    public PieView() {
        super(PageType.EditCustomerPage);
        this.pageRootLayout = new BorderPane();
        loadUI();
        this.tab.setContent(this.pageRootLayout);
    }

    private void loadUI() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Apples", 50);
        dataset.setValue("Bananas", 25);
        dataset.setValue("Oranges", 25);

        // Create a JFreeChart pie chart from the dataset
        JFreeChart chart = ChartFactory.createPieChart(
                "Fruit Consumption",  // Chart title
                dataset,              // Dataset
                true,                 // Legend
                true,                 // Tooltips
                false                 // URLs
        );

        ChartViewer viewer = new ChartViewer(chart);

        this.pageRootLayout.setCenter(viewer);
    }
}
