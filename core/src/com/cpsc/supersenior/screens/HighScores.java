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

    private SuperSenior game;
    private Stage stage = new Stage();

    public HighScores (SuperSenior game){
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("buttons/button.json"));
        Table table = new Table();

        Texture texture = new Texture(Gdx.files.internal("backgrounds/game_background_1/game_background_1.png"));
        Image background = new Image(texture);
        Button back = new Button(skin, "arrow-left");
        TextButton high_scores = new TextButton("  High Scores  ", skin, "header");
        TextButton empty = new TextButton(" ", skin, "header");

        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background.setPosition(0, Gdx.graphics.getHeight()-background.getHeight());
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });

        stage.addActor(background);
        stage.addActor(table);
        stage.addActor(back);
        stage.addActor(high_scores);

        table.setDebug(true);
        table.setFillParent(true);
        table.add(back).width(200).height(200).padTop(50).padLeft(50);
        table.add(high_scores).expandX().padTop(50).padRight(50);
        table.row();
        table.add(empty).colspan(2).expand().pad(50);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        stage.act();
        stage.draw();
        game.batch.end();
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
    public void dispose() { }
}
