package com.core.UI.Screens;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Created by siefker on 14.09.2015.
 */
public class StartScreenFiller {

    public static void fillPaneWithStartScreenComponents(final Pane root) {

        Button button = new Button();
        button.setText("to EndScreen");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EndScreenFiller.fillPaneWithEndScreenComponents(root);
            }
        });

        root.getChildren().clear();
        root.getChildren().add(button);
    }
}
