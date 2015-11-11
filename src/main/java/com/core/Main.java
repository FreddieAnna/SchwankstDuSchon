package com.core;

import com.core.UI.Highscore.HighscoreManager;
import com.core.UI.Highscore.Score;
import com.core.UI.WebcamManager;
import com.github.sarxos.webcam.Webcam;
import javafx.application.Application;
import javafx.application.Platform;
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

import javax.swing.border.Border;

/**
 * Created by siefker on 04.11.2015.
 */
public class Main extends Application {

    public static String nickname;

    BorderPane root = new BorderPane();

    BorderPane RegisterScreen = fillPaneWithRegisterScreenComponents();
    BorderPane startScreen = fillPaneWithStartScreenComponents();
    BorderPane webcamTestOneInformationScreen = fillPaneWithWebcamTest1ScreenComponents();
    BorderPane webcamTestOneCircleOneScreen = initiateCircle(1);
    BorderPane webcamTestOneCircleTwoScreen = initiateCircle(2);
    BorderPane webcamTestOneCircleThreeScreen = initiateCircle(3);
    BorderPane webcamTestOneCircleFourScreen = initiateCircle(4);
    BorderPane Ergebnisauswertung = fillPaneWithErgebnisauswertungComponents();
    BorderPane RanglisteScreen = fillPaneWithRanglistenScreenComponents();

    public static void main(String args[]) {
        HighscoreManager hm1 = new HighscoreManager();
        //Daten in Highscore speichern
        hm1.addScore("Bart",240);
        hm1.addScore("Marge",300);
        hm1.addScore("Maggie",220);
        hm1.addScore("Homer",100);
        hm1.addScore("Lisa",270);
        hm1.addScore(nickname, 400);
        System.out.print(hm1.getHighscoreStringinGUI());

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Test");
        root.setCenter(Ergebnisauswertung);
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public BorderPane fillPaneWithRegisterScreenComponents() {

        final BorderPane RegisterScreen = new BorderPane();
        HighscoreManager hm2 = new HighscoreManager();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(55, 55, 55, 55));
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


        final Label dieBesten3L = new Label("Die Besten 3:");
        grid.add(dieBesten3L, 0, 4);
        dieBesten3L.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));


        final Label dieBesten3 = new Label(hm2.getHighscoreStringinGUI());
        grid.add(dieBesten3, 1, 4);
        dieBesten3.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));


        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 3);

        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
                actiontarget.setText("Registriert");
                nickname = userNickname.getText();
                System.out.print(nickname);
            }
        });





        Button teststarten = createButton("Test starten");
        HBox tsBtn = new HBox(50);
        tsBtn.setAlignment(Pos.BOTTOM_RIGHT);
        tsBtn.getChildren().add(teststarten);
        grid.add(tsBtn, 5, 2);

        teststarten.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().remove(RegisterScreen);
                root.setCenter(startScreen);
            }
        });
        RegisterScreen.setCenter(grid);
        return RegisterScreen;
    }

    public BorderPane fillPaneWithStartScreenComponents() {

        final BorderPane startScreen = new BorderPane();

        Button button = new Button();
        button.setText("To WebcamTest Screen");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                root.getChildren().remove(startScreen);
                root.setCenter(webcamTestOneInformationScreen);

                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(8000);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    //initiateCircleTest(root);
                                    root.getChildren().remove(webcamTestOneInformationScreen);
                                    root.setCenter(webcamTestOneCircleOneScreen);
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

    public BorderPane fillPaneWithWebcamTest1ScreenComponents() {

        BorderPane webcamTest1Screen = new BorderPane();

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
        webcamTest1Screen.setBottom(bottomBox);
        webcamTest1Screen.setCenter(text);

        return webcamTest1Screen;
    }

    private void initiateCircleTest(BorderPane root) {

        WebcamManager webcamManager = new WebcamManager();
        Webcam webcam = webcamManager.displayWebcamAtPosition(root, "CENTER");

        //initiateCircle(root, 1);

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
    }

    /*
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
     * Methode die zwei positions-Strings nutzt um den entsprechenden Kreis auf dem Bildschirm anzuzeigen
     */

    private BorderPane showCircleInCorner(String posVertical, String posHorizontal) {

        BorderPane circleInCornerScreen = new BorderPane();

        Image image = new Image("file:///C:\\Users\\Thomas\\Desktop\\SchwankstDuSchon-development\\src\\main\\java\\com\\core\\UI\\Graphics\\kreis_blau.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        HBox hBox = new HBox();
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

    public BorderPane fillPaneWithErgebnisauswertungComponents() {
        final BorderPane Ergebnisauswertung = new BorderPane();

        int testpunkte = 50;

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(4, 4, 4, 4));


        if(testpunkte >= 50){
            Image imagehoch = new Image("file:src/main/java/com/core/UI/Graphics/gruener-daumen_.jpg");
            ImageView imageViewhoch = new ImageView();
            imageViewhoch.setImage(imagehoch);

            HBox hBox = new HBox();
            hBox.getChildren().add(imageViewhoch);
            grid.add(imageViewhoch, 2, 2);
            Ergebnisauswertung.setCenter(grid);

            final Label bewertungstext = new Label("Echt gut!");
            bewertungstext.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
            bewertungstext.setAlignment(Pos.TOP_CENTER);
            grid.add(bewertungstext, 2, 1);
            Ergebnisauswertung.setCenter(grid);


        }
        else {
                Image imagerunter = new Image("file:src/main/java/com/core/UI/Graphics/Daumen_runter.jpg");
                ImageView imageViewrunter = new ImageView();
                imageViewrunter.setImage(imagerunter);

                HBox hBox = new HBox();
                hBox.getChildren().add(imageViewrunter);
                grid.add(imageViewrunter, 2, 2);
                Ergebnisauswertung.setCenter(grid);

                final Label bewertungstext = new Label("Läuft bei dir");
                bewertungstext.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));
                bewertungstext.setAlignment(Pos.TOP_CENTER);
                grid.add(bewertungstext, 2, 1);
                Ergebnisauswertung.setCenter(grid);


        }//End else


        Button zeigeRangliste = createButton("Rangliste anzeigen");
        HBox zrBtn = new HBox(30);
        zrBtn.setAlignment(Pos.BOTTOM_CENTER);
        zrBtn.getChildren().add(zeigeRangliste);
        grid.add(zeigeRangliste, 2, 3);
        Ergebnisauswertung.setCenter(grid);

        zeigeRangliste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().remove(Ergebnisauswertung);
                root.setCenter(RanglisteScreen);
            }
        });

    return Ergebnisauswertung;
    }

    public BorderPane fillPaneWithRanglistenScreenComponents(){
        final BorderPane RanglisteScreen = new BorderPane();
        HighscoreManager hm3 = new HighscoreManager();

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


        final Label dieBesten = new Label(hm3.getHighscoreString());
        dieBesten.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        grid.add(dieBesten, 1, 3);
        RanglisteScreen.setCenter(grid);



        return RanglisteScreen;
    }

    public Button createButton(String text){

        Button button = new Button();
        button.setText(text);
        button.setStyle("-fx-font: 36 arial");
        button.setMinSize(250, 150);

        return button;

    }
}
