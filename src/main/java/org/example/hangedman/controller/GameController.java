package org.example.hangedman.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.example.hangedman.model.SecretWord;

import java.io.IOException;

public class GameController {
    private SecretWord secretWord;

    @FXML
    void onAddLetterButtonClick(ActionEvent actionEvent) throws IOException {
        Label label = new Label();
        label.setText("Hola");
    }

    public void setSecretWord(SecretWord secretWord) {
        this.secretWord = secretWord;
    }
}
