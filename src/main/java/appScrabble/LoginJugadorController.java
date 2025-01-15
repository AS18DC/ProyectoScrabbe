package appScrabble;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

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
            mostrarMensaje("Error", "Uno o ambos jugadores no fueron encontrados. Intente de nuevo.");
            return;
        }

        if (jugador1.getNombre().equalsIgnoreCase(jugador2.getNombre())) {
            mostrarMensaje("Error", "Los nombres de los jugadores no pueden ser iguales.");
            return;
        }

        try {
            juego = new Juego(jugador1, jugador2, "src/main/resources/listado.txt");
            if (juego.iniciarPartida()) {
                mostrarMensaje("Éxito", "La partida ha comenzado.");
            } else {
                mostrarMensaje("Error", "No se pudo iniciar la partida. Regresando al menú...");
            }
        } catch (IOException e) {
            mostrarMensaje("Error", "Error al cargar el diccionario: " + e.getMessage());
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