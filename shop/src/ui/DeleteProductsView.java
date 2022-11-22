package ui;

import commands.RemoveAllProductsCommand;
import commands.RemoveLastProductCommand;
import commands.RemoveProductIdCommand;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class DeleteProductsView extends VBox implements View {

    private TextField productId;

    private Button deleteProductByIdBtn;
    private Button deleteLastProductBtn;
    private Button deleteAllProductsBtn;
    private Button backBtn3;

    public DeleteProductsView() {
        GridPane pane = new GridPane();
        createDeleteProductIdBtn(pane, 1);
        createDeleteLastProductBtn(pane,2);
        createDeleteAllProductsBtn(pane,3);
        createBackBtn3(pane,4);

        getChildren().addAll(pane);
        pane.setVgap(10);
        setAlignment(Pos.CENTER_LEFT);
    }

	private void createDeleteProductIdBtn(GridPane pane, int row) {
		deleteProductByIdBtn = new Button();
        productId = new TextField();
        styleBtns(deleteProductByIdBtn, "Delete Product By Id");
        pane.addRow(row, deleteProductByIdBtn, productId );
	}
	
	private void createDeleteLastProductBtn(GridPane pane, int row) {
		deleteLastProductBtn = new Button();
        styleBtns(deleteLastProductBtn, "Delete Last Product");
        pane.addRow(row, deleteLastProductBtn);
	}
	
		private void createDeleteAllProductsBtn(GridPane pane, int row) {
		deleteAllProductsBtn = new Button();
        styleBtns(deleteAllProductsBtn, "Delete All Products");
        pane.addRow(row, deleteAllProductsBtn);

	}
		
		private void createBackBtn3(GridPane pane, int row) {
			backBtn3 = new Button();
	        styleBtns(backBtn3, "Back");
	        backBtn3.setAlignment(Pos.BASELINE_CENTER);
	        pane.addRow(row, backBtn3);
		}
	    
	
    private void styleBtns(Button btnComp, String text) {
        btnComp.setText(text);
        btnComp.setTextFill(Color.BLACK);
        btnComp.setLineSpacing(4);
    }

    @Override
    public void initViewActions(ViewOperations operations) {
        backBtn3.setOnAction((e) -> {
            operations.goBack();
        });
        deleteProductByIdBtn.setOnAction(e -> {
            operations.submitCommand(new RemoveProductIdCommand(productId.getText()));
            productId.clear();
        });
        deleteAllProductsBtn.setOnAction(e -> {
            operations.submitCommand(new RemoveAllProductsCommand());
        });
        deleteLastProductBtn.setOnAction(e -> {
            operations.submitCommand(new RemoveLastProductCommand());
        });

        productId.clear();
        operations.setRoot(this);
    }

    @Override
    public String getName() {
        return "Delete Products Options";
    }

}
