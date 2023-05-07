package susstore.chart2;

import susstore.susstore.plugin.BasePlugin;
import susstore.susstore.plugin.Plugin;
import susstore.susstore.view.MainWindow;

public class PieChart extends BasePlugin implements Plugin
{
    @Override
    public void run()
    {
        try {
            MainWindow window = MainWindow.getInstance();

            window.getInstance().getPageManager().addTab(
                    "Pie Chart",
                    (String) -> new PieView()
            );

            window.getNavbar().addNewMenu("Pie Chart");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
