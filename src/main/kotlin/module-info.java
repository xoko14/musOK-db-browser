module com.musok.musokdbbrowser {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires unirest.java;
    requires com.google.gson;

    opens com.musok.musokdbbrowser to javafx.fxml;
    opens com.musok.musokdbbrowser.api.mappings.auth to com.google.gson;
    opens com.musok.musokdbbrowser.api.mappings.user to com.google.gson;
    opens com.musok.musokdbbrowser.api.mappings.song to com.google.gson;
    exports com.musok.musokdbbrowser;
}