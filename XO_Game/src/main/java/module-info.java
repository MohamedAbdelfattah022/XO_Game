module com.example.XO_Game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.XO_Game to javafx.fxml;
    exports com.example.XO_Game;
}