package appScrabble;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGestionApplication extends Application {
    private Gestion gestion;

    @Override
    public void start(Stage stage) throws IOException {
        gestion = new Gestion();

        FXMLLoader fxmlLoader = new FXMLLoader(MainGestionApplication.class.getResource("main-gestion-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);

        MainGestionController mainController = fxmlLoader.getController();
        mainController.setGestion(gestion);

        stage.setTitle("Scrabble - Aplicacion de Gestión de Usuarios");
        stage.setScene(scene);
        stage.show();
    }
}