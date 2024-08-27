package com.lucentus.game.weapons;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.lucentus.game.enemy.Enemy;

public abstract class Weapon {

    // Properties
    protected Rectangle hitbox;

    // Constructors
    public Weapon() {

    }

    // Abstract Methods
    protected abstract void onAttack(Array<Enemy> enemies);
}
