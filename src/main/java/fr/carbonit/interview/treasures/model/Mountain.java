package fr.carbonit.interview.treasures.model;


import java.awt.*;

public class Mountain implements MapObject {
    private final Point position;

    /*
    Public constructor.
     */
    public Mountain(Point position) {
        this.position = position;
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
    Method returns the quantity, which is always 0 for a mountain.
     */
    public int getQuantity() {
        return 0;
    }
}
