package fr.carbonit.interview.treasures.model;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Map {
    private ConcurrentHashMap<Point, MapObject> map = new ConcurrentHashMap<>();

    private int columns = 0;

    private int rows = 0;

    private LogicFactory factory;

    private List<Treasure> treasures = new ArrayList<Treasure>();

    private List<Mountain> mountains = new ArrayList<Mountain>();

    private java.util.Map<String, Point> adventurerPositions = new HashMap<>();

    /*
    Public constructor.
     */
    public Map(LogicFactory factory) {
        this.factory = factory;
    }

    /*
    Method returns a boolean indicating if the parameter position is legal in this map.
     */
    public boolean isLegal(Point position) {
        return (this.map.get(position) == null ?
                position.getX()<this.columns &
                position.getY()<this.rows :
                !this.map.get(position).getType().equals(Mountain.class)
                );
    }

    /*
    Method returns a boolean indicating if the parameter position is not occupied by
    any OTHER player in this map. Name parameter is used to this end.
     */
    public boolean isOccupied(String name, Point position) {
        return (adventurerPositions.containsValue(position) && !adventurerPositions.get(name).equals(position));
    }

    /*
    Method returns an int indicating the collected treasure in this position, 0 otherwise.
     */
    public int visit(String name, Point position) {
        adventurerPositions.put(name, position);
        int quantity = 0;
        if (this.map.get(position) != null) {
            quantity = this.map.get(position).getQuantity();
            this.map.remove(position);
        }
        return quantity;
    }

    /*
    Method to initialize a map from a text file.
     */
    public void init(File mapFile) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(mapFile));
        String line = br.readLine();
        if (!line.substring(0,1).equals("C")) {
            throw new  IllegalArgumentException("Incorrect file format, first line must contain C X Y");
        }
        String[] st = line.split("\\s");
        this.columns = Integer.parseInt(st[1]);
        this.rows = Integer.parseInt(st[2]);


        String[] pos;
        Point position;
        while ((line = br.readLine()) != null) {
            char[] sub = line.toCharArray();
            switch (sub[0]) {
                case 'T' :
                    st = line.split("\\s");
                    pos = st[1].split("-");
                    position = factory.createPosition(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
                    treasures.add(factory.createTreasure(position, Integer.parseInt(st[2])));
                    break;
                case 'M' :
                    st = line.split("\\s");
                    pos = st[1].split("-");
                    position = factory.createPosition(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
                    mountains.add(factory.createMountain(position));
                    break;
                default:
                    throw new IllegalArgumentException("Incorrect file format.");

            }
        }

        br.close();
        treasures.forEach(k -> map.put(k.getPosition(), k));
        mountains.forEach(k -> map.put(k.getPosition(), k));
    }
    
}
