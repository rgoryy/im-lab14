module com.example.imlab14 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.imlab14 to javafx.fxml;
    exports com.example.imlab14;
}