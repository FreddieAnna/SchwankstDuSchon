package com.core;

import com.core.UI.Highscore.HighscoreManager;
import com.core.UI.WebcamEvaluation;
import com.core.UI.WebcamManager;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by siefker on 04.11.2015.
 */
public class Main extends Application {

    public static final int webcamTestRandomizer = (int) (System.currentTimeMillis()%3);
    public static final int mouseTestRandomizer = (int) (System.currentTimeMillis()%2);

    final HighscoreManager hm2 = new HighscoreManager();
    public static String nickname;
    public static int score;

    public static Boolean choseRightPicture = false;
    public static Boolean choseWrongPicture = false;

    BorderPane root = new BorderPane();

    BorderPane registerScreen = fillPaneWithRegisterScreenComponents();
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
    BorderPane webcamTestThreeInformationScreen = fillPaneWithWebcamTestThreeInformationScreenComponents();
    BorderPane webcamTestThreeScreen = showBalanceCamera();
    BorderPane webcamTestThreeTwoScreen = continueBalanceCamera();
    BorderPane explanationClockTest = fillPaneWithExplanationClockTest();
    BorderPane clockTestClock1 = fillPaneWithClock1();
    BorderPane clockTestClock2 = fillPaneWithClock2();
    BorderPane clockTestClock3 = fillPaneWithClock3();
    BorderPane explanationCalculateTest = fillPaneWithExplanationCalculateTest();
    BorderPane calculateTestCalculate1 = fillPaneWithCalculate1();
    BorderPane calculateTestCalculate2 = fillPaneWithCalculate2();
    BorderPane ergebnisauswertung = fillPaneWithErgebnisauswertungComponents();
    BorderPane ranglisteScreen = fillPaneWithRanglistenScreenComponents();
    BorderPane pickedCorrectAnswerScreen = fillPaneWithCorrectAnswerScreenComponents();
    BorderPane pickedCorrectAnswerScreenBalanceTest = fillPaneWithCorrectAnswerScreenComponentsBalanceTest();
    BorderPane pickedWrongAnswerScreen = fillPaneWithWrongAnswerScreenComponents();
    BorderPane warningScreen = fillPaneWithWarningScreenComponents();

    public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Schwankst du schon?");
        primaryStage.getIcons().add(new Image("file:img/icon.png"));
        root.setCenter(registerScreen);
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);
        primaryStage.show();

        Webcam webcam = Webcam.getDefault();
        webcam.close();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        webcam.open();
    }

    /**
     * Thomas Klaus; Registrierungsscreen anzeigen
     */
    private BorderPane fillPaneWithRegisterScreenComponents() {

        final BorderPane RegisterScreen = new BorderPane();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));
        Text scenetitle = new Text("Willkommen");
        scenetitle.setFont(Font.font("Arial", FontWeight.NORMAL, 40));
        grid.add(scenetitle, 0, 0, 1, 1);


        final Label nickName = new Label("Nickname:");
        grid.add(nickName, 0, 1);
        nickName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));


        final TextField userNickname = new TextField();
        grid.add(userNickname, 1, 1);

        Button register = createButton("Registrieren");
        HBox reBtn = new HBox(50);
        reBtn.setAlignment(Pos.BOTTOM_RIGHT);
        reBtn.getChildren().add(register);
        grid.add(reBtn, 1, 2);

        //register.disableProperty().bind(Bindings.isEmpty(userNickname.textProperty()));

        final Label dieBesten3L = new Label("Die Besten 3:");
        grid.add(dieBesten3L, 0, 4);
        dieBesten3L.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));


        final Label dieBesten3 = new Label(hm2.getHighscoreStringinGUI());
        grid.add(dieBesten3, 1, 4);
        dieBesten3.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        //disclaimer Text
        Text disclaimer = new Text();
        disclaimer.setFont(Font.font("Tahoma", FontWeight.NORMAL, 9));
        disclaimer.setText("  \n \n \n \n");
        grid.add(disclaimer, 15, 15);

        final Button teststarten = createButton("Test starten");
        teststarten.setDisable(true);
        HBox tsBtn = new HBox(50);
        tsBtn.setAlignment(Pos.BOTTOM_RIGHT);
        tsBtn.getChildren().add(teststarten);
        grid.add(tsBtn, 5, 2);

        //nächsten Screen anzeigen
        teststarten.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().remove(RegisterScreen);
                switch (webcamTestRandomizer) {
                    case (0):
                        initiateCircleTest();
                        break;
                    case (1):
                        initiatePictureTest();
                        break;
                    case (2):
                        initiateBalanceTest();
                        break;
                }
            }
        });

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 3);

        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
                actiontarget.setText("Registriert");
                nickname = userNickname.getText();
                teststarten.setDisable(false);
            }
        });

        RegisterScreen.setCenter(grid);
        return RegisterScreen;
    }

    /**
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

    private void initiateCircleTest() {
        root.setCenter(webcamTestOneInformationScreen);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            root.getChildren().remove(webcamTestOneInformationScreen);
                            try {
                                initiateCircleTestPhase(webcamTestOneCircleOneScreen, "CIRCLE_TEST_CIRCLE_TOP_LEFT", 2);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(r).start();
    }

    /**
     * @Author Jonas Siefker
     * Methode die mithilfe übergebener Parameter den jeweils richtigen Kreis-Bildschirm auswählt
     */

    private void initiateCircleTestPhase(final BorderPane currentPane, final String circleKeyword, final int nextPhaseNumber) throws IOException {

        root.setCenter(currentPane);

        Webcam webcam = Webcam.getDefault();

        ImageIO.write(webcam.getImage(), "PNG", new File("img/1.png"));
        File file1 = new File("img/1.png");
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
                            detectedMotion = webcamEvaluation.checkIfMotionDetectedForGivenTest(circleKeyword, image1);
                        } catch (IOException e) {
                            e.printStackTrace();
                            detectedMotion = false;
                        }

                        if (detectedMotion) {

                            root.getChildren().remove(currentPane);
                            root.setCenter(pickedCorrectAnswerScreen);
                            score = score+5;

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
                                            switch (nextPhaseNumber) {
                                                case(2):
                                                    try {
                                                        initiateCircleTestPhase(webcamTestOneCircleTwoScreen, "CIRCLE_TEST_CIRCLE_BOTTOM_RIGHT", 3);
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    break;
                                                case(3):
                                                    try {
                                                        initiateCircleTestPhase(webcamTestOneCircleThreeScreen, "CIRCLE_TEST_CIRCLE_TOP_RIGHT", 4);
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    break;
                                                case(4):
                                                    try {
                                                        initiateCircleTestPhase(webcamTestOneCircleFourScreen, "CIRCLE_TEST_CIRCLE_BOTTOM_LEFT", 5);
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    break;
                                                case(5):
                                                    try {
                                                        initiateCircleTestPhase(webcamTestOneCircleThreeScreen, "CIRCLE_TEST_CIRCLE_TOP_RIGHT", 6);
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    break;
                                                case(6):
                                                    switch (webcamTestRandomizer) {
                                                        case(0): initiatePictureTest(); break;
                                                        case(1): switch (mouseTestRandomizer) {
                                                            case(0): initiateClockTest(); break;
                                                            case(1): initiateCalculateTest(); break;
                                                        } break;
                                                        case(2): initiatePictureTest(); break;
                                                    }
                                                default: break;
                                            }
                                        }
                                    });
                                }
                            };

                            new Thread(r).start();
                        }
                        else {
                            root.getChildren().remove(currentPane);
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
                                            switch (nextPhaseNumber) {
                                                case(2):
                                                    try {
                                                        initiateCircleTestPhase(webcamTestOneCircleTwoScreen, "CIRCLE_TEST_CIRCLE_BOTTOM_RIGHT", 3);
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    break;
                                                case(3):
                                                    try {
                                                        initiateCircleTestPhase(webcamTestOneCircleThreeScreen, "CIRCLE_TEST_CIRCLE_TOP_RIGHT", 4);
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    break;
                                                case(4):
                                                    try {
                                                        initiateCircleTestPhase(webcamTestOneCircleFourScreen, "CIRCLE_TEST_CIRCLE_BOTTOM_LEFT", 5);
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    break;
                                                case(5):
                                                    try {
                                                        initiateCircleTestPhase(webcamTestOneCircleThreeScreen, "CIRCLE_TEST_CIRCLE_TOP_RIGHT", 6);
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    break;
                                                case(6):
                                                    switch (webcamTestRandomizer) {
                                                        case(0): initiatePictureTest(); break;
                                                        case(1): switch (mouseTestRandomizer) {
                                                            case(0): initiateClockTest(); break;
                                                            case(1): initiateCalculateTest(); break;
                                                        } break;
                                                        case(2): initiatePictureTest(); break;
                                                    }
                                                default: break;
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

    /**
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

    /**
     * @Author Jonas Siefker
     * Methode die zwei positions-Strings nutzt um den entsprechenden Kreis auf dem Bildschirm anzuzeigen
     */

    private BorderPane showCircleInCorner(String posVertical, String posHorizontal) {

        BorderPane circleInCornerScreen = new BorderPane();

        WebcamManager webcamManager = new WebcamManager();
        Webcam webcam = webcamManager.displayWebcamAtPosition(circleInCornerScreen, "CENTER");

        Image image = new Image("file:img/kreis_blau.png");
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

    private BorderPane fillPaneWithWebcamTest2InformationScreenComponents() {

        BorderPane webcamTest2InformationScreen = new BorderPane();

        Text text = new Text();
        text.setFont(new Font(36));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText("Merke dir das Bild das du unten siehst! \n Du wirst als nächstes eine Abfolge von Bildern sehen. \n Erkennst du das untenstehende wieder, \nstrecke deine Arme in die beiden oberen Bildschirmecken aus!");

        /**
         * Informations-Text zum Reaktions-Test auf dem Bildschirm anzeigen
         */

        Image image = new Image("file:img/Bild_4.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        HBox hBox = new HBox();
        hBox.setMaxHeight(640);
        hBox.setMaxWidth(568);
        hBox.getChildren().add(imageView);

        HBox hBoxText = new HBox();
        hBoxText.setAlignment(Pos.CENTER);
        hBoxText.getChildren().add(text);

        webcamTest2InformationScreen.setTop(hBoxText);
        webcamTest2InformationScreen.setCenter(hBox);

        return webcamTest2InformationScreen;
    }

    /**
     * @Author Jonas Siefker
     */

    private void initiatePictureTest() {

        root.setCenter(webcamTestTwoInformationScreen);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        root.getChildren().remove(webcamTestTwoInformationScreen);
                        try {
                            initiatePictureTestPhase(webcamTestTwoPictureOneScreen, 2);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };

        new Thread(r).start();
    }

    private void initiatePictureTestPhase(final BorderPane currentPane, final int nextPhaseNumber) throws IOException {

        root.setCenter(currentPane);

        Webcam webcam = Webcam.getDefault();

        ImageIO.write(webcam.getImage(), "PNG", new File("img/1.png"));
        File file1 = new File("img/1.png");
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
                            detectedMotion = webcamEvaluation.checkIfMotionDetectedForGivenTest("PICTURES_TEST", image1);
                        } catch (IOException e) {
                            e.printStackTrace();
                            detectedMotion = false;
                        }

                        if (nextPhaseNumber!=5 && detectedMotion==true) {
                            choseWrongPicture=true;
                        }

                        if (nextPhaseNumber==5 && detectedMotion==true && choseWrongPicture==false) {
                            choseRightPicture=true;
                        }

                        if (nextPhaseNumber!=6) {

                            root.getChildren().remove(currentPane);
                            root.setCenter(warningScreen);
                        }

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
                                        root.getChildren().remove(warningScreen);
                                        switch (nextPhaseNumber) {
                                            case(2):
                                                try {
                                                    initiatePictureTestPhase(webcamTestTwoPictureTwoScreen, 3);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case(3):
                                                try {
                                                    initiatePictureTestPhase(webcamTestTwoPictureThreeScreen, 4);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case(4):
                                                try {
                                                    initiatePictureTestPhase(webcamTestTwoPictureFourScreen, 5);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case(5):
                                                try {
                                                    initiatePictureTestPhase(webcamTestTwoPictureFiveScreen, 6);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                break;
                                            case(6):

                                                Boolean answer = choseRightPicture && !choseWrongPicture;
                                                final BorderPane answerScreen;
                                                if (answer) {
                                                    answerScreen = pickedCorrectAnswerScreen;
                                                    score = score+15;
                                                }
                                                else {
                                                    answerScreen = pickedWrongAnswerScreen;
                                                }

                                                root.setCenter(answerScreen);

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
                                                                root.getChildren().remove(answerScreen);
                                                                switch (webcamTestRandomizer) {
                                                                    case(0): initiateBalanceTest(); break;
                                                                    case(1): initiateBalanceTest(); break;
                                                                    case(2): switch (mouseTestRandomizer) {
                                                                        case(0): initiateClockTest(); break;
                                                                        case(1): initiateCalculateTest(); break;
                                                                    } break;
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
                });
            }
        };

        new Thread(r).start();
    }

    private BorderPane showPicture(int pictureNumber) {

        BorderPane pictureScreen = new BorderPane();

        Image image;

        switch (pictureNumber) {
            case(1): image = new Image("file:img/Bild_1.jpg"); break;
            case(2): image = new Image("file:img/Bild_2.png"); break;
            case(3): image = new Image("file:img/Bild_3.jpg"); break;
            case(4): image = new Image("file:img/Bild_4.png"); break;
            case(5): image = new Image("file:img/Bild_5.png"); break;
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

    /**
     * @author Friederike
     * @return
     */
    private BorderPane fillPaneWithWebcamTestThreeInformationScreenComponents() {

        BorderPane webcamTestThreeInformationScreen = new BorderPane();

        Text text = new Text();
        text.setFont(new Font(36));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText("Nach verschwinden des Textes \n stelle dich drei Meter nach hinten und strecke den rechten Arm aus!");

        /**
         * Informations-Text auf dem Bildschirm anzeigen
         */

        HBox bottomBox = new HBox();
        bottomBox.setSpacing(50);
        bottomBox.setAlignment(Pos.CENTER);
        webcamTestThreeInformationScreen.setBottom(bottomBox);
        webcamTestThreeInformationScreen.setCenter(text);

        return webcamTestThreeInformationScreen;
    }
    private BorderPane showBalanceCamera() {

        BorderPane BalanceCamera = new BorderPane();

        WebcamManager webcamManager = new WebcamManager();
        Webcam webcam = webcamManager.displayWebcamAtPosition(BalanceCamera, "CENTER");


        return BalanceCamera;
    }

    private BorderPane continueBalanceCamera() {

        BorderPane webcamTestThreeTwoScreen = new BorderPane();

        WebcamManager webcamManager = new WebcamManager();
        Webcam webcam = webcamManager.displayWebcamAtPosition(webcamTestThreeTwoScreen, "CENTER");

        Text text = new Text();
        text.setFont(new Font(36));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText("Bleibe so 10 Sekunden stehen.");

        /**
         * Informations-Text auf dem Bildschirm anzeigen
         */

        HBox bottomBox = new HBox();
        bottomBox.setSpacing(50);
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);
        webcamTestThreeTwoScreen.setBottom(bottomBox);
        webcamTestThreeTwoScreen.setBottom(text);


        return webcamTestThreeTwoScreen;
    }

    private void initiateBalanceTest() {

        root.setCenter(webcamTestThreeInformationScreen);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            initiateBalanceTestPhase();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };

        new Thread(r).start();
    }

    private void initiateBalanceTestPhase() throws IOException {

        root.setCenter(webcamTestThreeScreen);

        Webcam webcam = Webcam.getDefault();

        ImageIO.write(webcam.getImage(), "PNG", new File("img/1.png"));
        File file1 = new File("img/1.png");
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
                            detectedMotion = webcamEvaluation.checkIfMotionDetectedForGivenTest("BALANCE_TEST_RIGHT", image1);
                        } catch (IOException e) {
                            e.printStackTrace();
                            detectedMotion = false;
                        }

                        if (detectedMotion) {

                            root.getChildren().remove(webcamTestThreeScreen);
                            root.setCenter(pickedCorrectAnswerScreenBalanceTest);

                            Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(5000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            root.getChildren().remove(pickedCorrectAnswerScreen);
                                            try {
                                                continueBalanceTest();
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
                            root.getChildren().remove(webcamTestThreeScreen);
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
                                            switch (webcamTestRandomizer) {
                                                case(0): switch (mouseTestRandomizer) {
                                                    case(0): initiateClockTest(); break;
                                                    case(1): initiateCalculateTest(); break;

                                                } break;
                                                case(1): initiateCircleTest(); break;
                                                case(2): initiateCircleTest(); break;
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

    private void continueBalanceTest() throws IOException {

        root.setCenter(webcamTestThreeTwoScreen);

        Webcam webcam = Webcam.getDefault();


        ImageIO.write(webcam.getImage(), "PNG", new File("img/1.png"));
        File file1 = new File("img/1.png");
        FileInputStream fis1 = new FileInputStream(file1);
        final BufferedImage image1 = ImageIO.read(fis1);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        WebcamEvaluation webcamEvaluation = new WebcamEvaluation();
                        Boolean detectedMotion = null;

                        try {
                            detectedMotion = webcamEvaluation.checkIfMotionDetectedForGivenTest("WHOLE_PIC", image1);
                        } catch (IOException e) {
                            e.printStackTrace();
                            detectedMotion = false;
                        }

                        if (detectedMotion) {

                            root.getChildren().remove(webcamTestThreeTwoScreen);
                            root.setCenter(pickedWrongAnswerScreen);

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
                                            root.getChildren().remove(pickedWrongAnswerScreen);
                                            switch (webcamTestRandomizer) {
                                                case(0): switch (mouseTestRandomizer) {
                                                    case(0): initiateClockTest(); break;
                                                    case(1): initiateCalculateTest(); break;

                                                } break;
                                                case(1): initiateCircleTest(); break;
                                                case(2): initiateCircleTest(); break;
                                            }
                                        }
                                    });
                                }
                            };


                            new Thread(r).start();
                        }
                        else {
                            root.getChildren().remove(webcamTestThreeTwoScreen);
                            root.setCenter(pickedCorrectAnswerScreen);
                            score = score+20;

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
                                            root.getChildren().remove(pickedCorrectAnswerScreen);
                                            switch (webcamTestRandomizer) {
                                                case(0): switch (mouseTestRandomizer) {
                                                    case(0): initiateClockTest(); break;
                                                    case(1): initiateCalculateTest(); break;

                                                } break;
                                                case(1): initiateCircleTest(); break;
                                                case(2): initiateCircleTest(); break;
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

    private void initiateClockTest() {

        root.setCenter(explanationClockTest);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        root.getChildren().remove(explanationClockTest);
                        initiateClockTestPhase1();
                    }
                });
            }
        };

        new Thread(r).start();
    }

    /**
     * Startet die erste Uhr
     */
    public void initiateClockTestPhase1() {

        root.setCenter(clockTestClock1);

        // Timer von 10 Sekunden bevor der Bildschirm zur zweiten Uhr wechselt
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        root.getChildren().remove(clockTestClock1);
                        initiateClockTestPhase2();
                    }
                });
            }
        };

        new Thread(r).start();
    }

    /**
     * Startet die zweite Uhr
     */
    public void initiateClockTestPhase2() {

        root.setCenter(clockTestClock2);

        // Timer von 5 Sekunden bevor der Bildschirm zur dritten Uhr wechselt
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        root.getChildren().remove(clockTestClock2);
                        initiateClockTestPhase3();
                    }
                });
            }
        };

        new Thread(r).start();
    }

    /**
     * Startet die dritte Uhr
     */
    public void initiateClockTestPhase3() {

        root.setCenter(clockTestClock3);

        // Timer von 5 Sekunden bevor der Bildschirm zum nächsten Test wechselt
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        root.getChildren().remove(clockTestClock3);
                        switch (mouseTestRandomizer) {
                            case(0): initiateCalculateTestPhase1(); break;
                            case(1): hm2.addScore(nickname, score); root.setCenter(ergebnisauswertung);
                        }
                    }
                });
            }
        };

        new Thread(r).start();
    }

    /***
     * Startet die erste Rechenaufgabe
     */
    public void initiateCalculateTestPhase1() {

        root.setCenter(calculateTestCalculate1);

        // Timer von 10 Sekunden bevor der Bildschirm zur zweiten Aufgabe
        // wechselt
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        root.getChildren().remove(calculateTestCalculate1);
                        initiateCalculateTestPhase2();
                    }
                });
            }
        };

        new Thread(r).start();
    }

    /***
     * Startet die zweite Rechenaufgabe
     */
    public void initiateCalculateTestPhase2() {

        root.setCenter(calculateTestCalculate2);

        // Timer von 10 Sekunden bevor der Bildschirm gewechselt wird
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        root.getChildren().remove(calculateTestCalculate2);
                        switch (mouseTestRandomizer) {
                            case(0): hm2.addScore(nickname, score); root.setCenter(ergebnisauswertung); break;
                            case(1): initiateClockTestPhase1();
                        }
                    }
                });
            }
        };

        new Thread(r).start();
    }

    /**
     * Zeigt eine Erklärung zum Uhrentest an
     *
     * @author Caroline Neuner
     * @return
     */
    public BorderPane fillPaneWithExplanationClockTest() {

        final BorderPane explanationClockTest = new BorderPane();

        //Text erstellen und Schriftgröße und Aurichtung setzen
        Text t = new Text();
        t.setFont(new Font(36));
        t.setTextAlignment(TextAlignment.CENTER);
        t.setText("Du siehst gleich eine Uhr und darunter vier verschiedene Uhrzeiten.\n"
                + "Wähle unter den vier Uhrzeiten die richtige auf der Uhr angezeigte aus.");

        //Text auf dem Bildschirm platzieren
        explanationClockTest.setCenter(t);

        return explanationClockTest;
    }

    /**
     * Platziert die Elemente für den ersten Durchlauf des Uhrentests
     *
     * @author Caroline Neuner
     * @return BorderPane
     */
    private BorderPane fillPaneWithClock1() {

        final BorderPane clockTestClock1 = new BorderPane();

        // Buttons erstellen
        final Button button1 = createButton("15:30 Uhr");
        final Button button2 = createButton("17:00 Uhr");
        final Button button3 = createButton("16:00 Uhr");
        final Button button4 = createButton("13:30 Uhr");

		/*
		 * Events festlegen, bei richtiger Antwort 3 wird Button 3 grün und
		 * Punkte werden hochgezählt, bei falschen Antworten wird der gedrückte
		 * Button rot und Button 3 grün
		 */
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonRed(button1);
                colorButtonGreen(button3);
            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonRed(button2);
                colorButtonGreen(button3);
            }
        });

        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonGreen(button3);
                score = score+5;
            }
        });

        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonRed(button4);
                colorButtonGreen(button3);
            }
        });

        // Uhr und Buttons auf dem Bildschirm platzieren
        Image img = new Image("file:img/Uhr16_00.png");
        ImageView imgView = new ImageView();
        imgView.setImage(img);
        HBox bottomBox = new HBox();
        bottomBox.setSpacing(50);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getChildren().addAll(button1, button2, button3, button4);

        clockTestClock1.setCenter(imgView);
        clockTestClock1.setBottom(bottomBox);

        return clockTestClock1;
    }

    /**
     * Platziert die Elemente für den zweiten Durchlauf des Uhrentests
     *
     * @author Caroline Neuner
     * @return
     */
    private BorderPane fillPaneWithClock2() {

        final BorderPane clockTestClock2 = new BorderPane();

        // Buttons erstellen
        final Button button1 = createButton("12:30 Uhr");
        final Button button2 = createButton("08:30 Uhr");
        final Button button3 = createButton("19:00 Uhr");
        final Button button4 = createButton("21:00 Uhr");

		/*
		 * Events festlegen, bei richtiger Antwort 2 wird Button 2 grün und
		 * Punkte werden hochgezählt, bei falschen Antworten wird der gedrückte
		 * Button rot und Button 2 grün
		 */
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonRed(button1);
                colorButtonGreen(button2);
            }

        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonGreen(button2);
                score = score+10;
            }
        });

        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonRed(button3);
                colorButtonGreen(button2);
            }
        });

        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonRed(button4);
                colorButtonGreen(button2);
            }
        });

        // Uhr und Buttons auf dem Bildschirm platzieren
        Image img = new Image("file:img/Uhr08_30.png");
        ImageView imgView = new ImageView();
        imgView.setImage(img);
        HBox bottomBox = new HBox();
        bottomBox.setSpacing(50);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getChildren().addAll(button1, button2, button3, button4);

        clockTestClock2.setCenter(imgView);
        clockTestClock2.setBottom(bottomBox);

        return clockTestClock2;

    }

    /**
     * Platziert die Elemente für den dritten Durchlauf des Uhrentests
     *
     * @author Caroline Neuner
     * @return
     */
    private BorderPane fillPaneWithClock3() {

        final BorderPane clockTestClock3 = new BorderPane();

        // Buttons erstellen
        final Button button1 = createButton("11:00 Uhr");
        final Button button2 = createButton("16:00 Uhr");
        final Button button3 = createButton("17:30 Uhr");
        final Button button4 = createButton("10:30 Uhr");

		/*
		 * Events festlegen, bei richtiger Antwort 1 wird Button 1 grün und
		 * Punkte werden hochgezählt, bei falschen Antworten wird der gedrückte
		 * Button rot und Button 1 grün
		 */
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonGreen(button1);
                score = score + 5;
            }

        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonRed(button2);
                colorButtonGreen(button1);
            }
        });

        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonRed(button3);
                colorButtonGreen(button1);
            }
        });

        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonRed(button4);
                colorButtonGreen(button1);
            }
        });

        // Uhr und Buttons auf dem Bildschirm platzieren
        Image img = new Image("file:img/Uhr11_00.png");
        ImageView imgView = new ImageView();
        imgView.setImage(img);
        HBox bottomBox = new HBox();
        bottomBox.setSpacing(50);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getChildren().addAll(button1, button2, button3, button4);

        clockTestClock3.setCenter(imgView);
        clockTestClock3.setBottom(bottomBox);

        return clockTestClock3;
    }

    /**
     * Zeigt eine Erklärung zum Rechnentest
     *
     * @author Caroline Neuner
     * @return
     */
    public BorderPane fillPaneWithExplanationCalculateTest() {

        BorderPane explanationCalculateTest = new BorderPane();

        //Text erstellen und Schriftgröße und Aurichtung setzen
        Text t = new Text();
        t.setFont(new Font(36));
        t.setTextAlignment(TextAlignment.CENTER);
        t.setText("Du siehst gleich vier verschiedene Rechnenaufgaben mit Ergebnis.\n"
                + "Wähle unter den vier Aufgaben die aus, die das richtige Ergebnis hat.");

        //Text auf dem Bildschirm platzieren
        explanationCalculateTest.setCenter(t);

        return explanationCalculateTest;
    }

    private void initiateCalculateTest() {

        root.setCenter(explanationCalculateTest);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        root.getChildren().remove(explanationCalculateTest);
                        initiateCalculateTestPhase1();
                    }
                });
            }
        };

        new Thread(r).start();
    }

    /**
     * Platziert die Elemente für den ersten Durchlauf des Rechentests
     *
     * @author Caroline Neuner
     * @return
     */
    private BorderPane fillPaneWithCalculate1() {

        final BorderPane calculateTestCalculate1 = new BorderPane();

        // Buttons erstellen
        final Button button1 = createButton("5 + 3 = 7");
        final Button button2 = createButton("7 - 3 = 3");
        final Button button3 = createButton("9 - 2 = 6");
        final Button button4 = createButton("3 + 6 = 9");

		/*
		 * Events festlegen, bei richtiger Antwort 4 wird Button 4 grün und
		 * Punkte werden hochgezählt, bei falschen Antworten wird der gedrückte
		 * Button rot und Button 4 grün
		 */
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonRed(button1);
                colorButtonGreen(button4);
            }

        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonRed(button2);
                colorButtonGreen(button4);
            }
        });

        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonRed(button3);
                colorButtonGreen(button4);
            }
        });

        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonGreen(button4);
                score = score + 10;
            }
        });

        // Buttons auf dem Bildschirm platzieren
        HBox bottomBox = new HBox();
        bottomBox.setSpacing(50);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getChildren().addAll(button1, button2, button3, button4);
        calculateTestCalculate1.setCenter(bottomBox);

        return calculateTestCalculate1;
    }

    /**
     * Platziert die Elemente für den zweiten Durchlauf
     *
     * @author Caroline Neuner
     * @return
     */
    private BorderPane fillPaneWithCalculate2() {

        BorderPane calculateTestCalculate2 = new BorderPane();

        // Buttons erstellen
        final Button button1 = createButton("6 + 4 = 10");
        final Button button2 = createButton("8 - 3 = 6");
        final Button button3 = createButton("5 + 6 = 10");
        final Button button4 = createButton("7 - 3 = 5");

		/*
		 * Events festlegen, bei richtiger Antwort 1 wird Button 1 grün und
		 * Punkte werden hochgezählt, bei falschen Antworten wird der gedrückte
		 * Button rot und Button 1 grün
		 */
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonGreen(button1);
                score = score + 10;
            }

        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonRed(button2);
                colorButtonGreen(button1);
            }
        });

        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonRed(button3);
                colorButtonGreen(button1);
            }
        });

        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                colorButtonRed(button4);
                colorButtonGreen(button1);
            }
        });

        // Buttons auf dem Bildschirm platzieren
        HBox bottomBox = new HBox();
        bottomBox.setSpacing(50);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.getChildren().addAll(button1, button2, button3, button4);
        calculateTestCalculate2.setCenter(bottomBox);

        return calculateTestCalculate2;

    }

    /**
     * Thomas Klaus; Auswertung des Ergebnis
     */
    private BorderPane fillPaneWithErgebnisauswertungComponents() {
        final BorderPane Ergebnisauswertung = new BorderPane();



        Button ergebnisButton = createButton("Zeig mir mein Ergebnis!");
        ergebnisButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Ergebnisauswertung.getChildren().clear();

                GridPane grid = new GridPane();
                grid.setAlignment(Pos.CENTER);
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(5, 5, 5, 5));

                //wenn das Ergebnis gut ist und der Nutzer fahrtüchtig ist
                if(score >= 75){   //score hierhin
                    Image imagehoch = new Image("file:img/handgruen.png");
                    ImageView imageViewhoch = new ImageView();
                    imageViewhoch.setImage(imagehoch);

                    HBox hBox = new HBox();
                    hBox.getChildren().add(imageViewhoch);
                    grid.add(imageViewhoch, 2, 3);
                    Ergebnisauswertung.setCenter(grid);

                    final Label bewertungstext = new Label("Du hast den Test gut absolviert! \nDu bist nüchtern und" +
                            "kannst Autofahren oder dich nun betrinken");
                    bewertungstext.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
                    bewertungstext.setAlignment(Pos.TOP_CENTER);
                    grid.add(bewertungstext, 2, 2);
                    Ergebnisauswertung.setCenter(grid);
                }//End if

                else if(score >= 50 && score < 75){
                    Image imagerunter = new Image("file:img/Daumen_quer.jpg");
                    ImageView imageViewrunter = new ImageView();
                    imageViewrunter.setImage(imagerunter);

                    HBox hBox = new HBox();
                    hBox.getChildren().add(imageViewrunter);
                    grid.add(imageViewrunter, 2, 3);
                    Ergebnisauswertung.setCenter(grid);

                    final Label bewertungstext = new Label("Du bist angetrunken. An deiner Stelle würde ich \nnicht mehr fahren");
                    bewertungstext.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
                    bewertungstext.setAlignment(Pos.TOP_CENTER);
                    grid.add(bewertungstext, 2, 2);
                    Ergebnisauswertung.setCenter(grid);
                }//End else if

                else if(score >= 25 && score < 50){
                    Image imagerunter = new Image("file:img/Daumen_quer.jpg");
                    ImageView imageViewrunter = new ImageView();
                    imageViewrunter.setImage(imagerunter);

                    HBox hBox = new HBox();
                    hBox.getChildren().add(imageViewrunter);
                    grid.add(imageViewrunter, 2, 3);
                    Ergebnisauswertung.setCenter(grid);

                    final Label bewertungstext = new Label("Eindeutig betrunken! Auf keinen Fall mehr Auto fahren");
                    bewertungstext.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
                    bewertungstext.setAlignment(Pos.TOP_CENTER);
                    grid.add(bewertungstext, 2, 2);
                    Ergebnisauswertung.setCenter(grid);
                }//End else if

                else if(score >= 0 && score < 25){
                    Image imagerunter = new Image("file:img/daumenrot.png");
                    ImageView imageViewrunter = new ImageView();
                    imageViewrunter.setImage(imagerunter);

                    HBox hBox = new HBox();
                    hBox.getChildren().add(imageViewrunter);
                    grid.add(imageViewrunter, 2, 3);
                    Ergebnisauswertung.setCenter(grid);

                    final Label bewertungstext = new Label("Du bist sturzbetrunken! Gehe dich ausnüchtern und \nversuche es dann nochmal");
                    bewertungstext.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
                    bewertungstext.setAlignment(Pos.TOP_CENTER);
                    grid.add(bewertungstext, 2, 2);
                    Ergebnisauswertung.setCenter(grid);
                }//End else if

                //disclaimer Text
                Text disclaimer = new Text();
                disclaimer.setFont(Font.font("Tahoma", FontWeight.NORMAL, 9));
                disclaimer.setText("Das Institut für Suchtmittelaufklärung übernimmt keinerlei Gewähr \n" +
                        "für die Verbindlichkeit der Aussagen zur Fahrtüchtikkeit. \n" +
                        "Diese Software ist keine medizinische Software und macht daher \n" +
                        "keine verbindlichen Aussagen zum Alkoholpegel des Nutzers");
                grid.add(disclaimer, 5, 5);

                //Button zum RanglistenScreen
                Button zeigeRangliste = createButton("Rangliste anzeigen");
                HBox zrBtn = new HBox(30);
                zrBtn.setAlignment(Pos.BOTTOM_CENTER);
                zrBtn.getChildren().add(zeigeRangliste);
                grid.add(zeigeRangliste, 2, 4);
                Ergebnisauswertung.setCenter(grid);

                zeigeRangliste.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        root.getChildren().remove(Ergebnisauswertung);
                        root.setCenter(ranglisteScreen);
                    }
                });
            }
        });

        Ergebnisauswertung.setCenter(ergebnisButton);

        return Ergebnisauswertung;
    }

    /**
     * Thomas Klaus; Rangliste wird angezeigt
     */
    public BorderPane fillPaneWithRanglistenScreenComponents() {
        final BorderPane RanglisteScreen = new BorderPane();

        Button rankingButton = createButton("Zeig mir die Rangliste!");
        rankingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                RanglisteScreen.getChildren().clear();

                GridPane grid = new GridPane();
                grid.setAlignment(Pos.CENTER);
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(5, 5, 5, 5));

                final Label dieBestenz = new Label("Rangliste:");
                dieBestenz.setFont(Font.font("Tahoma", FontWeight.NORMAL, 45));
                dieBestenz.setAlignment(Pos.TOP_CENTER);
                grid.add(dieBestenz, 1, 1);
                RanglisteScreen.setCenter(grid);


                final Label dieBesten = new Label(hm2.getHighscoreString());
                dieBesten.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
                grid.add(dieBesten, 1, 3);
                RanglisteScreen.setCenter(grid);

                Button Ranglisteweg = createButton("OK");
                HBox zrBtn = new HBox(30);
                zrBtn.setAlignment(Pos.BOTTOM_CENTER);
                zrBtn.getChildren().add(Ranglisteweg);
                grid.add(Ranglisteweg, 5, 5);
                RanglisteScreen.setCenter(grid);

                Ranglisteweg.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        root.getChildren().remove(RanglisteScreen);
                        root.setCenter(registerScreen);

                        score=0;
                    }
                });
            }
        });

        RanglisteScreen.setCenter(rankingButton);

        return RanglisteScreen;
    }

    private BorderPane fillPaneWithCorrectAnswerScreenComponents() {

        BorderPane correctAnswerScreen = new BorderPane();

        Text text = new Text();
        text.setFont(new Font(150));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText("Sehr gut!");

        correctAnswerScreen.setCenter(text);

        return correctAnswerScreen;
    }

    private BorderPane fillPaneWithCorrectAnswerScreenComponentsBalanceTest() {

        BorderPane correctAnswerScreen = new BorderPane();

        Text text = new Text();
        text.setFont(new Font(90));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText("Sehr gut!\nBleibe 10 Sekunden so stehen!");

        correctAnswerScreen.setCenter(text);

        return correctAnswerScreen;
    }

    /*
     * @Author Jonas Siefker
     */

    private BorderPane fillPaneWithWrongAnswerScreenComponents() {

        BorderPane wrongAnswerScreen = new BorderPane();

        Text text = new Text();
        text.setFont(new Font(150));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText("Leider nicht...");

        wrongAnswerScreen.setCenter(text);

        return wrongAnswerScreen;
    }

    /*
     * @Author Jonas Siefker
     */

    private BorderPane fillPaneWithWarningScreenComponents() {

        BorderPane warningScreen = new BorderPane();

        Text text = new Text();
        text.setFont(new Font(150));
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText("3... 2... 1...");

        warningScreen.setCenter(text);

        return warningScreen;
    }

    /**
     * Erstellt einen Button
     *
     * @author Caroline Neuner
     * @param text
     * @return Button
     */
    private Button createButton(String text) {

        Button button = new Button();
        button.setText(text);
        button.setStyle("-fx-font: 36 arial");
        button.setMinSize(250, 150);

        return button;

    }

    /**
     * Färbt einen Button grün
     *
     * @author Caroline Neuner
     * @param button
     */
    public void colorButtonGreen(Button button) {

        button.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");

    }

    /**
     * Färbt einen Button rot
     *
     * @author Caroline Neuner
     * @param button
     */
    public void colorButtonRed(Button button) {

        button.setStyle("-fx-font: 36 arial; -fx-base: #DF0101");

    }
}
