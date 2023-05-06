package susstore.susstore.view.component;

import javafx.scene.control.*;
import susstore.susstore.view.MenuType;
import susstore.susstore.view.PageType;
import susstore.susstore.view.page.PageManager;

import java.util.*;

public class Navbar {
    private final MenuBar menuBar;
    private final Map<String, Menu> menus;
    private final Map<String, List<PageType>> menuItems;
    private final PageManager pageManager;

    public Navbar(PageManager pageManager) {
        this.menuBar = new MenuBar();
        this.menus = new LinkedHashMap<>();
        this.menuItems = new LinkedHashMap<>();
        this.pageManager = pageManager;
        loadUI();
    }

    public void loadUI() {
        initializeMenuItems();
        initializeMenus();
        addMenuItems();
        addMenusToMenuBar();
        setStylesheet();
    }

    private void initializeMenuItems() {
        for (MenuType menu : MenuType.values()) {
            this.menuItems.put(menu.name(), new ArrayList<>());
        }

        // customer menu items
        this.menuItems.get(MenuType.Customer.name()).add(PageType.AllCustomerPage);
        this.menuItems.get(MenuType.Customer.name()).add(PageType.RegisterNewMember);
        this.menuItems.get(MenuType.Customer.name()).add(PageType.EditCustomerPage);

        // barang menu items
        this.menuItems.get(MenuType.Barang.name()).add(PageType.AllBarang);
        this.menuItems.get(MenuType.Barang.name()).add(PageType.Kasir);
    }

    private void initializeMenus() {
        for (MenuType menu : MenuType.values()) {
            this.menus.put(menu.name(), new Menu(menu.getName()));
        }
    }

    private void addMenuItems() {
        for (Map.Entry<String, List<PageType>> menuItem : this.menuItems.entrySet()) {
            for (PageType menuItemName : menuItem.getValue()) {
                MenuItem mItem = new MenuItem(menuItemName.getName());
                mItem.setOnAction(actionEvent -> pageManager.addTab(menuItemName));
                menus.get(menuItem.getKey()).getItems().add(mItem);
            }
        }
    }

    private void addMenusToMenuBar() {
        for (Map.Entry<String, Menu> menu : this.menus.entrySet()) {
            this.menuBar.getMenus().add(menu.getValue());
        }
    }

    private void setStylesheet() {
        this.menuBar.getStylesheets().add("css/menubarstyle.css");
    }

    public MenuBar getMenuBar() {
        return this.menuBar;
    }
}
