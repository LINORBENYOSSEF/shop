//David Halevi 305268153 Moshe samahov 205787229
package ui;

import commands.FindProductCommand;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ProductIDView extends VBox implements View {

    private TextField findProductByID;
    private Button findProductByIdBtn;
    private Button backBtn2;

    public ProductIDView() {
        HBox findProductByIDRow = new HBox();
        Label askfindProductByID = new Label("Product ID: ");
        askfindProductByID.setTextFill(Color.BLACK);
        findProductByID = new TextField();
        findProductByIDRow.setSpacing(15);
        findProductByIDRow.setAlignment(Pos.CENTER_LEFT);
        findProductByIDRow.getChildren().addAll(askfindProductByID, findProductByID);

        findProductByIdBtn = new Button();
        styleBtns(findProductByIdBtn, "Find Product By ID");

        backBtn2 = new Button();
        styleBtns(backBtn2, "Back");

        getChildren().addAll(findProductByIDRow, findProductByIdBtn, backBtn2);
        setAlignment(Pos.CENTER_LEFT);
    }

    private void styleBtns(Button btnComp, String text) {
        btnComp.setText(text);
        btnComp.setTextFill(Color.BLACK);
        btnComp.setAlignment(Pos.CENTER_LEFT);

    }

    @Override
    public String getName() {
        return "Find Product by ID";
    }

    @Override
    public void initViewActions(ViewOperations operations) {
        backBtn2.setOnAction((e) -> {
            findProductByID.clear();
            operations.goBack();
        });
        findProductByIdBtn.setOnAction((e) -> {
            operations.submitCommand(new FindProductCommand(findProductByID.getText()));
            operations.goBack();
        });

        clearFindProductPage();
        operations.setRoot(this);
    }

    private void clearFindProductPage() {
        findProductByID.clear();
    }

}
