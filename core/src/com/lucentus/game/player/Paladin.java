package com.lucentus.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.lucentus.game.DungeonBattlerGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Paladin Player Class
 * A Tank class that specializes in defending other party members
 */
public class Paladin extends Player {

    // Static Members
    public static final String RUN_SHEET = "paladin/Animations/Run.png";
    public static final int RUN_FRAME_COLS = 6;
    public static final int RUN_FRAME_ROWS = 1;

    // Properties


    // Constructor

    /**
     *
     */
    public Paladin() {
        super();

        // Load the running animation
        this.runSheet = new Texture(Gdx.files.internal(Paladin.RUN_SHEET));
        TextureRegion[][] temp = TextureRegion.split(runSheet,
                                        runSheet.getWidth() / Paladin.RUN_FRAME_COLS,
                                        runSheet.getHeight() / Paladin.RUN_FRAME_ROWS);
        TextureRegion[] runFrames = new TextureRegion[Paladin.RUN_FRAME_COLS * Paladin.RUN_FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < Paladin.RUN_FRAME_ROWS; i++) {
            for (int j = 0; j < Paladin.RUN_FRAME_COLS; j++) {
                runFrames[index++] = temp[i][j];
            }
        }

        this.runningAnimation = new Animation<>((float) (Paladin.RUN_FRAME_COLS * Paladin.RUN_FRAME_ROWS) / 60, runFrames);

        // Initialize variables
        this.hitbox = new Rectangle(0, 0, Player.PLAYER_SIZE, Player.PLAYER_SIZE);

        // Read the stat data from the stat file
        this.moveSpeed = 200;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(Gdx.files.internal("stats/paladin.json"))));
        } catch(IOException e) {
            System.out.println("Error reading Paladin stat file.");
        }
    }

    // Override Methods
    @Override
    public TextureRegion getCurrentFrame(float time) {
        return runningAnimation.getKeyFrame(time, true);
    }

    /*
     * Allow the player to move
     * TODO: Update rectangle x and y instead of
     */

    @Override
    public void moveUp() {
        hitbox.y += moveSpeed * Gdx.graphics.getDeltaTime();

        // Check bounds
        if (hitbox.y < 0) hitbox.y = 0;
        if (hitbox.y > DungeonBattlerGame.VIEWPORT_HEIGHT - Player.PLAYER_SIZE)
            hitbox.y = DungeonBattlerGame.VIEWPORT_HEIGHT - Player.PLAYER_SIZE;
    }

    @Override
    public void moveDown() {
        hitbox.y -= moveSpeed * Gdx.graphics.getDeltaTime();

        // Check bounds
        if (hitbox.y < 0) hitbox.y = 0;
        if (hitbox.y > DungeonBattlerGame.VIEWPORT_HEIGHT - Player.PLAYER_SIZE)
            hitbox.y = DungeonBattlerGame.VIEWPORT_HEIGHT - Player.PLAYER_SIZE;
    }

    @Override
    public void moveRight() {
        hitbox.x += moveSpeed * Gdx.graphics.getDeltaTime();

        // Check bounds
        if (hitbox.x < 0) hitbox.x = 0;
        if (hitbox.x > DungeonBattlerGame.VIEWPORT_WIDTH - Player.PLAYER_SIZE)
            hitbox.x = DungeonBattlerGame.VIEWPORT_WIDTH - Player.PLAYER_SIZE;
    }

    @Override
    public void moveLeft() {
        hitbox.x -= moveSpeed * Gdx.graphics.getDeltaTime();

        // Check bounds
        if (hitbox.x < 0) hitbox.x = 0;
        if (hitbox.x > DungeonBattlerGame.VIEWPORT_WIDTH - Player.PLAYER_SIZE)
            hitbox.x = DungeonBattlerGame.VIEWPORT_WIDTH - Player.PLAYER_SIZE;
    }

    // Methods

}
