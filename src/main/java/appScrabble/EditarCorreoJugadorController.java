package appScrabble;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarCorreoJugadorController {

    @FXML
    private TextField aliasField;

    @FXML
    private TextField correoField;

    private Gestion gestion;

    public void setGestion(Gestion gestion) {
        this.gestion = gestion;
    }

    @FXML
    protected void onEditarClick() {
        String alias = aliasField.getText().trim();
        String correoNuevo = correoField.getText().trim();

        if (alias.isEmpty() || correoNuevo.isEmpty()) {
            mostrarMensaje("Error", "Los campos no pueden estar vacio.");
            return;
        }

        boolean validPlayer = false;
        while (!validPlayer) {
            try {
                gestion.validarJugador(alias, correoNuevo); // Validar los datos del jugador
                validPlayer = true; // Si no lanza excepción, la validación es exitosa
            } catch (JugadorInvalido e) {
                mostrarMensaje("Datos inválidos", e.getMessage());
                return; // Salir del metodo y permitir corrección de datos en la interfaz
            }
        }

        boolean editado = gestion.editarCorreo(alias, correoNuevo);
        GestionListaJSON.leerJugadoresExistentes();
        GestionListaJSON.guardarJugadores(gestion.jugadores);

        if (editado) {
            mostrarMensaje("Correo Editado", "El correo del jugador con alias " + alias + " ha sido editado correctamente.");
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
}
