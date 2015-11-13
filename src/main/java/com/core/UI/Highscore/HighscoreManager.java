package com.core.UI.Highscore;

/**
 * Created by Thomas on 09.11.2015.
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class HighscoreManager {
    //arraylist des Typs "score" um mit den scores in der Klasse zu arbeiten
    private ArrayList<Score> scores;

    //name der Datei, in der die Hisghscores gespeichert werden
    private static final String HIGHSCORE_FILE = "scores.dat";

    //initialisierung eines in- und outputstream um mit der Datei zu arbeiten
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public HighscoreManager() {
        //initialisierung der scores-arraylist
        scores = new ArrayList<Score>();
    }
    public ArrayList<Score> getScores() {
        loadScoreFile();
        sort();
        return scores;
    }

     //erstellung eines Objektes "comparator" der Klasse ScoreComparator
     //sortieren der Arraylist "scores" mit Hilfe des "comparator"
    private void sort() {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
    }

    //scores zu der Scoredatei hinzufügen
    //neue scores werden zu der arraylist hinzugefügt und die Datei wird aktualisiert
    public void addScore(String name, int score) {
        loadScoreFile();
        scores.add(new Score(name, score));
        updateScoreFile();
    }

    public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            scores = (ArrayList<Score>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("[Load] FNF Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Load] IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[Load] CNF Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Load] IO Error: " + e.getMessage());
            }
        }
    }

    public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }


    //um die Highscores im Startbildschirm anzuzeigen
    public String getHighscoreStringinGUI() {
        String highscoreString = "";
        int max = 3;

        ArrayList<Score> scores;
        scores = getScores();

        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            highscoreString += (i + 1) + ".\t" + scores.get(i).getName() + "\t\t" + scores.get(i).getScore() + "\n";
            i++;
        }
        return highscoreString;
    }
    //um die Highscores im Endscreen anzuzeigen
    public String getHighscoreString() {
        String highscoreString = "";
        int max = 15;

        ArrayList<Score> scores;
        scores = getScores();

        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            highscoreString += (i + 1) + ".\t" + scores.get(i).getName() + "\t" + scores.get(i).getScore() + "\n";
            i++;
        }
        return highscoreString;
    }

}//End class