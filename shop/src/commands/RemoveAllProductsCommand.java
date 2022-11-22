package commands;

import javafx.application.Platform;
import storage.Storage;
import ui.Dialogs;

public class RemoveAllProductsCommand implements Command {

    @Override
    public void execute(Storage storage) throws Exception {
        storage.removeAllProducts();
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
            Dialogs.showMessage("All products removed successfully");
        });
    }

}
