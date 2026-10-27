module com.example.employeemisapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.employeemisapp to javafx.fxml;
    exports com.example.employeemisapp;
}