package com.example.assignment;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AppointmentPopupController {

    @FXML
    private DatePicker dateDatePicker;
    @FXML
    private TextField timeTextField;
    @FXML
    private TextField typeTextField;
    @FXML
    private Label emptyBoxLabel;
    @FXML
    private Button cancelButton;

    private Pet selectedPet;

    @FXML
    void initialize() {

        selectedPet = MainMenuController.getSelectedPet();

    }

    @FXML
    void addAppointment() throws IOException {

        String date;

        try {
            date = dateDatePicker.getValue().toString();
        } catch (Exception e) {
            date = "";
        }

        if (date.isBlank() || timeTextField.getText().isBlank() || typeTextField.getText().isBlank()) {
            emptyBoxLabel.setVisible(true);
        }
        else {
            MainController.addAppointment(selectedPet, new Appointment(0, date, timeTextField.getText(), typeTextField.getText()));
            MainController.openWindow("MainMenu.fxml", "Main Menu", 960, 1080);
            MainController.closeWindow(cancelButton);
        }

    }

    @FXML
    void inputChanged() {

        emptyBoxLabel.setVisible(false);

    }

    @FXML
    void cancel() throws IOException {

        MainController.openWindow("MainMenu.fxml", "Main Menu", 960, 1080);
        MainController.closeWindow(cancelButton);

    }

}
