//package com.core.UI.Screens;
//
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.control.Button;
//import javafx.scene.layout.HBox;
//import javafx.geometry.Pos;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.scene.text.TextAlignment;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.util.Duration;
//
///**
// * Created by Neuner on 03.11.2015.
// */
//@SuppressWarnings("restriction")
//public class Test2ScreenFiller {
//
//	//Punkte für diesen Test
//	static int pointsTestRechnen = 0;
//
//	/**
//	 * Zeigt eine Erklärung zum folgenden Test an, die mit OK bestätigt werden muss
//	 * @param root
//	 */
//	public static void fillPaneWithTest2ScreenComponents(final BorderPane root) {
//		
//		Text t = new Text();
//		t.setFont(new Font(36));
//		t.setTextAlignment(TextAlignment.CENTER);
//		t.setText("Du siehst gleich vier verschiedene Rechnenaufgaben mit Ergebnis.\n"
//				+ "Wähle unter den vier Aufgaben die aus, die das richtige Ergebnis hat.");
//		
//		Button buttonOK = new Button();
//		buttonOK.setText("OK");
//		buttonOK.setStyle("-fx-font: 36 arial");
//		buttonOK.setMinSize(250, 150);
//		
//		buttonOK.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				fillRechnen1(root);
//			}
//		});
//		
//		HBox bottomBox = new HBox();
//		bottomBox.setSpacing(50);
//		bottomBox.setAlignment(Pos.CENTER);
//		bottomBox.getChildren().addAll(buttonOK);
//		root.getChildren().clear();
//		root.setBottom(bottomBox);
//		root.setCenter(t);
//	}
//
//	/**
//	 * Platziert die Elemente für den ersten Durchlauf
//	 * @param root
//	 */
//	private static void fillRechnen1(final BorderPane root) {
//
//		//Button erstellen und Text, Textgröße und Buttongröße festlegen
//		final Button button1 = new Button();
//		button1.setText("5 + 3 = 7");
//		button1.setStyle("-fx-font: 36 arial");
//		button1.setMinSize(250, 150);
//
//		final Button button2 = new Button();
//		button2.setText("7 - 3 = 3");
//		button2.setStyle("-fx-font: 36 arial");
//		button2.setMinSize(250, 150);
//
//		final Button button3 = new Button();
//		button3.setText("9 - 2 = 6");
//		button3.setStyle("-fx-font: 36 arial");
//		button3.setMinSize(250, 150);
//
//		final Button button4 = new Button();
//		button4.setText("3 + 6 = 9");
//		button4.setStyle("-fx-font: 36 arial");
//		button4.setMinSize(250, 150);
//
//		/*Events festlegen, bei richtiger Antwort 4 wird Button 4 grün und Punkte werden hochgezählt, 
//		bei falschen Antworten wird der gedrückte Button rot und Button 4 grün*/
//		button1.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button1.setStyle("-fx-font: 36 arial; -fx-base: #DF0101");
//				button4.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				fillRechnen2(root);
//			}
//
//		});
//
//		button2.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button2.setStyle("-fx-font: 36 arial; -fx-base: #DF0101");
//				button4.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				fillRechnen2(root);
//			}
//		});
//
//		button3.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button3.setStyle("-fx-font: 36 arial; -fx-base: #DF0101");
//				button4.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				fillRechnen2(root);
//			}
//		});
//
//		button4.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button4.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				pointsTestRechnen = +10;
//				fillRechnen2(root);
//			}
//		});
//		
//		//Buttons auf dem Bildschirm platzieren
//		buttonsPlatzieren(root, button1, button2, button3, button4);
//		
//		//Timer zum antworten innerhalb von 10 Sekunden festlegen
//		Timeline timeline = new Timeline();
//		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(10),
//		    new EventHandler<ActionEvent>() {
//		        @Override
//		        public void handle(ActionEvent event) {
//		        	fillRechnen2(root);
//		        }
//		    }));
//		timeline.play();
//
//	}
//
//	/**
//	 * Platziert die Elemente für den zweiten Durchlauf
//	 * @param root
//	 */
//	private static void fillRechnen2(final BorderPane root) {
//
//		//Button erstellen und Text, Textgröße und Buttongröße festlegen
//		final Button button1 = new Button();
//		button1.setText("6 + 4 = 10");
//		button1.setStyle("-fx-font: 36 arial");
//		button1.setMinSize(250, 150);
//
//		final Button button2 = new Button();
//		button2.setText("8 - 3 = 6");
//		button2.setStyle("-fx-font: 36 arial");
//		button2.setMinSize(250, 150);
//
//		final Button button3 = new Button();
//		button3.setText("5 + 6 = 10");
//		button3.setStyle("-fx-font: 36 arial");
//		button3.setMinSize(250, 150);
//
//		final Button button4 = new Button();
//		button4.setText("7 - 3 = 5");
//		button4.setStyle("-fx-font: 36 arial");
//		button4.setMinSize(250, 150);
//		
//		/*Events festlegen, bei richtiger Antwort 1 wird Button 1 grün und Punkte werden hochgezählt, 
//		bei falschen Antworten wird der gedrückte Button rot und Button 1 grün*/
//		button1.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button1.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				pointsTestRechnen = +10;
//				//TODO nächster Test
//			}
//
//		});
//
//		button2.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button2.setStyle("-fx-font: 36 arial; -fx-base: #DF0101");
//				button1.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				//TODO nächster Test
//			}
//		});
//
//		button3.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button3.setStyle("-fx-font: 36 arial; -fx-base: #DF0101");
//				button1.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				//TODO nächster Test
//			}
//		});
//
//		button4.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button4.setStyle("-fx-font: 36 arial; -fx-base: #DF0101");
//				button1.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				//TODO nächster Test
//			}
//		});
//
//		//Buttons auf dem Bildschirm platzieren
//		buttonsPlatzieren(root, button1, button2, button3, button4);
//		
//		//Timer zum antworten innerhalb von 10 Sekunden festlegen
//		Timeline timeline = new Timeline();
//		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(10),
//		    new EventHandler<ActionEvent>() {
//		        @Override
//		        public void handle(ActionEvent event) {
//		        	//TODO nächster Test
//		        }
//		    }));
//		timeline.play();
//
//	}
//	
//	/**
//	 * Platziert die Buttons
//	 * @param root
//	 * @param button1
//	 * @param button2
//	 * @param button3
//	 * @param button4
//	 */
//	public static void buttonsPlatzieren(final BorderPane root, Button button1, Button button2, Button button3, Button button4){
//		root.getChildren().clear();
//		HBox bottomBox = new HBox();
//		bottomBox.setSpacing(50);
//		bottomBox.setAlignment(Pos.CENTER);
//		bottomBox.getChildren().addAll(button1, button2, button3, button4);
//		root.setCenter(bottomBox);
//	}
//	
//}