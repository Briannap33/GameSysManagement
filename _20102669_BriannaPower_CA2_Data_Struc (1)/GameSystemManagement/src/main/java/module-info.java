module com.example.gamesystemmanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gamesystemmanagement to javafx.fxml;
    exports com.example.gamesystemmanagement.controllers;
    exports com.example.gamesystemmanagement.models;
    exports com.example.gamesystemmanagement.javafxml;
}