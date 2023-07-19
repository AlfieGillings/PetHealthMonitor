package com.example.assignment;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;

public class LoginPopupController {

    @FXML
    private Button cancelButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label invalidLoginLabel;
    @FXML
    private Label emptyBoxLabel;

    @FXML
    void loginChanged() {

        invalidLoginLabel.setVisible(false);
        emptyBoxLabel.setVisible(false);

    }

    @FXML
    void logIn() throws IOException {

        if (usernameTextField.getText().isBlank() || passwordTextField.getText().isEmpty()) {
            emptyBoxLabel.setVisible(true);
        }
        else if (!MainController.logIn(usernameTextField.getText(), passwordTextField.getText())) {
            invalidLoginLabel.setVisible(true);
        }
        else {

            MainController.openWindow("MainMenu.fxml", "Main Menu", 960, 1080);
            MainController.closeWindow(cancelButton);

        }

    }

    @FXML
    void cancel() throws IOException {

        MainController.openWindow("LoginMenu.fxml", "Pet Health Monitor", 400, 250);
        MainController.closeWindow(cancelButton);

    }

}
