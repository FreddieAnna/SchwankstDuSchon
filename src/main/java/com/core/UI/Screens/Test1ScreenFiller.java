package com.core.UI.Screens;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;

/**
 * Created by Neuner on 03.11.2015.
 */

@SuppressWarnings("restriction")
public class Test1ScreenFiller {

	public static void fillPaneWithTest1ScreenComponents(final BorderPane root) {
		
//		Button button = new Button();
//        button.setText("to StartScreen");
//        button.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                StartScreenFiller.fillPaneWithStartScreenComponents(root);
//            }
//        });
		

        Image img = new Image("file:img/Unbenannt.png");
        ImageView imgView = new ImageView();
        imgView.setImage(img);
        root.getChildren().clear();
        root.setCenter(imgView);
	}
}