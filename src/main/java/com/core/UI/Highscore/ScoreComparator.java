package com.core.UI.Highscore;

/**
 * Created by Thomas on 09.11.2015.
 */
/*
    compare 2 objects of the type score.
    -1 means the first score is greater than the 2nd one, 1  means it's smaller and 0 means it's equal.
 */
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