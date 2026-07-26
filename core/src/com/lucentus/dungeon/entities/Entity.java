package com.lucentus.dungeon.entities;

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
