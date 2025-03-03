module com.example.firstassignment {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.firstassignment to javafx.fxml;
    exports com.example.firstassignment;
}