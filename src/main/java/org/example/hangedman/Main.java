package org.example.hangedman;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.hangedman.view.WelcomeStage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        WelcomeStage.getInstance();
    }

    public static void main(String[] args) {
        launch(args);
    }
}