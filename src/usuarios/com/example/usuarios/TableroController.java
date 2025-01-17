package com.example.usuarios;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import juego.*;
import juego.Character;
import juego.Main;
import juego.Tablero;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TableroController {
    private Partida partida;
    private int pass;

    @FXML
    private HBox playerTiles; // HBox para las fichas del jugador

    @FXML
    private GridPane board; // GridPane para el tablero de juego

    @FXML
    private Button sendButton;

    @FXML
    private Button passButton;

    @FXML
    private Button statsButton;

    private Button selectedTileButton = null;
    private String selectedLetter = null; // Letra seleccionada por el jugador

    private Integer columna;
    private Integer fila;
    private boolean horizontal;

    // Mapa para rastrear qué botón del HBox corresponde a cada letra colocada
    private Map<String, Button> usedTilesMap = new HashMap<>();

    public void iniciarPartida(Jugador jugador1, Jugador jugador2) {
        this.partida = new Partida(jugador1, jugador2);
        this.pass = 0;
    }

    @FXML
    public void initialize() {
        iniciarPartida(new Jugador("aharon", "aharon@gmail.com"), new Jugador("jose", "jose@gmail.com"));


        if (partida.getActualTurn() == 1) {
            FichasJugador fichas1 = partida.getJugador1().getPlayerCharacters();
            while (fichas1.existeComodin()) {
                String nuevaFicha = showAlertComodin("FELICIDADES", "Te has encontrado un comodin, intercambialo por una letra");
                nuevaFicha = nuevaFicha.toUpperCase();
                while (!LetraValida(nuevaFicha)) {
                    System.out.println("Necesitas elegir una letra entre la A-Z o CH,LL,RR, intenta de nuevo");
                    nuevaFicha = showAlertComodin("FELICIDADES", "Necesitas elegir una letra entre la A-Z o CH,LL,RR, intenta de nuevo");
                    nuevaFicha = nuevaFicha.toUpperCase();
                }
                partida.getJugador1().getPlayerCharacters().setComodin(nuevaFicha);

                fichas1 = partida.getJugador1().getPlayerCharacters();
            }
            mostrarFichas(partida.getJugador1());
        } else {
            FichasJugador fichas2 = partida.getJugador2().getPlayerCharacters();
            while (fichas2.existeComodin()) {
                String nuevaFicha2 = showAlertComodin("FELICIDADES", "Te has encontrado un comodin, intercambialo por una letra");
                nuevaFicha2 = nuevaFicha2.toUpperCase();
                while (!LetraValida(nuevaFicha2)) {
                    System.out.println("Necesitas elegir una letra entre la A-Z o CH,LL,RR, intenta de nuevo");
                    nuevaFicha2 = showAlertComodin("FELICIDADES", "Necesitas elegir una letra entre la A-Z o CH,LL,RR, intenta de nuevo");
                    nuevaFicha2 = nuevaFicha2.toUpperCase();
                }
                partida.getJugador2().getPlayerCharacters().setComodin(nuevaFicha2);

                fichas2 = partida.getJugador2().getPlayerCharacters();
            }
            mostrarFichas(partida.getJugador2());
        }

        // Asignar evento a las fichas del jugador
        for (Node node : playerTiles.getChildren()) {
            if (node instanceof Button button) {
                button.setOnMouseClicked(event -> onTileClick(event, button));
            }
            sendButton.setOnMouseClicked(event -> onSendClick());
            passButton.setOnMouseClicked(event -> onPassClick());
            statsButton.setOnMouseClicked(event -> showPlayerStats());
        }

        // Asignar evento a los botones del tablero
        for (Node node : board.getChildren()) {
            if (node instanceof Button button) {
                button.setOnMouseClicked(event -> onBoardClick(event, button, board));
            }
        }

    }

    // Evento al hacer clic en una ficha del jugador
    private void onTileClick(MouseEvent event, Button tileButton) {
        if (!tileButton.isDisable()) { // Solo permitir seleccionar fichas activas
            selectedLetter = tileButton.getText(); // Guardar la letra seleccionada
            selectedTileButton = tileButton; // Guardar el botón seleccionado
            System.out.println("Ficha seleccionada: " + selectedLetter);
        } else {
            System.out.println("Esta ficha ya fue usada.");
        }
    }

    // Evento al hacer clic en el tablero
    private void onBoardClick(MouseEvent event, Button boardButton, GridPane board) {
        String currentText = boardButton.getText();

        if (selectedLetter != null) {

            if (currentText != null && !currentText.isEmpty()) {
                // Validar si el botón del tablero ya tiene una ficha
                System.out.println("El espacio ya está ocupado. No se puede cambiar la ficha.");
                return;
            }
            // Cambiar el texto del botón del tablero
            boardButton.setText(selectedLetter);

            // Extraer las coordenadas del botón
            Integer row = GridPane.getRowIndex(boardButton);
            Integer col = GridPane.getColumnIndex(boardButton);

            // Manejar índices nulos
            row = (row == null) ? 0 : row;
            col = (col == null) ? 0 : col;

            horizontal = Objects.equals(fila, row);
            System.out.println(horizontal);
            columna = (columna == null) ? col : columna;
            System.out.println(columna);

            fila = (fila == null) ? row : fila;
            System.out.println(fila);

            System.out.println("Letra '" + selectedLetter + "' colocada en fila: " + row + ", columna: " + col);

            // Deshabilitar la ficha seleccionada para que no pueda usarse de nuevo
            if (selectedTileButton != null) {
                usedTilesMap.put(selectedLetter, selectedTileButton); // Asociar letra con el botón del HBox
                selectedTileButton.setDisable(true);
                selectedTileButton = null; // Limpiar la referencia al botón seleccionado
            }

            // Desmarcar la ficha seleccionada
            selectedLetter = null;

        } else if (currentText != null && !currentText.isEmpty()) {
            // Restaurar una ficha colocada
            Button originalTileButton = usedTilesMap.get(currentText);
            if (originalTileButton != null) {
                originalTileButton.setDisable(false); // Habilitar el botón original
                usedTilesMap.remove(currentText); // Quitar del mapa de fichas usadas
                boardButton.setText(""); // Limpiar el texto del tablero
                System.out.println("Ficha '" + currentText + "' restaurada al HBox.");
            }
        } else {
            System.out.println("No hay ficha seleccionada y el botón está vacío.");
        }
    }

    private String[][] getBoardAsMatrix(GridPane board) {
        // Determinar las dimensiones del GridPane
        int rows = board.getRowCount();
        int cols = board.getColumnCount();

        // Crear una matriz para almacenar el contenido del tablero
        String[][] matrix = new String[rows][cols];

        // Recorrer los hijos del GridPane
        for (Node node : board.getChildren()) {
            if (node instanceof Button button) {
                // Obtener la posición del botón
                Integer row = GridPane.getRowIndex(node);
                Integer col = GridPane.getColumnIndex(node);

                // Manejar índices nulos (por defecto fila/columna es 0)
                row = (row == null) ? 0 : row;
                col = (col == null) ? 0 : col;

                // Asignar el texto del botón a la posición correspondiente en la matriz
                matrix[row][col] = button.getText();
            }
        }

        // Rellenar celdas vacías con un valor predeterminado (por ejemplo, "")
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == null) {
                    matrix[i][j] = ""; // Dejar vacío si no hay texto
                }
            }
        }

        return matrix;
    }

    private String getWordFromMatrix(String[][] board, int startRow, int startCol, boolean isHorizontal) {
        StringBuilder word = new StringBuilder();

        // Validar las dimensiones de la matriz
        int rows = board.length;
        int cols = (rows > 0) ? board[0].length : 0;

        // Recorrer en la dirección especificada
        if (isHorizontal) {
            for (int col = startCol; col < cols; col++) {
                String cell = board[startRow][col];
                if (cell == null || cell.isEmpty()) {
                    break; // Detenerse si se encuentra una celda vacía
                }
                word.append(cell);
            }
        } else { // Dirección vertical
            for (int row = startRow; row < rows; row++) {
                String cell = board[row][startCol];
                if (cell == null || cell.isEmpty()) {
                    break; // Detenerse si se encuentra una celda vacía
                }
                word.append(cell);
            }
        }
        return word.toString();
    }

    private void onSendClick() {
        String[][] tablero = getBoardAsMatrix(board);
        String palabra = getWordFromMatrix(tablero, fila, columna, horizontal);

        if (!validarPalabra(palabra)) {
            showAlert("Palabra invalida", "La palabra que quieres colocar es invalida, por favor intenta con otra o pasa turno");
            return;
        }

        if (partida.getActualTurn() == 1) {
            partida.ubicarPalabra(palabra, fila, columna, horizontal, partida.getJugador1());
            pass = 0;
        } else {
            partida.ubicarPalabra(palabra, fila, columna, horizontal, partida.getJugador2());
            pass = 0;
        }
        partida.getTablero().mostrarTablero();
        System.out.println(palabra);
        partida.alternarTurno();
        columna = null;
        fila = null;
        if (partida.getActualTurn() == 1) {
            mostrarFichas(partida.getJugador1());
        } else {
            mostrarFichas(partida.getJugador2());
        }
        for (Node node : playerTiles.getChildren()) {
            if (node instanceof Button button) {
                button.setDisable(false);
            }
        }
        usedTilesMap = new HashMap<>();
    }

    private void onPassClick() {
        partida.alternarTurno();
        pass += 1;
        if (pass == 4) {
            partida.chooseWinner();
        }

        if (partida.getActualTurn() == 1) {
            mostrarFichas(partida.getJugador1());
        } else {
            mostrarFichas(partida.getJugador2());
        }
    }

    private void mostrarFichas(Jugador jugador) {
        int index = 0;
        juego.FichasJugador fichasJugador = jugador.getPlayerCharacters();
        ArrayList<juego.Character> fichas = fichasJugador.getFichas();
        for (Node node : playerTiles.getChildren()) {
            if (node instanceof Button button) {
                button.setText(fichas.get(index).getSymbol());
            }
            if (index < 6) index += 1;
        }
    }

    private boolean validarPalabra(String palabra) {
        Diccionario diccionario = new Diccionario();
        return diccionario.existePalabra(palabra);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showPlayerStats() {
        Jugador jugador;
        if (partida.getActualTurn() == 1) {
            jugador = partida.getJugador1();
        } else {
            jugador = partida.getJugador2();
        }
        // Crear una nueva alerta
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Estadísticas del Jugador");
        alert.setHeaderText("Estadísticas de " + jugador.getAlias());
        alert.setContentText("Alias: " + jugador.getAlias() + "\n" +
                "Correo: " + jugador.getEmail() + "\n" +
                "Puntaje: " + jugador.getScore() + "\n" +
                "Palabras Jugadas: " + jugador.getPalabrasJugadas() + "\n" +
                "Tiempo Total Jugado: " + jugador.getTiempoTotalJugado() + " segundos\n" +
                "Partidas Jugadas: " + jugador.getPartidasJugadas());

        // Mostrar la alerta
        alert.showAndWait();
    }

    private String showAlertComodin(String title, String message) {
        // Crear una nueva alerta
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Crear un campo de texto
        TextField inputField = new TextField();
        inputField.setPromptText("Ingresa tu valor aquí");

        // Añadir el campo de texto a la alerta
        VBox vBox = new VBox();
        vBox.getChildren().addAll(alert.getDialogPane().getContent(), inputField);
        alert.getDialogPane().setContent(vBox);

        // Mostrar la alerta y esperar a que el usuario presione el botón
        Optional<ButtonType> result = alert.showAndWait();

        // Devolver el valor ingresado por el usuario
        if (result.isPresent() && result.get() == ButtonType.OK) {
            return inputField.getText();
        } else {
            return null;
        }
    }

    private boolean LetraValida(String letra) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(letra);
        if (matcher.matches()) {
            return true;
        } else return (letra.equals("RR") | letra.equals("CH") | letra.equals("LL"));
    }

    @FXML
    public void onBolsaFichasClick() {
        SpanishBag bag = partida.getBag();

        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Letras restantes en el saco:\n");

        int letras = bag.numberOfCharacters();
        mensaje.append("\nTotal de letras restantes: ").append(letras);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Letras en el Saco");
        alert.setHeaderText(null);
        alert.setContentText(mensaje.toString());
        alert.showAndWait();
    }

}
