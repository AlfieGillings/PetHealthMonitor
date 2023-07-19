package com.example.assignment;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditPetPopupController {

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

    private Pet selectedPet;

    @FXML
    void initialize() {

        selectedPet = MainMenuController.getSelectedPet();
        LocalDate date = LocalDate.parse(selectedPet.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        nameTextField.setText(selectedPet.getName());
        speciesTextField.setText(selectedPet.getSpecies());
        coatColourTextField.setText(selectedPet.getCoatColour());
        dateOfBirthDatePicker.setValue(date);
        notesTextArea.setText(selectedPet.getNotes());

    }

    @FXML
    void editPet() throws IOException {

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
            Pet newPet = selectedPet;

            newPet.setName(nameTextField.getText());
            newPet.setSpecies(speciesTextField.getText());
            newPet.setCoatColour(coatColourTextField.getText());
            newPet.setDateOfBirth(dateOfBirth);
            newPet.setNotes(notesTextArea.getText());

            MainController.editPet(selectedPet);

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
