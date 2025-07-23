module org.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.google.gson;


    opens org.example.demo1 to javafx.fxml;
    exports org.example.demo1;
}