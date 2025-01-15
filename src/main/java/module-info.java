module appScrabble {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.google.gson;

    opens appScrabble to javafx.fxml, com.google.gson;
    exports appScrabble;
}