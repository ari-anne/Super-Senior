package com.cpsc.supersenior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion ;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.cpsc.supersenior.SuperSenior;

public class GameScreen implements Screen {

    // Pausing      https://stackoverflow.com/questions/21576181/pause-resume-a-simple-libgdx-game-for-android

    final SuperSenior game;
    Stage stage;
    Skin skin;
    Table table;
    Label scoreTxt;
    TextButton pauseTxt;
    Button pause;
    Button resume;
    Button main_menu;
    Button restart;
    Texture overlay;
    TextureAtlas character;
    Animation<TextureRegion> animation;
    float elapseTime = 0f;

    float middleX, middleY;     // middle of screen
    float btnWidth, btnHeight;  // button dimensions
    int padding;
    int score;

    public enum GameState{
        Running,
        Pause,
        Resume
    }

    GameState state;

    public GameScreen(SuperSenior game){
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        skin = new Skin(Gdx.files.internal("buttons/button.json"));
        scoreTxt = new Label("0", skin);
        pauseTxt = new TextButton("Paused", skin, "header");
        pause = new Button(skin, "pause");
        resume = new Button(skin, "play");
        main_menu = new Button(skin, "home");
        restart = new Button(skin, "restart");
        overlay = new Texture("overlay.png");
        character = new TextureAtlas("character/run/run.atlas");
        animation = new Animation<TextureRegion>(1f/5f, character.getRegions());
        state = GameState.Running;

        middleX = Gdx.graphics.getWidth()/2;
        middleY = Gdx.graphics.getHeight()/2;
        btnWidth = 150;
        btnHeight = 150;
        padding = 50;
        score = 0;

        game.scrollingBackground.setFixedSpeed(false);

        pause.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                state = GameState.Pause;
            }
        });
        resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                state = GameState.Resume;
            }
        });
        main_menu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.scrollingBackground.resetSpeed();
                game.setScreen(new MainMenu(game));
            }
        });
        restart.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.scrollingBackground.reset();
                game.setScreen(new GameScreen(game));
            }
        });

        scoreTxt.setBounds(middleX - scoreTxt.getWidth()/2, Gdx.graphics.getHeight() - scoreTxt.getHeight() - padding, scoreTxt.getWidth(), scoreTxt.getHeight());
        pauseTxt.setBounds(middleX - pauseTxt.getWidth()/2, middleY - pauseTxt.getHeight()/2, pauseTxt.getWidth(), pauseTxt.getHeight());
        pause.setBounds(Gdx.graphics.getWidth() - btnWidth - padding, padding, btnWidth, btnHeight);
        resume.setBounds(middleX - btnWidth * 3,middleY - pauseTxt.getHeight()/2 - btnHeight * 2, btnWidth, btnHeight);
        main_menu.setBounds(middleX - btnWidth/2, middleY - pauseTxt.getHeight()/2 - btnHeight * 2, btnWidth, btnHeight);
        restart.setBounds(middleX + btnWidth * 2,middleY - pauseTxt.getHeight()/2 - btnHeight * 2, btnWidth, btnHeight);

        stage.addActor(scoreTxt);
        stage.addActor(pause);
    }

    @Override
    public void render(float delta) {
        elapseTime += delta;
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        switch(state){
            case Running:
                running(delta);
                TextureRegion currentFrame = animation.getKeyFrame(elapseTime, true);
                game.batch.draw(currentFrame,50,50, 512, 512);
                break;
            case Pause:
                pause();
                break;
            case Resume:
                resume();
                break;
        }

        game.batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    public void running(float delta) {
        game.scrollingBackground.update(delta);
        game.scrollingBackground.render(game.batch);

        // update score
        // if (collide with coin)
            // score++;
            // scoreTxt.setText(Integer.toString(score));


    }

    @Override
    public void pause() {
        game.scrollingBackground.render(game.batch);
        game.batch.draw(overlay, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        pause.remove();

        stage.addActor(pauseTxt);
        stage.addActor(resume);
        stage.addActor(main_menu);
        stage.addActor(restart);
    }

    @Override
    public void resume() {
        pauseTxt.remove();
        resume.remove();
        main_menu.remove();
        restart.remove();

        stage.addActor(pause);

        state = GameState.Running;
    }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        game.batch.dispose();
        Gdx.input.setInputProcessor(null);
        character.dispose();
    }
}
