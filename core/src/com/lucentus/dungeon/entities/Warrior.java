package com.lucentus.dungeon.entities;

import com.badlogic.gdx.Gdx;
import com.lucentus.dungeon.utilities.XMLHelper;
import org.w3c.dom.Document;


/**
 * The Playable Character Class aka Warriors
 * Represents a playable character that can move and has access to 4 main abilities
 */
public class Warrior extends MovingEntity {

    /*
     * Properties
     */
    private float attackPwr;
    private float magicPwr;


    /*
     * Constructors
     */

    /**
     * Default Constructor
     * Constructs a Default warrior with base stats
     */
    public Warrior() {
        super();

        this.width = 5.0f;
        this.height = 5.0f;
    }

    /**
     * Constructs a warrior based off of a name and loads related data
     * @param name the name of the pre-existing warrior the player is using
     */
    public Warrior(String name) {
        try {
            String filepath = String.format("game/warriors/%s.xml", name);

            Document warriorInfo = XMLHelper.parseXMLFile(filepath);

            attackPwr = Float.parseFloat(String.valueOf(warriorInfo.getElementById("AttackPwr")));
        } catch (Exception e) {
            System.err.println("Error reading and loading Warrior information: " + e.getMessage());
        }
    }


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

}
