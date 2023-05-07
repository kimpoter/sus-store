package susstore.susstore.plugin;

import susstore.susstore.models.*;
import susstore.susstore.view.MainWindow;

import java.util.List;

public class BasePlugin {

    private final List<Barang> listOfBarang;

    private final List<Customer> listOfCustomer;

    private final List<Member> listOfMember;

    private final List<FixedBill> listOfFixedBill;

    private final List<TemporaryBill> listOfTemporaryBill;

    public BasePlugin()
    {
        MainWindow window = MainWindow.getInstance();

        this.listOfBarang = window.getPageManager().getBarangController().getBarangs();

        this.listOfCustomer = window.getPageManager().getUserController().getCustomer();

        this.listOfMember = window.getPageManager().getUserController().getMember();

        this.listOfFixedBill = window.getPageManager().getFixedBillController().getFixedBills();

        this.listOfTemporaryBill = window.getPageManager().getTemporaryBillController().getTemporaryBills();
    }


}
