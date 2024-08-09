package com.lucentus.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

/**
 * Core Class of the Dungeon Battler Game
 */
public class DungeonBattlerGame extends ApplicationAdapter {

	// Variables
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Rectangle bucket;
	private Array<Rectangle> raindrops;

	private long lastDropTime;

	private Texture dropImage;
	private Texture bucketImage;
	private Sound dropSound;
	private Music rainMusic;
	
	@Override
	public void create () {

		// Create the camera
		// Used to ensure that we will always be rendering the target resolution regardless of screen
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		// Create the spritebatch
		// Used to draw the loaded sprites
		batch = new SpriteBatch();

		// Load images/sprites for the game (64x64 pixels)
		dropImage = new Texture(Gdx.files.internal("drop.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));

		// Load the sound effects and background music
		dropSound = Gdx.audio.newSound(Gdx.files.internal("waterdrop.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rainsounds.mp3"));

		// Start playback of background music immediately
		rainMusic.setLooping(true);
		rainMusic.play();

		// Create the Rectangles for the bucket and raindrops
		bucket = new Rectangle();
		bucket.x = 800 / 2 - 64 / 2;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height = 64;

		// Create raindrops and spawn the first one
		raindrops = new Array<Rectangle>();
		spawnRaindrop();
	}

	@Override
	public void render () {
		// Clear the screen with a dark blue color
		ScreenUtils.clear(0, 0, 0.2f, 1);

		// Update the camera
		camera.update();

		// Render the bucket
		batch.setProjectionMatrix(camera.combined);		// Use the coordinate system specified by camera
		batch.begin();
		batch.draw(bucketImage, bucket.x, bucket.y);
		for (Rectangle raindrop : raindrops)
			batch.draw(dropImage, raindrop.x, raindrop.y);
		batch.end();

		// Detect input
		// User can control the bucket with touch
		if (Gdx.input.isTouched()) {
			// Bucket will center around where the user touches
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucket.x = touchPos.x - 64/3;
		}

		// User can control the bucket with left and right buttons as well
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			bucket.x -= 200 * Gdx.graphics.getDeltaTime();

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			bucket.x += 200 * Gdx.graphics.getDeltaTime();

		// Check bucket bounds
		if (bucket.x < 0)
			bucket.x = 0;

		if (bucket.x > 800 - 64)
			bucket.x = 800 - 64;

		// Check how long it has been since the last raindrop and spawn a new one if necessary
		if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
			spawnRaindrop();

		// Make the raindrops move/fall
		for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext();) {
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if (raindrop.y + 64 < 0)
				iter.remove();

			// Check for any raindrops hitting the bucket
			// Then, play sound and remove raindrop
			if (raindrop.overlaps(bucket)) {
				dropSound.play();
				iter.remove();
			}
		}
	}
	
	@Override
	public void dispose () {
		dropImage.dispose();
		bucketImage.dispose();
		dropSound.dispose();
		rainMusic.dispose();
		batch.dispose();
	}

	private void spawnRaindrop() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800 - 64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}
}
