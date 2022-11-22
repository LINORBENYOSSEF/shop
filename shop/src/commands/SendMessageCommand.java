package commands;

import javafx.application.Platform;
import messaging.Message;
import messaging.MessageQueue;
import storage.Storage;
import ui.Dialogs;

public class SendMessageCommand implements Command {

    private Message message;

    public SendMessageCommand(Message message) {
        this.message = message;
    }

    @Override
    public void execute(Storage storage) throws Exception {
        MessageQueue.getInstance().sendMessage(message);
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onSuccess() {
        Platform.runLater(() -> {
            Dialogs.showMessage("Sent: " + message.getSubject());
        });
    }
}
