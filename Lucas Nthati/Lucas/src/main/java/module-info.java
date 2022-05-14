module com.example.lucas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;



    opens com.example.lucas to javafx.fxml;
    exports com.example.lucas;
}