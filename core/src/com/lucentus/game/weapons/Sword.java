package com.lucentus.game.weapons;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.lucentus.game.enemy.Enemy;

public class Sword extends Weapon {

    // Properties

    // Constructors
    public Sword() {
        super();

        // Initialize the hitbox
        this.hitbox = new Rectangle();
    }

    // Override Methods
    @Override
    protected void onAttack(Array<Enemy> enemies) {
        // Check if there are any enemies that overlap with the sword's hitbox
        for (Enemy enemy : enemies) {
            if (this.hitbox.overlaps(enemy.getHitbox())) {
                
            }
        }
    }
}
