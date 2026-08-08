package com.lucentus.dungeon.entities;

public class MovingEntity extends Entity {

    /*
     * Properties
     */
    protected float moveSpeed;


    /*
     * Overridden Methods
     */
    @Override
    void onAttack() {

    }

    @Override
    void onHit() {

    }


    /*
     * Getters and Setters
     */
    public float getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
}
