package com.lucentus.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.lucentus.game.DungeonBattlerGame;
import com.lucentus.game.enemy.Enemy;
import com.lucentus.game.player.Paladin;
import com.lucentus.game.player.Player;

/**
 *
 */
public class GameScreen implements Screen {

    // Variables
    private final DungeonBattlerGame game;

    private float stateTime;
    private OrthographicCamera camera;

    private Music defaultMusic;

    // Entities
    private Player player;
    private Array<Enemy> enemies;

    // Game Variables
    private boolean displayHitboxes = false;

    // Constructors

    /**
     *
     * @param game
     */
    public GameScreen(final DungeonBattlerGame game) {
        this.game = game;

        // Create the camera
        // Used to ensure that we will always be rendering the target resolution regardless of screen
        camera = new OrthographicCamera();
        camera.setToOrtho(false, DungeonBattlerGame.VIEWPORT_WIDTH, DungeonBattlerGame.VIEWPORT_HEIGHT);

        // Load the player data / class
        this.player = new Paladin();

        // Create the initial group of enemies


        // Load the sound effects and background music
        // defaultMusic = Gdx.audio.newMusic(Gdx.files.internal("field_music_01.wav"));

        // Start playback of background music immediately
        // defaultMusic.setLooping(true);
        // defaultMusic.play();

        stateTime = 0f;
    }

    /*
     * Methods
     */

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        // Clear the screen with a dark blue color
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // Increment the state time
        stateTime += Gdx.graphics.getDeltaTime();

        // Update the camera
        camera.update();

        // Get current animation frames
        TextureRegion currentPlayerFrame = player.getCurrentFrame(stateTime);

        // Render the player
        game.batch.setProjectionMatrix(camera.combined);		// Use the coordinate system specified by camera
        game.batch.begin();

        // Check if the frame needs to be flipped if the player is facing a certain direction
        // SOURCE: https://stackoverflow.com/questions/28000623/libgdx-flip-2d-sprite-animation
        boolean flip = (player.getFacingDir() == Player.Direction.WEST);
        game.batch.draw(currentPlayerFrame, flip ? player.getX() + player.getPlayerSize() : player.getX(), player.getY(), flip ? - player.getPlayerSize() : player.getPlayerSize(), player.getPlayerSize());

        // game.batch.draw(currentPlayerFrame, player.getX(), player.getY());
        game.batch.end();


        /*
         * Detect input
         */


        // User can control the player with WASD and arrow keys
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W))
            player.moveUp();
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))
            player.moveDown();
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))
            player.moveLeft();
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
            player.moveRight();
        else
            player.idle();

        // Check if the player is attacking
        if (Gdx.input.isTouched())
            player.attack();

    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        player.dispose();
        defaultMusic.dispose();
    }
}
