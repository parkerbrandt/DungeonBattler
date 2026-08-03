package com.lucentus.dungeon.entities;

/**
 * Base of all classes that are actual objects in the game
 */
public abstract class Entity {

    /*
     * Constructors
     */
    public Entity() {

    }


    /*
     * Abstract Methods
     */
    abstract void onAttack();
    abstract void onHit();
}
