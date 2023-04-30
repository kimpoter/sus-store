package susstore.susstore.view.component;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuBarComponent {
    private final BorderPane layout;
    private final TabsComponent tabs;

    public MenuBarComponent(BorderPane layout, TabsComponent tabs) {
        this.layout = layout;
        this.tabs = tabs;
        loadUI();
    }

    private void loadUI() {
        Menu settings = new Menu("Settings");
        Menu customer = new Menu("Customer");
        Menu barang = new Menu("Barang");
        Menu aboutUs = new Menu("About Us");

        List<String> customerMenuItemTitle = new ArrayList<>(
                Arrays.asList("All Customer", "Add Customer")
        );
        for (String menuItemTitle : customerMenuItemTitle
        ) {
            MenuItem menuItem = new MenuItem(menuItemTitle);
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    tabs.addNewTab(new Tab("Bakane"));
                    tabs.getTabPane().getSelectionModel().selectLast();
                }
            });
            customer.getItems().add(menuItem);
        }

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(settings, customer, barang, aboutUs);
        menuBar.getStylesheets().add("/src/main/java/susstore/susstore/assets/css/menubarstyle.css");

        layout.setTop(menuBar);
    }
}
