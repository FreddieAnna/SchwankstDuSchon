package com.core.UI.Screens;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Created by siefker on 14.09.2015.
 */
@SuppressWarnings("restriction")
public class StartScreenFiller {

    public void fillPaneWithStartScreenComponents(final BorderPane root) {

        Button button = new Button();
        button.setText("To WebcamTest Screen");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    WebcamTest1ScreenFiller webcamTest1ScreenFiller = new WebcamTest1ScreenFiller();
                    webcamTest1ScreenFiller.fillPaneWithWebcamTest1ScreenComponents(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        root.getChildren().clear();
        root.setCenter(button);
    }
}
