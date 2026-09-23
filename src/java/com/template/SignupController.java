package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignupController
{
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField usernameField;
    @FXML private TextField phoneField;
    @FXML private TextField dateOfBirthField;
    @FXML private ComboBox<String> favoriteColorBox;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private CheckBox agreementBox;
    @FXML private Label errorLabel;

    @FXML
    private void handleSignup() throws IOException
    {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        UserProfile profile = new UserProfile(firstNameField.getText(), lastNameField.getText(),
                emailField.getText(), phoneField.getText(), dateOfBirthField.getText().trim(),
                favoriteColorBox.getValue());

        String role;
        try
        {
            UserStore.validateRegistration(username, password, profile);
            if (!password.equals(confirmPasswordField.getText()))
            {
                throw new IllegalArgumentException("Passwords do not match.");
            }
            if (!agreementBox.isSelected())
            {
                throw new IllegalArgumentException("Please agree to the rules and conditions.");
            }
            role = UserStore.registerUser(username, password, profile);
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
        RulesController.open();
    }

    @FXML
    private void goToLogin() throws IOException
    {
        Main.setRoot("login");
    }
}
