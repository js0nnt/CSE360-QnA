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
        String role;
        try
        {
            role = UserStore.registerUser(username, password);
        }
        catch (IllegalArgumentException exception)
        {
            errorLabel.setText(exception.getMessage());
            return;
        }

        Main.setRoot("admin".equals(role) ? "congratulations" : "login");
    }

    @FXML
    private void openRules() throws IOException
    {
        RulesController.open("signup");
    }

    @FXML
    private void goToLogin() throws IOException
    {
        Main.setRoot("login");
    }
}
