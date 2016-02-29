package fr.carbonit.interview.treasures.model;


import java.awt.*;
import java.io.IOException;

public class LogicFactory {
    /*
    Method returns a new point.
     */
    public Point createPosition(int x, int y) {
        return new Point(x, y);
    }

    /*
    Method returns a new treasure.
     */
    public Treasure createTreasure(Point position, int quantity) {
        return new Treasure(position, quantity);
    }

    /*
    Method returns a new mountain.
     */
    public Mountain createMountain(Point position) {
        return new Mountain(position);
    }

    /*
    Method returns a new map.
     */
    public Map createMap(LogicFactory factory) {
        return new Map(factory);
    }

    /*
    Method returns a new adventurer.
     */
    public Adventurer createAdventurer(LogicFactory factory, String line, Map map) {
        Adventurer adventurer = null;
        try {
            adventurer = new Adventurer(factory, line, map);
        } catch (IOException e) {
            System.out.println(e);
        }
        return adventurer;
    }
}
