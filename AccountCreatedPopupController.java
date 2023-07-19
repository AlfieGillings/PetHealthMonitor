package com.example.assignment;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;

public class AccountCreatedPopupController {

    @FXML
    private Button doneButton;

    @FXML
    void done() throws IOException {

        MainController.openWindow("LoginMenu.fxml", "Pet Health Monitor", 400, 250);
        MainController.closeWindow(doneButton);

    }

}
