package appScrabble;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StatsJugadorController {

    @FXML
    private TextField aliasField;

    @FXML
    private TableView<Estadistica> statsTable;

    @FXML
    private TableColumn<Estadistica, String> columnaCategoria;

    @FXML
    private TableColumn<Estadistica, String> columnaValor;

    private ObservableList<Estadistica> datosEstadisticas;

    private Gestion gestion;

    public void setGestion(Gestion gestion) {
        this.gestion = gestion;
    }

    @FXML
    public void initialize() {
        // Configurar las columnas de la tabla
        columnaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        columnaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        // Inicializar la lista observable
        datosEstadisticas = FXCollections.observableArrayList();
        statsTable.setItems(datosEstadisticas);
    }

    @FXML
    protected void onStatsClick() {
        String alias = aliasField.getText().trim();

        if (alias.isEmpty()) {
            mostrarMensaje("Error", "El campo no puede estar vacío.");
            return;
        }

        // Limpiar datos anteriores
        datosEstadisticas.clear();

        // Cargar estadísticas
        gestion.cargarPartidasGuardadas();
        gestion.mostrarEstadisticasDePartidas(alias);

        aliasField.clear();
    }

    @FXML
    protected void onCancelarClick() {
        aliasField.clear();
    }

    private void mostrarMensaje(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    // Metodo para agregar una estadística a la tabla
    public void agregarEstadistica(String categoria, String valor) {
        datosEstadisticas.add(new Estadistica(categoria, valor));
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

    // Clase interna para representar una estadística
    public static class Estadistica {
        private final String categoria;
        private final String valor;

        public Estadistica(String categoria, String valor) {
            this.categoria = categoria;
            this.valor = valor;
        }

        public String getCategoria() {
            return categoria;
        }

        public String getValor() {
            return valor;
        }
    }
}
