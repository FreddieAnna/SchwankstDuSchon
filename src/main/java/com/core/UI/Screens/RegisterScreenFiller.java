package com.core.UI.Screens;

import com.core.UI.Highscore.HighscoreManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by Thomas on 09.11.2015.
 */
public class RegisterScreenFiller {

    public static String nickname;

    public void fillPaneWithRegisterScreen(final BorderPane root){

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
        grid.add(nickName, 0, 2);
        nickName.setFont(Font.font("Tahoma", FontWeight.NORMAL, 35));


        final TextField userNickname = new TextField();
        grid.add(userNickname, 1, 2);

        Button anmelden = new Button("Anmelden");
        HBox hbBtn = new HBox(30);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(anmelden);
        grid.add(hbBtn, 1, 3);


        final Label dieBesten3L = new Label("Die Besten 3:");
        grid.add(dieBesten3L, 0, 8);
        dieBesten3L.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));


        final Label dieBesten3 = new Label(hm2.getHighscoreStringinGUI());
        grid.add(dieBesten3, 1, 8);
        dieBesten3.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));


        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 7);

        anmelden.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
                actiontarget.setText("Registriert");
                nickname = userNickname.getText();
                System.out.print(nickname);
            }
        });

    }
}
