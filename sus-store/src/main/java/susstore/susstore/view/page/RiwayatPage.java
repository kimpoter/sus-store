package susstore.susstore.view.page;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import susstore.susstore.controller.FixedBillController;
import susstore.susstore.plugin.PluginManager;
import susstore.susstore.view.PageType;
import susstore.susstore.view.component.JoinDataTest;
import susstore.susstore.report.FixedBillReportGenerator;
import susstore.susstore.report.SalesReportGenerator;

public class RiwayatPage extends Page {
    private final BorderPane pageRootLayout;
    private FixedBillReportGenerator fBillReportGenerator;
    private FixedBillController fixedBillController;
    private SalesReportGenerator salesReportGenerator;

    public RiwayatPage(FixedBillController fixedBillController) {
        super(PageType.Riwayat);
        this.pageRootLayout = new BorderPane();
        loadUI();
        this.tab.setContent(this.pageRootLayout);
        this.fixedBillController = fixedBillController;
    }

    private void loadUI() {
        Button printButton = new Button("Print sales result");
        printButton.setOnAction(
            e->{
                this.salesReportGenerator = new SalesReportGenerator(this.fixedBillController.getFixedBills());
                try {
                    this.salesReportGenerator.GenerateReport("FixedBill.json");
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
                
            }
        );
        this.pageRootLayout.setCenter(printButton);
    }
    
}
