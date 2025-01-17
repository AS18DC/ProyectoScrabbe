module proyecto.poo.interfaz {
    requires java.logging;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dlsc.formsfx;
    opens juego to com.fasterxml.jackson.databind;
    exports com.example.usuarios;
    exports juego;
    opens com.example.usuarios to com.fasterxml.jackson.databind, javafx.fxml;
}