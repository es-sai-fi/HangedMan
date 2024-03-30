package org.example.hangedman.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.example.hangedman.model.SecretWord;

import java.io.IOException;

public class GameController {
    private SecretWord secretWord;

    @FXML
    private TextField inputLetterTextField;

    //@FXML
    //public onAddLetterButtonClick(ActionEvent actionEvent) throws IOException {

    //}

    public void setSecretWord(SecretWord secretWord) {
        this.secretWord = secretWord;
    }
}
