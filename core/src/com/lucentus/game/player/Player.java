package com.lucentus.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



/**
 * Abstract class used to define common functionality across all playable characters
 */
public abstract class Player {

    public enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    };

    // Properties
    protected int playerSize;
    protected float scale = 1.0f;

    protected Rectangle hitbox;
    protected int teamNum;
    protected Direction facingDir = Direction.NORTH;

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

    // Methods
    protected void readStatsFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(Gdx.files.internal(filename))));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                switch(data[0]) {
                    case "hit_points":
                        this.maxHitPoints = Integer.parseInt(data[1]);
                        this.currentHitPoints = this.maxHitPoints;
                        break;

                    case "atk_damage":
                        this.atkDamage = Integer.parseInt(data[1]);
                        break;

                    case "move_speed":
                        this.moveSpeed = Integer.parseInt(data[1]);
                        break;

                    default:
                        break;
                }
            }
            reader.close();

        } catch(IOException e) {
            System.out.printf("Error reading %s stat file.", filename);
        }
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
    public int getPlayerSize() {
        return (int)scale * playerSize;
    }

    public float getX() {
        return hitbox.x;
    }

    public float getY() {
        return hitbox.y;
    }

    public Direction getFacingDir() {
        return facingDir;
    }
}
