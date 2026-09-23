package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class AdminController
{
    @FXML private Label titleLabel;
    @FXML private Label messageLabel;

    @FXML
    private void initialize()
    {
        if (!UserSession.isAdmin())
        {
            throw new IllegalStateException("The admin page requires an admin session.");
        }

        titleLabel.setText("Welcome back, " + UserSession.getUsername() + "!");
        messageLabel.setText("You are signed in to your admin account.");
    }

    @FXML
    private void goToHome() throws IOException
    {
        Main.setRoot("home");
    }

    @FXML
    private void handleLogout() throws IOException
    {
        UserSession.clear();
        Main.setRoot("login");
    }
}
