module com.example.fis_practica_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fis_practica_1 to javafx.fxml;
    exports com.example.fis_practica_1;
}