module com.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires testfx.core;
    requires testfx.junit;
    requires io.reactivex.rxjava2;
    requires org.kordamp.bootstrapfx.core;
//    requires org.kordamp.bootstrapfx;

    opens com.main to javafx.fxml;
    exports com.main;
    exports com.main.test;
}