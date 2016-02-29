package fr.carbonit.interview.treasures.model;


import java.awt.*;

public class Treasure implements MapObject {
    private final Point position;
    private final int quantity;

    /*
    Public constructor.
     */
    public Treasure(Point position, int quantity) {
        this.position = position;
        this.quantity = quantity;
    }

    /*
    Method returns the class type of this object.
     */
    public Class getType () {
        return this.getClass();
    }

    /*
    Method returns the position.
     */
    public Point getPosition() {
        return this.position;
    }

    /*
    Method returns the quantity of the treasure.
     */
    public int getQuantity() {
        return this.quantity;
    }
}
