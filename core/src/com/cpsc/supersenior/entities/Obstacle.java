package com.cpsc.supersenior.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.entitydata.ActorSubtype;
import com.cpsc.supersenior.tools.Randomize;
import com.cpsc.supersenior.entitydata.ObstacleUserData;

public class Obstacle extends Actor {

    public static final ActorSubtype.ObstacleType OBSTACLE_TYPE = Randomize.obstacleType();

    Body body;
    Vector2 linearVelocity;

    public Obstacle(World world) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(OBSTACLE_TYPE.x, OBSTACLE_TYPE.y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(OBSTACLE_TYPE.width, OBSTACLE_TYPE.height);

        body = world.createBody(bodyDef);
        body.createFixture(shape, OBSTACLE_TYPE.density);
        body.setUserData(new ObstacleUserData(OBSTACLE_TYPE, OBSTACLE_TYPE.width, OBSTACLE_TYPE.height));

        body.resetMassData();
        shape.dispose();
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(linearVelocity);
    }

}
