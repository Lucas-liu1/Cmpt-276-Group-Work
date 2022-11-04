package ca.cmpt276.myapplication2.model;

import java.util.ArrayList;

public class Configuration {
    private String name;
    private int poor_score;
    private int great_score;
    private ArrayList<Game> gamesList = new ArrayList<>(); // Store all the game under the configuration

    // All Constructors
    public Configuration() {
    }

    public Configuration(String name, int poor_score, int great_score){
        this.name = name;
        this.poor_score = poor_score;
        this.great_score = great_score;
    }

    // Add a new game
    public void addGame(Game newGame){
        gamesList.add(newGame);
    }

    // All Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPoor_score(int poor_score) {
        this.poor_score = poor_score;
    }

    public void setGreat_score(int great_score) {
        this.great_score = great_score;
    }

    public void setGamesList(ArrayList<Game> gamesList) {
        this.gamesList = gamesList;
    }

    // All Getters
    public String getName() {
        return name;
    }

    public int getPoor_score() {
        return poor_score;
    }

    public int getGreat_score() {
        return great_score;
    }

    public ArrayList<Game> getGamesList() {
        return gamesList;
    }

    public int getGamesListSize(){
        return gamesList.size();
    }
}
