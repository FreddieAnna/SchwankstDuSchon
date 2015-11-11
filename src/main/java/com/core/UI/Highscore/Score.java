package com.core.UI.Highscore;

/**
 * Created by Thomas on 09.11.2015.
 */
import java.io.Serializable;
/*
    To make an arraylist of the type Score that contains the name and score of a player.
  */

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