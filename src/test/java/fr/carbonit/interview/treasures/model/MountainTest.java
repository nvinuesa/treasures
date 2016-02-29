package fr.carbonit.interview.treasures.model;


import org.junit.Before;
import org.junit.Test;
import java.awt.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MountainTest {
    private static final int posX = 5;
    private static final int posY = 6;
    private static final Point position = new Point(posX, posY);
    Mountain mountain;

    @Before
    public void setUp() {
        mountain = new Mountain(position);
    }

    @Test
    public void testMountain() {
        assertEquals(Mountain.class, mountain.getType());
        assertEquals(position, mountain.getPosition());
        assertNotNull(mountain);
    }

}
