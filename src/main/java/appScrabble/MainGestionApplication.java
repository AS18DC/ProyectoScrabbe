package appScrabble;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGestionApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainGestionApplication.class.getResource("main-gestion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1860, 1000);
        stage.setTitle("Scrabble - Aplicacion de Gestión de Usuarios");
        stage.setScene(scene);
        stage.show();
    }
}