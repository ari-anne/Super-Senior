package com.cpsc.supersenior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.cpsc.supersenior.SuperSenior;
import com.cpsc.supersenior.tools.ScrollingBackground;

public class GameScreen implements Screen {

    // Pausing      https://stackoverflow.com/questions/21576181/pause-resume-a-simple-libgdx-game-for-android

    final SuperSenior game;

    Stage stage;
    Skin skin;
    Table table;
    Texture img;
    Label score;
    TextButton pauseTxt;
    Button pause;
    Button resume;
    Button main_menu;
    Button restart;

    float middleX, middleY;
    float btnWidth, btnHeight;

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
        score = new Label("00000", skin);
        pauseTxt = new TextButton("Paused", skin, "header");
        pause = new Button(skin, "pause");
        resume = new Button(skin, "play");
        main_menu = new Button(skin, "home");
        restart = new Button(skin, "restart");
        state = GameState.Running;

        middleX = Gdx.graphics.getWidth()/2;
        middleY = Gdx.graphics.getHeight()/2;
        btnWidth = 150;
        btnHeight = 150;

        game.scrollingBackground.setFixedSpeed(false);
        game.scrollingBackground.setSpeed(ScrollingBackground.DEFAULT_SPEED);

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

        score.setBounds(middleX - score.getWidth()/2, Gdx.graphics.getHeight() - score.getHeight() - 50, score.getWidth(), score.getHeight());
        pauseTxt.setBounds(middleX - pauseTxt.getWidth()/2, middleY - pauseTxt.getHeight()/2, pauseTxt.getWidth(), pauseTxt.getHeight());
        pause.setBounds(Gdx.graphics.getWidth() - btnWidth - 50, 50, btnWidth, btnHeight);
        resume.setBounds(middleX - btnWidth * 3,middleY - pauseTxt.getHeight()/2 - btnHeight * 2, btnWidth, btnHeight);
        main_menu.setBounds(middleX - btnWidth/2, middleY - pauseTxt.getHeight()/2 - btnHeight * 2, btnWidth, btnHeight);
        restart.setBounds(middleX + btnWidth * 2,middleY - pauseTxt.getHeight()/2 - btnHeight * 2, btnWidth, btnHeight);

        stage.addActor(score);
        stage.addActor(pause);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        switch(state){
            case Running:
                running(delta);
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
    }

    @Override
    public void pause() {
        game.scrollingBackground.render(game.batch);
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
        img.dispose();
    }
}
