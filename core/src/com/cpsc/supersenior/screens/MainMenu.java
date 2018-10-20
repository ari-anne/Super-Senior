package com.cpsc.supersenior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.cpsc.supersenior.SuperSenior;

public class MainMenu implements Screen{

    private SuperSenior game;
    private Stage stage = new Stage();
    // Labels   https://libgdx.info/basic-label/
    // Skins    https://github.com/libgdx/libgdx/wiki/Skin#skin-json
    // Tables   https://github.com/EsotericSoftware/tablelayout
    // Buttons  https://stackoverflow.com/questions/21488311/how-to-create-a-button-in-libgdx



    public MainMenu (SuperSenior game){
        this.game = game;
    }



    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("sgx/skin/sgx-ui.json"));

        // BACKGROUND
        Texture texture = new Texture(Gdx.files.internal("game_background_1.png"));
        TextureRegion textureRegion = new TextureRegion(texture);
        Image bg = new Image(textureRegion);
        bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bg.setPosition(0, Gdx.graphics.getHeight()-bg.getHeight());
        stage.addActor(bg);

        // SET UP TABLE
        Table table = new Table();
        table.setFillParent(true);
        //table.debug();
        stage.addActor(table);

        // TITLE
        Label title = new Label("Super Senior", skin, "title");
        stage.addActor(title);
        table.add(title).expandX().pad(100);
        table.row();

        // PLAY BUTTON
        TextButton play = new TextButton("Play", skin);
        play.setSize(150, 50);
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });
        stage.addActor(play);
        table.add(play).expandX().pad(100);
        table.row();

        // HIGH SCORES BUTTON
        TextButton high_scores = new TextButton("High Scores", skin);
        high_scores.setSize(150, 50);
        high_scores.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new HighScores(game));
            }
        });
        stage.addActor(high_scores);
        table.add(high_scores).expandX().pad(100);
        table.row();

        // SETTINGS BUTTON
        TextButton settings = new TextButton("Settings", skin);
        settings.setSize(150, 50);
        settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Settings(game));
            }
        });
        stage.addActor(settings);
        table.add(settings).expandX().pad(100);
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
