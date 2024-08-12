package com.lucentus.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.lucentus.game.DungeonBattlerGame;

/**
 * The Paladin Player Class
 * A Tank class that specializes in defending other party members
 */
public class Paladin extends Player {

    // Static Members
    public static final String IDLE_SHEET = "paladin/Animations/Idle.png";
    public static final int IDLE_FRAME_COLS = 5;
    public static final int IDLE_FRAME_ROWS = 1;

    public static final String RUN_SHEET = "paladin/Animations/Run.png";
    public static final int RUN_FRAME_COLS = 6;
    public static final int RUN_FRAME_ROWS = 1;

    public static final String ATK_SHEET = "paladin/Animations/Attack_1.png";
    public static final int ATK_FRAME_COLS = 4;
    public static final int ATK_FRAME_ROWS = 1;

    // Properties
    private Animation<TextureRegion> currentAnimation;
    private int lastAtkTime;


    // Constructor

    /**
     *
     */
    public Paladin() {
        super();

        // Load the idle animation
        this.idleSheet = new Texture(Gdx.files.internal(Paladin.IDLE_SHEET));
        TextureRegion[][] temp = TextureRegion.split(idleSheet,
                                    idleSheet.getWidth() / Paladin.IDLE_FRAME_COLS,
                                    idleSheet.getHeight() / Paladin.IDLE_FRAME_ROWS);
        TextureRegion[] idleFrames = new TextureRegion[Paladin.IDLE_FRAME_COLS * Paladin.IDLE_FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < Paladin.IDLE_FRAME_ROWS; i++) {
            for (int j = 0; j < Paladin.IDLE_FRAME_COLS; j++) {
                idleFrames[index++] = temp[i][j];
            }
        }

        this.idleAnimation = new Animation<>((float) (Paladin.IDLE_FRAME_COLS * Paladin.IDLE_FRAME_ROWS) / 60, idleFrames);
        this.currentAnimation = idleAnimation;

        // Load the running animation
        this.runSheet = new Texture(Gdx.files.internal(Paladin.RUN_SHEET));
        temp = TextureRegion.split(runSheet,
                                        runSheet.getWidth() / Paladin.RUN_FRAME_COLS,
                                        runSheet.getHeight() / Paladin.RUN_FRAME_ROWS);
        TextureRegion[] runFrames = new TextureRegion[Paladin.RUN_FRAME_COLS * Paladin.RUN_FRAME_ROWS];
        index = 0;
        for (int i = 0; i < Paladin.RUN_FRAME_ROWS; i++) {
            for (int j = 0; j < Paladin.RUN_FRAME_COLS; j++) {
                runFrames[index++] = temp[i][j];
            }
        }

        this.runningAnimation = new Animation<>((float) (Paladin.RUN_FRAME_COLS * Paladin.RUN_FRAME_ROWS) / 60, runFrames);

        // Load the attack animation
        this.atkSheet = new Texture(Gdx.files.internal(Paladin.ATK_SHEET));
        temp = TextureRegion.split(atkSheet,
                                atkSheet.getWidth() / Paladin.ATK_FRAME_COLS,
                                atkSheet.getHeight() / Paladin.ATK_FRAME_ROWS);
        TextureRegion[] atkFrames = new TextureRegion[Paladin.ATK_FRAME_COLS * Paladin.ATK_FRAME_ROWS];
        index = 0;
        for (int i = 0; i < Paladin.ATK_FRAME_ROWS; i++) {
            for (int j = 0; j < Paladin.ATK_FRAME_COLS; j++) {
                atkFrames[index++] = temp[i][j];
            }
        }

        this.attackAnimation = new Animation<>((float) (Paladin.ATK_FRAME_COLS * Paladin.ATK_FRAME_ROWS) / 60, atkFrames);

        // Initialize variables
        this.hitbox = new Rectangle(0, 0, Player.PLAYER_SIZE, Player.PLAYER_SIZE);

        this.moveSpeed = 200;

        // Load all stats from designated file
        readStatsFromFile("stats/paladin.csv");
    }

    // Override Methods

    /**
     * Determine which frame of animation the player character will be on
     * Currently can display either running (WASD) or attacking (Left Click)
     * @param time the current statetime of the game to determine which frame
     * @return the frame of animation that will be displayed
     */
    @Override
    public TextureRegion getCurrentFrame(float time) {
        return currentAnimation.getKeyFrame(time, true);
    }

    @Override
    public void attack() {
        currentAnimation = attackAnimation;
    }

    @Override
    public void idle() {
        currentAnimation = idleAnimation;
    }

    /*
     * Allow the player to move
     */

    @Override
    public void moveUp() {
        hitbox.y += moveSpeed * Gdx.graphics.getDeltaTime();

        // Check bounds
        if (hitbox.y < 0) hitbox.y = 0;
        if (hitbox.y > DungeonBattlerGame.VIEWPORT_HEIGHT - Player.PLAYER_SIZE)
            hitbox.y = DungeonBattlerGame.VIEWPORT_HEIGHT - Player.PLAYER_SIZE;

        currentAnimation = runningAnimation;
    }

    @Override
    public void moveDown() {
        hitbox.y -= moveSpeed * Gdx.graphics.getDeltaTime();

        // Check bounds
        if (hitbox.y < 0) hitbox.y = 0;
        if (hitbox.y > DungeonBattlerGame.VIEWPORT_HEIGHT - Player.PLAYER_SIZE)
            hitbox.y = DungeonBattlerGame.VIEWPORT_HEIGHT - Player.PLAYER_SIZE;

        currentAnimation = runningAnimation;
    }

    @Override
    public void moveRight() {
        hitbox.x += moveSpeed * Gdx.graphics.getDeltaTime();

        // Check bounds
        if (hitbox.x < 0) hitbox.x = 0;
        if (hitbox.x > DungeonBattlerGame.VIEWPORT_WIDTH - Player.PLAYER_SIZE)
            hitbox.x = DungeonBattlerGame.VIEWPORT_WIDTH - Player.PLAYER_SIZE;

        currentAnimation = runningAnimation;
    }

    @Override
    public void moveLeft() {
        hitbox.x -= moveSpeed * Gdx.graphics.getDeltaTime();

        // Check bounds
        if (hitbox.x < 0) hitbox.x = 0;
        if (hitbox.x > DungeonBattlerGame.VIEWPORT_WIDTH - Player.PLAYER_SIZE)
            hitbox.x = DungeonBattlerGame.VIEWPORT_WIDTH - Player.PLAYER_SIZE;

        currentAnimation = runningAnimation;
    }

    @Override
    public void dispose() {
        runSheet.dispose();
        atkSheet.dispose();
    }

    // Methods

}
