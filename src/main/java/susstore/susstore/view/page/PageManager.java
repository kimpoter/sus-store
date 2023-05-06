package susstore.susstore.view.page;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import susstore.susstore.view.PageType;

import java.util.HashMap;
import java.util.function.Function;

public class PageManager {
    private final HashMap<String, Function<String, Page>> pages;
    private final HashMap<String, Tab> tabs;
    private final TabPane tabPane;
    private final Stage primaryStage;

    public PageManager(Stage primaryStage) {
        this.pages = new HashMap<>();
        this.tabs = new HashMap<>();
        this.tabPane = new TabPane();
        this.primaryStage = primaryStage;
        loadUI();
    }

    public void loadUI() {
        initializePages();
    }

    private void initializePages() {
        pages.put(PageType.AllCustomerPage.name(), (String) -> new AllCustomerPage());
        pages.put(PageType.RegisterNewMember.name(), (String) -> new RegisterNewMember());
        pages.put(PageType.EditCustomerPage.name(), (String) -> new EditCustomerPage());
        pages.put(PageType.AllBarang.name(), (String) -> new AllBarangPage(primaryStage));
        pages.put(PageType.Kasir.name(), (String) -> new KasirPage());
    }

    public void addTab(PageType pageType) {
        if (tabs.containsKey(pageType.name())) {
            this.tabPane.getSelectionModel().select(tabs.get(pageType.name()));
        } else {
            Tab newTab = this.pages.get(pageType.name()).apply(pageType.name()).getPage();
            newTab.setOnCloseRequest(e -> {
                this.tabs.remove(pageType.name());
            });
            this.tabs.put(pageType.name(), newTab);
            this.tabPane.getTabs().add(newTab);
            this.tabPane.getSelectionModel().selectLast();
        }
    }

    public TabPane getTabPane() {
        return this.tabPane;
    }
}
