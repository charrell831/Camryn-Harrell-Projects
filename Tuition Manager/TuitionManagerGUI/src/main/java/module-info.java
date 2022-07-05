module com.example.tuitionmanagergui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.tuitionmanagergui to javafx.fxml;
    exports com.example.tuitionmanagergui;
}