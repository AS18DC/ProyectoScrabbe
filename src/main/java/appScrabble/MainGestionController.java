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
    protected void onEliminarJugadorClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("eliminar-jugador-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);

            EliminarJugadorController eliminarJugadorController = fxmlLoader.getController();
            eliminarJugadorController.setGestion(gestion);

            Stage stage = new Stage();
            stage.setTitle("Eliminar Jugador");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onMostrarEstadisticasClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("stats-jugador-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);

            StatsJugadorController statsJugadorController = fxmlLoader.getController();
            statsJugadorController.setGestion(gestion);

            Stage stage = new Stage();
            stage.setTitle("Mostrar estadisticas de Jugador");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onSalirClick() {
        mostrarMensaje("Salir", "Cerrando la aplicación...");
        System.exit(0);
    }

    private void mostrarMensaje(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
