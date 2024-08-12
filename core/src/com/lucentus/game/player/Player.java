package com.lucentus.game.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Abstract class used to define common functionality across all playable characters
 */
public abstract class Player {

    // Static Members
    public static final int PLAYER_SIZE = 64;

    // Properties
    protected Rectangle hitbox;
    protected int teamNum;

    // Animations
    protected Animation<TextureRegion> idleAnimation;
    protected Texture idleSheet;

    protected Animation<TextureRegion> runningAnimation;
    protected Texture runSheet;

    protected Animation<TextureRegion> attackAnimation;
    protected Texture atkSheet;

    // Stats
    protected int maxHitPoints;
    protected int currentHitPoints;
    protected int atkDamage;
    protected int moveSpeed;


    // Constructor
    public Player() {

    }


    // Abstract Methods
    public abstract void attack();

    public abstract void idle();

    public abstract void moveUp();

    public abstract void moveDown();

    public abstract void moveLeft();

    public abstract void moveRight();

    public abstract TextureRegion getCurrentFrame(float time);

    public abstract void dispose();

    // Getters & Setters
    public float getX() {
        return hitbox.x;
    }

    public float getY() {
        return hitbox.y;
    }
}
