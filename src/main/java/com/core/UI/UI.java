package com.core.UI;

import com.github.sarxos.webcam.Webcam;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.awt.image.BufferedImage;

/**
 * Created by siefker on 04.11.2015.
 */
public class UI {

    static Webcam webcam = null;
    private static BufferedImage grabbedImage;
    private static ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();
    private static ImageView imgWebCamCapturedImage;

    public static void displayWebcam(BorderPane root) {

        BorderPane webCamPane = new BorderPane();
        webCamPane.setStyle("-fx-background-color: #ccc;");
        imgWebCamCapturedImage = new ImageView();

        imgWebCamCapturedImage.setFitWidth(427);
        imgWebCamCapturedImage.setFitHeight(240);

        imgWebCamCapturedImage.setScaleX(-1);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(webCamPane);

        webCamPane.setCenter(imgWebCamCapturedImage);
        root.setBottom(hBox);

        webcam = Webcam.getDefault();
        webcam.open();

        startWebCamStream();
    }

    public static void closeWebcam() {

        webcam.close();
    }

    protected static void startWebCamStream() {

        final boolean stopCamera;

        stopCamera = false;

        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {

                while (!stopCamera) {
                    try {
                        if ((grabbedImage = webcam.getImage()) != null) {

                            Platform.runLater(new Runnable() {

                                @Override
                                public void run() {
                                    Image mainiamge = SwingFXUtils.toFXImage(grabbedImage, null);
                                    imageProperty.set(mainiamge);
                                }
                            });

                            grabbedImage.flush();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                return null;
            }
        };

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
        imgWebCamCapturedImage.imageProperty().bind(imageProperty);

    }
}
