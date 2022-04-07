module com.musok.musokdbbrowser {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires unirest.java;
    requires gson;

    opens com.musok.musokdbbrowser to javafx.fxml;
    opens com.musok.musokdbbrowser.api.mappings.auth to gson;
    opens com.musok.musokdbbrowser.api.mappings.user to gson;
    opens com.musok.musokdbbrowser.api.mappings.song to gson;
    exports com.musok.musokdbbrowser;
}