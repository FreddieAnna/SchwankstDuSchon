package com.core.UI.Screens;

import com.core.UI.WebcamManager;
import com.github.sarxos.webcam.Webcam;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * Created by siefker on 04.11.2015.
 */
public class WebcamTest2ScreenFiller {

    public void fillPaneWithWebcamTest2ScreenComponents(final BorderPane root) {

        root.getChildren().clear();

        final WebcamManager webcamManager = new WebcamManager();
        Webcam webcam = webcamManager.displayWebcamAtPosition(root, "CENTER");

        Button button = new Button();
        button.setText("To StartScreen");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                webcamManager.closeWebcam();
                /*StartScreenFiller startScreenFiller = new StartScreenFiller();
                startScreenFiller.fillPaneWithStartScreenComponents(root);*/
            }
        });

        root.setCenter(button);
    }
}
