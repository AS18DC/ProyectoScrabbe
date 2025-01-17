package com.example.usuarios;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Tablero extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/resources/interfazUsuario/Tablero.fxml"));
        primaryStage.setTitle("Tablero");
        primaryStage.setScene(new Scene(root, 600, 800));
        primaryStage.show();
    }
}
