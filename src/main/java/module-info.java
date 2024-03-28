module org.example.hangedman {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.hangedman to javafx.fxml;
    exports org.example.hangedman;
}