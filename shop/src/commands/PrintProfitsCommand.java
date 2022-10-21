//David Halevi 305268153 Moshe samahov 205787229
package commands;

import javafx.application.Platform;
import model.Product;
import storage.Storage;
import ui.Dialogs;

import java.util.List;

public class PrintProfitsCommand implements Command {

    private String data;

    @Override
    public void execute(Storage storage) throws Exception {
        List<Product> products = storage.getAllProducts();
    	if(products.isEmpty()) {
    		throw new Exception("No profits to show!");
    	}
        StringBuilder builder = new StringBuilder();
        for (Product product : products) {
            builder.append(product.getId());
            builder.append(": ");
            builder.append(product.profitOfShopForProduct());
            builder.append('\n');
        }

        data = builder.toString();
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
            Dialogs.showText(data);
        });
    }

}
