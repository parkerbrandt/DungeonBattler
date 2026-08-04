package com.lucentus.dungeon;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.lucentus.dungeon.screens.BattleScreen;
import com.lucentus.dungeon.screens.HomeScreen;
import com.lucentus.dungeon.screens.StartScreen;

public class DungeonBattler extends Game {

	/*
	 * Static Class Members
	 */
	public static final int VIEWPORT_HEIGHT = 1080;
	public static final int VIEWPORT_WIDTH = 1920;


	/*
	 * Properties
	 */
	public ShapeRenderer shape;
	public SpriteBatch batch;
	public BitmapFont font;


	/*
	 * Overridden Methods
	 */
	@Override
	public void create () {

		shape = new ShapeRenderer();
		batch = new SpriteBatch();
		font = new BitmapFont();

		// Set to title screen
		this.setScreen(new BattleScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		shape.dispose();
		batch.dispose();
		font.dispose();
	}
}
