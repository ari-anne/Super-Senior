package com.cpsc.supersenior.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.tools.GameStage;

public class Ground extends Actor {

    public static final float X = 0;
    public static final float Y = 0;
    public static final float WIDTH = 45f;
    public static final float HEIGHT = 5f;
    public static final float DENSITY = 0f;

    Body body;

    GameStage.UserDataType type;

    public Ground(Body body) {
        this.body = body;
        type = GameStage.UserDataType.GROUND;
    }

    public static Body createGround(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(0, 0);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH/2, HEIGHT/2);

        Body body = world.createBody(bodyDef);
        body.createFixture(shape, DENSITY);

        shape.dispose();

        return body;
    }

}
