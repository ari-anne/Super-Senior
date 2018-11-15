package com.cpsc.supersenior.entitydata;

import com.cpsc.supersenior.tools.GameStage;

public class GroundUserData extends UserData {

    ActorSubtype.GroundType groundType;

    public GroundUserData(ActorSubtype.GroundType groundType, float width, float height) {
        actorType = GameStage.ActorType.GROUND;
        this.groundType = groundType;
        this.width = width;
        this.height = height;
    }
}
