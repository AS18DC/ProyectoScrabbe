package appScrabble;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class MainGestionController {

    @FXML
    protected void onRegistrarJugadorClick() {
        mostrarMensaje("Registrar jugador", "Función para registrar un jugador");
        // Aquí puedes agregar la lógica específica para registrar un jugador.
    }

    @FXML
    protected void onConsultarJugadorClick() {
        mostrarMensaje("Consultar jugador", "Función para consultar un jugador");
        // Aquí puedes agregar la lógica específica para consultar un jugador.
    }

    @FXML
    protected void onEditarCorreoClick() {
        mostrarMensaje("Editar correo de jugador", "Función para editar el correo de un jugador");
        // Aquí puedes agregar la lógica específica para editar el correo de un jugador.
    }

    @FXML
    protected void onEditarAliasClick() {
        mostrarMensaje("Editar alias de jugador", "Función para editar el alias de un jugador");
        // Aquí puedes agregar la lógica específica para editar el alias de un jugador.
    }

    @FXML
    protected void onMostrarEstadisticasClick() {
        mostrarMensaje("Mostrar estadísticas", "Función para mostrar estadísticas de un jugador");
        // Aquí puedes agregar la lógica específica para mostrar estadísticas.
    }

    @FXML
    protected void onVolverMenuClick() {
        mostrarMensaje("Volver al menú principal", "Regresando al menú principal...");
        // Aquí puedes agregar la lógica específica para volver al menú principal.
    }

    private void mostrarMensaje(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
