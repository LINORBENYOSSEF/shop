package commands;

import messaging.Message;
import messaging.Observer;
import storage.Storage;

public class AcknowledgeMessageCommand implements Command {

    private Message message;
    private Observer observer;

    public AcknowledgeMessageCommand(Message message, Observer observer) {
        this.message = message;
        this.observer = observer;
    }

    @Override
    public void execute(Storage storage) throws Exception {
        message.acknowledge(observer);
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onSuccess() {

    }
}
