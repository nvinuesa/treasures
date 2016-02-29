package fr.carbonit.interview.treasures.model;


import rx.Observable;
import rx.Subscriber;

import java.awt.*;
import java.io.IOException;

public class Adventurer extends Thread {
    private String name;

    private LogicFactory factory;

    private Map map;

    private int treasuresCollected = 0;

    private String orientation;

    private Point currentPosition;

    private Observable<String> observable = null;

    /*
    Method to return collected treasures of this player.
     */
    public int getTreasuresCollected() {
        return treasuresCollected;
    }

    /*
    Public constructor.
     */
    public Adventurer(LogicFactory factory, String line, Map map) throws IOException {
        this.factory = factory;
        String[] st = line.split("\\s");
        this.name = st[0];
        String[] pos = st[1].split("-");
        currentPosition = factory.createPosition(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
        orientation = st[2];
        this.map = map;
        if (4 == st.length) {
            this.observable = Observable.from(st[3].split(""));
        }
        map.visit(this.name, currentPosition);
    }

    /*
    Implemented run() method from java.lang.Thread.
     */
    public void run() {
        if (observable != null) {
            observable.subscribe(subscriber);
        }
    }

    /*
    RxJava Subscriber overriden, definitions of each movement in onNext().
     */
    private Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            System.out.println("Player " +
                    name +
                    " finished his movements, he collected " +
                    treasuresCollected +
                    " treasures."
            );
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println(throwable);
        }

        @Override
        public void onNext(String direction) {
            switch (direction) {
                case "A":
                    if (map.isLegal(moveForward(currentPosition))) {
                        while (map.isOccupied(name, moveForward(currentPosition))) ;
                        treasuresCollected += map.visit(name, moveForward(currentPosition));
                        currentPosition = moveForward(currentPosition);
                    }
                    break;
                case "G":
                    if (map.isLegal(moveLeft(currentPosition))) {
                        while (map.isOccupied(name, moveLeft(currentPosition))) ;
                        treasuresCollected += map.visit(name, moveLeft(currentPosition));
                        currentPosition = moveLeft(currentPosition);
                    }
                    break;
                case "D":
                    if (map.isLegal(moveRight(currentPosition))) {
                        while (map.isOccupied(name, moveRight(currentPosition))) ;
                        treasuresCollected += map.visit(name, moveRight(currentPosition));
                        currentPosition = moveRight(currentPosition);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Illegal move, incorrect direction.");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    };

    /*
    Move one case forward on this adventurer. The direction taken at each turn depends
    on the initial direction.
     */
    private Point moveForward(Point position) {
        switch (orientation) {
            case "N" :
                return factory.createPosition((int) position.getX(), (int) position.getY() - 1);
            case "S" :
                return factory.createPosition((int) position.getX(), (int) position.getY() + 1);
            case "E" :
                return factory.createPosition((int) position.getX() - 1, (int) position.getY());
            case "O" :
                return factory.createPosition((int) position.getX() + 1, (int) position.getY());
            default :
                throw new IllegalArgumentException("Illegal move, incorrect orientation.");
        }
    }

    /*
    Move one case right on this adventurer. The direction taken at each turn depends
    on the initial direction.
     */
    private Point moveRight(Point position) {
        switch (orientation) {
            case "N" :
                return factory.createPosition((int) position.getX() + 1, (int) position.getY());
            case "S" :
                return factory.createPosition((int) position.getX() - 1, (int) position.getY());
            case "E" :
                return factory.createPosition((int) position.getX(), (int) position.getY() - 1);
            case "O" :
                return factory.createPosition((int) position.getX(), (int) position.getY() + 1);
            default :
                throw new IllegalArgumentException("Illegal move, incorrect orientation.");
        }
    }

    /*
    Move one case left on this adventurer. The direction taken at each turn depends
    on the initial direction.
     */
    private Point moveLeft(Point position) {
        switch (orientation) {
            case "N" :
                return factory.createPosition((int) position.getX() - 1, (int) position.getY());
            case "S" :
                return factory.createPosition((int) position.getX() + 1, (int) position.getY());
            case "E" :
                return factory.createPosition((int) position.getX(), (int) position.getY() + 1);
            case "O" :
                return factory.createPosition((int) position.getX(), (int) position.getY() - 1);
            default :
                throw new IllegalArgumentException("Illegal move, incorrect orientation.");
        }
    }
}
