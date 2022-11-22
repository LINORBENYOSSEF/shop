package commands;

import javafx.application.Platform;
import model.Product;
import storage.Storage;
import ui.Dialogs;

public class FindProductCommand implements Command {

    private String id;
    private Product foundProduct;

    public FindProductCommand(String id) {
        this.id = id;
    }

    @Override
    public void execute(Storage storage) throws Exception {
        foundProduct = storage.findProductById(id);
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
            Dialogs.showText(foundProduct.toString());
        });
    }

}
