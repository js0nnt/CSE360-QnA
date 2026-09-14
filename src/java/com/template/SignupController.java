package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignupController
{
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label errorLabel;

    @FXML
    private void handleSignup() throws IOException
    {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (username.isEmpty() || password.isEmpty())
        {
            errorLabel.setText("Please enter a username and password.");
            return;
        }
        if (!password.equals(confirmPassword))
        {
            errorLabel.setText("Passwords do not match.");
            return;
        }
        if (UserStore.usernameExists(username))
        {
            errorLabel.setText("That username is already taken.");
            return;
        }

        UserStore.registerUser(username, password);
        UserSession.setUsername(username);
        Main.setRoot("home");
    }

    @FXML
    private void goToLogin() throws IOException
    {
        Main.setRoot("login");
    }
}
