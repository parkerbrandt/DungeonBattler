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

    // Animations
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
    public void moveUp() {}

    public void moveDown() {}

    public void moveLeft() {}

    public void moveRight() {}

    public TextureRegion getCurrentFrame(float time) {
        return null;
    }


    // Getters & Setters
    public float getX() {
        return hitbox.x;
    }

    public float getY() {
        return hitbox.y;
    }
}
