package com.cpsc.supersenior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.cpsc.supersenior.SuperSenior;

public class GameScreen implements Screen {

    // Pausing      https://stackoverflow.com/questions/21576181/pause-resume-a-simple-libgdx-game-for-android

    SuperSenior game;

    Stage stage = new Stage();
    Skin skin;
    Texture img;
    Button pause;
    Button resume;
    Button main_menu;
    Button restart;

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

        skin = new Skin(Gdx.files.internal("buttons/button.json"));
        pause = new Button(skin, "pause");
        resume = new Button(skin, "play");
        main_menu = new Button(skin, "home");
        restart = new Button(skin, "restart");
        state = GameState.Running;

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

        pause.setBounds(0,0,217, 230);
        resume.setBounds(0,0,217, 230);
        main_menu.setBounds(300,0,217, 230);
        restart.setBounds(600,0,217, 230);

        stage.addActor(pause);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        switch(state){
            case Running:
                game.scrollingBackground.render(delta, game.batch);
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

    @Override
    public void pause() {
        game.scrollingBackground.paused(game.batch);
        pause.remove();
        stage.addActor(resume);
        stage.addActor(main_menu);
        stage.addActor(restart);
    }

    @Override
    public void resume() {
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
