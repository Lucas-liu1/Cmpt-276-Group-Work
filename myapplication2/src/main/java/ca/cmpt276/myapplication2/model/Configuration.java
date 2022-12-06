package ca.cmpt276.myapplication2.model;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import ca.cmpt276.myapplication2.EditGamePlay;

/**
 * This class is for a single game configuration.
 */

public class Configuration {
    private String name;
    private int poor_score;
    private int great_score;
    private ArrayList<Game> gamesList = new ArrayList<>(); // Store all the game under the configuration
    private byte[] photo_byte;

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

    // Returns the number of times this game has achieved each level
    // index 0 is the lowest achievement and index 7 is the top achievement
    public ArrayList<Integer> getConfigurationHistoryStats(){
        ArrayList<Integer> stats = new ArrayList<>();
        int count;
        for(int i = 0; i< 8; i++) {
            stats.add(0);
        }

        for(int j =0; j < getGamesListSize(); j++){
            int scoreLevel = 0;
            ArrayList<Achievement> tempList = gamesList.get(j).getAchievementList().getAchievementsList();
            String level = gamesList.get(j).getLevel();
            for(int i =0 ;i < 8; i++){
                if (Objects.equals(level, tempList.get(i).getName())){
                    scoreLevel = i;
                    break;
                }
            }
            Log.i("CONFIGURATION", ""+scoreLevel);
            count = stats.get(scoreLevel);
            count+=1;
            stats.set(scoreLevel,count);
        }
        return stats;
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

    public void setPhoto_byte(byte[] photo){
        photo_byte = photo;
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

    public byte[] getPhoto_byte() {
        return photo_byte;
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
