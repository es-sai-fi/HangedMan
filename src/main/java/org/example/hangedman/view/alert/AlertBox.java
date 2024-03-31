package org.example.hangedman.view.alert;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AlertBox implements IAlertBox {

    @Override
    public void showMessage(String title, String header, String content, Stage stage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.initOwner(stage);
        alert.showAndWait();
    }
}
