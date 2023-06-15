package com.example.XO_Game;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;


public class HelloApplication extends Application {
    private int[] count = {1};

    public Button[][] createButtons() {
        Button[][] arr = new Button[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                arr[i][j] = new Button(" ");
                arr[i][j].setFont(new Font(16));
            }

        return arr;
    }

    public void setLayout(Button[][] arr) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                arr[i][j].setMinHeight(40);
                arr[i][j].setMinWidth(40);
            }
    }

    public void addButtonsToPane(Button[][] arr, GridPane pane) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                pane.add(arr[i][j], i, j);
    }

    public void btnClicked(Button btn, int[] counter, Button[][] arr) {
        btn.setOnAction(e -> {
            if (btn.getText().equals(" ")) {
                if (counter[0] % 2 == 1) {
                    btn.setText("X");
                    btn.setStyle("-fx-background-color: #FF1744; -fx-text-fill: #FFFFFF");
                } else {
                    btn.setText("O");
                    btn.setStyle("-fx-background-color: #2979FF; -fx-text-fill: #FFFFFF");
                }
                counter[0]++;
            }

            if (checkWin(arr)) {
                winAlert(arr);
            } else if (counter[0] >= 10) {
                drawAlert(arr);
            }
        });
    }

    public void manageButtons(Button[][] arr) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                btnClicked(arr[i][j], count, arr);
    }

    public boolean checkColumn(Button[][] arr) {
        boolean c1 = false, c2 = false, c3 = false;
        if (arr[0][0].getText().equals("X") && arr[1][0].getText().equals("X") && arr[2][0].getText().equals("X"))
            c1 = true;
        else if (arr[0][0].getText().equals("O") && arr[1][0].getText().equals("O") && arr[2][0].getText().equals("O"))
            c1 = true;

        if (arr[0][1].getText().equals("X") && arr[1][1].getText().equals("X") && arr[2][1].getText().equals("X"))
            c2 = true;
        else if (arr[0][1].getText().equals("O") && arr[1][1].getText().equals("O") && arr[2][1].getText().equals("O"))
            c2 = true;

        if (arr[0][2].getText().equals("X") && arr[1][2].getText().equals("X") && arr[2][2].getText().equals("X"))
            c3 = true;
        else if (arr[0][2].getText().equals("O") && arr[1][2].getText().equals("O") && arr[2][2].getText().equals("O"))
            c3 = true;

        return (c1 || c2 || c3);
    }

    public boolean checkRow(Button[][] arr) {

        boolean r1 = false, r2 = false, r3 = false;
        if (arr[0][0].getText().equals("X") && arr[0][1].getText().equals("X") && arr[0][2].getText().equals("X"))
            r1 = true;
        else if (arr[0][0].getText().equals("O") && arr[0][1].getText().equals("O") && arr[0][2].getText().equals("O"))
            r1 = true;

        if (arr[1][0].getText().equals("X") && arr[1][1].getText().equals("X") && arr[1][2].getText().equals("X"))
            r2 = true;
        else if (arr[1][0].getText().equals("O") && arr[1][1].getText().equals("O") && arr[1][2].getText().equals("O"))
            r2 = true;

        if (arr[2][0].getText().equals("X") && arr[2][1].getText().equals("X") && arr[2][2].getText().equals("X"))
            r3 = true;
        else if (arr[2][0].getText().equals("O") && arr[2][1].getText().equals("O") && arr[2][2].getText().equals("O"))
            r3 = true;

        return (r1 || r2 || r3);
    }

    public boolean checkDiagonal(Button[][] arr) {

        boolean d1 = false, d2 = false;
        if (arr[0][0].getText().equals("X") && arr[1][1].getText().equals("X") && arr[2][2].getText().equals("X"))
            d1 = true;
        else if (arr[0][0].getText().equals("O") && arr[1][1].getText().equals("O") && arr[2][2].getText().equals("O"))
            d1 = true;

        if (arr[0][2].getText().equals("X") && arr[1][1].getText().equals("X") && arr[2][0].getText().equals("X"))
            d2 = true;
        else if (arr[0][2].getText().equals("O") && arr[1][1].getText().equals("O") && arr[2][0].getText().equals("O"))
            d2 = true;

        return (d1 || d2);
    }

    public boolean checkWin(Button[][] arr) {
        return (checkColumn(arr) || checkRow(arr) || checkDiagonal(arr));
    }

    public void winAlert(Button[][] arr) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Congratulations");
        alert.setHeaderText(null);
        alert.setTitle("Win");
        Image img = new Image("cup.png");
        alert.setGraphic(new ImageView(img));
        alert.showAndWait();
        reset(arr);
    }

    public void drawAlert(Button[][] arr) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Try Again");
        alert.setHeaderText(null);
        alert.setTitle("Draw");
        Image img = new Image("game-over.png");
        alert.setGraphic(new ImageView(img));
        alert.showAndWait();
        reset(arr);
    }

    public void reset(Button[][] arr) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                arr[i][j].setText(" ");
                arr[i][j].setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000");
            }
        count[0] = 1;
    }

    @Override
    public void start(Stage stage) {
        GridPane gp = new GridPane();
        Scene scene = new Scene(gp, 280, 280);

        gp.setAlignment(Pos.CENTER);
        gp.setBackground(new Background(new BackgroundFill(Color.valueOf("#00253e"), CornerRadii.EMPTY, Insets.EMPTY)));
        gp.setPadding(new Insets(10, 10, 10, 10));
        Button[][] btnArray = createButtons();
        setLayout(btnArray);

        addButtonsToPane(btnArray, gp);
        gp.setVgap(10);
        gp.setHgap(10);

        manageButtons(btnArray);
        Image icon = new Image("tic-tac-toe.png");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("X-O Game");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}