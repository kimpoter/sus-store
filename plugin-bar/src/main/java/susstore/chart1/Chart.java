package susstore.chart1;

import susstore.susstore.plugin.BasePlugin;
import susstore.susstore.plugin.Plugin;

public class Chart extends BasePlugin implements Plugin
{
    @Override
    public void run()
    {
        try {
            BasePlugin.CreateNewTab(
                    new BarView(
                            BasePlugin.getListOfBarang(),
                            BasePlugin.getListOfCustomer()
                    ),
                    "Bar Chart"
            );

            BasePlugin.CreateNewTab(
                    new LineView(),
                    "Line Chart"
            );
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
