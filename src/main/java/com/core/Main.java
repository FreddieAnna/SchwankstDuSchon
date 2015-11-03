package com.core;

import com.core.UI.Screens.StartScreenFiller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by siefker on 14.09.2015.
 */
@SuppressWarnings("restriction")
public class Main extends Application {

    public static void main(String args[]) {
        launch(args);
        //Initiator.initiateProgram();
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Test");
        BorderPane root = new BorderPane();
        //Pane root = new Pane();
        StartScreenFiller.fillPaneWithStartScreenComponents(root);
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}
