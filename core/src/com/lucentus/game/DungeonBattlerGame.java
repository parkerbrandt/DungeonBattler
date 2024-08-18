package com.lucentus.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.lucentus.game.screens.MainMenuScreen;

import java.util.Iterator;

/**
 * Core Class of the Dungeon Battler Game
 */
public class DungeonBattlerGame extends Game {

	// Static Class Members
	public static final int VIEWPORT_HEIGHT = 1080;
	public static final int VIEWPORT_WIDTH = 1920;

	// Variables
	public ShapeRenderer shape;
	public SpriteBatch batch;
	public BitmapFont font;
	
	@Override
	public void create () {
		shape = new ShapeRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();

		// Set the main menu screen
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}


	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
