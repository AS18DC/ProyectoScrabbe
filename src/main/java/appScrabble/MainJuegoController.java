package appScrabble;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/**
 * Controlador para la interfaz del juego de Scrabble.
 */
public class MainJuegoController {

    @FXML
    protected void onIniciarJuegoClick() {
        mostrarMensaje("Iniciar Juego", "Función para iniciar un nuevo juego.");
        // Aquí puedes llamar a la lógica de iniciarJuego de la clase MainJuegoApplication.
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
     * Método auxiliar para mostrar mensajes informativos.
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
