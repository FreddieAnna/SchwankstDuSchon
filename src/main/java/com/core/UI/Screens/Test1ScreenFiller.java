//package com.core.UI.Screens;
//
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.HBox;
//import javafx.geometry.Pos;
//import javafx.scene.layout.BorderPane;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.util.Duration;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.scene.text.TextAlignment;
//
///**
// * Created by Neuner on 03.11.2015.
// */
//@SuppressWarnings("restriction")
//public class Test1ScreenFiller {
//
//	//Punkte für diesen Test
//	static int pointsTestUhr = 0;
//
//	/**
//	 * Zeigt eine Erklärung zum folgenden Test an, die mit OK bestätigt werden muss
//	 * @param root
//	 */
//	public static void fillPaneWithTest1ScreenComponents(final BorderPane root) {
//		
//		Text t = new Text();
//		t.setFont(new Font(36));
//		t.setTextAlignment(TextAlignment.CENTER);
//		t.setText("Du siehst gleich eine Uhr und darunter vier verschiedene Uhrzeiten.\n"
//				+ "Wähle unter den vier Uhrzeiten die richtige auf der Uhr angezeigte aus.");
//		
//		Button buttonOK = new Button();
//		//buttonOK = createButton("OK");
//		buttonOK.setText("OK");
//		buttonOK.setStyle("-fx-font: 36 arial");
//		buttonOK.setMinSize(250, 150);
//		
//		buttonOK.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				fillUhr1(root);
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
//	private static void fillUhr1(final BorderPane root) {
//
//		//Button erstellen und Text, Textgröße und Buttongröße festlegen
//		final Button button1 = new Button();
//		button1.setText("15:30 Uhr");
//		button1.setStyle("-fx-font: 36 arial");
//		button1.setMinSize(250, 150);
//
//		final Button button2 = new Button();
//		button2.setText("17:00 Uhr");
//		button2.setStyle("-fx-font: 36 arial");
//		button2.setMinSize(250, 150);
//
//		final Button button3 = new Button();
//		button3.setText("16:00 Uhr");
//		button3.setStyle("-fx-font: 36 arial");
//		button3.setMinSize(250, 150);
//
//		final Button button4 = new Button();
//		button4.setText("13:30 Uhr");
//		button4.setStyle("-fx-font: 36 arial");
//		button4.setMinSize(250, 150);
//
//		/*Events festlegen, bei richtiger Antwort 3 wird Button 3 grün und Punkte werden hochgezählt, 
//		bei falschen Antworten wird der gedrückte Button rot und Button 3 grün*/
//		button1.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button1.setStyle("-fx-font: 36 arial; -fx-base: #DF0101");
//				button3.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				fillUhr2(root);
//			}
//
//		});
//
//		button2.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button2.setStyle("-fx-font: 36 arial; -fx-base: #DF0101");
//				button3.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				fillUhr2(root);
//			}
//		});
//
//		button3.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button3.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				pointsTestUhr = +5;
//				fillUhr2(root);
//			}
//		});
//
//		button4.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button4.setStyle("-fx-font: 36 arial; -fx-base: #DF0101");
//				button3.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				fillUhr2(root);
//			}
//		});
//		
//		//Uhr und Buttons auf dem Bildschirm platzieren
//		Image img = new Image("file:img/Uhr16_00.png");
//		ImageView imgView = new ImageView();
//		imgView.setImage(img);
//		root.getChildren().clear();
//		root.setCenter(imgView);
//		HBox bottomBox = new HBox();
//		bottomBox.setSpacing(50);
//		bottomBox.setAlignment(Pos.CENTER);
//		bottomBox.getChildren().addAll(button1, button2, button3, button4);
//		root.setBottom(bottomBox);
//		
//		//Timer zum antworten innerhalb von 5 Sekunden festlegen
//		Timeline timeline = new Timeline();
//		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(5),
//		    new EventHandler<ActionEvent>() {
//		        @Override
//		        public void handle(ActionEvent event) {
//		           fillUhr2(root);
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
//	private static void fillUhr2(final BorderPane root) {
//
//		//Button erstellen und Text, Textgröße und Buttongröße festlegen
//		final Button button1 = new Button();
//		button1.setText("12:30 Uhr");
//		button1.setStyle("-fx-font: 36 arial");
//		button1.setMinSize(250, 150);
//
//		final Button button2 = new Button();
//		button2.setText("08:30 Uhr");
//		button2.setStyle("-fx-font: 36 arial");
//		button2.setMinSize(250, 150);
//
//		final Button button3 = new Button();
//		button3.setText("19:00 Uhr");
//		button3.setStyle("-fx-font: 36 arial");
//		button3.setMinSize(250, 150);
//
//		final Button button4 = new Button();
//		button4.setText("21:00 Uhr");
//		button4.setStyle("-fx-font: 36 arial");
//		button4.setMinSize(250, 150);
//		
//		/*Events festlegen, bei richtiger Antwort 2 wird Button 2 grün und Punkte werden hochgezählt, 
//		bei falschen Antworten wird der gedrückte Button rot und Button 2 grün*/
//		button1.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button1.setStyle("-fx-font: 36 arial; -fx-base: #DF0101");
//				button2.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				fillUhr3(root);
//			}
//
//		});
//
//		button2.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button2.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				pointsTestUhr = +10;
//				fillUhr3(root);
//			}
//		});
//
//		button3.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button3.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				button2.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				fillUhr3(root);
//			}
//		});
//
//		button4.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button4.setStyle("-fx-font: 36 arial; -fx-base: #DF0101");
//				button2.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				fillUhr3(root);
//			}
//		});
//
//		//Uhr und Buttons auf dem Bildschirm platzieren
//		Image img = new Image("file:img/Uhr08_30.png");
//		ImageView imgView = new ImageView();
//		imgView.setImage(img);
//		root.getChildren().clear();
//		root.setCenter(imgView);
//		HBox bottomBox = new HBox();
//		bottomBox.setSpacing(50);
//		bottomBox.setAlignment(Pos.CENTER);
//		bottomBox.getChildren().addAll(button1, button2, button3, button4);
//		root.setBottom(bottomBox);
//		
//		//Timer zum antworten innerhalb von 5 Sekunden festlegen
//		Timeline timeline = new Timeline();
//		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(5),
//		    new EventHandler<ActionEvent>() {
//		        @Override
//		        public void handle(ActionEvent event) {
//		           fillUhr3(root);
//		        }
//		    }));
//		timeline.play();
//
//	}
//	
//	/**
//	 * Platziert die Elemente für den dritten Durchlauf
//	 * @param root
//	 */
//	private static void fillUhr3(final BorderPane root) {
//
//		//Button erstellen und Text, Textgröße und Buttongröße festlegen
//		final Button button1 = new Button();
//		button1.setText("11:00 Uhr");
//		button1.setStyle("-fx-font: 36 arial");
//		button1.setMinSize(250, 150);
//
//		final Button button2 = new Button();
//		button2.setText("16:00 Uhr");
//		button2.setStyle("-fx-font: 36 arial");
//		button2.setMinSize(250, 150);
//
//		final Button button3 = new Button();
//		button3.setText("17:30 Uhr");
//		button3.setStyle("-fx-font: 36 arial");
//		button3.setMinSize(250, 150);
//
//		final Button button4 = new Button();
//		button4.setText("10:30 Uhr");
//		button4.setStyle("-fx-font: 36 arial");
//		button4.setMinSize(250, 150);
//
//		/*Events festlegen, bei richtiger Antwort 1 wird Button 1 grün und Punkte werden hochgezählt, 
//		bei falschen Antworten wird der gedrückte Button rot und Button 1 grün*/
//		button1.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button1.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				pointsTestUhr = +5;
//				//TODO nächsten Test starten;
//			}
//
//		});
//
//		button2.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button2.setStyle("-fx-font: 36 arial; -fx-base: #DF0101");
//				button1.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				//TODO nächsten Test starten;
//			}
//		});
//
//		button3.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button3.setStyle("-fx-font: 36 arial; -fx-base: #DF0101");
//				button1.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				//TODO nächsten Test starten;
//			}
//		});
//
//		button4.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				button4.setStyle("-fx-font: 36 arial; -fx-base: #DF0101");
//				button1.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");
//				//TODO nächsten Test starten;
//			}
//		});
//
//		//Uhr und Buttons auf dem Bildschirm platzieren
//		Image img = new Image("file:img/Uhr11_00.png");
//		ImageView imgView = new ImageView();
//		imgView.setImage(img);
//		root.getChildren().clear();
//		root.setCenter(imgView);
//		HBox bottomBox = new HBox();
//		bottomBox.setSpacing(50);
//		bottomBox.setAlignment(Pos.CENTER);
//		bottomBox.getChildren().addAll(button1, button2, button3, button4);
//		root.setBottom(bottomBox);
//
//		//Timer zum antworten innerhalb von 5 Sekunden festlegen
//		Timeline timeline = new Timeline();
//		timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(5),
//		    new EventHandler<ActionEvent>() {
//		        @Override
//		        public void handle(ActionEvent event) {
//		        	//TODO nächsten Test starten;
//		        }
//		    }));
//		timeline.play();
//	}
//}