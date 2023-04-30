package susstore.susstore.view.component;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class TabsComponent {
    private final Pane rootLayout;
    private final TabPane tabPane;

    public TabsComponent(Pane rootLayout) {
        this.rootLayout = rootLayout;
        this.tabPane = new TabPane();
        loadUI();
    }

    private void loadUI() {
        this.rootLayout.getChildren().add(this.tabPane);
    }

    public void addNewTab(Tab newTab) {
        this.tabPane.getTabs().add(newTab);
    }

    public TabPane getTabPane() {
        return this.tabPane;
    }
}
