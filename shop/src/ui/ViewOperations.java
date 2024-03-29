package ui;

import commands.Command;
import javafx.scene.Node;

public interface ViewOperations {

    void submitCommand(Command command);

    void submitCommand(Command command, long delayMs);

    void goBack();

    void setRoot(Node node);
}
