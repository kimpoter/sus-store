package susstore.susstore;

import susstore.susstore.models.Barang;
import susstore.susstore.models.Bill;

import java.util.ArrayList;
import java.util.List;

public class HalamanController {
    private static List<Barang> daftarBarang;
    // private User currentUser;
    private Bill currentBill;

    public HalamanController(Bill currentBill) {
        this.currentBill = currentBill;
        this.daftarBarang = new ArrayList<>();
    }

    public static List<Barang> getDaftarBarang() {
        return daftarBarang;
    }

    // public User getCurrentUser() {
    //     return this.currentUser;
    // }

    public Bill getCurrentBill() {
        return this.currentBill;
    }

    public void execute() {

    }
}
