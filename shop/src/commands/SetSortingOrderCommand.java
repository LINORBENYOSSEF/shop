//David Halevi 305268153 Moshe samahov 205787229
package commands;

import javafx.application.Platform;
import storage.Storage;
import storage.StorageOrder;
import ui.Dialogs;

public class SetSortingOrderCommand implements Command {

    private StorageOrder order;

    public SetSortingOrderCommand(StorageOrder order) {
        this.order = order;
    }

    @Override
    public void execute(Storage storage) throws Exception {
        storage.setOrder(order);
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
            Dialogs.showMessage("Sorted with " + order.name());
        });
    }
}
