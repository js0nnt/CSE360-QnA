package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class HomeController
{
    @FXML private Label welcomeLabel;
    @FXML private Button adminButton;

    @FXML
    private void initialize()
    {
        welcomeLabel.setText("Welcome, " + UserSession.getUsername() + "!");
        adminButton.setVisible(UserSession.isAdmin());
        adminButton.setManaged(UserSession.isAdmin());
    }

    @FXML
    private void openAdminPage() throws IOException
    {
        if (UserSession.isAdmin())
        {
            Main.setRoot("admin");
        }
    }

    @FXML
    private void handleLogout() throws IOException
    {
        UserSession.clear();
        Main.setRoot("login");
    }
}
