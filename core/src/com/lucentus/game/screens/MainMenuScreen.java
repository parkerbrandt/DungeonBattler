package com.lucentus.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.lucentus.game.DungeonBattlerGame;

/**
 *
 */
public class MainMenuScreen implements Screen {

    // Properties
    private final DungeonBattlerGame game;
    private OrthographicCamera camera;

    /*
     * Constructors
     */

    public MainMenuScreen(final DungeonBattlerGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, DungeonBattlerGame.VIEWPORT_WIDTH, DungeonBattlerGame.VIEWPORT_HEIGHT);
    }

    /*
     * Methods
     */

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.begin();
        game.font.draw(game.batch, "FIGHT THE HORDES", 100, 150);
        game.font.draw(game.batch, "Touch Anywhere to Begin", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
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
