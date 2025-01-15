package appScrabble;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal para ejecutar la interfaz del juego de Scrabble.
 */
public class MainJuegoApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainJuegoApplication.class.getResource("main-juego-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1860, 1000);
        stage.setTitle("Scrabble - Aplicacion de Juego");
        stage.setScene(scene);
        stage.show();
    }
}