package com.lucentus.dungeon.users;

import com.lucentus.dungeon.entities.Entity;
import com.lucentus.dungeon.entities.Warrior;

import java.util.ArrayList;


/**
 * Class to represent each User/Player
 * and all information associated
 */
public class Player {

    /*
     * Properties
     */
    private final String name;
    private final String userId;

    private ArrayList<Warrior> characters;
    private int activeChar;


    /*
     * Constructors
     */

    /**
     * Base Constructor - Create user information and persist
     */
    public Player() {
        // TODO: Create a username and ID
        this.name = "";
        this.userId = "000000";
    }

    /**
     * Populate user with previously existing information
     * @param name
     * @param userId
     */
    public Player(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }


    /*
     * Methods
     */

    /**
     * Retrieves all player information from the database
     */
    private void getPlayerInformation() {

    }

    /*
     * Getters and Setters
     */
    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public Warrior getCharacter() {
        return characters.get(activeChar);
    }
}
