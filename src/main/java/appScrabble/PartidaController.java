package appScrabble;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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

    private Tablero tableroLogico;

    private Juego juego;

    @FXML
    private Button btnEstadisticas;

    @FXML
    private Button btnEnviar;

    @FXML
    private Button btnDeshacer;

    @FXML
    private Button btnBolsaFichas;

    @FXML
    private Button btnPasar;

    @FXML
    private Button btnCambiarFichas;



    public void setJuego(Juego juego) {
        this.juego = juego;
        actualizarVistaJugadores();
    }

    public void initialize() {
        // Inicializar el tablero lógico y sus multiplicadores
        tableroLogico = new Tablero(new Saco());
        tableroLogico.colocarMultiplicadores();

        // Dibujar el tablero visual
        mostrarTablero();
    }

    private void mostrarTablero() {
        // Limpiar cualquier contenido previo del GridPane
        tablero.getChildren().clear();

        String[][] matriz = tableroLogico.getTablero();
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int col = 0; col < matriz[fila].length; col++) {
                // Crear una celda visual para cada posición en el tablero
                StackPane celda = new StackPane();
                Rectangle fondo = new Rectangle(40, 40); // Tamaño de cada celda
                fondo.setStroke(Color.BLACK); // Borde negro

                // Estilo según el contenido
                String contenido = matriz[fila][col].trim();
                if (contenido.contains("3XP")) {
                    fondo.setFill(Color.BLUE); // Triple palabra
                } else if (contenido.contains("2XP")) {
                    fondo.setFill(Color.RED); // Doble palabra
                } else if (contenido.contains("3XL")) {
                    fondo.setFill(Color.YELLOW); // Triple letra
                } else if (contenido.contains("2XL")) {
                    fondo.setFill(Color.GREEN); // Doble letra
                } else {
                    fondo.setFill(Color.LIGHTGRAY); // Fondo normal
                }

                // Mostrar texto dentro de la celda
                Text texto = new Text(contenido.replaceAll("[^a-zA-Z0-9]", "")); // Limpiar colores ANSI
                texto.setStyle("-fx-font-weight: bold;");

                // Añadir elementos a la celda
                celda.getChildren().addAll(fondo, texto);
                tablero.add(celda, col, fila); // Agregar celda a la cuadrícula
            }
        }
    }

    private void actualizarVistaJugadores() {
        nombreJugador1.setText(juego.getJugador1().getNombre());
        scoreJugador1.setText("Puntos: " + juego.getJugador1().getPuntajePartida());

        nombreJugador2.setText(juego.getJugador2().getNombre());
        scoreJugador2.setText("Puntos: " + juego.getJugador2().getPuntajePartida());
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
        // Mostrar las fichas restantes en la bolsa
    }

    @FXML
    protected void onPasarClick() {
        // Lógica para pasar el turno
    }

    @FXML
    protected void onCambiarFichasClick() {
        // Cambiar todas las fichas y pasar turno
    }

    @FXML
    protected void onEstadisticasClick() {
        // Mostrar estadísticas de los jugadores
    }
}
