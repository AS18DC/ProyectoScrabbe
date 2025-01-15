package appScrabble;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class EditarAliasJugadorController {

    @FXML
    private TextField aliasField;

    @FXML
    private TextField aliasNuevoField;

    private Gestion gestion;

    public void setGestion(Gestion gestion) {
        this.gestion = gestion;
    }

    @FXML
    protected void onEditarClick() {
        String alias = aliasField.getText().trim();
        String aliasNuevo = aliasNuevoField.getText().trim();

        if (alias.isEmpty() || aliasNuevo.isEmpty()) {
            mostrarMensaje("Error", "Los campos no pueden estar vacio.");
            return;
        }

        boolean editado = gestion.editarAlias(alias, aliasNuevo);
        GestionListaJSON.leerJugadoresExistentes();
        GestionListaJSON.guardarJugadores(gestion.jugadores);

        if (editado) {
            mostrarMensaje("Exito", "Alias editado correctamente.");
        } else {
            mostrarMensaje("Error", "No se encontro el alias a editar.");
        }

        // Limpiar los campos de texto
        aliasField.clear();
        aliasNuevoField.clear();
    }

    @FXML
    protected void onCancelarClick() {
        aliasField.clear();
        aliasNuevoField.clear();
    }

    private void mostrarMensaje(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
