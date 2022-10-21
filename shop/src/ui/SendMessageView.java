//David Halevi 305268153 Moshe samahov 205787229
package ui;

import commands.SendMessageCommand;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import messaging.Message;

public class SendMessageView extends VBox implements View {

    private TextField subjectField;
    private TextArea textArea;
    private Button sendMessageBtn;
    private Button backBtn;

    public SendMessageView() {
        subjectField = new TextField();
        textArea = new TextArea();
        sendMessageBtn = new Button("Send");
        backBtn = new Button("Back");

        getChildren().addAll(subjectField, textArea, sendMessageBtn, backBtn);
    }

    @Override
    public String getName() {
        return "Send Message";
    }

    @Override
    public void initViewActions(ViewOperations operations) {
        sendMessageBtn.setOnAction((e) -> {
            operations.submitCommand(new SendMessageCommand(new Message(subjectField.getText(), textArea.getText())));
            textArea.clear();
        });
        backBtn.setOnAction((e) -> {
            operations.goBack();
        });

        textArea.clear();
        operations.setRoot(this);
    }
}
