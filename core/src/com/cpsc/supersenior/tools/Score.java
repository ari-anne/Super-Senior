package com.cpsc.supersenior.tools;

import com.cpsc.supersenior.entitydata.ActorSubtype;

public class Score {

    static int score;

    public Score() {
        score = 0;
    }

    public static void addScore(ActorSubtype.CoinType coinType) {

        switch(coinType) {
            case COIN:
                score += 1;
                break;
//            case GREEN_GEM:
//                score += 10;
//                break;
//            case RED_GEM:
//                score += 50;
//                break;
//            case GREEN_DIAMOND:
//                score += 100;
//                break;
//            case RED_DIAMOND:
//                score += 500;
//                break;
        }

    }

    public int getScore() {
        return score;
    }
}
