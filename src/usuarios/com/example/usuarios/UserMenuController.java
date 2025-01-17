package com.example.usuarios;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserMenuController {

    @FXML
    protected void onButtonRegistrar(ActionEvent event) {
        try {

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/interfazUsuario/MenuDeRegistro.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Menu De Registro");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onButtonModificar(ActionEvent event) {
        try {

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/interfazUsuario/MenuDeModificar.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Menu de Modificacion");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onButtonEliminar(ActionEvent event) {
        try {

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/interfazUsuario/MenuDeEliminar.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Menu de Eliminacion");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onButtonEstadisticas(ActionEvent event) {
        try {

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/interfazUsuario/MenuDeEstadisticas.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Menu de Estadisticas");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onButtonSalir(ActionEvent event) {
        System.exit(0);
    }
}
