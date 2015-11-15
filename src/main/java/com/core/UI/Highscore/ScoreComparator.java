package com.core.UI.Highscore;

/**
 * Created by Thomas on 09.11.2015.
 */

//2 Objekte vom Typ score vergleichen
//-1 bedeutet der erste score ist größer, 1 bedeutet der zweite score ist größer. 0 bedeutet sie sind gleich
import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {
    public int compare(Score score1, Score score2) {

        int sc1 = score1.getScore();
        int sc2 = score2.getScore();

        if (sc1 > sc2){
            return -1;
        }else if (sc1 < sc2){
            return 1;
        }else{
            return 0;
        }
    }

}//End class
