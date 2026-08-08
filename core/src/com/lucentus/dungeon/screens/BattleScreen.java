package com.lucentus.dungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.lucentus.dungeon.DungeonBattler;
import com.lucentus.dungeon.entities.NPCEnemy;
import com.lucentus.dungeon.users.Player;

import java.util.ArrayList;


/**
 * Battle Screen
 */
public class BattleScreen implements Screen {

    /*
     * Properties
     */
    private final DungeonBattler game;

    private final OrthographicCamera camera;
    private float stateTime;

    private Player player;
    private ArrayList<NPCEnemy> enemies = new ArrayList<>();

    // Game Options
    private boolean showHitboxes = false;


    /*
     * Constructors
     */
    public BattleScreen(DungeonBattler game) {
        this.game = game;

        player = new Player();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, DungeonBattler.VIEWPORT_WIDTH, DungeonBattler.VIEWPORT_HEIGHT);
    }

    /*
     * Methods
     */
    private void input() {

        float deltaTime = Gdx.graphics.getDeltaTime();

        float playerX = player.getCharacter().getPosX();
        float playerY = player.getCharacter().getPosY();

        float moveSpeed = player.getCharacter().getMoveSpeed();

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.getCharacter().setPosX(playerY + (moveSpeed * deltaTime));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.getCharacter().setPosY(playerY - (moveSpeed * deltaTime));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            player.getCharacter().setPosX(playerX + (moveSpeed * deltaTime));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.getCharacter().setPosX(playerX - (moveSpeed * deltaTime));
        }

        // TODO: Check bounds
    }

    private void logic() {

    }

    private void draw() {
        // Update camera
        camera.update();

        // Render the player's character as a rectangle
        player.getCharacter().render(game, Color.GREEN);
        if (showHitboxes)
            player.getCharacter().renderHitbox(game, Color.WHITE);

        for (NPCEnemy enemy : enemies) {
            enemy.render(game, Color.GRAY);
            if (showHitboxes)
                enemy.renderHitbox(game, Color.RED);
        }
    }

    private void drawUI() {
        // TODO: Render any UI elements
    }


    /*
     * Overridden Methods
     */
    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0.2f, 0, 1);

        // Increment State Time
        stateTime += Gdx.graphics.getDeltaTime();

        input();
        logic();
        draw();
        drawUI();
    }

    @Override
    public void resize(int i, int i1) {

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
