package com.example.tuitionmanagergui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

/**
 * The TuitionManagerMain class launches the front end of the GUI system.
 * @author Camryn Harrell, Varsha Balaji
 */
public class TuitionManagerMain extends Application {

    /**
     * Presents the stage for the Tuition Manager GUI.
     * @param primaryStage main stage for GUI.
     * @throws Exception catches any exceptions.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tuition Manager");
        //load fxml class into main file
        Parent root = FXMLLoader.
                load(getClass().getResource(
                        "tuitionmanager.fxml"
                ));
        Scene home = new Scene(root);
        primaryStage.setScene(home);
        primaryStage.show();
    }

    /**
     * Launches application.
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}