module com.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires junit;
    requires testfx.core;
    requires testfx.junit;
    requires javafx.swing;
    requires hamcrest.core;
    //requires org.testfx.junit;

    opens com.main to javafx.fxml;
    exports com.main;
    exports com.main.test;
}