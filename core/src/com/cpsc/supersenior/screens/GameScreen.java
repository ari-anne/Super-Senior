package com.cpsc.supersenior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.cpsc.supersenior.SuperSenior;
import com.cpsc.supersenior.tools.GameStage;
import com.cpsc.supersenior.tools.Score;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GameScreen implements Screen {

    // Pausing      https://stackoverflow.com/questions/21576181/pause-resume-a-simple-libgdx-game-for-android

    private static final int MIDDLE_X = Gdx.graphics.getWidth() / 2;
    private static final int MIDDLE_Y = Gdx.graphics.getHeight() / 2;
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 150;
    private static final int PADDING = 50;

    private final SuperSenior game;
    private GameStage stage;
    private Skin skin;
    private Table table;

    public static GameState state;
    private Label scoreTxt;
    private Label gameOverTxt;
    private Label clickAnywhere;
    private TextButton pauseTxt;
    private Button pause;
    private Button resume;
    private Button main_menu;
    private Button restart;
    private Texture overlay;
    private TextureAtlas character;
    private TextureRegion currentFrame;
    private Image runnerTexture;
    public Sprite sprite = new Sprite();


    private float elapsedTime = 0f;

    public enum GameState {
        RUNNING,
        PAUSE,
        RESUME,
        GAME_OVER
    }

    public GameScreen(SuperSenior game) {
        this.game = game;
        stage = new GameStage();
    }

    @Override
    public void show() {
        stage = new GameStage();
        Gdx.input.setInputProcessor(new GestureDetector(stage));

        table = new Table();
        skin = new Skin(Gdx.files.internal("buttons/button.json"));
        scoreTxt = new Label("0", skin);
        gameOverTxt = new Label("Game Over", skin);
        clickAnywhere = new Label("Click anywhere to continue", skin, "medium");
        pauseTxt = new TextButton("Paused", skin, "header");
        pause = new Button(skin, "pause");
        resume = new Button(skin, "play");
        main_menu = new Button(skin, "home");
        restart = new Button(skin, "restart");
        overlay = new Texture("overlay.png");
        runnerTexture = new Image(currentFrame);
        state = GameState.RUNNING;

        pause.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                state = GameState.PAUSE;
            }
        });
        resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                state = GameState.RESUME;
            }
        });
        main_menu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SuperSenior.background.resetSpeed();
                game.setScreen(new MainMenu(game));
            }
        });
        restart.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SuperSenior.background.reset();
                game.setScreen(new GameScreen(game));
            }
        });

        scoreTxt.setBounds(MIDDLE_X - scoreTxt.getWidth() / 2, Gdx.graphics.getHeight() - scoreTxt.getHeight() - PADDING, scoreTxt.getWidth(), scoreTxt.getHeight());
        gameOverTxt.setBounds(MIDDLE_X - gameOverTxt.getWidth() / 2, MIDDLE_Y - gameOverTxt.getHeight() / 2, gameOverTxt.getWidth(), gameOverTxt.getHeight());
        clickAnywhere.setBounds(MIDDLE_X - clickAnywhere.getWidth() / 2, 0, clickAnywhere.getWidth(), clickAnywhere.getHeight());
        pauseTxt.setBounds(MIDDLE_X - pauseTxt.getWidth() / 2, MIDDLE_Y - pauseTxt.getHeight() / 2, pauseTxt.getWidth(), pauseTxt.getHeight());
        pause.setBounds(Gdx.graphics.getWidth() - BUTTON_WIDTH - PADDING, PADDING, BUTTON_WIDTH, BUTTON_HEIGHT);
        pause.setName("Pause");
        resume.setBounds(MIDDLE_X - BUTTON_WIDTH * 3, MIDDLE_Y - pauseTxt.getHeight() / 2 - BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        main_menu.setBounds(MIDDLE_X - BUTTON_WIDTH / 2, MIDDLE_Y - pauseTxt.getHeight() / 2 - BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        restart.setBounds(MIDDLE_X + BUTTON_WIDTH * 2, MIDDLE_Y - pauseTxt.getHeight() / 2 - BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT);

        stage.addActor(scoreTxt);
        stage.addActor(gameOverTxt);
        stage.addActor(clickAnywhere);
        stage.addActor(pause);
        stage.addActor(pauseTxt);
        stage.addActor(resume);
        stage.addActor(main_menu);
        stage.addActor(restart);
        stage.addActor(runnerTexture);

        gameOverTxt.setVisible(false);
        clickAnywhere.setVisible(false);
        pauseTxt.setVisible(false);
        resume.setVisible(false);
        main_menu.setVisible(false);
        restart.setVisible(false);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();


        switch (state) {
            case RUNNING:
                running(delta);
                break;
            case PAUSE:
                pause();
                break;
            case RESUME:
                resume();
                break;
            case GAME_OVER:
                gameOver();
        }

        stage.draw();
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    private void running(float delta) {
        stage.act(delta);
        elapsedTime += delta;
        scoreTxt.setText(Integer.toString(Score.getScore()));
    }

    @Override
    public void pause() {
        Gdx.input.setInputProcessor(stage);
        game.batch.draw(overlay, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        pause.setVisible(false);

        pauseTxt.setVisible(true);
        resume.setVisible(true);
        main_menu.setVisible(true);
        restart.setVisible(true);
    }

    @Override
    public void resume() {
        Gdx.input.setInputProcessor(new GestureDetector(stage));

        pause.setVisible(true);
        pauseTxt.setVisible(false);
        resume.setVisible(false);
        main_menu.setVisible(false);
        restart.setVisible(false);

        state = GameState.RUNNING;
    }

    private void gameOver() {
        game.batch.draw(overlay, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        pause.setVisible(false);
        gameOverTxt.setVisible(true);
        clickAnywhere.setVisible(true);

        if (Gdx.input.isTouched()) {
            game.setScreen(new EndGame(game));
        }
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        game.batch.dispose();
        Gdx.input.setInputProcessor(null);
//        character.dispose();
    }
}
