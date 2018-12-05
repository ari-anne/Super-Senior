package com.cpsc.supersenior.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.cpsc.supersenior.SuperSenior;
import com.cpsc.supersenior.entities.*;
import com.cpsc.supersenior.entitydata.CoinUserData;
import com.cpsc.supersenior.entitydata.UserData;
import com.cpsc.supersenior.screens.GameScreen;

public class GameStage extends Stage implements ContactListener {

    // http://williammora.com/a-running-game-with-libgdx-part-1

    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;
    private static final Vector2 GRAVITY = new Vector2(0,-10);

    private static final float VELOCITY_TIMER = 20f;
    private static final float MAX_VELOCITY = 10f;

    private static final float GAME_OVER_TIMER = 1f;
    private static final float COIN_TIMER = 0.5f;

    private static final float OBSTACLE_SPAWN_MAX = 4f;
    private static final float OBSTACLE_SPAWN_MIN = 2f;

    private float accumulator;
    private final float TIME_STEP = 1/300f;

    private World world;
    private Ground ground;
    private Runner runner;
    private Obstacle obstacle;
    private Coin coin;

    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;

    private float linearVelocityX;
    private float speedUp;          // shortens obstacle spawn time when velocity increases
    private float velocityTimer;
    private float obstacleTimer;
    private float coinTimer;
    private float gameOverTimer;

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

        accumulator = 0f;
        linearVelocityX = 5f;
        speedUp = 0f;
        velocityTimer = VELOCITY_TIMER;
        obstacleTimer = Randomize.obstacleSpawnTime(OBSTACLE_SPAWN_MAX, OBSTACLE_SPAWN_MIN);
        coinTimer = COIN_TIMER;
        gameOverTimer = GAME_OVER_TIMER;

        addActor(SuperSenior.background);
        makeGround();
        makeRunner();
        makeObstacle();
        makeCoin(true);

        // TODO: implement swipe input
        // temporary controls to test gravity
        // divide screen in half, tapping right side jumps, tapping left side crouches
        touchPoint = new Vector3();
        rightSide = new Rectangle(getCamera().viewportWidth/2,0,getCamera().viewportWidth/2, getCamera().viewportHeight);
        leftSide = new Rectangle(0, 0, getCamera().viewportWidth/2, getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
    }

    private void makeGround() {
        ground = new Ground(world);
        addActor(ground);
    }

    private void makeRunner() {
        runner = new Runner(world);
        addActor(runner);
    }

    private void makeObstacle() {
        obstacle = new Obstacle(world);
        obstacle.setLinearVelocity(new Vector2(-linearVelocityX, 0));
        addActor(obstacle);
    }

    private void makeCoin(boolean withObstacle) {
        if (!withObstacle) {
            coin = new Coin(world);
        }
        else {
            coin = new Coin(world, obstacle.getObstacleType());
        }
        coin.setLinearVelocity(new Vector2(-linearVelocityX, 0));
        addActor(coin);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        boolean withObstacle;

        Array<Body> bodies = new Array<Body>(world.getBodyCount());
        world.getBodies(bodies);

        for (Body body : bodies) {
            if (!onScreen(body)) {
                world.destroyBody(body);
            } else if (CheckBodyType.isCoin(body)) {
                CoinUserData coinData = (CoinUserData) body.getUserData();
                if (coinData.toDelete) {
                    world.destroyBody(body);
                }
            }
        }

        // wait before stopping game
        if (runner.isHit()) {
            gameOverTimer -= delta;
            if (gameOverTimer <= 0) {
                GameScreen.state = GameScreen.GameState.GAME_OVER;
            }
        }

        // if coin spawns with obstacle, move coin to accommodate obstacle
        if (obstacleTimer <= 0 && coinTimer <= 0 ) {
            coinTimer = COIN_TIMER;
            obstacleTimer = Randomize.obstacleSpawnTime(OBSTACLE_SPAWN_MAX + speedUp * 2, OBSTACLE_SPAWN_MIN + speedUp);
            withObstacle = true;
            makeObstacle();
            makeCoin(withObstacle);
        }
        // spawn coin
        else if (obstacleTimer > 0 && coinTimer <= 0) {
            coinTimer = COIN_TIMER;
            withObstacle = false;
            makeCoin(withObstacle);
        }
        // spawn obstacle
        else if (obstacleTimer <= 0 && coinTimer > 0) {
            obstacleTimer = Randomize.obstacleSpawnTime(OBSTACLE_SPAWN_MAX + speedUp * 2, OBSTACLE_SPAWN_MIN + speedUp);
            makeObstacle();
        }

        // wait for screen to clear of obstacles before increasing velocity
        if (velocityTimer > 0) {
            obstacleTimer -= delta;
            coinTimer -= delta;
        }

        // increase velocity if it hasn't reached max
        if (linearVelocityX < MAX_VELOCITY) {
            if (bodies.size == 2 && velocityTimer <= 0) {
                SuperSenior.background.speedUp();
                speedUp -= 0.1f;
                linearVelocityX += 0.5f;
                velocityTimer = VELOCITY_TIMER;
            }
            velocityTimer -= delta;
        }

        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
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
            SuperSenior.gameMusic.playJumpSound();
            runner.jump();
        }
        else if (leftSide.contains(touchPoint.x, touchPoint.y)) {
            runner.crouch();
        }

        return super.touchDown(x, y, pointer, button);
    }

    public Vector2 getBodyPosition(){
        return runner.getBodyPosition();
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

        if ((CheckBodyType.isGround(a) && CheckBodyType.isRunner(b)) ||
                (CheckBodyType.isRunner(a) && CheckBodyType.isGround(b))) {
            runner.landed();
        } else if ((CheckBodyType.isObstacle(a) && CheckBodyType.isRunner(b)) ||
                (CheckBodyType.isRunner(a) && CheckBodyType.isObstacle(b))) {
            SuperSenior.gameMusic.playBuzzSound();
            runner.hit();
        } else if (CheckBodyType.isCoin(a) && CheckBodyType.isRunner(b)) {
            CoinUserData coinData = (CoinUserData) a.getUserData();
            coinData.toDelete = true;
            SuperSenior.gameMusic.playCoinSound();
            Score.addScore(a);

        } else if (CheckBodyType.isRunner(a) && CheckBodyType.isCoin(b)) {
            CoinUserData coinData = (CoinUserData) b.getUserData();
            coinData.toDelete = true;
            SuperSenior.gameMusic.playCoinSound();
            Score.addScore(b);
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

    private static boolean onScreen(Body body) {
        UserData userData = (UserData) body.getUserData();
        return body.getPosition().x + userData.getWidth() > 0;
    }
}
