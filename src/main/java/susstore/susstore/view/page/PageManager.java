package susstore.susstore.view.page;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import susstore.susstore.plugin.PluginManager;
import susstore.susstore.view.PageType;
import susstore.susstore.view.component.JoinDataTest;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;
import susstore.susstore.controller.BarangController;
import susstore.susstore.controller.UserController;


public class PageManager {
    protected final JoinDataTest joinDataTest;
    private final HashMap<String, Function<String, Page>> pages;
    private final HashMap<String, Tab> tabs;
    private final TabPane tabPane;
    private final Stage primaryStage;
    private BarangController barangController;
    private UserController customerController;

    public PageManager(Stage primaryStage) {
        this.pages = new HashMap<>();
        this.tabs = new HashMap<>();
        this.tabPane = new TabPane();
        this.joinDataTest = new JoinDataTest();
        this.primaryStage = primaryStage;
        this.barangController = new BarangController();
        this.customerController = new UserController();
        loadUI();
    }

    public void loadUI() {
        initializePages();

        PluginManager.register("plugin-bar/target/plugin-bar-1.0-SNAPSHOT-jar-with-dependencies.jar");
    }

    private void initializePages() {
        pages.put(PageType.AllCustomerPage.getName(), (String) -> new AllCustomerPage(this.customerController));
        pages.put(PageType.RegisterNewMember.getName(), (String) -> new RegisterNewMember());
        pages.put(PageType.EditCustomerPage.getName(), (String) -> new EditCustomerPage(this.joinDataTest));
        pages.put(PageType.AllBarang.getName(), (String) -> new AllBarangPage(primaryStage,this.barangController));
        pages.put(PageType.NewBarang.getName(),(String) -> new NewBarangPage(primaryStage,this.barangController));
        pages.put(PageType.Kasir.getName(), (String) -> new KasirPage());
        pages.put(PageType.SettingsPage.getName(), (String) -> new SettingsPage(primaryStage));
        pages.put(PageType.AllMemberPage.getName(), (String) -> new AllMemberPage(this.customerController));
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
