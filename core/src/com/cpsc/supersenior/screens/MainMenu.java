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

public class MainMenu implements Screen{

    private SuperSenior game;
    private Stage stage = new Stage();
    // Labels       https://libgdx.info/basic-label/
    // Skins        https://github.com/libgdx/libgdx/wiki/Skin#skin-json
    // Tables       https://github.com/EsotericSoftware/tablelayout
    // ButtonSkin   https://stackoverflow.com/questions/21488311/how-to-create-a-button-in-libgdx


    public MainMenu (SuperSenior game){
        this.game = game;
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("buttons/button.json"));
        Table table = new Table();

        Texture texture = new Texture(Gdx.files.internal("backgrounds/game_background_3/game_background_3.1.png"));
        Image background = new Image(texture);
        TextButton title = new TextButton("  Super Senior  ", skin, "title");
        Button play = new Button(skin, "play");
        Button high_scores = new Button(skin, "leaderboard");
        Button settings = new Button(skin, "settings");

        int padding = 100;

        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background.setPosition(0, Gdx.graphics.getHeight()-background.getHeight());
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });
        high_scores.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new HighScores(game));
            }
        });
        settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Settings(game));
            }
        });

        stage.addActor(background);
        stage.addActor(table);
        stage.addActor(title);
        stage.addActor(play);
        stage.addActor(high_scores);
        stage.addActor(settings);

        table.setFillParent(true);
        table.row().height(500);
        table.add(title).colspan(3).padTop(padding);
        table.row();
        table.add(play).pad(padding);
        table.add(high_scores).pad(padding);
        table.add(settings).pad(padding);
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
    public void dispose() {
        game.batch.dispose();
    }
}
