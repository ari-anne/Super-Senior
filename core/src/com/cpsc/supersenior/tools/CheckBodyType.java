package com.cpsc.supersenior.tools;

import com.badlogic.gdx.physics.box2d.Body;
import com.cpsc.supersenior.entitydata.UserData;

public class CheckBodyType {

    private static UserData userData;

    public static boolean isRunner(Body body) {
        userData = (UserData) body.getUserData();
        return userData.getActorType() == GameStage.ActorType.RUNNER && userData != null;
    }

    public static boolean isGround(Body body) {
        userData = (UserData) body.getUserData();
        return userData.getActorType() == GameStage.ActorType.GROUND && userData != null;
    }

    public static boolean isObstacle(Body body) {
        userData = (UserData) body.getUserData();
        return userData.getActorType() == GameStage.ActorType.OBSTACLE && userData != null;
    }

    public static boolean isCoin(Body body) {
        userData = (UserData) body.getUserData();
        return userData.getActorType() == GameStage.ActorType.COIN && userData != null;
    }
}
