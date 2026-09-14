package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HomeController
{
    @FXML private Label welcomeLabel;

    @FXML
    private void initialize()
    {
        welcomeLabel.setText("Welcome, " + UserSession.getUsername() + "!");
    }

    @FXML
    private void handleLogout() throws IOException
    {
        UserSession.setUsername(null);
        Main.setRoot("login");
    }
}
