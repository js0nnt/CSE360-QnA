package com.template;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController
{
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    private void handleLogin() throws IOException
    {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty())
        {
            errorLabel.setText("Please enter a username and password.");
            return;
        }

        if (UserStore.verifyLogin(username, password))
        {
            UserSession.start(username, UserStore.getRole(username));
            Main.setRoot(UserSession.isAdmin() ? "admin" : "home");
        }
        else
        {
            errorLabel.setText("Incorrect username or password.");
        }
    }
    @FXML
    private void handleForgottenPW() throws IOException
    {
    	System.out.println("TBA - this should do something in the future!");
    }

    @FXML
    private void goToSignup() throws IOException
    {
        Main.setRoot("signup");
    }
}
