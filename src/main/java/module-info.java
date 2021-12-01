module com.adithya.moviedatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.java;


    opens com.adithya.moviedatabase to javafx.fxml;
    exports com.adithya.moviedatabase;
}