package com.cpsc.supersenior.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.entitydata.ObstacleType;
import com.cpsc.supersenior.tools.GameStage;
import com.cpsc.supersenior.tools.Randomize;
import com.cpsc.supersenior.entitydata.ObstacleUserData;

public class Obstacle extends Actor {

    private ObstacleType obstacleType;
    private Body body;
    private Vector2 linearVelocity;

    Texture obstacle;

    public Obstacle(World world) {
        obstacleType = Randomize.obstacleType();

        switch(obstacleType) {
            case SAW:
                obstacle = new Texture(Gdx.files.internal("obstacles/Saw.png"));
                break;
            case SHORT_SPIKE:
                obstacle = new Texture(Gdx.files.internal("obstacles/Spike_Small.png"));
                break;
            case LONG_SPIKE:
                obstacle = new Texture(Gdx.files.internal("obstacles/Spike_Tall.png"));
        }

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(obstacleType.x, obstacleType.y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(obstacleType.width/2, obstacleType.height/2);

        body = world.createBody(bodyDef);
        body.createFixture(shape, obstacleType.density);
        body.setUserData(new ObstacleUserData(obstacleType, obstacleType.width/2, obstacleType.height/2));

        body.resetMassData();
        shape.dispose();
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public ObstacleType getObstacleType() {
        return obstacleType;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(linearVelocity);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(obstacle, body.getPosition().x * GameStage.WORLD_X - (obstacleType.width * GameStage.WORLD_X), body.getPosition().y * GameStage.WORLD_Y - (obstacleType.height * GameStage.WORLD_Y), obstacleType.width * GameStage.WORLD_X * 2, obstacleType.height * GameStage.WORLD_Y * 2);
    }

    public Vector2 getBodyPosition(){
        return body.getPosition();
    }

    @Override
    public boolean remove(){
        body.getWorld().destroyBody(body);
        return super.remove();
    }

}
