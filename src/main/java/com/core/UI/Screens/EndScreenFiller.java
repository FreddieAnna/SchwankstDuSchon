package com.core.UI.Screens;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Created by siefker on 27.10.2015.
 */
public class EndScreenFiller {

    public static void fillPaneWithEndScreenComponents(final Pane root) {

        Button button = new Button();
        button.setText("to StartScreen");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StartScreenFiller.fillPaneWithStartScreenComponents(root);
            }
        });

        root.getChildren().clear();
        root.getChildren().add(button);
    }
}
