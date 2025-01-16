package appScrabble;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EliminarJugadorController {

    @FXML
    private TextField aliasField;
    private Gestion gestion;

    public void setGestion(Gestion gestion) {
        this.gestion = gestion;
    }

    /**
     * Acción al hacer clic en el botón "Eliminar".
     */
    public void onEliminarClick() {
        String alias = aliasField.getText().trim();

        if (alias.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Alias vacío");
            alert.setContentText("Por favor, ingrese un alias válido para eliminar.");
            alert.showAndWait();
            return;
        }

        Gestion gestion = new Gestion();
        boolean eliminado = gestion.eliminarJugador(alias);

        if (eliminado) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText("Jugador eliminado");
            alert.setContentText("El jugador con alias \"" + alias + "\" ha sido eliminado exitosamente.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo eliminar el jugador");
            alert.setContentText("No se encontró un jugador con el alias \"" + alias + "\".");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onSalirClick() {
        Stage currentStage = (Stage) aliasField.getScene().getWindow();
        if (currentStage != null) {
            currentStage.close();
        } else {
            mostrarMensaje("Salir", "No se pudo cerrar la ventana.");
        }
    }

    private void mostrarMensaje(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    /**
     * Acción al hacer clic en el botón "Vaciar" para limpiar el campo de texto.
     */
    public void onCancelarClick() {
        aliasField.clear();
    }
}
