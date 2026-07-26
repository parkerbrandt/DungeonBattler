package com.lucentus.dungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.lucentus.dungeon.DungeonBattler;


/**
 * Title/Start Screen
 */
public class StartScreen implements Screen {

    /*
     * Properties
     */
    private final DungeonBattler game;

    // View
    private OrthographicCamera camera;

    // UI
    private final Skin skin;
    private final Stage stage;


    /*
     * Constructors
     */
    public StartScreen(DungeonBattler game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, DungeonBattler.VIEWPORT_WIDTH, DungeonBattler.VIEWPORT_HEIGHT);

        // Initialize skin for UI
        skin = new Skin(Gdx.files.internal("assets/resources/ui/skins/uiskin.json"));

        // Initialize Scene2d UI components
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
    }


    /*
     * Overridden Methods
     */
    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.begin();
        game.batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
