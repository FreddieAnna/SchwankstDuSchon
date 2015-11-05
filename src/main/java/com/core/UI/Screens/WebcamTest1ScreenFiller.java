package com.core.UI.Screens;

import com.core.UI.UI;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamImageTransformer;
import com.github.sarxos.webcam.WebcamPanel;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by siefker on 04.11.2015.
 */
public class WebcamTest1ScreenFiller {

    public static void fillPaneWithWebcamTest1ScreenComponents(final BorderPane root) {

        root.getChildren().clear();

        UI.displayWebcam(root);

        Button button = new Button();
        button.setText("To WebcamTest2");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UI.closeWebcam();
                WebcamTest2ScreenFiller.fillPaneWithWebcamTest2ScreenComponents(root);
            }
        });

        root.setCenter(button);
    }
}
