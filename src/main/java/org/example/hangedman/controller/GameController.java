package org.example.hangedman.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.example.hangedman.model.SecretWord;
import org.example.hangedman.view.alert.AlertBox;
import org.example.hangedman.view.LoseStage;
import org.example.hangedman.view.WinStage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isLetter;

public class GameController {
    private SecretWord secretWord;

    private List<TextField> textFields = new ArrayList<>();

    private List<Integer> indicesOfChar = new ArrayList<>();

    private List<Integer> randomNumArray = new ArrayList<>();

    private int errorCount = 0;

    private int helpCount = 0;

    @FXML
    private HBox gameViewHBox;

    @FXML
    private ImageView hangedManProgress;

    @FXML
    private TextField addLetterTextField;

    @FXML
    private Button addButton;

    @FXML
    private Button helpButton;

    @FXML
    private Label stateLabel;

    public void createTextFields(){
        int wordLength = secretWord.getWordLength();
        for (int i = 0; i < wordLength; i++){
            TextField textField = new TextField();
            textField.setPrefHeight(25);
            textField.setPrefWidth(25);
            textField.setMinWidth(Region.USE_PREF_SIZE);
            textField.setText("");
            textField.setEditable(false);
            textFields.add(textField);
            gameViewHBox.getChildren().add(textField);
        }
    }

    @FXML
    void onAddLetterButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) stateLabel.getScene().getWindow();
        int wordLength = secretWord.getWordLength();
        boolean wordHasAlreadyBeenAdded = false;
        String inputLetter = addLetterTextField.getText().toLowerCase();
        if (!isLetter(inputLetter.charAt(0))) {
            AlertBox alert = new AlertBox();
            alert.showMessage("¡Error!", "Palabra no válida.", "La palabra ingresada no es válida, por favor ingresar solo letras y/o no utilizar espacio.", stage);
        }
        else {
            for (int i = 0; i < wordLength; i++) {
                String letterToCompare = textFields.get(i).getText();
                if (letterToCompare.equals(inputLetter)) {
                    wordHasAlreadyBeenAdded = true;
                }
            }
            if(wordHasAlreadyBeenAdded){
                stateLabel.setStyle("-fx-font-size: 15px");
                stateLabel.setText("La letra ya ha sido ingresada antes.");
            }
            else{
                if (secretWord.letterIsInWord(inputLetter)) {
                    indicesOfChar = secretWord.getIndicesOfChar(inputLetter);
                    for (Integer integer : indicesOfChar) {
                        TextField textField = textFields.get(integer);
                        textField.setText(inputLetter);
                    }
                    stateLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: green;");
                    stateLabel.setText("Letra agregada.");
                    String computedWord = "";
                    for (int k = 0; k < wordLength; k++) {
                        computedWord += textFields.get(k).getText();
                    }
                    if (computedWord.equals(secretWord.getWord())) {
                        stateLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: green;");
                        stateLabel.setText("¡¡¡HAS GANADO!!!");
                        addLetterTextField.setText("");
                        addLetterTextField.setEditable(false);
                        addButton.setDisable(true);
                        helpButton.setDisable(true);
                        WinStage winStage = new WinStage();
                        winStage.show();
                        stage.close();
                    }
                }
                else {
                    errorCount += 1;
                    stateLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: red;");
                    stateLabel.setText("La letra ingresada no se encuentra en la palabra secreta.");
                    hangedManProgress.setImage(new Image(String.valueOf(getClass().getResource("/org/example/hangedman/images/hangedManProgress" + errorCount + ".png"))));
                    if (errorCount == 6) {
                        stateLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: red;");
                        stateLabel.setText("Has perdido.");
                        addLetterTextField.setText("");
                        addLetterTextField.setEditable(false);
                        addButton.setDisable(true);
                        helpButton.setDisable(true);
                        LoseStage loseStage = new LoseStage();
                        loseStage.show();
                        stage.close();
                    }
                }
            }
        }
        addLetterTextField.setText("");
    }

    @FXML
    void onAddLetterKeyPressed(KeyEvent keyEvent){
        addLetterTextField.setEditable(true);
        if(addLetterTextField.getText().length() >= 1){
            addLetterTextField.setEditable(false);
            addLetterTextField.setText("");
            stateLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: red;");
            stateLabel.setText("Ingrese solo una letra.");
        }
    }

    @FXML
    void onHelpButtonClick(ActionEvent actionEvent) throws IOException{
        String[] splitWordArray = secretWord.getSplitWordArray();
        if(helpCount == 3){
            stateLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: red;");
            stateLabel.setText("Ya has recibido demasiadas pistas.");
            helpButton.setDisable(true);
        }
        else{
            boolean reapeatedRandomNum;
            boolean letterAlreadyIn;
            String helpLetter;
            do{
                int randomNum = (int)(Math.random() * secretWord.getWordLength());
                helpLetter = splitWordArray[randomNum];
                reapeatedRandomNum = randomNumArray.contains(randomNum);
                letterAlreadyIn = false;
                for(int l = 0; l < textFields.size(); l++){
                    if(helpLetter.equals(textFields.get(l).getText())){
                        letterAlreadyIn = true;
                        break;
                    }
                }
                if(!reapeatedRandomNum && !letterAlreadyIn){
                    randomNumArray.add(randomNum);
                    indicesOfChar = secretWord.getIndicesOfChar(helpLetter);
                    for (Integer integer : indicesOfChar) {
                        TextField textField = textFields.get(integer);
                        textField.setText(helpLetter);
                    }
                    stateLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: green;");
                    stateLabel.setText("Se agregó la letra: " + helpLetter + ".");
                }
            }
            while (reapeatedRandomNum || letterAlreadyIn);
            helpCount += 1;
        }
        String computedWord = "";
        for (int k = 0; k < secretWord.getWordLength(); k++) {
            computedWord += textFields.get(k).getText();
        }
        if (computedWord.equals(secretWord.getWord())) {
            Stage stage = (Stage) stateLabel.getScene().getWindow();
            stateLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: green;");
            stateLabel.setText("¡¡¡HAS GANADO!!!");
            addLetterTextField.setText("");
            addLetterTextField.setEditable(false);
            addButton.setDisable(true);
            helpButton.setDisable(true);
            WinStage winStage = new WinStage();
            winStage.show();
            stage.close();
        }
    }


    public void setSecretWord(SecretWord secretWord) {
        this.secretWord = secretWord;
    }
}

