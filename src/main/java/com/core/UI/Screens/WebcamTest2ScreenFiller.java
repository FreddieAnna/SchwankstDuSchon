package com.core.UI.Screens;

import com.core.UI.UI;
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

        UI.displayWebcam(root);

        Button button = new Button();
        button.setText("To StartScreen");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UI.closeWebcam();
                StartScreenFiller startScreenFiller = new StartScreenFiller();
                startScreenFiller.fillPaneWithStartScreenComponents(root);
            }
        });

        root.setCenter(button);
    }
}
