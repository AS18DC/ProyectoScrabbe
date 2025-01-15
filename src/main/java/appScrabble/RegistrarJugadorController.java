package appScrabble;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class RegistrarJugadorController {

    @FXML
    private TextField aliasField;

    @FXML
    private TextField correoField;

    private Gestion gestion;

    public void setGestion(Gestion gestion) {
        this.gestion = gestion;
    }

    @FXML
    protected void onRegistrarClick() {
        String alias = aliasField.getText().trim();
        String correo = correoField.getText().trim();

        if (alias.isEmpty() || correo.isEmpty()) {
            mostrarMensaje("Error", "Todos los campos deben estar completos.");
            return;
        }

        boolean validPlayer = false;

        // Loop de validación
//        while (!validPlayer) {
//            try {
//                gestion.validarJugador(alias, correo); // Validar los datos del jugador
//                validPlayer = true; // Si no lanza excepción, la validación es exitosa
//            } catch (JugadorInvalido e) {
//                mostrarMensaje("Datos inválidos", e.getMessage());
//                return; // Salir del metodo y permitir corrección de datos en la interfaz
//            }
//        }

        // Registrar el jugador
        gestion.registrarJugador(correo, alias);

        // Leer y guardar la lista de jugadores
        GestionListaJSON.leerJugadoresExistentes();
        GestionListaJSON.guardarJugadores(gestion.jugadores);

        // Mostrar mensaje de confirmación
        mostrarMensaje("Registro exitoso", "El jugador ha sido registrado correctamente.");

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
