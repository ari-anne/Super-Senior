package com.cpsc.supersenior.entitydata;

import com.cpsc.supersenior.tools.GameStage;

public class CoinUserData extends UserData {

    public boolean toDelete;

    public CoinUserData(float width, float height) {
        actorType = GameStage.ActorType.COIN;
        this.width = width;
        this.height = height;
        toDelete = false;
    }

}
