package susstore.susstore.controller.pageController;

import javafx.scene.control.Label;
import susstore.susstore.Subscriber;

public class AllBarangController implements Subscriber {
    private Label namaBarangLabel;
    private Label stockLabel;
    private Label categoryLabel;
    private Label hargaBarangLabel;
    private Label hargaBeliBarangLabel;
    private Label nameLabel;
    private Label priceLabel;

    public AllBarangController(Label namaBarangLabel, Label stockLabel, Label categoryLabel, Label hargaBarangLabel, Label hargaBeliBarangLabel, Label nameLabel, Label priceLabel) {
        this.namaBarangLabel = namaBarangLabel;
        this.stockLabel = stockLabel;
        this.categoryLabel = categoryLabel;
        this.hargaBarangLabel = hargaBarangLabel;
        this.hargaBeliBarangLabel = hargaBeliBarangLabel;
        this.nameLabel = nameLabel;
        this.priceLabel = priceLabel;
    }

    public Label getNamaBarangLabel() {
        return namaBarangLabel;
    }

    public void setNamaBarangLabel(Label namaBarangLabel) {
        this.namaBarangLabel = namaBarangLabel;
    }

    public Label getStockLabel() {
        return stockLabel;
    }

    public void setStockLabel(Label stockLabel) {
        this.stockLabel = stockLabel;
    }

    public Label getCategoryLabel() {
        return categoryLabel;
    }

    public void setCategoryLabel(Label categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    public Label getHargaBarangLabel() {
        return hargaBarangLabel;
    }

    public void setHargaBarangLabel(Label hargaBarangLabel) {
        this.hargaBarangLabel = hargaBarangLabel;
    }

    public Label getHargaBeliBarangLabel() {
        return hargaBeliBarangLabel;
    }

    public void setHargaBeliBarangLabel(Label hargaBeliBarangLabel) {
        this.hargaBeliBarangLabel = hargaBeliBarangLabel;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(Label nameLabel) {
        this.nameLabel = nameLabel;
    }

    public Label getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(Label priceLabel) {
        this.priceLabel = priceLabel;
    }

    @Override
    public void update() {

    }
}
