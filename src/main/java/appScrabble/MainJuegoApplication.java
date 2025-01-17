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
    private Juego juego;

    @Override
    public void start(Stage stage) throws IOException {
        Jugador jugador1 = new Jugador(" "," ");
        Jugador jugador2 = new Jugador(" "," ");
        String filePath = "src/main/resources/listado.txt";
        System.out.println("el pepe");

        juego = new Juego(jugador1, jugador2, filePath);

        FXMLLoader fxmlLoader = new FXMLLoader(MainJuegoApplication.class.getResource("/appScrabble/main-juego-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);

        MainJuegoController mainController = fxmlLoader.getController();
        mainController.setJuego(juego);

        stage.setTitle("Scrabble - Aplicacion de Juego");
        stage.setScene(scene);
        stage.show();
    }
}