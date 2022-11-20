package ca.cmpt276.myapplication2.model;

import android.widget.Toast;

import java.util.ArrayList;

import ca.cmpt276.myapplication2.EditGamePlay;

/**
 * This class is for a single game configuration.
 */

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

    public void deleteGameById(int index){
        this.gamesList.remove(index);
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

    public Game getGame(int index){
        Game gameToReturn;
        try{
            gameToReturn = gamesList.get(index);
        }catch(ArrayIndexOutOfBoundsException outOfBoundsException){
            return null;
        }
        return gameToReturn;
    }

    public int getGamesListSize(){
        return gamesList.size();
    }

}
