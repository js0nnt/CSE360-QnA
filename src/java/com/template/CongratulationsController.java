package com.template;

import javafx.fxml.FXML;

import java.io.IOException;

public class CongratulationsController
{
    @FXML
    private void handleAccept() throws IOException
    {
        // The first account is already saved as admin; logging in again opens the admin page.
        Main.setRoot("login");
    }
}
