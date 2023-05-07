package susstore.susstore.view.component;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TransactionCardComponent {
    private final VBox componentRootLayout;

    public TransactionCardComponent() {
        this.componentRootLayout = new VBox();
        loadUI();
        setStyleSheet();
    }

    private void loadUI() {
        Label transactionDateLabel = new Label("22/10/2023");
        for (int i = 0; i < 10; i++) {
            
        }
    }


    private void setStyleSheet() {
    }

    public Pane getComponent() {
        return this.componentRootLayout;
    }
}

