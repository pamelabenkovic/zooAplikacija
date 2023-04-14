module com.example.javaaplikacijazoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.javaaplikacijazoo to javafx.fxml;
    exports com.example.javaaplikacijazoo;
}