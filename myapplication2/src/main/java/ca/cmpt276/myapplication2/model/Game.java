package ca.cmpt276.myapplication2.model;

import java.util.ArrayList;

/**
 * This class is for a single game.
 */

public class Game {
    private int numPlayers;
    private int score;
    private AchievementList achievementList; //Single Game's Achievement List
    private ArrayList<Integer> scoresList;
    private String difficulty;
    private String theme;
    private byte[] photo;

    public Game(int numPlayers, ArrayList<Integer> scoresList, String difficulty) {
        this.numPlayers = numPlayers;
        this.scoresList = scoresList;
        this.difficulty = difficulty;
        score = 0;

        //calculate the total score they got(ignore difficulty)
        for(int i=0; i<scoresList.size(); i++){
            score += scoresList.get(i);
        }
    }

    // All Setters
    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public void setAchievementList(AchievementList achievementList) {
        this.achievementList = achievementList;
    }

    public void setScoresList(ArrayList<Integer> scoresList) {
        this.scoresList = scoresList;
        score = 0;
        for(int i=0; i<scoresList.size(); i++){
            score += scoresList.get(i);
        }
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setPhoto(byte[] photo){
        this.photo = photo;
    }



    // All Getters
    public int getNumPlayers() {
        return numPlayers;
    }

    public int getScore() {
        return score;
    }


    public String getRecord(){
        return String.format(" NumPlayers: %d,\n Score: %d point(s),\n Achievement: %s,\n Difficulty: %s,\n Theme: %s,\n\n For reaching next level ' %s ',\n another %d point(s) needed.",
                numPlayers,
                score,
                achievementList.findLevel(score),
                difficulty,
                theme,
                achievementList.findLevel( achievementList.findNextLevel(score) ),
                achievementList.findNextLevel(score)  - score
                );
    }

    public String getLevel(){
        return achievementList.findLevel(score);
    }

    public AchievementList getAchievementList() {
        return achievementList;
    }

    public String getTheme() {
        return theme;
    }

    public byte[] getPhoto(){
        return photo;
    }

    public ArrayList<Integer> getScoresList() {
        return scoresList;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
