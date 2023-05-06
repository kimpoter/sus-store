package susstore.susstore.view.component;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JoinDataTest {
    private StringProperty name;

    public JoinDataTest() {
        this.name = new SimpleStringProperty("Initial");
    }

    public final void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return this.name;
    }
}
