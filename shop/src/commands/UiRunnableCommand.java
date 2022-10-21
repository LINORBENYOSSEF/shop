//David Halevi 305268153 Moshe samahov 205787229
package commands;

import javafx.application.Platform;
import storage.Storage;

public class UiRunnableCommand implements Command {

    private Runnable runnable;

    public UiRunnableCommand(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void execute(Storage storage) throws Exception {
        Platform.runLater(runnable);
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onSuccess() {

    }
}
