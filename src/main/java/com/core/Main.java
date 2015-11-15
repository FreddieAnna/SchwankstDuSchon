package com.core;

import com.core.UI.Highscore.HighscoreManager;
import com.core.UI.WebcamManager;
import com.github.sarxos.webcam.Webcam;
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

/**
 * Created by siefker on 04.11.2015.
 */
public class Main extends Application {

    public static String nickname;

    BorderPane root = new BorderPane();
    static final HighscoreManager hm2 = new HighscoreManager();
    public int score;

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
        hm2.deleteScore();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Test");
        root.setCenter(RegisterScreen);
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    /**
     * Thomas Klaus; Registrierungsscreen anzeigen
     */
    public BorderPane fillPaneWithRegisterScreenComponents() {
        final BorderPane RegisterScreen = new BorderPane();

        //grid erstellen
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

        //damit man einen nicknamen angeben muss
        register.disableProperty().bind(Bindings.isEmpty(userNickname.textProperty()));

        final Label dieBesten3L = new Label("Die Besten 3:");
        grid.add(dieBesten3L, 0, 4);
        dieBesten3L.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));


        final Label dieBesten3 = new Label(hm2.getHighscoreStringinGUI());
        grid.add(dieBesten3, 1, 4);
        dieBesten3.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        //damit der Inhalt vom screen weiter oben ist
        Text schoenmach = new Text();
        schoenmach.setFont(Font.font("Tahoma", FontWeight.NORMAL, 9));
        schoenmach.setText("  \n \n \n \n");
        grid.add(schoenmach, 15, 15);

        final Button teststarten = createButton("Test starten");
        HBox tsBtn = new HBox(50);
        tsBtn.setAlignment(Pos.BOTTOM_RIGHT);
        tsBtn.getChildren().add(teststarten);
        grid.add(tsBtn, 5, 2);
        teststarten.setDisable(true); //button ausblenden um registrierung zu erzwingen


        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 3);

        //registrieren und nickname speichern; teststarten button freigeben
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


        //nächsten Screen anzeigen
        teststarten.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().remove(RegisterScreen);
                root.setCenter(Ergebnisauswertung);
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

    /**
     * Thomas Klaus; Auswertung des Ergebnis
    */
    public BorderPane fillPaneWithErgebnisauswertungComponents() {
        final BorderPane Ergebnisauswertung = new BorderPane();

        int testpunkte = 55; // nur zum testen, auch in den ifs zu den scores ändern

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));

        //wenn das Ergebnis gut ist und der Nutzer fahrtüchtig ist
        if(testpunkte >= 75){   //score hierhin
            Image imagehoch = new Image("file:src/main/java/com/core/UI/Graphics/handgruen.png");
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

        //wenn das Ergebnis okay ist
        else if(testpunkte >= 50 && testpunkte < 75)
        {
                Image imagerunter = new Image("file:src/main/java/com/core/UI/Graphics/Daumen_quer.jpg");
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

        //wenn das Ergebnis okay ist
        else if(testpunkte >= 25 && testpunkte < 50){
            Image imagerunter = new Image("file:src/main/java/com/core/UI/Graphics/Daumen_quer.jpg");
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

        //wenn das Ergebnis schlecht ist
        else if(testpunkte >= 0 && testpunkte < 25){
            Image imagerunter = new Image("file:src/main/java/com/core/UI/Graphics/daumenrot.png");
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

        //ranglistenscreen anzeigen
        zeigeRangliste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().remove(Ergebnisauswertung);
                root.setCenter(RanglisteScreen);
            }
        });

        //benutzer und seinen score zur Rangliste hinzufügen
        hm2.addScore(nickname, score);

        return Ergebnisauswertung;
    }

    /**
     * Thomas Klaus; Rangliste wird angezeigt
     */
    public BorderPane fillPaneWithRanglistenScreenComponents(){
        final BorderPane RanglisteScreen = new BorderPane();

        //grid erstellen
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

        //Rangliste anzeigen
        final Label dieBesten = new Label(hm2.getHighscoreString());
        dieBesten.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        grid.add(dieBesten, 1, 3);
        RanglisteScreen.setCenter(grid);

        //Rangliste schließen
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
                //root.setCenter(RanglisteScreen);
            }
        });


        return RanglisteScreen;
    }



    //Caros createButton Methode
    public Button createButton(String text){

        Button button = new Button();
        button.setText(text);
        button.setStyle("-fx-font: 36 arial");
        button.setMinSize(250, 150);

        return button;

    }


    }//End class


