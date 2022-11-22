package ui;

import commands.PrintAllProductsCommand;
import commands.PrintProfitsCommand;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class PrintView extends VBox implements View {

    private Button printAllProductsBtn;
    private Button printProfitsBtn;
    private Button backBtn4;

    public PrintView() {
        printAllProductsBtn = new Button();
        styleBtns(printAllProductsBtn, "Print All Products");
        printProfitsBtn = new Button();
        styleBtns(printProfitsBtn, "Print Profits");
        backBtn4 = new Button();
        styleBtns(backBtn4, "Back");
        getChildren().addAll(printAllProductsBtn, printProfitsBtn, backBtn4);
        setSpacing(10);
        setAlignment(Pos.CENTER_LEFT);

    }

    private void styleBtns(Button btnComp, String text) {
        btnComp.setText(text);
        btnComp.setTextFill(Color.BLACK);
        btnComp.setLineSpacing(4);
    }

    @Override
    public void initViewActions(ViewOperations operations) {
        backBtn4.setOnAction((e) -> {
            operations.goBack();
        });
        printAllProductsBtn.setOnAction(e -> {
            operations.submitCommand(new PrintAllProductsCommand());
        });
        printProfitsBtn.setOnAction(e -> {
            operations.submitCommand(new PrintProfitsCommand());
        });
        operations.setRoot(this);

    }


    @Override
    public String getName() {
        return "Print Options";
    }

}
