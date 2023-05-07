package susstore.susstore.view.page;

import javafx.scene.control.Tab;
import susstore.susstore.view.PageType;
import susstore.susstore.view.component.JoinDataTest;


public class Page {
    protected final Tab tab;


    public Page(PageType pageType) {
        this.tab = new Tab(pageType.getName());

    }

    public Tab getPage() {
        return this.tab;
    }
}
