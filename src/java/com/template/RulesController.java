package com.template;

import javafx.fxml.FXML;

import java.io.IOException;

public class RulesController
{
    private static String returnPage = "login";

    public static void open(String fromPage) throws IOException
    {
        returnPage = fromPage;
        Main.setRoot("rules");
    }

    @FXML
    private void goBack() throws IOException
    {
        Main.setRoot(returnPage);
    }
}
