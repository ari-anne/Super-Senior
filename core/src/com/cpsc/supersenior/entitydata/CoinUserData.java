package com.cpsc.supersenior.entitydata;

import com.cpsc.supersenior.tools.GameStage;

public class CoinUserData extends UserData {

    ActorSubtype.CoinType coinType;

    public CoinUserData(ActorSubtype.CoinType coinType, float width, float height) {
        actorType = GameStage.ActorType.COIN;
        this.coinType = coinType;
        this.width = width;
        this.height = height;
    }

    public ActorSubtype.CoinType getCoinType() {
        return coinType;
    }
}
