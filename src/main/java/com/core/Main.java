package com.core;

import java.util.Vector;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.application.Platform;
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

@SuppressWarnings("restriction")
public class Main extends Application {

	BorderPane root = new BorderPane();

	BorderPane startScreen = fillPaneWithStartScreenComponents();
	BorderPane explanationClockTest = fillPaneWithExplanationClockTest();
	BorderPane clockTestClock1 = fillPaneWithClock1();
	BorderPane clockTestClock2 = fillPaneWithClock2();
	BorderPane clockTestClock3 = fillPaneWithClock3();

	BorderPane explanationCalculateTest = fillPaneWithExplanationCalculateTest();
	BorderPane calculateTestCalculate1 = fillPaneWithCalculate1();
	BorderPane calculateTestCalculate2 = fillPaneWithCalculate2();
	
	Vector<BorderPane> m_panes = new Vector<BorderPane>();

	private int panenr = 0;
	private Timeline timeline = new Timeline();

	// Punkte für den Uhrentest
	int pointsClockTest = 0;

	/**
	 * @author Jonas Siefker
	 * @param args
	 */
	
	public void cyclePanes(){
		if(panenr == m_panes.size()){return;}
		panenr++;
	}
	
	public static void main(String args[]) {
		launch(args);
	}
	
	public BorderPane getNextPane() {
		return getPane(panenr);
	}
	
	public BorderPane getLastPane() {
		return getPane(panenr-1);
	}
	
	public BorderPane getPane(int i){
		if(i<0 || i>m_panes.size()){return null;}
		return m_panes.get(i);
	}

	/**
	 * @author Jonas Siefker
	 */
	@Override
	public void start(Stage primaryStage) {
		m_panes.add(clockTestClock1);
		m_panes.add(clockTestClock2);
		m_panes.add(clockTestClock3);
		//m_panes.add(clockTestClock1);
		primaryStage.setTitle("Test");
		root.setCenter(startScreen);
		primaryStage.setScene(new Scene(root));
		primaryStage.setFullScreen(true);
		primaryStage.show();
	}

	/**
	 * @author Jonas Siefker
	 * @return
	 */
	public BorderPane fillPaneWithStartScreenComponents() {

		final BorderPane startScreen = new BorderPane();

		Button button = new Button();
		button = createButton("Test 1");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				root.getChildren().remove(startScreen);
				root.setCenter(explanationClockTest);

				Runnable r = new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(8000);
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									root.getChildren().remove(explanationClockTest);
									BorderPane nextpane = getNextPane();
									if(nextpane!=null){root.setCenter(nextpane);}
									setNextPaneAndActivateNewTimeline();
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
	
	public void setNextPaneAndActivateNewTimeline(){
		timeline.getKeyFrames().add(
				new KeyFrame(Duration.seconds(5),
						new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								cyclePanes();
								BorderPane lastpane = getLastPane();
								BorderPane nextpane = getNextPane();
								if(lastpane!=null){root.getChildren().remove(lastpane);}
								if(nextpane!=null){root.setCenter(nextpane);}
								setNextPaneAndActivateNewTimeline();
							}
						}));
	}

	/**
	 * @author Caroline Neuner
	 * @param text
	 * @return Button
	 */
	public Button createButton(String text) {

		Button button = new Button();
		button.setText(text);
		button.setStyle("-fx-font: 36 arial");
		button.setMinSize(250, 150);

		return button;

	}

	/**
	 * @author Caroline Neuner
	 * @param button
	 */
	public void colorButtonGreen(Button button) {

		button.setStyle("-fx-font: 36 arial; -fx-base: #74DF00");

	}

	/**
	 * @author Caroline Neuner
	 * @param button
	 */
	public void colorButtonRed(Button button) {

		button.setStyle("-fx-font: 36 arial; -fx-base: #DF0101");

	}

	/**
	 * Zeigt eine Erklärung zum folgenden Uhrentest an
	 * 
	 * @author Caroline Neuner
	 * @return
	 */
	public BorderPane fillPaneWithExplanationClockTest() {

		final BorderPane explanationClockTest = new BorderPane();

		Text t = new Text();
		t.setFont(new Font(36));
		t.setTextAlignment(TextAlignment.CENTER);
		t.setText("Du siehst gleich eine Uhr und darunter vier verschiedene Uhrzeiten.\n"
				+ "Wähle unter den vier Uhrzeiten die richtige auf der Uhr angezeigte aus.");

		HBox bottomBox = new HBox();
		bottomBox.setSpacing(50);
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.getChildren().addAll(t);
		explanationClockTest.setCenter(bottomBox);

		// Timeline timeline = new Timeline();
		// timeline.getKeyFrames().add(
		// new KeyFrame(Duration.seconds(7),
		// new EventHandler<ActionEvent>() {
		// @Override
		// public void handle(ActionEvent event) {
		// root.getChildren().remove(explanationClockTest);
		// root.setCenter(clockTestClock1);
		// }
		// }));
		// timeline.play();

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

		// Button erstellen
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
				root.getChildren().remove(clockTestClock1);
				root.setCenter(clockTestClock2);
			}

		});

		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				colorButtonRed(button2);
				colorButtonGreen(button3);
				root.getChildren().remove(clockTestClock1);
				root.setCenter(clockTestClock2);
			}
		});

		button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				colorButtonGreen(button3);
				pointsClockTest = +5;
				root.getChildren().remove(clockTestClock1);
				root.setCenter(clockTestClock2);
			}
		});

		button4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				colorButtonRed(button4);
				colorButtonGreen(button3);
				root.getChildren().remove(clockTestClock1);
				root.setCenter(clockTestClock2);
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

		// Timer zum Antworten innerhalb von 5 Sekunden festlegen
//		timeline1 = new Timeline();
//		timeline1.getKeyFrames().add(
//				new KeyFrame(Duration.seconds(5),
//						new EventHandler<ActionEvent>() {
//							@Override
//							public void handle(ActionEvent event) {
//								root.getChildren().remove(clockTestClock1);
//
//								root.setCenter(clockTestClock2);
//
//							}
//						}));

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

		// Button erstellen
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
				root.getChildren().remove(clockTestClock2);
				root.setCenter(clockTestClock3);
			}

		});

		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				colorButtonGreen(button2);
				pointsClockTest = +10;
				root.getChildren().remove(clockTestClock2);
				root.setCenter(clockTestClock3);
			}
		});

		button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				colorButtonRed(button3);
				colorButtonGreen(button2);
				root.getChildren().remove(clockTestClock2);
				root.setCenter(clockTestClock3);
			}
		});

		button4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				colorButtonRed(button4);
				colorButtonGreen(button2);
				root.getChildren().remove(clockTestClock2);
				root.setCenter(clockTestClock3);
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

		// Timer zum Antworten innerhalb von 5 Sekunden festlegen
//		timeline2 = new Timeline();
//		timeline2.getKeyFrames().add(
//				new KeyFrame(Duration.seconds(5),
//						new EventHandler<ActionEvent>() {
//							@Override
//							public void handle(ActionEvent event) {
//								root.getChildren().remove(clockTestClock2);
//								timeline2.stop();
//								root.setCenter(clockTestClock3);
//								timeline3.play();
//							}
//						}));

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

		// Button erstellen
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
				pointsClockTest = +5;
				// TODO nächsten Test starten;
			}

		});

		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				colorButtonRed(button2);
				colorButtonGreen(button1);
				// TODO nächsten Test starten;
			}
		});

		button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				colorButtonRed(button3);
				colorButtonGreen(button1);
				// TODO nächsten Test starten;
			}
		});

		button4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				colorButtonRed(button4);
				colorButtonGreen(button1);
				// TODO nächsten Test starten;
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

		// Timer zum antworten innerhalb von 5 Sekunden festlegen
//		timeline3 = new Timeline();
//		timeline3.getKeyFrames().add(
//				new KeyFrame(Duration.seconds(5),
//						new EventHandler<ActionEvent>() {
//							@Override
//							public void handle(ActionEvent event) {
//								timeline3.stop();
//								// TODO nächsten Test starten;
//							}
//						}));

		return clockTestClock3;
	}

	static int pointsTestRechnen = 0;

	/**
	 * Zeigt eine Erklärung zum Rechnentest
	 * 
	 * @author Caroline Neuner
	 * @return
	 */
	public BorderPane fillPaneWithExplanationCalculateTest() {

		BorderPane explanationCalculateTest = new BorderPane();

		Text t = new Text();
		t.setFont(new Font(36));
		t.setTextAlignment(TextAlignment.CENTER);
		t.setText("Du siehst gleich vier verschiedene Rechnenaufgaben mit Ergebnis.\n"
				+ "Wähle unter den vier Aufgaben die aus, die das richtige Ergebnis hat.");

		explanationCalculateTest.setCenter(t);

		return explanationCalculateTest;
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
				root.getChildren().remove(calculateTestCalculate1);
				root.setCenter(calculateTestCalculate2);
			}

		});

		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				colorButtonRed(button2);
				colorButtonGreen(button4);
				root.getChildren().remove(calculateTestCalculate1);
				root.setCenter(calculateTestCalculate2);
			}
		});

		button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				colorButtonRed(button3);
				colorButtonGreen(button4);
				root.getChildren().remove(calculateTestCalculate1);
				root.setCenter(calculateTestCalculate2);
			}
		});

		button4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				colorButtonGreen(button4);
				pointsTestRechnen = +10;
				root.getChildren().remove(calculateTestCalculate1);
				root.setCenter(calculateTestCalculate2);
			}
		});

		// Buttons auf dem Bildschirm platzieren
		HBox bottomBox = new HBox();
		bottomBox.setSpacing(50);
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.getChildren().addAll(button1, button2, button3, button4);
		calculateTestCalculate1.setCenter(bottomBox);

		// Timer zum antworten innerhalb von 10 Sekunden festlegen
//		Timeline timeline = new Timeline();
//		timeline.getKeyFrames().add(
//				new KeyFrame(Duration.seconds(10),
//						new EventHandler<ActionEvent>() {
//							@Override
//							public void handle(ActionEvent event) {
//								root.getChildren().remove(
//										calculateTestCalculate1);
//								root.setCenter(calculateTestCalculate2);
//							}
//						}));
//		timeline.play();

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
				pointsTestRechnen = +10;
				// TODO nächster Test
			}

		});

		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				colorButtonRed(button2);
				colorButtonGreen(button1);
				// TODO nächster Test
			}
		});

		button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				colorButtonRed(button3);
				colorButtonGreen(button1);
				// TODO nächster Test
			}
		});

		button4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				colorButtonRed(button4);
				colorButtonGreen(button1);
				// TODO nächster Test
			}
		});

		// Buttons auf dem Bildschirm platzieren
		HBox bottomBox = new HBox();
		bottomBox.setSpacing(50);
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.getChildren().addAll(button1, button2, button3, button4);
		calculateTestCalculate2.setCenter(bottomBox);

		// Timer zum antworten innerhalb von 10 Sekunden festlegen
//		Timeline timeline = new Timeline();
//		timeline.getKeyFrames().add(
//				new KeyFrame(Duration.seconds(10),
//						new EventHandler<ActionEvent>() {
//							@Override
//							public void handle(ActionEvent event) {
//								// TODO nächster Test
//							}
//						}));
//		timeline.play();

		return calculateTestCalculate2;

	}

}
