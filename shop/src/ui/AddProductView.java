package ui;

import commands.AddProductCommand;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Customer;
import model.Product;

public class AddProductView extends VBox implements View {

    private TextField productId;
    private TextField productName;
    private TextField shopPrice;
    private TextField clientPrice;
    private TextField clientName;
    private TextField clientPhone;
    private CheckBox saleUpadtes;

    private Button backBtn1; 
    private Button addProductBtn;

    public AddProductView() {
        GridPane pane = new GridPane();
        pane.setVgap(5);

        createIdBtn(pane, 1);
        createProductNameBtn(pane, 2);
        createShopPriceBtn(pane, 3);
        createClientPriceBtn(pane, 4);
        createClientNameBtn(pane, 5);
        createClientPhoneBtn(pane, 6);
        createSalesUpdatesRowBtn(pane, 7);

        addProductBtn();
        backBtn1();
        setSpacing(6);
        getChildren().addAll(pane, addProductBtn, backBtn1);
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(5));

    }

    private void backBtn1() {
        backBtn1 = new Button();
        styleBtns(backBtn1, "Back");
    }

    private void addProductBtn() {
        addProductBtn = new Button();
        styleBtns(addProductBtn, "Add Product");
    }

    private void styleBtns(Button btnComp, String text) {
        btnComp.setText(text);
        btnComp.setTextFill(Color.BLACK);
        btnComp.setAlignment(Pos.CENTER_LEFT);
    }

    private void createSalesUpdatesRowBtn(GridPane pane, int row) {
        saleUpadtes = new CheckBox();
        Label asksaleUpadtes = new Label("Sale Updates: ");
        asksaleUpadtes.setTextFill(Color.BLACK);

        pane.addRow(row, asksaleUpadtes, saleUpadtes);
    }

    private void createClientPhoneBtn(GridPane pane, int row) {
        Label ClientPhone = new Label("Client Phone: ");
        ClientPhone.setTextFill(Color.BLACK);
        clientPhone = new TextField();

        pane.addRow(row, ClientPhone, clientPhone);
    }

    private void createClientNameBtn(GridPane pane, int row) {
        Label ClientName = new Label("Client Name: ");
        ClientName.setTextFill(Color.BLACK);
        clientName = new TextField();
        pane.addRow(row, ClientName, clientName);
    }

    private void createClientPriceBtn(GridPane pane, int row) {
        Label ClientPrice = new Label("Client Price: ");
        ClientPrice.setTextFill(Color.BLACK);
        clientPrice = new TextField();
        pane.addRow(row, ClientPrice, clientPrice);
    }

    private void createShopPriceBtn(GridPane pane, int row) {
        Label askPriceForShopRow = new Label("Shop Price: ");
        askPriceForShopRow.setTextFill(Color.BLACK);
        shopPrice = new TextField();
        pane.addRow(row, askPriceForShopRow, shopPrice);
    }

    private void createProductNameBtn(GridPane pane, int row) {
        Label askProductName = new Label("Product Name: ");
        askProductName.setTextFill(Color.BLACK);
        productName = new TextField();
        pane.addRow(row, askProductName, productName);
    }

    private void createIdBtn(GridPane pane, int row) {
        Label askProductId = new Label("Product ID: ");
        askProductId.setTextFill(Color.BLACK);
        productId = new TextField();
        pane.addRow(row, askProductId, productId);
    }


    @Override
    public void initViewActions(ViewOperations operations) {
        backBtn1.setOnAction((e) -> {
            operations.goBack();
        });
        addProductBtn.setOnAction((e) -> {
            try {
                if (shopPrice.getText().equals("") || clientPrice.getText().equals("")) {
                    shopPrice.setText("0");
                    clientPrice.setText("0");
                }

                Product product = new Product(productId.getText(), productName.getText(),
                        Integer.parseInt(shopPrice.getText()),
                        Integer.parseInt(clientPrice.getText()),
                        new Customer(clientName.getText(), clientPhone.getText(),
                                saleUpadtes.isSelected()));
                operations.submitCommand(new AddProductCommand(product));
            } catch (NumberFormatException ex) {
                Dialogs.showError("Prices illegal");
            }

            operations.goBack();
        });

        clearAddProductPage();
        operations.setRoot(this);
    }

    private void clearAddProductPage() {
        productId.clear();
        productName.clear();
        shopPrice.clear();
        clientPrice.clear();
        clientName.clear();
        clientPhone.clear();
        saleUpadtes.setSelected(false);
    }

    @Override
    public String getName() {
        return "Add Product";
    }
}

