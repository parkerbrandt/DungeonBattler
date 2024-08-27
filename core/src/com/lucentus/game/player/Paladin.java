package com.lucentus.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.lucentus.game.DungeonBattlerGame;
import com.lucentus.game.enemy.Enemy;

import java.io.IOException;

/**
 * The Paladin Player Class
 * A Tank class that specializes in defending other party members
 */
public class Paladin extends Player {

    // Static Members
    public static final String STAT_FILENAME = "stats/paladin.csv";

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
    private String currentAnimationName = "idle";
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
        this.playerSize = idleSheet.getWidth() / Paladin.IDLE_FRAME_COLS;
        this.hitbox = new Rectangle(0, 0, playerSize, playerSize);

        // Load all stats from designated file
        try {
            readStatsFromFile(Paladin.STAT_FILENAME);
        } catch (IOException e) {
            System.out.println("Exception occurred while reading " + Paladin.STAT_FILENAME);

            // Set default stats
            this.maxHitPoints = 300;
            this.currentHitPoints = 300;
            this.atkDamage = 15;
            this.moveSpeed = 200;
        }
    }

    // Override Methods
    @Override
    public void draw(final DungeonBattlerGame game, OrthographicCamera camera, float time) {
        game.batch.begin();

        // Check if the frame needs to be flipped if the player is facing a certain direction
        // SOURCE: https://stackoverflow.com/questions/28000623/libgdx-flip-2d-sprite-animation
        boolean flip = (this.getFacingDir() == Player.Direction.WEST);
        game.batch.draw(this.getCurrentFrame(time), flip ? this.getX() + this.getPlayerSize() : this.getX(), this.getY(), flip ? - this.getPlayerSize() : this.getPlayerSize(), this.getPlayerSize());
        game.batch.end();

        // If we are attacking, then draw the sword as a box in front of where the player is facing
        if (this.currentAnimationName.equals("attack")) {
            game.shape.setProjectionMatrix(camera.combined);
            game.shape.begin(ShapeRenderer.ShapeType.Filled);
            game.shape.setColor(Color.WHITE);
            float weaponX = this.facingDir == Direction.EAST ? getX() + 70 : getX() - 70;
            game.shape.rect(weaponX, getY(), getHitbox().getWidth() / 2, getHitbox().getHeight());
            game.shape.end();
        }
    }

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
    public void idle() {
        currentAnimation = idleAnimation;
        currentAnimationName = "idle";
    }

    @Override
    public void onAttack(Array<Enemy> enemies) {
        // TODO: Need to check for last time we attacked

        currentAnimation = attackAnimation;
        currentAnimationName = "attack";

        // TODO: Swing sword and check if collides with any enemies
        // Sword will swing hit in box in front of player
        // Sword will only exist while attacking
    }

    @Override
    public void onHit(Enemy enemy) {
        // TODO: Reduce HP by an amount
    }

    /*
     * Allow the player to move
     */

    @Override
    public void moveUp() {
        hitbox.y += moveSpeed * Gdx.graphics.getDeltaTime();

        // Check bounds
        if (hitbox.y < 0) hitbox.y = 0;
        if (hitbox.y > DungeonBattlerGame.VIEWPORT_HEIGHT - playerSize)
            hitbox.y = DungeonBattlerGame.VIEWPORT_HEIGHT - playerSize;

        currentAnimation = runningAnimation;
        currentAnimationName = "run";
    }

    @Override
    public void moveDown() {
        hitbox.y -= moveSpeed * Gdx.graphics.getDeltaTime();

        // Check bounds
        if (hitbox.y < 0) hitbox.y = 0;
        if (hitbox.y > DungeonBattlerGame.VIEWPORT_HEIGHT - playerSize)
            hitbox.y = DungeonBattlerGame.VIEWPORT_HEIGHT - playerSize;

        currentAnimation = runningAnimation;
        currentAnimationName = "run";
    }

    @Override
    public void moveRight() {
        hitbox.x += moveSpeed * Gdx.graphics.getDeltaTime();

        // Check bounds
        if (hitbox.x < 0) hitbox.x = 0;
        if (hitbox.x > DungeonBattlerGame.VIEWPORT_WIDTH - playerSize)
            hitbox.x = DungeonBattlerGame.VIEWPORT_WIDTH - playerSize;

        this.facingDir = Direction.EAST;
        currentAnimation = runningAnimation;
        currentAnimationName = "run";
    }

    @Override
    public void moveLeft() {
        hitbox.x -= moveSpeed * Gdx.graphics.getDeltaTime();

        // Check bounds
        if (hitbox.x < 0) hitbox.x = 0;
        if (hitbox.x > DungeonBattlerGame.VIEWPORT_WIDTH - playerSize)
            hitbox.x = DungeonBattlerGame.VIEWPORT_WIDTH - playerSize;

        this.facingDir = Direction.WEST;
        currentAnimation = runningAnimation;
        currentAnimationName = "run";

    }

    @Override
    public void dispose() {
        idleSheet.dispose();
        runSheet.dispose();
        atkSheet.dispose();
    }

    // Methods

}
