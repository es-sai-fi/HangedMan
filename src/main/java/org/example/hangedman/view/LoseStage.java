package org.example.hangedman.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LoseStage extends Stage {

    public LoseStage () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/org/example/hangedman/lose-view.fxml"));
        Parent root = loader.load();
        setTitle("Hanged Man");
        Scene scene = new Scene(root);
        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource("/org/example/hangedman/images/favicon.png"))));
        setScene(scene);
        setResizable(false);
        show();
    }

    private static class LoseStageHolder {
        private static LoseStage INSTANCE;
    }

    public static LoseStage getInstance() throws IOException{
        return LoseStageHolder.INSTANCE = new LoseStage();
    }

}

