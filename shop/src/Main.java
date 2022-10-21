
import commands.CommandQueue;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import messaging.MessageQueue;
import storage.Storage;
import ui.MainView;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Storage storage = new Storage(new File("product.txt"));
        CommandQueue commandQueue = new CommandQueue(storage);
        MessageQueue messageQueue = new MessageQueue(commandQueue);
        MessageQueue.setInstance(messageQueue);

        storage.load();

        MainView menuView = new MainView(commandQueue);
        Scene scene = new Scene(menuView, 850, 650);

        primaryStage.setTitle("Online Shop");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}