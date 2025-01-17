package com.example.usuarios;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserMenuApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/interfazUsuario/InterfazGraficaUsuario.fxml"));
        primaryStage.setTitle("Menu de Jugador");
        primaryStage.setScene(new Scene(root, 600, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}