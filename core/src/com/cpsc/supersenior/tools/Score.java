package com.cpsc.supersenior.tools;

public class Score {

    private static int score;

    public Score() {
        score = 0;
    }

    public static void addScore() {
        score += 1;
    }

    public static int getScore() {
        return score;
    }

    public static void resetScore() {
        score = 0;
    }
}
