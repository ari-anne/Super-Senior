package com.cpsc.supersenior.entitydata;

import com.cpsc.supersenior.tools.GameStage;

public class RunnerUserData extends UserData {

    public RunnerUserData(float width, float height) {
        actorType = GameStage.ActorType.RUNNER;
        this.width = width;
        this.height = height;
    }

}
