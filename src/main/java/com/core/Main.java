package com.core;

import com.core.UI.Screens.StartScreenFiller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 * Created by siefker on 14.09.2015.
 */
@SuppressWarnings("restriction")
public class Main extends Application {

    public static void main(String args[]) {
        launch(args);
        //Initiator.initiateProgram();
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Test");
        BorderPane root = new BorderPane();
        //Pane root = new Pane();
        explanationClockTest(root);
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
    
    
    public Button createButton(String text){
    	
    	Button button = new Button();
		button.setText(text);
		button.setStyle("-fx-font: 36 arial");
		button.setMinSize(250, 150);
    
		return button;
    	
    }
    
 public void colorButtonGreen(Button button){
    	
	 	button.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
    	
    }
 
 public void colorButtonRed(Button button){
 	
	 	button.setStyle("-fx-font: 36 arial; -fx-base: #DF0101");
 	
 }

    	//Punkte für diesen Test
    	static int pointsClockTest = 0;

    	/**
    	 * Zeigt eine Erklärung zum folgenden Test an, die mit OK bestätigt werden muss
    	 * @param root
    	 */
    	public void explanationClockTest(final BorderPane root) {
    		
    		Text t = new Text();
    		t.setFont(new Font(36));
    		t.setTextAlignment(TextAlignment.CENTER);
    		t.setText("Du siehst gleich eine Uhr und darunter vier verschiedene Uhrzeiten.\n"
    				+ "Wähle unter den vier Uhrzeiten die richtige auf der Uhr angezeigte aus.");
    		
    		
    		HBox bottomBox = new HBox();
    		bottomBox.setSpacing(50);
    		bottomBox.setAlignment(Pos.CENTER);
    		bottomBox.getChildren().addAll(t);
    		root.setCenter(bottomBox);
    		
    		Timeline timeline = new Timeline();
    		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(7),
    		    new EventHandler<ActionEvent>() {
    		        @Override
    		        public void handle(ActionEvent event) {
    		        	fillUhr1(root);
    		        }
    		    }));
    		timeline.play();
    	}
    	

    	/**
    	 * Platziert die Elemente für den ersten Durchlauf
    	 * @param root
    	 */
    	private void fillUhr1(final BorderPane root) {

    		//Button erstellen
    		final Button button1 = createButton("15:30 Uhr");
    		final Button button2 = createButton("17:00 Uhr");
    		final Button button3 = createButton("16:00 Uhr");
    		final Button button4 = createButton("13:30 Uhr");

    		/*Events festlegen, bei richtiger Antwort 3 wird Button 3 grün und Punkte werden hochgezählt, 
    		bei falschen Antworten wird der gedrückte Button rot und Button 3 grün*/
    		button1.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent actionEvent) {
    				colorButtonRed(button1);
    				colorButtonGreen(button3);
    				fillUhr2(root);
    			}

    		});

    		button2.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent actionEvent) {
    				colorButtonRed(button2);
    				colorButtonGreen(button3);
    				fillUhr2(root);
    			}
    		});

    		button3.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent actionEvent) {
    				colorButtonGreen(button3);
    				pointsClockTest = +5;
    				fillUhr2(root);
    			}
    		});

    		button4.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent actionEvent) {
    				colorButtonRed(button4);
    				colorButtonGreen(button3);
    				fillUhr2(root);
    			}
    		});
    		
    		//Uhr und Buttons auf dem Bildschirm platzieren
    		Image img = new Image("file:img/Uhr16_00.png");
    		ImageView imgView = new ImageView();
    		imgView.setImage(img);
    		root.getChildren().clear();
    		root.setCenter(imgView);
    		HBox bottomBox = new HBox();
    		bottomBox.setSpacing(50);
    		bottomBox.setAlignment(Pos.CENTER);
    		bottomBox.getChildren().addAll(button1, button2, button3, button4);
    		root.setBottom(bottomBox);
    		
    		//Timer zum antworten innerhalb von 5 Sekunden festlegen
    		Timeline timeline = new Timeline();
    		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(5),
    		    new EventHandler<ActionEvent>() {
    		        @Override
    		        public void handle(ActionEvent event) {
    		           fillUhr2(root);
    		        }
    		    }));
    		timeline.play();

    	}

    	/**
    	 * Platziert die Elemente für den zweiten Durchlauf
    	 * @param root
    	 */
    	private void fillUhr2(final BorderPane root) {

    		//Button erstellen
    		final Button button1 = createButton("12:30 Uhr");
    		final Button button2 = createButton("08:30 Uhr");
    		final Button button3 = createButton("19:00 Uhr");
    		final Button button4 = createButton("21:00 Uhr");
    		
    		/*Events festlegen, bei richtiger Antwort 2 wird Button 2 grün und Punkte werden hochgezählt, 
    		bei falschen Antworten wird der gedrückte Button rot und Button 2 grün*/
    		button1.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent actionEvent) {
    				colorButtonRed(button1);
    				colorButtonGreen(button2);
    				fillUhr3(root);
    			}

    		});

    		button2.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent actionEvent) {
    				colorButtonGreen(button2);
    				pointsClockTest = +10;
    				fillUhr3(root);
    			}
    		});

    		button3.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent actionEvent) {
    				colorButtonRed(button3);
    				colorButtonGreen(button2);
    				fillUhr3(root);
    			}
    		});

    		button4.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent actionEvent) {
    				colorButtonRed(button4);
    				colorButtonGreen(button2);
    				fillUhr3(root);
    			}
    		});

    		//Uhr und Buttons auf dem Bildschirm platzieren
    		Image img = new Image("file:img/Uhr08_30.png");
    		ImageView imgView = new ImageView();
    		imgView.setImage(img);
    		root.getChildren().clear();
    		root.setCenter(imgView);
    		HBox bottomBox = new HBox();
    		bottomBox.setSpacing(50);
    		bottomBox.setAlignment(Pos.CENTER);
    		bottomBox.getChildren().addAll(button1, button2, button3, button4);
    		root.setBottom(bottomBox);
    		
    		//Timer zum antworten innerhalb von 5 Sekunden festlegen
    		Timeline timeline = new Timeline();
    		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(5),
    		    new EventHandler<ActionEvent>() {
    		        @Override
    		        public void handle(ActionEvent event) {
    		           fillUhr3(root);
    		        }
    		    }));
    		timeline.play();

    	}
    	
    	/**
    	 * Platziert die Elemente für den dritten Durchlauf
    	 * @param root
    	 */
    	private void fillUhr3(final BorderPane root) {

    		//Button erstellen
    		final Button button1 = createButton("11:00 Uhr");
    		final Button button2 = createButton("16:00 Uhr");
    		final Button button3 = createButton("17:30 Uhr");
    		final Button button4 = createButton("10:30 Uhr");

    		/*Events festlegen, bei richtiger Antwort 1 wird Button 1 grün und Punkte werden hochgezählt, 
    		bei falschen Antworten wird der gedrückte Button rot und Button 1 grün*/
    		button1.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent actionEvent) {
    				colorButtonGreen(button1);
    				pointsClockTest = +5;
    				//TODO nächsten Test starten;
    			}

    		});

    		button2.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent actionEvent) {
    				colorButtonRed(button2);
    				colorButtonGreen(button1);
    				//TODO nächsten Test starten;
    			}
    		});

    		button3.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent actionEvent) {
    				colorButtonRed(button3);
    				colorButtonGreen(button1);
    				//TODO nächsten Test starten;
    			}
    		});

    		button4.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent actionEvent) {
    				colorButtonRed(button4);
    				colorButtonGreen(button1);
    				//TODO nächsten Test starten;
    			}
    		});

    		//Uhr und Buttons auf dem Bildschirm platzieren
    		Image img = new Image("file:img/Uhr11_00.png");
    		ImageView imgView = new ImageView();
    		imgView.setImage(img);
    		root.getChildren().clear();
    		root.setCenter(imgView);
    		HBox bottomBox = new HBox();
    		bottomBox.setSpacing(50);
    		bottomBox.setAlignment(Pos.CENTER);
    		bottomBox.getChildren().addAll(button1, button2, button3, button4);
    		root.setBottom(bottomBox);

    		//Timer zum antworten innerhalb von 5 Sekunden festlegen
    		Timeline timeline = new Timeline();
    		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(5),
    		    new EventHandler<ActionEvent>() {
    		        @Override
    		        public void handle(ActionEvent event) {
    		        	//TODO nächsten Test starten;
    		        }
    		    }));
    		timeline.play();
    	}
}
