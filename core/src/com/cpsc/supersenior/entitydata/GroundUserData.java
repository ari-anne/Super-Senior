package com.cpsc.supersenior.entitydata;

import com.cpsc.supersenior.tools.GameStage;

public class GroundUserData extends UserData {
    public GroundUserData(float width, float height) {
        actorType = GameStage.ActorType.GROUND;
        this.width = width;
        this.height = height;
    }
}
