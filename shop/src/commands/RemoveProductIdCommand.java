//David Halevi 305268153 Moshe samahov 205787229
package commands;

import javafx.application.Platform;
import storage.Storage;
import ui.Dialogs;

public class RemoveProductIdCommand implements Command {

    private String id;

    public RemoveProductIdCommand(String id) {
        this.id = id;
    }

    @Override
    public void execute(Storage storage) throws Exception {
        storage.removeProductById(id);
    }

    @Override
    public void onError(Throwable t) {
        Platform.runLater(() -> {
            Dialogs.showError(t);
        });

    }

    @Override
    public void onSuccess() {
        Platform.runLater(() -> {
            Dialogs.showMessage("The Product removed successfully");
        });
    }

}
