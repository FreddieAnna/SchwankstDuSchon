package com.core.UI.Highscore;

/**
 * Created by Thomas on 09.11.2015.
 */
import java.io.Serializable;

//um eine arraylist des Typs Score mit name und score eines Spielers zu erstellen
public class Score  implements Serializable {
    private int score;
    private String name;

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public Score(String name, int score) {
        this.score = score;
        this.name = name;
    }

}//End class
