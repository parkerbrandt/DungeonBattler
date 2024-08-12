package com.lucentus.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.lucentus.game.enemy.Enemy;
import com.lucentus.game.player.Paladin;
import com.lucentus.game.player.Player;

import java.util.Iterator;

/**
 * Core Class of the Dungeon Battler Game
 */
public class DungeonBattlerGame extends ApplicationAdapter {

	// Static Class Members
	public static final int VIEWPORT_HEIGHT = 1080;
	public static final int VIEWPORT_WIDTH = 1920;

	// Variables
	private float stateTime;
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Music defaultMusic;

	// Entities
	private Player player;
	private Array<Enemy> enemies;
	
	@Override
	public void create () {

		// Create the camera
		// Used to ensure that we will always be rendering the target resolution regardless of screen
		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

		// Create the spritebatch
		// Used to draw the loaded sprites
		batch = new SpriteBatch();

		// Load the player data / class
		this.player = new Paladin();

		// Load the sound effects and background music
		// defaultMusic = Gdx.audio.newMusic(Gdx.files.internal("field_music_01.wav"));

		// Start playback of background music immediately
		// defaultMusic.setLooping(true);
		// defaultMusic.play();

		stateTime = 0f;
	}

	@Override
	public void render () {
		// Clear the screen with a dark blue color
		ScreenUtils.clear(0, 0, 0.2f, 1);

		// Increment the state time
		stateTime += Gdx.graphics.getDeltaTime();

		// Update the camera
		camera.update();

		// Get current animation frames
		TextureRegion currentPlayerFrame = player.getCurrentFrame(stateTime);

		// Render the player
		batch.setProjectionMatrix(camera.combined);		// Use the coordinate system specified by camera
		batch.begin();
		batch.draw(currentPlayerFrame, player.getX(), player.getY());
		batch.end();


		/*
		 * Detect input
		 */

		// User can control the player with WASD and arrow keys
		if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W))
			player.moveUp();

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))
			player.moveDown();

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))
			player.moveLeft();

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
			player.moveRight();
	}


	@Override
	public void dispose () {
		defaultMusic.dispose();
		batch.dispose();
	}
}
