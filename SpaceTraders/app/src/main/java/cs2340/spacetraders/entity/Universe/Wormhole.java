package cs2340.spacetraders.entity.Universe;

import java.io.Serializable;

public class Wormhole implements Serializable {
    private Wormhole connectedWormhole;
    private RelativePosition position;
    private int id;
    private static int idCounter;

    /**
     * Constructor for wormhole given to integers representing position
     *
     * @param thisHoleX x coordinate of wormhole
     * @param thisHoleY y coordinate of wormhole
     */
    public Wormhole(int thisHoleX, int thisHoleY) {
        position = new RelativePosition(thisHoleX,thisHoleY);
        id = ++idCounter;
    }

    /**
     * Constructor for wormhole given Relative Position Object
     *
     * @param pos RelativePosition object to represent where the wormhole is
     */
    public Wormhole(RelativePosition pos) {
        this(pos.getX(), pos.getY());
    }

    /**
     * Given wormhole A and wormhole B (called routeToHere)
     * A's connectedWormhole pointer is set to B
     *
     * @param routeToHere Wormhole to route to
     */
    private void setConnectingWormhole(Wormhole routeToHere) {
        this.connectedWormhole = routeToHere;
    }

    /**
     * Given wormhole A and B
     * A's womrhole pointer points to B, and B's pointer will point to A
     *
     * @param routeToHere the second wormhole to join the two wormholes
     */
    public void joinWormholes(Wormhole routeToHere) {
        this.connectedWormhole = routeToHere;
        routeToHere.setConnectingWormhole(this);
    }

    /**
     *
     * @return this wormhole's x position
     */
    public int getX() {
        return position.getX();
    }

    /**
     *
     * @return this wormhole's y position
     */
    public int getY() {
        return position.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Wormhole)) {
            return false;
        }
        Wormhole targetHole = (Wormhole) o;
        return this.position.getY() == targetHole.position.getY() && this.position.getX() == targetHole.position.getX();
    }

    @Override
    public int hashCode() {
        return 3 * position.getY() + 7 * position.getX();
    }

    public String toString(){
        String retStr = "this wormhole is located at " + this.position;
        if (connectedWormhole == null) {
            return retStr + " but not connected to another wormhole";
        }
        return retStr + " connected to a wormhole located at " + connectedWormhole.position;
    }
}
