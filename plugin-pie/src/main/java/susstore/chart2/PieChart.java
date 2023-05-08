package susstore.chart2;

import susstore.susstore.controller.BarangController;
import susstore.susstore.plugin.BasePlugin;
import susstore.susstore.plugin.Plugin;
import susstore.susstore.view.MainWindow;

public class PieChart extends BasePlugin implements Plugin
{
    @Override
    public void run()
    {
        try {
            BasePlugin.CreateNewTab(
                    new PieView(
                            BasePlugin.getBarangController()
                    ),
                    "Pie Chart"
            );
            MainWindow window = MainWindow.getInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
