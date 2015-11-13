package com.core;

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

/**
 * Projekt Schwankst du Schon UI für die 5 Tests
 *
 */
@SuppressWarnings("restriction")
public class Main extends Application {

	BorderPane root = new BorderPane();

	BorderPane startScreen = fillPaneWithStartScreenComponents();

	// Initialisieren der Panes für den Uhrentest
	BorderPane explanationClockTest = fillPaneWithExplanationClockTest();
	BorderPane clockTestClock1 = fillPaneWithClock1();
	BorderPane clockTestClock2 = fillPaneWithClock2();
	BorderPane clockTestClock3 = fillPaneWithClock3();

	// Initialisieren der Panes für den Rechentest
	BorderPane explanationCalculateTest = fillPaneWithExplanationCalculateTest();
	BorderPane calculateTestCalculate1 = fillPaneWithCalculate1();
	BorderPane calculateTestCalculate2 = fillPaneWithCalculate2();

	// Punkte für den Uhrentest
	// TODO Ändern global
	int pointsClockTest = 0;

	/**
	 * @author Jonas Siefker
	 * @param args
	 */
	public static void main(String args[]) {
		launch(args);
	}

	/**
	 * @author Jonas Siefker
	 */
	@Override
	public void start(Stage primaryStage) {
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

				// Timer von 8 Sekunden bevor der Bildschirm zum ersten Test
				// wechselt
				Runnable r = new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(8000);
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									root.getChildren().remove(
											explanationClockTest);
									initiateClockTestPhase1();
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

	/**
	 * Erstellt einen Button
	 * 
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

	/**
	 * Startet die erste Uhr
	 */
	public void initiateClockTestPhase1() {

		root.setCenter(clockTestClock1);

		// Timer von 5 Sekunden bevor der Bildschirm zur zweiten Uhr wechselt
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
						// TODO nächster Test
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
						// TODO nächster Test
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
				pointsClockTest = +5; //TODO
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
				pointsClockTest = +10; //TODO
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
				pointsClockTest = +5; //TODO
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

	static int pointsTestRechnen = 0;

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
				pointsTestRechnen = +10; //TODO
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
				pointsTestRechnen = +10; //TODO
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

}
