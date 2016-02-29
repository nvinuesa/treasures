package fr.carbonit.interview.treasures.model;


import java.awt.*;

public interface MapObject {
    Class getType();

    Point getPosition();

    int getQuantity();
}
