package com.cpsc.supersenior.entitydata;

import com.cpsc.supersenior.tools.GameStage;

public class ObstacleUserData extends UserData {

    ActorSubtype.ObstacleType obstacleType;

    public ObstacleUserData(ActorSubtype.ObstacleType obstacleType, float width, float height) {
        actorType = GameStage.ActorType.OBSTACLE;
        this.obstacleType = obstacleType;
        this.width = width;
        this.height = height;
    }

}
