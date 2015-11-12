package com.core.UI;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
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
public class WebcamManager {

    Webcam webcam = Webcam.getDefault();
    private BufferedImage grabbedImage;
    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();
    private ImageView imgWebCamCapturedImage;

    public Webcam displayWebcamAtPosition(BorderPane webcamTestPane, String position) {

        BorderPane webCamPane = new BorderPane();
        webCamPane.setStyle("-fx-background-color: #ccc;");
        imgWebCamCapturedImage = new ImageView();

        imgWebCamCapturedImage.setFitWidth(427);
        imgWebCamCapturedImage.setFitHeight(240);

        imgWebCamCapturedImage.setScaleX(-1);

        HBox hBox = new HBox();
        hBox.setPrefWidth(427);
        hBox.setMaxHeight(240);
        if (position.equals("BOTTOM_RIGHT")) {
            hBox.setAlignment(Pos.BOTTOM_RIGHT);
        }
        else if (position.equals("CENTER")) {
            hBox.setAlignment(Pos.CENTER);
        }
        else {
            hBox.setAlignment(Pos.BOTTOM_RIGHT);
        }
        hBox.getChildren().add(webCamPane);

        webCamPane.setCenter(imgWebCamCapturedImage);
        if (position.equals("BOTTOM_RIGHT")) {
            webcamTestPane.setBottom(hBox);
        }
        else if (position.equals("CENTER")) {
            webcamTestPane.setCenter(hBox);
        }
        else {
            webcamTestPane.setBottom(hBox);
        }

        webcam.open();

        startWebCamStream();

        return webcam;
    }

    public void closeWebcam() {

        Webcam.getDefault().close();
    }

    protected void startWebCamStream() {

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
