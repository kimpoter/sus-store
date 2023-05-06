package susstore.susstore.view.page;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import susstore.susstore.view.PageType;
import susstore.susstore.view.component.BillCardComponent;
import susstore.susstore.view.component.JoinDataTest;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;

public class PageManager {
    protected final JoinDataTest joinDataTest;
    private final HashMap<String, Function<String, Page>> pages;
    private final HashMap<String, Tab> tabs;
    private final ObservableList<Node> billcards;
    private final TabPane tabPane;
    private final Stage primaryStage;

    public PageManager(Stage primaryStage) {
        this.pages = new HashMap<>();
        this.tabs = new HashMap<>();
        this.tabPane = new TabPane();
        this.joinDataTest = new JoinDataTest();
        this.billcards = FXCollections.observableArrayList();
        this.billcards.addAll(new Button("button1"), new Button("button2"));
        this.primaryStage = primaryStage;
        loadUI();
    }

    public void loadUI() {
        initializePages();
    }

    private void initializePages() {
        pages.put(PageType.AllCustomerPage.getName(), (String) -> new AllCustomerPage(this.joinDataTest));
        pages.put(PageType.RegisterNewMember.getName(), (String) -> new RegisterNewMember());
        pages.put(PageType.EditCustomerPage.getName(), (String) -> new EditCustomerPage(this.joinDataTest));
        pages.put(PageType.AllBarang.getName(), (String) -> new AllBarangPage(primaryStage));
        pages.put(PageType.Kasir.getName(), (String) -> new KasirPage(this.billcards));
        pages.put(PageType.SettingsPage.getName(), (String) -> new SettingsPage(primaryStage));
        pages.put(PageType.TransactionHistory.getName(), (String) -> new TransactionHisotryPage());
    }

    public void addTab(String pageName) {
        if (tabs.containsKey(pageName)) {
            this.tabPane.getSelectionModel().select(tabs.get(pageName));
        } else {
            Tab newTab = this.pages.get(pageName).apply(pageName).getPage();
            newTab.setOnCloseRequest(e -> {
                this.tabs.remove(pageName);
            });
            this.tabs.put(pageName, newTab);
            this.tabPane.getTabs().add(newTab);
            this.tabPane.getSelectionModel().selectLast();
        }
    }

    public void addTab(String pageName, Function<String, Page> externalPage) {
        this.pages.put(pageName, externalPage);
        this.addTab(pageName);
    }

    public TabPane getTabPane() {
        return this.tabPane;
    }

    public Stage getStage() {
        return this.primaryStage;
    }
}
