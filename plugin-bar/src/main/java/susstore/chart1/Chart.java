package susstore.chart1;

import susstore.susstore.plugin.Plugin;
import susstore.susstore.view.MainWindow;

import java.awt.*;

public class Chart implements Plugin
{
    @Override
    public void run()
    {
        try {
            MainWindow.getNavbar().addNewMenu("Bar Chart");

            MainWindow.getInstance().getPageManager().addTab(
                    "Bar Chart",
                    (String) -> new BarView()
            );

            MainWindow.getNavbar().addNewMenu("Line Chart");

            MainWindow.getInstance().getPageManager().addTab(
                    "Line Chart",
                    (String) -> new LineView()
            );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
