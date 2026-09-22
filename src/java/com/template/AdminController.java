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

        String username = UserSession.getUsername();
        if (UserSession.consumeAdminCongratulations())
        {
            titleLabel.setText("Congratulations, " + username + "!");
            messageLabel.setText("You created the first account, so you are now the admin of Q&A.");
        }
        else
        {
            titleLabel.setText("Welcome back, " + username + "!");
            messageLabel.setText("You are signed in to your admin account.");
        }
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
