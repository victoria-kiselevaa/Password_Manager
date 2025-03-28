module com.example.managepasword {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.managepasword to javafx.fxml;
    exports com.example.managepasword;
}