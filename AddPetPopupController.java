package com.example.assignment;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

public class AddPetPopupController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField speciesTextField;
    @FXML
    private TextField coatColourTextField;
    @FXML
    private DatePicker dateOfBirthDatePicker;
    @FXML
    private TextArea notesTextArea;
    @FXML
    private Label emptyBoxLabel;
    @FXML
    private Button cancelButton;

    @FXML
    void addPet() throws IOException {

        String dateOfBirth;

        try {
            dateOfBirth = dateOfBirthDatePicker.getValue().toString();
        } catch (Exception e) {
            dateOfBirth = "";
        }

        if (nameTextField.getText().isBlank() || speciesTextField.getText().isBlank() || coatColourTextField.getText().isBlank() || dateOfBirth.isBlank() || notesTextArea.getText().isBlank()) {
            emptyBoxLabel.setVisible(true);
        }
        else {
            MainController.addPet(new Pet(0, nameTextField.getText(), speciesTextField.getText(), coatColourTextField.getText(), dateOfBirth, notesTextArea.getText()));
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
