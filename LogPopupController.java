package com.example.assignment;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogPopupController {

    @FXML
    private DatePicker dateDatePicker;
    @FXML
    private TextField weightTextField;
    @FXML
    private TextField medicationTextField;
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
    void addLog() throws IOException {

        String date;

        try {
            date = dateDatePicker.getValue().toString();
        } catch (Exception e) {
            date = "";
        }

        if (date.isBlank() || weightTextField.getText().isBlank() || medicationTextField.getText().isBlank()) {
            emptyBoxLabel.setVisible(true);
        }
        else {
            MainController.addPetLog(selectedPet, new PetLog(0, date, weightTextField.getText(), medicationTextField.getText()));
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
