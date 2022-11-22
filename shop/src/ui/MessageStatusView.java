package ui;

import commands.UiRunnableCommand;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import messaging.Message;
import messaging.MessageQueue;
import messaging.Observer;

import java.util.ArrayList;
import java.util.List;

public class MessageStatusView extends VBox implements View {

    private TabPane tabPane;
    private Button backBtn;

    private List<Pair<ListView<Observer>, Message>> listViews;

    private boolean isShowing;

    public MessageStatusView() {
        tabPane = new TabPane();
        listViews = new ArrayList<>();
        this.backBtn = new Button("Back");
        getChildren().addAll(tabPane, backBtn);
        isShowing = false;
    }

    @Override
    public String getName() {
        return "Message Status";
    }

    @Override
    public void initViewActions(ViewOperations operations) {
        tabPane.getTabs().clear();
        listViews.clear();
        for (Message message : MessageQueue.getInstance().getMessageHistory()) {
            Tab tab = new Tab();
            tab.setText(message.getSubject());

            TextArea content = new TextArea();
            content.setEditable(false);
            content.setText(message.getText());

            ListView<Observer> acknowledged = new ListView<>();
            acknowledged.setEditable(false);
            listViews.add(new Pair<>(acknowledged, message));

            VBox root = new VBox();
            root.getChildren().addAll(content, acknowledged);
            tab.setContent(root);

            tabPane.getTabs().add(tab);
        }

        backBtn.setOnAction((e) -> {
            isShowing = false;
            operations.goBack();
        });
        operations.setRoot(this);
        isShowing = true;

        refreshTabs(operations);
    }

    private void refreshTabs(ViewOperations operations) {
        if (!isShowing) {
            return;
        }

        for (Pair<ListView<Observer>, Message> pair : listViews) {
            ListView<Observer> acknowledged = pair.getKey();
            Message message = pair.getValue();

            acknowledged.getItems().clear();
            acknowledged.getItems().addAll(message.getAcknowledgedBy());
        }

        operations.submitCommand(new UiRunnableCommand(() -> refreshTabs(operations)), 200);
    }
}
