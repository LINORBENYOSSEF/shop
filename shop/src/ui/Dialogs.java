package ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Dialogs {

    public static void showError(Throwable t) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(t.getMessage());

        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        t.printStackTrace(printWriter);

        TextArea textArea = new TextArea(writer.toString());
        textArea.setEditable(false);
        textArea.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        alert.getDialogPane().setExpandableContent(textArea);
        alert.getDialogPane().setExpanded(true);
        alert.showAndWait();
    }

    public static void showError(String message) {
        show(message, AlertType.ERROR);

    }

    public static void showMessage(String message) {
        show(message, AlertType.INFORMATION);
    }

    public static void showText(String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        TextArea textArea = new TextArea(text);
        textArea.setEditable(false);
        textArea.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        alert.getDialogPane().setExpandableContent(textArea);
        alert.getDialogPane().setExpanded(true);
        alert.showAndWait();
    }

    private static void show(String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait();

    }
}
