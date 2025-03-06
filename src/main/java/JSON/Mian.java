package JSON;

import frontEnd.MainScreen;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Mian extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        MainScreen screen = new MainScreen();
        screen.start(stage);
    }
}
