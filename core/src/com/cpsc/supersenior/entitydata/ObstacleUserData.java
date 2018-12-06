package com.cpsc.supersenior.entitydata;

import com.cpsc.supersenior.tools.GameStage;

public class ObstacleUserData extends UserData {

    private ObstacleType obstacleType;

    public ObstacleUserData(ObstacleType obstacleType, float width, float height) {
        actorType = GameStage.ActorType.OBSTACLE;
        this.obstacleType = obstacleType;
        this.width = width;
        this.height = height;
    }

}
