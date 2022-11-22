package ui;

import commands.SetSortingOrderCommand;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import storage.StorageOrder;

public class SortingView extends VBox implements View {

    private ToggleGroup group;
    private Button backBtn;

    public SortingView() {
        group = new ToggleGroup();

        for (StorageOrder order : StorageOrder.values()) {
            RadioButton button = new RadioButton();
            button.setText(order.name());
            button.setToggleGroup(group);
            button.setUserData(order);
            getChildren().add(button);

            if (order == StorageOrder.ID_ASCENDING) {
                button.setSelected(true);
            }
        }

        backBtn = new Button("Back");
        getChildren().add(backBtn);
    }

    @Override
    public String getName() {
        return "Sorting Options";
    }

    @Override
    public void initViewActions(ViewOperations operations) {
        backBtn.setOnAction((e) -> {
            operations.submitCommand(new SetSortingOrderCommand((StorageOrder) group.getSelectedToggle().getUserData()));
            operations.goBack();
        });
        operations.setRoot(this);
    }
}
