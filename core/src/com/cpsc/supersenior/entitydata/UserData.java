package com.cpsc.supersenior.entitydata;

import com.badlogic.gdx.math.Vector2;
import com.cpsc.supersenior.tools.GameStage;

public class UserData {

    GameStage.ActorType actorType;
    Vector2 linearVelocity;

    float width;
    float height;

    public UserData() {

    }

    public UserData(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public GameStage.ActorType getActorType() {
        return actorType;
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}
