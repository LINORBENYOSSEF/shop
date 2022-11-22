package commands;

import javafx.application.Platform;
import model.Product;
import storage.Storage;
import ui.Dialogs;

public class AddProductCommand implements Command {

    private Product product;

    public AddProductCommand(Product product) {
        this.product = product;
    }

    @Override
    public void execute(Storage storage) throws Exception {
        storage.addProduct(product);
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
            Dialogs.showMessage("Product added successfully");
        });
    }

}
