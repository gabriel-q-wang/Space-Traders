package cs2340.spacetraders.entity;

import java.io.Serializable;

/**
 * The player playing the game
 */
public class Player implements Serializable {


    private final int  initialCredits = 100000;
    private final String name;
    private final int engineerStat;
    private final int fighterStat;
    private final int traderStat;
    private final int pilotStat;
    private int credits;
    private final Ship ship;
    private final Inventory inventory;
    private boolean criminalStatus;

    /**
     * Makes a player with name, stats, and ship
     * @param name the name of the player
     * @param engineerStat the engineer stats of the player
     * @param fighterStat the fighter stats of the player
     * @param traderStat the trader stats of the player
     * @param pilotStat the pilot stats of the player
     */
    public Player(String name, int engineerStat, int fighterStat, int traderStat, int pilotStat) {
        this.name = name;
        this.engineerStat = engineerStat;
        this.fighterStat = fighterStat;
        this.traderStat = traderStat;
        this.pilotStat = pilotStat;
        this.credits = 100000;
        this.ship = new Ship();
        this.inventory = new Inventory(ship.getCargoCapacity());
        this.criminalStatus = false;
    }

    /**
     * Return the player's inventory
     * @return the player's inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * @return the number of credit held by the player
     */
    public int getCredits() {return credits;}

    /**
     * Returns ship price
     * @return ship's shipType price
     */
    public int getShipTypePrice() {return ship.getShipTypePrice();}

    /**
     * @param changeNum the number of credits to be changed
     */
    public void changeCredits(int changeNum) {credits += changeNum;}

    /**
     * @param shipType the ship type being changed to
     */
    public void setShipType(ShipType shipType) {
        ship.setShipType(shipType);
    }

    /**
     * @return the player's ship type
     */
    public ShipType getShipType() {return ship.getShipType();}

    /**
     * Gets a string representation of the player's attributes
     * @return a string representation of the player's attributes
     */
    @Override
    public String toString() {
        String retStr = "";
        retStr += "Player name: " + name;
        retStr += ", Engineer stat: " + engineerStat;
        retStr += ", Fighter stat: " + fighterStat;
        retStr += ", Trader stat: " + traderStat;
        retStr += ", Pilot stat: " + pilotStat;
        retStr += ", Number of Credits: " + credits;
        retStr += ", Ship Info: " + ship;
        return retStr;
    }

    /**
     * Changes the criminal status of the player
     * @param status player's criminal status
     */
    public void setCriminalStatus(boolean status) {
        criminalStatus = status;
    }

    /**
     * Getter for the player's criminal status
     * @return criminalStatus
     */
    public boolean getCriminalStatus() {
        return criminalStatus;
    }

    /**
     * Action upon player's death
     */
    public void death() {

    }

    /**
     * @return the player's ship
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Player's ship takes damage after getting attacked
     * @param damage Damage taken
     */
    public void takeDamage(int damage) {
        ship.takeDamage(damage);
    }

    /**
     * Getter for the player's health
     * @return ship's health
     */
    public int getHealth() {
        return ship.getHealth();
    }

    public String getName() {
        return name;
    }

    public int[] getClassPoints() {
        return new int[]{engineerStat,  fighterStat,  traderStat,  pilotStat};
    }
}
