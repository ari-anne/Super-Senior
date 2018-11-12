package com.cpsc.supersenior.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.cpsc.supersenior.entities.*;

public class GameStage extends Stage implements ContactListener {

    // following this tutorial: http://williammora.com/a-running-game-with-libgdx-part-1

    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;
    public static final Vector2 GRAVITY = new Vector2(0,-10);

    private float elapsedTime = 0f;
    private final float TIME_STEP = 1/300f;

    World world;
    Ground ground;
    Runner runner;
    Obstacle obstacle;
    Coin coin;

    Box2DDebugRenderer renderer;
    OrthographicCamera camera;

    float linearVelocityX;

    Vector3 touchPoint;
    Rectangle rightSide;
    Rectangle leftSide;

    public enum ActorType {
        GROUND,
        RUNNER,
        OBSTACLE,
        COIN
    }

    public GameStage() {
        world = new World(GRAVITY, true);
        renderer = new Box2DDebugRenderer();
        world.setContactListener(this);

        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        camera.update();

        linearVelocityX = 4f;

//        addActor(new Background());
        makeGround();
        makeRunner();
        makeObstacle();
        makeCoin();

        // TODO: implement swipe input
        // temporary controls to test gravity
        // divide screen in half, tapping right side jumps, tapping left side crouches
        touchPoint = new Vector3();
        rightSide = new Rectangle(getCamera().viewportWidth/2,0,getCamera().viewportWidth/2, getCamera().viewportHeight);
        leftSide = new Rectangle(0, 0, getCamera().viewportWidth/2, getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
    }

    public void makeGround() {
        ground = new Ground(world);
//        ground.setLinearVelocity(new Vector2(-linearVelocityX, 0));
        addActor(ground);
    }

    public void makeRunner() {
        runner = new Runner(world);
//        runner.setLinearVelocity(new Vector2(0, 0));
        addActor(runner);
    }

    public void makeObstacle() {
        obstacle = new Obstacle(world);
        obstacle.setLinearVelocity(new Vector2(-linearVelocityX, 0));
        addActor(obstacle);
    }

    public void makeCoin() {
        coin = new Coin(world);
        coin.setLinearVelocity(new Vector2(-linearVelocityX, 0));
        addActor(coin);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        Array<Body> bodies = new Array<Body>(world.getBodyCount());
        world.getBodies(bodies);

        for (Body body : bodies) {
            if (!bodyInBounds(body)) {
                if(body.getUserData() == ActorType.OBSTACLE && !runner.isHit()) {
                    makeObstacle();
                }
                world.destroyBody(body);
            }
        }

        elapsedTime += delta;

        while (elapsedTime >= delta) {
            world.step(TIME_STEP, 6, 2);
            elapsedTime -= TIME_STEP;
        }
    }

    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);
    }

    // TODO: implement swipe input
    // temporary controls to test gravity
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        getCamera().unproject(touchPoint.set(x, y, 0));

        // if right side of screen is touched, jump
        // else if left side of screen is touched, crouch
        if(rightSide.contains(touchPoint.x, touchPoint.y)) {
            runner.jump();
        }
        else if (leftSide.contains(touchPoint.x, touchPoint.y)) {
            runner.crouch();
        }

        return super.touchDown(x, y, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (runner.isCrouching()) {
            runner.stand();
        }

        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        ActorType typeA = (ActorType) a.getUserData();
        ActorType typeB = (ActorType) b.getUserData();

        if ((typeA == ActorType.GROUND && typeB == ActorType.RUNNER) ||
                (typeA == ActorType.RUNNER && typeB == ActorType.GROUND)) {
            runner.landed();
        }
        else if ((typeA == ActorType.RUNNER && typeB == ActorType.OBSTACLE) ||
                (typeA == ActorType.OBSTACLE && typeB == ActorType.RUNNER)) {
            runner.hit();
        }
        else if (typeA == ActorType.COIN && typeB == ActorType.RUNNER) {
            world.destroyBody(a);
        }
        else if (typeA == ActorType.RUNNER && typeB == ActorType.COIN) {
            world.destroyBody(b);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    // TODO: accommodate randomized obstacles
    public static boolean bodyInBounds(Body body) {
        if(body.getUserData() == ActorType.RUNNER) {
            return body.getPosition().x + Runner.WIDTH > 0;
        }
        else if (body.getUserData() == ActorType.OBSTACLE) {
            return body.getPosition().x + Obstacle.ObstacleType.SAW.getWidth() > 0;
        }
        return true;
    }
}
