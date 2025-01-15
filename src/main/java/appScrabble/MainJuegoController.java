package appScrabble;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador para la interfaz del juego de Scrabble.
 */
public class MainJuegoController {
    private Juego juego;

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    @FXML
    protected void onIniciarJuegoClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-juego-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);

            LoginJugadorController loginJugadorController = fxmlLoader.getController();
            loginJugadorController.setJuego(juego);

            Stage stage = new Stage();
            stage.setTitle("Login - Scrabble");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            mostrarMensaje("Error", "No se pudo cargar la vista del juego: " + e.getMessage());
        }
    }

    @FXML
    protected void onContinuarPartidaClick() {
        mostrarMensaje("Continuar Partida", "Función para continuar una partida guardada.");
        // Aquí puedes llamar a la lógica de continuarPartida de la clase MainJuegoApplication.
    }

    @FXML
    protected void onSalirClick() {
        mostrarMensaje("Salir", "Cerrando la aplicación...");
        // Aquí puedes cerrar la aplicación o realizar otras acciones necesarias.
        System.exit(0); // Cerrar la aplicación
    }

    /**
     * Metodo auxiliar para mostrar mensajes informativos.
     *
     * @param titulo   El título del mensaje.
     * @param contenido El contenido del mensaje.
     */
    private void mostrarMensaje(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
