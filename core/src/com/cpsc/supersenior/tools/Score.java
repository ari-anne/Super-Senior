package com.cpsc.supersenior.tools;

import com.badlogic.gdx.physics.box2d.Body;
import com.cpsc.supersenior.entitydata.CoinUserData;

public class Score {

    private static int score;

    public Score() {
        score = 0;
    }

    public static void addScore(Body body) {
        CoinUserData coinData = (CoinUserData) body.getUserData();

        switch(coinData.getCoinType()) {
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
        }

    }

    public static int getScore() {
        return score;
    }
}
