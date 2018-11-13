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
import com.cpsc.supersenior.entitydata.UserData;

public class GameStage extends Stage implements ContactListener {

    // following a tutorial http://williammora.com/a-running-game-with-libgdx-part-1

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
    boolean initialRun;     // for creating initial grass ground

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

    public void makeGround(boolean initialRun) {
        ground = new Ground(world);
//        ground.setLinearVelocity(new Vector2(-linearVelocityX, 0));
        addActor(ground);
    }

    public void makeGround() {
        ground = new Ground(world);
//        ground.setLinearVelocity(new Vector2(-linearVelocityX, 0));
        addActor(ground);
    }

    public void makeRunner() {
        runner = new Runner(world);
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
        UserData userData;

        Array<Body> bodies = new Array<Body>(world.getBodyCount());
        world.getBodies(bodies);

        for (Body body : bodies) {
            if (!bodyInBounds(body)) {
                userData = (UserData) body.getUserData();
                if(userData.getActorType() == ActorType.OBSTACLE && !runner.isHit()) {
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
        UserData userDataA = (UserData) a.getUserData();
        UserData userDataB = (UserData) b.getUserData();

        if ((userDataA.getActorType() == ActorType.GROUND && userDataB.getActorType() == ActorType.RUNNER) ||
                (userDataA.getActorType() == ActorType.RUNNER && userDataB.getActorType() == ActorType.GROUND)) {
            runner.landed();
        }
        else if ((userDataA.getActorType() == ActorType.RUNNER && userDataB.getActorType() == ActorType.OBSTACLE) ||
                (userDataA.getActorType() == ActorType.OBSTACLE && userDataB.getActorType() == ActorType.RUNNER)) {
            runner.hit();
        }
        else if (userDataA.getActorType() == ActorType.COIN && userDataB.getActorType() == ActorType.RUNNER) {
            world.destroyBody(a);
        }
        else if (userDataA.getActorType() == ActorType.RUNNER && userDataB.getActorType() == ActorType.COIN) {
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

    public static boolean bodyInBounds(Body body) {
        UserData userData = (UserData) body.getUserData();

        if (userData.getActorType() == ActorType.RUNNER || userData.getActorType() == ActorType.OBSTACLE) {
            return body.getPosition().x + userData.getWidth() > 0;
        }

        return true;
    }
}
