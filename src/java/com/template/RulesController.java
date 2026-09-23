package com.template;

import javafx.fxml.FXML;
import javafx.scene.Parent;

import java.io.IOException;

public class RulesController
{
    private static Parent returnRoot;

    public static void open() throws IOException
    {
        // Keep the current screen itself so going back does not clear a half-filled form.
        returnRoot = Main.getRoot();
        Main.setRoot("rules");
    }

    @FXML
    private void goBack()
    {
        Main.setRoot(returnRoot);
    }
}
