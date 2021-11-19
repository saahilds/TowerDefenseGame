module com.main {
    requires javafx.controls;
//    requires javafx.fxml;
    requires junit;
//    requires testfx.core;
//    requires testfx.junit;
//    requires io.reactivex.rxjava2;
    requires org.kordamp.bootstrapfx.core;
    requires rxjavafx;
    requires hamcrest.core;
    requires javafx.fxml;
    requires rxjava;
    requires testfx.junit;
//    requires org.testfx.junit;

    opens com.main to javafx.fxml;
    exports com.main;
    exports com.test;
    exports com.game;
}