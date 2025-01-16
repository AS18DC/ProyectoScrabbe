package appScrabble;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginJugadorController {

    @FXML
    private TextField jugador1Field;

    @FXML
    private TextField jugador2Field;

    private Juego juego;

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    @FXML
    protected void onIniciarJuegoClick() {
        String nombreJugador1 = jugador1Field.getText().trim();
        String nombreJugador2 = jugador2Field.getText().trim();

        if (nombreJugador1.isEmpty() || nombreJugador2.isEmpty()) {
            mostrarMensaje("Error", "Ambos campos de nombre de usuario deben estar llenos.");
            return;
        }

        Jugador jugador1 = juego.seleccionJugador(nombreJugador1);
        Jugador jugador2 = juego.seleccionJugador(nombreJugador2);

        if (jugador1 == null || jugador2 == null) {
            if (jugador1 == null && jugador2 == null) {
                mostrarMensaje("Error", "Ambos jugadores no fueron encontrados. Intente de nuevo.");
            } else if (jugador1 == null) {
                mostrarMensaje("Error", "El jugador 1 no fue encontrado. Alias: " + nombreJugador1);
            } else if (jugador2 == null) {
                mostrarMensaje("Error", "El jugador 2 no fue encontrado. Alias: " + nombreJugador2);
            }
            return;
        }

        // Verificar si ambos jugadores están en la lista
        Gestion gestion = new Gestion();
        if (gestion.estaEnLista(nombreJugador1) && gestion.estaEnLista(nombreJugador2)) {
            if (jugador1.getNombre().equalsIgnoreCase(jugador2.getNombre())) {
                mostrarMensaje("Error", "Los nombres de los jugadores no pueden ser iguales.");
                return;
            }

            try {
                juego = new Juego(jugador1, jugador2, "src/main/resources/listado.txt");
                if (juego.iniciarPartida()) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("partida-view.fxml"));
                        Scene scene = new Scene(loader.load(), 700, 500);

                        PartidaController controller = loader.getController();
                        controller.setJuego(juego);

                        Stage stage = (Stage) jugador1Field.getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        mostrarMensaje("Error", "No se pudo cargar la vista de la partida: " + e.getMessage());
                    }
                } else {
                    mostrarMensaje("Error", "No se pudo iniciar la partida. Regresando al menú...");
                }
            } catch (IOException e) {
                mostrarMensaje("Error", "Error al cargar el diccionario: " + e.getMessage());
            }
        } else {
            mostrarMensaje("Error", "Uno o ambos jugadores no están en la lista.");
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