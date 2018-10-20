package com.cpsc.supersenior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.cpsc.supersenior.SuperSenior;
import com.cpsc.supersenior.tools.Send_HTTP_Request2;

public class HighScores implements Screen {

    SuperSenior game;
    BitmapFont font;
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Graduate-Regular.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    private static GlyphLayout glyphLayout = new GlyphLayout();
    private static final String TITLE = "Super Senior";
    private static final String NAME = "Name";
    private static final String SCORE = "Score";
    private static final String SETTINGS = "Settings";

    public HighScores(SuperSenior game) {

        this.game = game;
        parameter.size = 50;
        parameter.borderWidth = 1;
        parameter.color = Color.BLACK;
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Send_HTTP_Request2 readJson = new Send_HTTP_Request2();
        readJson.main(this.names);
        game.batch.begin();

        // Title
        glyphLayout.setText(font, TITLE);
        font.draw(game.batch, glyphLayout, SuperSenior.WIDTH / 2 - glyphLayout.width / 2, SuperSenior.HEIGHT - SuperSenior.HEIGHT / 6);
        createRow(SuperSenior.WIDTH / 5, SuperSenior.HEIGHT - SuperSenior.HEIGHT / 20 * 5, 1000, names, scores);

        game.batch.end();
    }

    private int createRow(int x, int y, int width, String[] names, String[] scores) {
        int maxY = y;
        // Name
        glyphLayout.setText(font, NAME);
        font.draw(game.batch, glyphLayout, x, y);
        // Score
        glyphLayout.setText(font, SCORE);
        font.draw(game.batch, glyphLayout, x + width - 20, y);
        for (int i = 0; i < names.length; i++) {
            glyphLayout.setText(font, names[i]);
            font.draw(game.batch, glyphLayout, x, y - (100 * (i + 1)));
            glyphLayout.setText(font, scores[i]);
            font.draw(game.batch, glyphLayout, x + width - 20, y - (100 * (i + 1)));
            maxY = y - (100 * (i + 1));
        }
        return maxY;
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
