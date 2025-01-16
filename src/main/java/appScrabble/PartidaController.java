package appScrabble;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class PartidaController {

    @FXML
    private VBox jugador1Info;

    @FXML
    private VBox jugador2Info;

    @FXML
    private Label nombreJugador1;

    @FXML
    private Label scoreJugador1;

    @FXML
    private Label nombreJugador2;

    @FXML
    private Label scoreJugador2;

    @FXML
    private GridPane tablero;

    @FXML
    private HBox letrasJugador;

    @FXML
    private Button btnPasar;

    @FXML
    private Button btnCambiarFichas;

    @FXML
    private Button btnEnviar;

    @FXML
    private Button btnBolsaFichas;

    @FXML
    private Button btnDeshacer;

    @FXML
    private Button btnStats;

    private Tablero tableroLogico;
    private Juego juego;
    private String letraSeleccionada;
    private Saco saco;

    public void setJuego(Juego juego) {
        this.juego = juego;
        actualizarVistaJugadores();
        actualizarLetrasJugador();
    }

    public void initialize() {
        saco = new Saco();
        tableroLogico = new Tablero(saco);
        tableroLogico.colocarMultiplicadores();
    }


    void mostrarTablero() {
        tablero.getChildren().clear();

        String[][] matriz = tableroLogico.getTablero();
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int col = 0; col < matriz[fila].length; col++) {
                StackPane celda = new StackPane();
                Rectangle fondo = new Rectangle(40, 40);
                fondo.setStroke(Color.BLACK);

                fondo.setFill(Color.LIGHTGRAY); // Default background color

                String contenido = matriz[fila][col].trim();
                Text texto = new Text(contenido.replaceAll("[^a-zA-Z0-9]", ""));
                texto.setStyle("-fx-font-weight: bold;");
                celda.getChildren().addAll(fondo, texto);
                tablero.add(celda, col, fila);
            }
        }
    }

    void actualizarVistaJugadores() {
        nombreJugador1.setText(juego.getJugador1().getNombre());
        scoreJugador1.setText("Puntos: " + juego.getJugador1().getPuntajePartida());

        nombreJugador2.setText(juego.getJugador2().getNombre());
        scoreJugador2.setText("Puntos: " + juego.getJugador2().getPuntajePartida());
    }

    private void actualizarLetrasJugador() {
        if (letrasJugador != null) {
            letrasJugador.getChildren().clear();
            List<String> letras = juego.getJugadorEnTurno().getLetras();

            for (String letra : letras) {
                Button botonFicha = new Button(letra);
                botonFicha.setPrefSize(50, 50);
                botonFicha.setStyle("-fx-font-size: 14;");
                botonFicha.setOnAction(e -> seleccionarLetra(letra));
                letrasJugador.getChildren().add(botonFicha);
            }
        } else {
            System.err.println("letrasJugador is not initialized.");
        }

    }

    private void seleccionarLetra(String letra) {
        letraSeleccionada = letra;
    }

    @FXML
    private void handleCellClick(MouseEvent event) {
        if (letraSeleccionada != null) {
            StackPane celda = (StackPane) event.getSource();
            int fila = (int) celda.getProperties().get("fila");
            int col = (int) celda.getProperties().get("col");

            Text texto = new Text(letraSeleccionada);
            texto.setStyle("-fx-font-weight: bold;");
            celda.getChildren().add(texto);

            juego.getJugadorEnTurno().getLetras().remove(letraSeleccionada);
            letraSeleccionada = null;
            actualizarLetrasJugador();
        }
    }

    public void resaltarJugadorEnTurno(boolean esJugador1Turno) {
        jugador1Info.setStyle(esJugador1Turno ? "-fx-background-color: lightgreen;" : "");
        jugador2Info.setStyle(esJugador1Turno ? "" : "-fx-background-color: lightgreen;");
    }

    @FXML
    protected void onEnviarClick() {
        // Lógica para enviar palabra
    }

    @FXML
    protected void onDeshacerClick() {
        // Lógica para deshacer movimientos
    }

    @FXML
    protected void onBolsaFichasClick() {
        // Obtener el número total de letras en el saco
        int totalLetras = saco.contarLetrasEnSaco();

        // Crear un mensaje para mostrar las letras restantes
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Letras restantes en el saco:\n");

        for (Letra letra : saco.getLetras()) {
            mensaje.append("Letra: ").append(letra.letra)
                    .append(", Cantidad: ").append(letra.cantidad)
                    .append("\n");
        }
        mensaje.append("\nTotal de letras restantes: ").append(totalLetras);

        // Mostrar el mensaje en la interfaz gráfica
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Letras en el Saco");
        alert.setHeaderText(null);
        alert.setContentText(mensaje.toString());
        alert.showAndWait();
    }


    @FXML
    protected void onPasarClick() {
        juego.pasarTurno();
        resaltarJugadorEnTurno(juego.esTurnoJugador1());
        actualizarLetrasJugador();
    }

    @FXML
    protected void onCambiarFichasClick() {
        juego.cambiarFichasJugadorEnTurno();
        actualizarLetrasJugador();
    }

    @FXML
    protected void onEstadisticasClick() {
        // Mostrar estadísticas de los jugadores
    }
}