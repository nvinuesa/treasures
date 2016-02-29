package fr.carbonit.interview.treasures.model;


import org.junit.Before;
import org.junit.Test;
import java.awt.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TreasureTest {
    private static final int posX = 5;
    private static final int posY = 6;
    private static final Point position = new Point(posX, posY);
    private static final int quantity = 100;
    private Treasure treasure;

    @Before
    public void setUp() {
        treasure = new Treasure(position, quantity);
    }

    @Test
    public void testTreasure() {
        assertEquals(Treasure.class, treasure.getType());
        assertEquals(position, treasure.getPosition());
        assertEquals(quantity, treasure.getQuantity());
        assertNotNull(treasure);

    }

}
