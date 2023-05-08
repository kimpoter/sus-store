package susstore.susstore.plugin;

import susstore.susstore.controller.BarangController;
import susstore.susstore.controller.UserController;
import susstore.susstore.models.*;
import susstore.susstore.view.MainWindow;
import susstore.susstore.view.page.Page;

import java.util.List;

public class BasePlugin {

    public static MainWindow window = MainWindow.getInstance();

    public static void CreateNewTab(Page page, String title)
    {
        window.getInstance().getPageManager().addTab(
                title,
                (String) -> page
        );

        window.getNavbar().addNewMenu(title);
    }

    public static BarangController getBarangController()
    {
        return window.getPageManager().getBarangController();
    }
}
