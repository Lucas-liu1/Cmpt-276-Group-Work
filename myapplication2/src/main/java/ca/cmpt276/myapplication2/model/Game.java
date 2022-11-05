package ca.cmpt276.myapplication2.model;

public class Game {
    private int numPlayers;
    private int score;
    private AchievementList achievementList; //Single Game's Achievement List

    public Game(int numPlayers, int score) {
        this.numPlayers = numPlayers;
        this.score = score;
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

    // All Getters
    public int getNumPlayers() {
        return numPlayers;
    }

    public int getScore() {
        return score;
    }

    public String getRecord(){
        return String.format("NumPlayers: %d,  Score: %d, Achievement:%s",
                numPlayers,
                score,
                achievementList.findLevel(score));
    }

    public AchievementList getAchievementList() {
        return achievementList;
    }
}
