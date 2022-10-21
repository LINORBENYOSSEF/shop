//David Halevi 305268153 Moshe samahov 205787229
package ui;

import commands.Command;
import commands.CommandQueue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainView extends StackPane implements ViewOperations {

    private CommandQueue commandQueue;

    private List<View> views;
    private VBox menuRoot;
    private FlowPane menuOptions;

    public MainView(CommandQueue commandQueue) {
        this.commandQueue = commandQueue;

        Image image = new Image(new File("SHOP.PNG").toURI().toString());
        BackgroundSize backGroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backGroundSize);
        Background background = new Background(backgroundImage);
        setBackground(background);

        views = new ArrayList<>();
        views.add(new AddProductView());
        views.add(new ProductIDView());
        views.add(new DeleteProductsView());
        views.add(new PrintView());
        views.add(new SortingView());
        views.add(new SendMessageView());
        views.add(new MessageStatusView());

        menuOptions = new FlowPane();
        menuOptions.setVgap(5);
        menuOptions.setHgap(5);

        for (View view : views) {
            Button button = new Button();
            button.setText(view.getName());
            button.setTextFill(Color.BLACK);
            button.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            button.setOnAction((e) -> showView(view));
            menuOptions.getChildren().add(button);
        }

        menuRoot = new VBox();
        menuRoot.getChildren().add(menuOptions);
        menuRoot.setAlignment(Pos.CENTER_LEFT);
        getChildren().add(menuRoot);
    }

    private void showView(View view) {
        view.initViewActions(this);
    }

    @Override
    public void goBack() {
        setRoot(menuOptions);
    }

    @Override
    public void setRoot(Node node) {
        menuRoot.getChildren().set(0, node);
    }

    @Override
    public void submitCommand(Command command) {
        commandQueue.addCommand(command);
    }

    @Override
    public void submitCommand(Command command, long delayMs) {
        commandQueue.addCommand(command, delayMs);
    }
}
