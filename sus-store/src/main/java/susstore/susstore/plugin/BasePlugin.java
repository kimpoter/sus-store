package susstore.susstore.plugin;

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

    public static List<Barang> getListOfBarang()
    {
        return window.getPageManager().getBarangController().getBarangs();
    }

    public static List<Customer> getListOfCustomer()
    {
        return window.getPageManager().getUserController().getCustomers();
    }
}
