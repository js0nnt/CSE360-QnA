package com.template;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application
{
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, SQLException
    {
        QuestionStore.initSchema();

        scene = new Scene(loadFxml("login"), 600, 400);

        stage.setTitle("QnA Mock");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxmlFile) throws IOException
    {
        scene.setRoot(loadFxml(fxmlFile));
    }

    private static Parent loadFxml(String fxmlFile) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlFile + ".fxml"));
        return loader.load();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
