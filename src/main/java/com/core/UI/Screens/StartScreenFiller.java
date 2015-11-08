package com.core.UI.Screens;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * Created by siefker on 14.09.2015.
 */
@SuppressWarnings("restriction")
public class StartScreenFiller {

    public static void fillPaneWithStartScreenComponents(final BorderPane root) {

        Button button = new Button();
        button.setText("to Test2");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
              Test2ScreenFiller.fillPaneWithTest2ScreenComponents(root);
            }
        });

        root.getChildren().clear();
        root.setCenter(button);
    }
}
