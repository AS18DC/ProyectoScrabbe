package appScrabble;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ConsultarJugadorController {

    @FXML
    private TextField aliasField;

    @FXML
    private TextField correoField;

    private Gestion gestion;

    public void setGestion(Gestion gestion) {
        this.gestion = gestion;
    }

    @FXML
    protected void onConsultarClick() {
        String alias = aliasField.getText().trim();

        if (alias.isEmpty()) {
            mostrarMensaje("Error", "El campo no puede estar vacio.");
            return;
        }

        Jugador jugador = gestion.consultarJugador(alias);
        if (jugador != null) {
            mostrarMensaje("Jugador Encontrado", "El jugador con alias " + alias + " ha sido encontrado en la base de datos.");
        } else {
            mostrarMensaje("Error", "El jugador con alias " + alias + " no fue encontrado en la base de datos. Intente de nuevo.");
        }

        // Limpiar los campos de texto
        aliasField.clear();
        correoField.clear();
    }

    @FXML
    protected void onCancelarClick() {
        aliasField.clear();
        correoField.clear();
    }

    private void mostrarMensaje(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
