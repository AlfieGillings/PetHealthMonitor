package com.example.assignment;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class LoginMenuController {

    @FXML
    private Button exitButton;

    @FXML
    void signUp() throws IOException {

        MainController.openWindow("SignupPopup.fxml", "Sign Up Menu", 400, 250);
        MainController.closeWindow(exitButton);

    }

    @FXML
    void logIn() throws IOException {

        MainController.openWindow("LoginPopup.fxml", "Login Menu", 400, 250);
        MainController.closeWindow(exitButton);

    }

    @FXML
    void exit() {

        MainController.closeWindow(exitButton);

    }

}
