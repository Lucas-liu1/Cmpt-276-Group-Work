package ca.cmpt276.myapplication2.model;

/**
 * This class is for single Achievement.
 */

public class Achievement {
    private String name;
    private int lowerBound;
    private int upperBound;
    private int numPlayers;

    public Achievement(String name, int lowerBound, int upperBound, int numPlayers) {
        this.name = name;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.numPlayers = numPlayers;
    }

    public String getName() {
        return name;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public int getNumPlayers() {
        return numPlayers;
    }
}
