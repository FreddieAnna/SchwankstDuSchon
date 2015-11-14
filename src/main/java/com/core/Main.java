package com.core;

import com.core.UI.WebcamEvaluation;
import com.core.UI.WebcamManager;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by siefker on 04.11.2015.
 */
public class Main extends Application {

    BorderPane root = new BorderPane();

    BorderPane startScreen = fillPaneWithStartScreenComponents();
    BorderPane webcamTestOneInformationScreen = fillPaneWithWebcamTest1InformationScreenComponents();
    BorderPane webcamTestOneCircleOneScreen = initiateCircle(1);
    BorderPane webcamTestOneCircleTwoScreen = initiateCircle(2);
    BorderPane webcamTestOneCircleThreeScreen = initiateCircle(3);
    BorderPane webcamTestOneCircleFourScreen = initiateCircle(4);
    BorderPane webcamTestTwoInformationScreen = fillPaneWithWebcamTest2InformationScreenComponents();
    BorderPane webcamTestTwoPictureOneScreen = showPicture(1);
    BorderPane webcamTestTwoPictureTwoScreen = showPicture(2);
    BorderPane webcamTestTwoPictureThreeScreen = showPicture(3);
    BorderPane webcamTestTwoPictureFourScreen = showPicture(4);
    BorderPane webcamTestTwoPictureFiveScreen = showPicture(5);
    BorderPane pickedCorrectAnswerScreen = fillPaneWithCorrectAnswerScreenComponents();
    BorderPane pickedWrongAnswerScreen = fillPaneWithWrongAnswerScreenComponents();

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Schwankst du schon?");
        root.setCenter(startScreen);
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);
        primaryStage.show();

        Webcam webcam = Webcam.getDefault();
        webcam.close();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webcam.open();
    }

    public BorderPane fillPaneWithStartScreenComponents() {

        final BorderPane startScreen = new BorderPane();

        Button button = new Button();
        button.setText("To WebcamTest Screen");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                root.getChildren().remove(startScreen);
                root.setCenter(webcamTestTwoInformationScreen);

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    root.getChildren().remove(webcamTestTwoInformationScreen);
                                    /*try {
                                        initiateCircleTestPhaseOne();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }*/
                                    root.setCenter(webcamTestTwoPictureOneScreen);
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };

                new Thread(r).start();
            }
        });

        startScreen.setCenter(button);

        return startScreen;
    }

    /*
     * @Author Jonas Siefker
     */

    public BorderPane fillPaneWithWebcamTest1InformationScreenComponents() {

        BorderPane webcamTest1InformationScreen = new BorderPane();

        Text text = new Text();
        text.setFont(new Font(36));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText(" Im nächsten Test werden farbige Kreise in verschiedenen Bildschirmecken auftauchen.\n Strecke deinen Arm schnell in die Richtung einer Ecke aus, in der ein Kreis erscheint!");

        /*
         * Informations-Text zum Kreis-Test auf dem Bildschirm anzeigen
         */

        webcamTest1InformationScreen.setCenter(text);

        return webcamTest1InformationScreen;
    }

    /*
     * @Author Jonas Siefker
     */

    private void initiateCircleTestPhaseOne() throws IOException {

        root.setCenter(webcamTestOneCircleOneScreen);

        Webcam webcam = Webcam.getDefault();

        ImageIO.write(webcam.getImage(), "PNG", new File("src/main/java/com/core/UI/Pictures/1.png"));
        File file1 = new File("src/main/java/com/core/UI/Pictures/1.png");
        FileInputStream fis1 = new FileInputStream(file1);
        final BufferedImage image1 = ImageIO.read(fis1);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        WebcamEvaluation webcamEvaluation = new WebcamEvaluation();
                        Boolean detectedMotion = null;
                        try {
                            detectedMotion = webcamEvaluation.checkIfMotionDetectedForGivenTest("CIRCLE_TEST_CIRCLE_TOP_LEFT", image1);
                        } catch (IOException e) {
                            e.printStackTrace();
                            detectedMotion = false;
                        }

                        if (detectedMotion) {

                            root.getChildren().remove(webcamTestOneCircleOneScreen);
                            root.setCenter(pickedCorrectAnswerScreen);

                            Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            root.getChildren().remove(pickedCorrectAnswerScreen);
                                            try {
                                                initiateCircleTestPhaseTwo();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            };

                            new Thread(r).start();
                        }
                        else {
                            root.getChildren().remove(webcamTestOneCircleOneScreen);
                            root.setCenter(pickedWrongAnswerScreen);

                            final Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            root.getChildren().remove(pickedWrongAnswerScreen);
                                            try {
                                                initiateCircleTestPhaseTwo();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            };

                            new Thread(r).start();
                        }
                    }
                });
            }
        };

        new Thread(r).start();
    }

    /*
     * @Author Jonas Siefker
     */

    private void initiateCircleTestPhaseTwo() throws IOException {

        root.setCenter(webcamTestOneCircleTwoScreen);

        Webcam webcam = Webcam.getDefault();

        ImageIO.write(webcam.getImage(), "PNG", new File("src/main/java/com/core/UI/Pictures/1.png"));
        File file1 = new File("src/main/java/com/core/UI/Pictures/1.png");
        FileInputStream fis1 = new FileInputStream(file1);
        final BufferedImage image1 = ImageIO.read(fis1);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        WebcamEvaluation webcamEvaluation = new WebcamEvaluation();
                        Boolean detectedMotion = null;
                        try {
                            detectedMotion = webcamEvaluation.checkIfMotionDetectedForGivenTest("CIRCLE_TEST_CIRCLE_BOTTOM_RIGHT", image1);
                        } catch (IOException e) {
                            e.printStackTrace();
                            detectedMotion = false;
                        }

                        if (detectedMotion) {

                            root.getChildren().remove(webcamTestOneCircleTwoScreen);
                            root.setCenter(pickedCorrectAnswerScreen);

                            Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            root.getChildren().remove(pickedCorrectAnswerScreen);
                                            try {
                                                initiateCircleTestPhaseThree();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            };

                            new Thread(r).start();
                        }
                        else {
                            root.getChildren().remove(webcamTestOneCircleTwoScreen);
                            root.setCenter(pickedWrongAnswerScreen);

                            final Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            root.getChildren().remove(pickedWrongAnswerScreen);
                                            try {
                                                initiateCircleTestPhaseThree();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            };

                            new Thread(r).start();
                        }
                    }
                });
            }
        };

        new Thread(r).start();
    }

    /*
     * @Author Jonas Siefker
     */

    private void initiateCircleTestPhaseThree() throws IOException {

        root.setCenter(webcamTestOneCircleThreeScreen);

        Webcam webcam = Webcam.getDefault();

        ImageIO.write(webcam.getImage(), "PNG", new File("src/main/java/com/core/UI/Pictures/1.png"));
        File file1 = new File("src/main/java/com/core/UI/Pictures/1.png");
        FileInputStream fis1 = new FileInputStream(file1);
        final BufferedImage image1 = ImageIO.read(fis1);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        WebcamEvaluation webcamEvaluation = new WebcamEvaluation();
                        Boolean detectedMotion = null;
                        try {
                            detectedMotion = webcamEvaluation.checkIfMotionDetectedForGivenTest("CIRCLE_TEST_CIRCLE_TOP_RIGHT", image1);
                        } catch (IOException e) {
                            e.printStackTrace();
                            detectedMotion = false;
                        }

                        if (detectedMotion) {

                            root.getChildren().remove(webcamTestOneCircleThreeScreen);
                            root.setCenter(pickedCorrectAnswerScreen);

                            Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            root.getChildren().remove(pickedCorrectAnswerScreen);
                                            try {
                                                initiateCircleTestPhaseFour();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            };

                            new Thread(r).start();
                        }
                        else {
                            root.getChildren().remove(webcamTestOneCircleThreeScreen);
                            root.setCenter(pickedWrongAnswerScreen);

                            final Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            root.getChildren().remove(pickedWrongAnswerScreen);
                                            try {
                                                initiateCircleTestPhaseFour();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            };

                            new Thread(r).start();
                        }
                    }
                });
            }
        };

        new Thread(r).start();
    }

    /*
     * @Author Jonas Siefker
     */

    private void initiateCircleTestPhaseFour() throws IOException {

        root.setCenter(webcamTestOneCircleFourScreen);

        Webcam webcam = Webcam.getDefault();

        ImageIO.write(webcam.getImage(), "PNG", new File("src/main/java/com/core/UI/Pictures/1.png"));
        File file1 = new File("src/main/java/com/core/UI/Pictures/1.png");
        FileInputStream fis1 = new FileInputStream(file1);
        final BufferedImage image1 = ImageIO.read(fis1);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        WebcamEvaluation webcamEvaluation = new WebcamEvaluation();
                        Boolean detectedMotion = null;
                        try {
                            detectedMotion = webcamEvaluation.checkIfMotionDetectedForGivenTest("CIRCLE_TEST_CIRCLE_BOTTOM_LEFT", image1);
                        } catch (IOException e) {
                            e.printStackTrace();
                            detectedMotion = false;
                        }

                        if (detectedMotion) {

                            root.getChildren().remove(webcamTestOneCircleFourScreen);
                            root.setCenter(pickedCorrectAnswerScreen);

                            Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            root.getChildren().remove(pickedCorrectAnswerScreen);
                                            try {
                                                initiateCircleTestPhaseFive();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            };

                            new Thread(r).start();
                        }
                        else {
                            root.getChildren().remove(webcamTestOneCircleFourScreen);
                            root.setCenter(pickedWrongAnswerScreen);

                            final Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            root.getChildren().remove(pickedWrongAnswerScreen);
                                            try {
                                                initiateCircleTestPhaseFive();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            };

                            new Thread(r).start();
                        }
                    }
                });
            }
        };

        new Thread(r).start();
    }

    private void initiateCircleTestPhaseFive() throws IOException {

        root.setCenter(webcamTestOneCircleThreeScreen);

        Webcam webcam = Webcam.getDefault();

        ImageIO.write(webcam.getImage(), "PNG", new File("src/main/java/com/core/UI/Pictures/1.png"));
        File file1 = new File("src/main/java/com/core/UI/Pictures/1.png");
        FileInputStream fis1 = new FileInputStream(file1);
        final BufferedImage image1 = ImageIO.read(fis1);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        WebcamEvaluation webcamEvaluation = new WebcamEvaluation();
                        Boolean detectedMotion = null;
                        try {
                            detectedMotion = webcamEvaluation.checkIfMotionDetectedForGivenTest("CIRCLE_TEST_CIRCLE_TOP_RIGHT", image1);
                        } catch (IOException e) {
                            e.printStackTrace();
                            detectedMotion = false;
                        }

                        if (detectedMotion) {

                            root.getChildren().remove(webcamTestOneCircleThreeScreen);
                            root.setCenter(pickedCorrectAnswerScreen);

                            Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            root.getChildren().remove(pickedCorrectAnswerScreen);
                                            initiatePictureTest();
                                        }
                                    });
                                }
                            };

                            new Thread(r).start();
                        }
                        else {
                            root.getChildren().remove(webcamTestOneCircleFourScreen);
                            root.setCenter(pickedWrongAnswerScreen);

                            final Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(1500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            root.getChildren().remove(pickedWrongAnswerScreen);
                                            initiatePictureTest();
                                        }
                                    });
                                }
                            };

                            new Thread(r).start();
                        }
                    }
                });
            }
        };

        new Thread(r).start();
    }

    /*
     * @Author Jonas Siefker
     * Methode die mithilfe einer übergebenen Nummer den richtigen Kreis auswählt
     */

    private BorderPane initiateCircle(int circleNumber) {
        switch (circleNumber) {

            case 1: return showCircleInCorner("TOP", "LEFT");
            case 2: return showCircleInCorner("BOTTOM", "RIGHT");
            case 3: return showCircleInCorner("TOP", "RIGHT");
            case 4: return showCircleInCorner("BOTTOM", "LEFT");
            default: break;
        }
        return new BorderPane();
    }

    /*
     * @Author Jonas Siefker
     * Methode die zwei positions-Strings nutzt um den entsprechenden Kreis auf dem Bildschirm anzuzeigen
     */

    private BorderPane showCircleInCorner(String posVertical, String posHorizontal) {

        BorderPane circleInCornerScreen = new BorderPane();

        WebcamManager webcamManager = new WebcamManager();
        Webcam webcam = webcamManager.displayWebcamAtPosition(circleInCornerScreen, "CENTER");

        Image image = new Image("file:src/main/java/com/core/UI/Graphics/kreis_blau.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        HBox hBox = new HBox();
        hBox.setMaxHeight(250);
        hBox.getChildren().add(imageView);

        if (posHorizontal.equals("LEFT") && posVertical.equals("TOP")) {

            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setMargin(imageView, new Insets(50, 0, 0, 50));
            circleInCornerScreen.setTop(hBox);
            return circleInCornerScreen;
        }
        else if (posHorizontal.equals("RIGHT") && posVertical.equals("TOP")) {

            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setMargin(imageView, new Insets(50, 50, 0, 0));
            circleInCornerScreen.setTop(hBox);
            return circleInCornerScreen;
        }
        else if (posHorizontal.equals("LEFT") && posVertical.equals("BOTTOM")) {

            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setMargin(imageView, new Insets(0, 0, 50, 50));
            circleInCornerScreen.setBottom(hBox);
            return circleInCornerScreen;
        }
        else if (posHorizontal.equals("RIGHT") && posVertical.equals("BOTTOM")) {

            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setMargin(imageView, new Insets(0, 50, 50, 0));
            circleInCornerScreen.setBottom(hBox);
            return circleInCornerScreen;
        }
        else {

        }
        return circleInCornerScreen;
    }

    /*
     * @Author Jonas Siefker
     */

    private BorderPane fillPaneWithCorrectAnswerScreenComponents() {

        BorderPane correctAnswerScreen = new BorderPane();

        Text text = new Text();
        text.setFont(new Font(75));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText("Sehr gut!");

        correctAnswerScreen.setCenter(text);

        return correctAnswerScreen;
    }

    /*
     * @Author Jonas Siefker
     */

    private BorderPane fillPaneWithWrongAnswerScreenComponents() {

        BorderPane wrongAnswerScreen = new BorderPane();

        Text text = new Text();
        text.setFont(new Font(75));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText("Leider nicht...");

        wrongAnswerScreen.setCenter(text);

        return wrongAnswerScreen;
    }

    private BorderPane fillPaneWithWebcamTest2InformationScreenComponents() {

        BorderPane webcamTest2InformationScreen = new BorderPane();

        Text text = new Text();
        text.setFont(new Font(36));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText("Merke dir das Bild das du unten siehst! \n Du wirst als nächstes eine Abfolge von Bildern sehen. \n Erkennst du das untenstehende wieder, \nstrecke deine Arme in die beiden oberen Bildschirmecken aus!");

        /*
         * Informations-Text zum Reaktions-Test auf dem Bildschirm anzeigen
         */

        Image image = new Image("file:src/main/java/com/core/UI/Graphics/Bild_4.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        HBox hBox = new HBox();
        hBox.setMaxHeight(640);
        hBox.setMaxWidth(568);
        hBox.getChildren().add(imageView);

        webcamTest2InformationScreen.setTop(text);
        webcamTest2InformationScreen.setCenter(hBox);

        return webcamTest2InformationScreen;
    }

    /*
     * @Author Jonas Siefker
     */

    private void initiatePictureTest() {

        root.setCenter(webcamTestTwoInformationScreen);
    }

    private BorderPane showPicture(int pictureNumber) {

        BorderPane pictureScreen = new BorderPane();

        Image image;

        switch (pictureNumber) {
            case(1): image = new Image("file:src/main/java/com/core/UI/Graphics/Bild_1.jpg"); break;
            case(2): image = new Image("file:src/main/java/com/core/UI/Graphics/Bild_2.png"); break;
            case(3): image = new Image("file:src/main/java/com/core/UI/Graphics/Bild_3.jpg"); break;
            case(4): image = new Image("file:src/main/java/com/core/UI/Graphics/Bild_4.png"); break;
            case(5): image = new Image("file:src/main/java/com/core/UI/Graphics/Bild_5.png"); break;
            default: image = null;
        }

        ImageView imageView = new ImageView();
        imageView.setImage(image);

        HBox hBox = new HBox();
        hBox.setMaxHeight(640);
        hBox.setMaxWidth(568);
        hBox.getChildren().add(imageView);

        pictureScreen.setCenter(hBox);

        WebcamManager webcamManager = new WebcamManager();
        Webcam webcam = webcamManager.displayWebcamAtPosition(pictureScreen, "BOTTOM_RIGHT");

        return pictureScreen;
    }
}
