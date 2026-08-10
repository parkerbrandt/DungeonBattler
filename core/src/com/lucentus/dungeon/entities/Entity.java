package com.lucentus.dungeon.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.lucentus.dungeon.DungeonBattler;
import com.lucentus.dungeon.entities.skills.Skill;

import java.util.ArrayList;


/**
 * Base of all classes that are actual objects in the game
 */
public abstract class Entity {

    public static enum TEAM {
        SOLAR,
        LUNAR,
        NEUTRAL
    };

    /*
     * Properties
     */
    protected float posX;
    protected float posY;

    protected float width;
    protected float height;

    protected boolean isCollidable = true;

    protected TEAM team = TEAM.NEUTRAL;

    protected ArrayList<Skill> skills;


    /*
     * Constructors
     */
    public Entity() {
        posX = 0.0f;
        posY = 0.0f;
        width = 0.0f;
        height = 0.0f;
    }


    /*
     * Abstract Methods
     */
    abstract void onAttack();
    abstract void onHit();


    /*
     * Methods
     */

    // Rendering
    public void render(DungeonBattler game, Color color) {
        game.shape.begin(ShapeRenderer.ShapeType.Filled);
        game.shape.setColor(color);
        game.shape.rect(
                getPosX(),
                getPosY(),
                getWidth(),
                getHeight()
        );
        game.shape.end();
    }

    public void renderHitbox(DungeonBattler game, Color color) {
        game.shape.begin(ShapeRenderer.ShapeType.Line);
        game.shape.setColor(color);
        game.shape.rect(
                getPosX(),
                getPosY(),
                getWidth(),
                getHeight()
        );
        game.shape.end();
    }

    protected Animation<TextureRegion> loadAnimation(String spriteSheetFilename, int sheetCols, int sheetRows) {

        // Separate sprite sheet into frames to create animation
        Texture sheet = new Texture(Gdx.files.internal(spriteSheetFilename));
        TextureRegion[][] temp = TextureRegion.split(
                                    sheet,
                                    sheet.getWidth() / sheetCols,
                                    sheet.getHeight() / sheetRows
                                );
        TextureRegion[] frames = new TextureRegion[sheetCols * sheetRows];
        int index = 0;
        for (int i = 0; i < sheetRows; i++) {
            for (int j = 0; j < sheetCols; j++) {
                frames[index++] = temp[i][j];
            }
        }

        return new Animation<TextureRegion>((float) (sheetCols * sheetRows) / 60, frames);
    }


    /*
     * Getters and Setters
     */
    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public boolean isCollidable() {
        return isCollidable;
    }

    public TEAM getTeam() {
        return team;
    }
}
