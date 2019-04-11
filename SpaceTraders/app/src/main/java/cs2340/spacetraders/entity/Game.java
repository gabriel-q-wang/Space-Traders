package cs2340.spacetraders.entity;

import java.io.Serializable;

import cs2340.spacetraders.entity.Universe.Galaxy;

public class Game {
    private Difficulty difficulty;
    private Galaxy galaxy;

    /** Initializes the game difficulty
     * @param difficulty the game difficulty
     */
    public Game(Difficulty difficulty) {
        this.difficulty = difficulty;
        makeGalaxy();
    }

    /**
     * Makes a new Galaxy
     */
    private void makeGalaxy() {
        galaxy = new Galaxy();
    }

    public Galaxy getGalaxy() {
        return galaxy;
    }

    /** Gets the game difficulty
     * @return the game difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /** Gets the string representation fo the game difficulty
     * @return the string representation fo the game difficulty
     */
    public String toString() {
        return "The Game Difficulty is" + difficulty.toString();
    }
}
