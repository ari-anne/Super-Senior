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

public class HighScores implements Screen {

    final SuperSenior game;

    Stage stage;
    Table table;
    Skin skin;
    Button back;
    TextButton high_scores;
    TextButton empty;

    public HighScores (SuperSenior game){
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        skin = new Skin(Gdx.files.internal("buttons/button.json"));
        back = new Button(skin, "arrow-left");
        high_scores = new TextButton("  High Scores  ", skin, "header");
        empty = new TextButton(" ", skin, "header");

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });

        stage.addActor(table);
        stage.addActor(back);
        stage.addActor(high_scores);

        table.setDebug(true);
        table.setFillParent(true);
        table.add(back).width(150).height(150).padTop(50).padLeft(50);
        table.add(high_scores).expandX().padTop(50).padRight(50);
        table.row();
        table.add(empty).colspan(2).expand().pad(50);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.scrollingBackground.update(delta);
        game.scrollingBackground.render(game.batch);
        game.batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }
}
