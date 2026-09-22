package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

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

        if ("admin".equals(role))
        {
            Window owner = usernameField.getScene().getWindow();
            UserSession.start(username, role, true);
            Main.setRoot("admin");

            Alert congratulations = new Alert(Alert.AlertType.INFORMATION);
            congratulations.initOwner(owner);
            congratulations.setTitle("Admin account");
            congratulations.setHeaderText("Congratulations on becoming admin!");
            congratulations.setContentText("You created the first account.");
            congratulations.showAndWait();
        }
        else
        {
            Main.setRoot("login");
        }
    }

    @FXML
    private void goToLogin() throws IOException
    {
        Main.setRoot("login");
    }
}
