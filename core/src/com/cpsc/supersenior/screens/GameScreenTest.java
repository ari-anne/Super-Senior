package com.cpsc.supersenior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.cpsc.supersenior.SuperSenior;
import com.cpsc.supersenior.tools.GameStage;
import com.cpsc.supersenior.tools.Score;

public class GameScreenTest implements Screen {

    private GameStage stage;

    SuperSenior game;
    Skin skin;
    Label scoreTxt;

    public static GameState state;

    public enum GameState{
        RUNNING,
        PAUSE,
        RESUME,
        GAME_OVER
    }

    public GameScreenTest(SuperSenior game) {
        this.game = game;
        stage = new GameStage();
    }

    @Override
    public void show() {
        skin = new Skin(Gdx.files.internal("buttons/button.json"));
        scoreTxt = new Label("0", skin);

        state = GameState.RUNNING;

        scoreTxt.setBounds(Gdx.graphics.getWidth()/2 - scoreTxt.getWidth()/2, Gdx.graphics.getHeight() - scoreTxt.getHeight() - 50, scoreTxt.getWidth(), scoreTxt.getHeight());

        stage.addActor(scoreTxt);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        scoreTxt.setText(Integer.toString(Score.getScore()));

        switch(state) {
            case RUNNING:
                stage.act(delta);
                break;
            case PAUSE:
                break;
            case RESUME:
                break;
            case GAME_OVER:
                break;
        }

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
