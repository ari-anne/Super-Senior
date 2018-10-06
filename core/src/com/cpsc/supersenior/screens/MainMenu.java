package com.cpsc.supersenior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.cpsc.supersenior.SuperSenior;

public class MainMenu implements Screen {

    SuperSenior game;
    BitmapFont font;
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Graduate-Regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private static GlyphLayout glyphLayout = new GlyphLayout();
    private static final String TITLE = "Super Senior";
    private static final String PLAY = "Play";
    private static final String HIGH_SCORES = "High Scores";
    private static final String SETTINGS = "Settings";

    /*
    *   GlyphLayout: https://gamedev.stackexchange.com/questions/101242/bitmapfont-where-is-getbounds-method-in-new-1-6-libgdx
    * */

    public MainMenu (SuperSenior game){
        this.game = game;
        parameter.size = 50;
        parameter.borderWidth = 1;
        parameter.color = Color.BLACK;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        // TITLE
        glyphLayout.setText(font, TITLE);
        font.draw(game.batch, glyphLayout, SuperSenior.WIDTH/2 - glyphLayout.width/2, SuperSenior.HEIGHT - SuperSenior.HEIGHT/3);

        // PLAY BUTTON
        glyphLayout.setText(font, PLAY);
        font.draw(game.batch, glyphLayout, SuperSenior.WIDTH/2 - glyphLayout.width/2, SuperSenior.HEIGHT/2);
        if( Gdx.input.getX() > SuperSenior.WIDTH/2 - glyphLayout.width/2 &&
                Gdx.input.getX() < SuperSenior.WIDTH/2 - glyphLayout.width/2 + glyphLayout.width &&
                SuperSenior.HEIGHT - Gdx.input.getY() < SuperSenior.HEIGHT/2 + glyphLayout.height &&
                SuperSenior.HEIGHT - Gdx.input.getY() > SuperSenior.HEIGHT/2){
            game.setScreen(new GameScreen(game));   // red screen
        }

        // HIGH SCORES BUTTON
        glyphLayout.setText(font, HIGH_SCORES);
        font.draw(game.batch, glyphLayout, SuperSenior.WIDTH/2 - glyphLayout.width/2, SuperSenior.HEIGHT/3);
        if( Gdx.input.getX() > SuperSenior.WIDTH/2 - glyphLayout.width/2 &&
                Gdx.input.getX() < SuperSenior.WIDTH/2 - glyphLayout.width/2 + glyphLayout.width &&
                SuperSenior.HEIGHT - Gdx.input.getY() < SuperSenior.HEIGHT/3 + glyphLayout.height &&
                SuperSenior.HEIGHT - Gdx.input.getY() > SuperSenior.HEIGHT/3){
            if(Gdx.input.isTouched()){
                game.setScreen(new HighScores(game));   // green screen
            }
        }

        // SETTINGS BUTTON
        glyphLayout.setText(font, SETTINGS);
        font.draw(game.batch, glyphLayout, SuperSenior.WIDTH/2 - glyphLayout.width/2, SuperSenior.HEIGHT/6);
        if( Gdx.input.getX() > SuperSenior.WIDTH/2 - glyphLayout.width/2 &&
                Gdx.input.getX() < SuperSenior.WIDTH/2 - glyphLayout.width/2 + glyphLayout.width &&
                SuperSenior.HEIGHT - Gdx.input.getY() < SuperSenior.HEIGHT/6 + glyphLayout.height &&
                SuperSenior.HEIGHT - Gdx.input.getY() > SuperSenior.HEIGHT/6){
            if(Gdx.input.isTouched()){
                game.setScreen(new Settings(game)); // blue screen
            }
        }

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
        font.dispose();
    }
}
