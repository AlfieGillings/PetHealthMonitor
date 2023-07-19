package com.example.assignment;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;

public class SignupPopupController {

    @FXML
    private Button cancelButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField passwordConfirmTextField;
    @FXML
    private Label passwordsMatchLabel;
    @FXML
    private Label usernameTakenLabel;
    @FXML
    private Label emptyBoxLabel;

    @FXML
    void createAccount() throws IOException {

        if (usernameTextField.getText().isBlank() || passwordTextField.getText().isEmpty() || passwordConfirmTextField.getText().isEmpty()) {
            emptyBoxLabel.setVisible(true);
        }
        else if (!passwordTextField.getText().equals(passwordConfirmTextField.getText())) {
            passwordsMatchLabel.setVisible(true);
        }
        else if (!MainController.signUp(usernameTextField.getText(), passwordTextField.getText())) {
            usernameTakenLabel.setVisible(true);
        }
        else {

            MainController.openWindow("AccountCreatedPopup.fxml", "Account Created", 300, 150);
            MainController.closeWindow(cancelButton);

        }

    }

    @FXML
    void signupChanged() {

        usernameTakenLabel.setVisible(false);
        passwordsMatchLabel.setVisible(false);
        emptyBoxLabel.setVisible(false);

    }

    @FXML
    void cancel() throws IOException {

        MainController.openWindow("LoginMenu.fxml", "Pet Health Monitor", 400, 250);
        MainController.closeWindow(cancelButton);

    }

}
