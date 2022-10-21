//David Halevi 305268153 Moshe samahov 205787229
package commands;

import messaging.Message;
import messaging.Observer;
import storage.Storage;

public class HandleObserverCommand implements Command {

    private Message message;
    private Observer observer;
    private CommandQueue commandQueue;

    public HandleObserverCommand(Message message, Observer observer, CommandQueue commandQueue) {
        this.message = message;
        this.observer = observer;
        this.commandQueue = commandQueue;
    }

    @Override
    public void execute(Storage storage) throws Exception {
        observer.handle(message, commandQueue);
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onSuccess() {

    }
}
