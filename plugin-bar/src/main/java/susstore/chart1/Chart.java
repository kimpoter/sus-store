package susstore.chart1;

import susstore.susstore.plugin.BasePlugin;
import susstore.susstore.plugin.Plugin;
import susstore.susstore.view.MainWindow;

import java.awt.*;

public class Chart extends BasePlugin implements Plugin
{
    @Override
    public void run()
    {
        try {
            MainWindow window = MainWindow.getInstance();

            window.getInstance().getPageManager().addTab(
                    "Bar Chart",
                    (String) -> new BarView()
            );

            window.getNavbar().addNewMenu("Bar Chart");

            window.getInstance().getPageManager().addTab(
                    "Line Chart",
                    (String) -> new LineView()
            );

            window.getNavbar().addNewMenu("Line Chart");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
