module com.mycompany.ferienprojekt_ben {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.ferienprojekt_ben to javafx.fxml;
    exports com.mycompany.ferienprojekt_ben;
}
