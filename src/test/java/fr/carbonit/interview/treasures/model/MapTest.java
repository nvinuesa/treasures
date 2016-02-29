package fr.carbonit.interview.treasures.model;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MapTest {
    private static final Point legalPosition = new Point(1, 1);
    private static final Point illegalPosition = new Point(5, 5);

    // private static final String mapFilePath = "C:\\workspaces\\treasures\\src\\main\\java\\fr\\carbonit\\interview\\treasures\\mapFile.txt";
    private static final String mapFilePath = "/home/nico/workspace/treasures/src/main/java/fr/carbonit/interview/treasures/mapFile.txt";


    private Map map;
    private Point position;

    @Mock
    private LogicFactory factory;

    @Before
    public void setUp() throws IOException {
        // when(factory.createPosition(Matchers.anyInt(), Matchers.anyInt())).thenReturn();
        this.map = new Map(factory);
    }

    @Test
    public void testMapInit() throws IOException {
        File file = new File(mapFilePath);
        map.init(file);
        assertTrue(map.isLegal(legalPosition));
        assertFalse(map.isLegal(illegalPosition));
    }

}
