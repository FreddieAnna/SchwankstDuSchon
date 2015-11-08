package com.core.UI.Screens;

import com.core.UI.WebcamManager;
import com.github.sarxos.webcam.Webcam;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.*;
import javafx.scene.text.Font;

import java.io.IOException;

/**
 * Created by siefker on 04.11.2015.
 */
public class WebcamTest1ScreenFiller {

    public void fillPaneWithWebcamTest1ScreenComponents(final BorderPane root) throws IOException {

        root.getChildren().clear();

        Text text = new Text();
        text.setFont(new Font(36));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText(" Im nächsten Test werden farbige Kreise in verschiedenen Bildschirmecken auftauchen.\n Strecke deinen Arm schnell in die Richtung einer Ecke aus, in der ein Kreis erscheint!");

        /*
         * Informations-Text auf dem Bildschirm anzeigen
         */

        HBox bottomBox = new HBox();
        bottomBox.setSpacing(50);
        bottomBox.setAlignment(Pos.CENTER);
        root.setBottom(bottomBox);
        root.setCenter(text);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            initiateCircleTest(root);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(r).start();
    }

    private void initiateCircleTest(BorderPane root) {

        initiateCircle(root, 1);

        WebcamManager webcamManager = new WebcamManager();
        Webcam webcam = webcamManager.displayWebcamAtPosition(root, "CENTER");

        long currentTime = System.currentTimeMillis();
        long end = currentTime+3000;
        boolean pickedCorrectAnswer = false;

        while (currentTime < end) {

            if (/* Spieler greift in richtige Ecke*/ false) {

                pickedCorrectAnswer = true;
                        /* show 'Sehr Gut!' */
                break;
            }

            else if (/* Spieler greift in falsche Ecke*/ false) {

                        /* show 'Leider Nicht...' */
                break;
            }

            currentTime = System.currentTimeMillis();
        }

        if (pickedCorrectAnswer = false) {

                    /* show 'Zu Langsam!' */
        }

        //initiateCircle(root, 4);
    }

    /*
     * Methode die mithilfe einer übergebenen Nummer den richtigen Kreis auswählt
     */

    private void initiateCircle(final BorderPane root, int circleNumber) {
        root.getChildren().clear();

        switch (circleNumber) {

            case 1: showCircleInCorner(root, "TOP", "LEFT"); break;
            case 2: showCircleInCorner(root, "BOTTOM", "RIGHT"); break;
            case 3: showCircleInCorner(root, "TOP", "RIGHT"); break;
            case 4: showCircleInCorner(root, "BOTTOM", "LEFT"); break;
            default: break;
        }
    }

    /*
     * Methode die zwei positions-Strings nutzt um den entsprechenden Kreis auf dem Bildschirm anzuzeigen
     */

    private void showCircleInCorner(BorderPane root, String posVertical, String posHorizontal) {

        Image image = new Image("file:///C:/Users/Nutzer/Desktop/Unizeug/Software Engineering/SchwankstDuSchon/src/main/java/com/core/WebcamManager/Graphics/kreis_blau.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        HBox hBox = new HBox();
        hBox.getChildren().add(imageView);

        if (posHorizontal.equals("LEFT") && posVertical.equals("TOP")) {

            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setMargin(imageView, new Insets(50,0,0,50));
            root.setTop(hBox);
        }
        else if (posHorizontal.equals("RIGHT") && posVertical.equals("TOP")) {

            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setMargin(imageView, new Insets(50,50,0,0));
            root.setTop(hBox);
        }
        else if (posHorizontal.equals("LEFT") && posVertical.equals("BOTTOM")) {

            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setMargin(imageView, new Insets(0,0,50,50));
            root.setBottom(hBox);
        }
        else if (posHorizontal.equals("RIGHT") && posVertical.equals("BOTTOM")) {

            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setMargin(imageView, new Insets(0,50,50,0));
            root.setBottom(hBox);
        }
        else {

        }
    }
}
