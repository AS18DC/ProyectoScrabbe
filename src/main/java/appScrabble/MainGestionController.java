package appScrabble;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

public class MainGestionController {
    private Gestion gestion;

    public void setGestion(Gestion gestion) {
        this.gestion = gestion;
    }

    @FXML
    protected void onRegistrarJugadorClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registrar-jugador-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);

            RegistrarJugadorController registrarController = fxmlLoader.getController();
            registrarController.setGestion(gestion);

            Stage stage = new Stage();
            stage.setTitle("Registrar Jugador");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onConsultarJugadorClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("consultar-jugador-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);

            ConsultarJugadorController consultarJugadorController = fxmlLoader.getController();
            consultarJugadorController.setGestion(gestion);

            Stage stage = new Stage();
            stage.setTitle("Consultar Jugador");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onEditarCorreoClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editar-correo-jugador-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);

            EditarCorreoJugadorController editarCorreoJugadorController = fxmlLoader.getController();
            editarCorreoJugadorController.setGestion(gestion);

            Stage stage = new Stage();
            stage.setTitle("Editar correo de Jugador");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onEditarAliasClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editar-alias-jugador-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);

            EditarAliasJugadorController editarAliasJugadorController = fxmlLoader.getController();
            editarAliasJugadorController.setGestion(gestion);

            Stage stage = new Stage();
            stage.setTitle("Editar alias de Jugador");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onMostrarEstadisticasClick() {
        mostrarMensaje("Mostrar estadísticas", "Función para mostrar estadísticas de un jugador");
        // Aquí puedes agregar la lógica específica para mostrar estadísticas.
    }

    @FXML
    protected void onSalirClick() {
        mostrarMensaje("Salir", "Cerrando la aplicación...");
        // Aquí puedes cerrar la aplicación o realizar otras acciones necesarias.
        System.exit(0); // Cerrar la aplicación
    }

    private void mostrarMensaje(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
