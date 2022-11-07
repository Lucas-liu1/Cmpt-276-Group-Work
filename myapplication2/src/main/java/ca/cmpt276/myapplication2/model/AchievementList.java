package ca.cmpt276.myapplication2.model;

import java.util.ArrayList;

/**
 * This class is for store all the Achievements
 */

public class AchievementList {

    public ArrayList<Achievement> achievementsList = new ArrayList<>();// Store all the achievements


    //We can create a new achievement list by poor score, great score and number of players.
    public AchievementList(int poor_score, int great_score, int numPlayers) {

        int interval = (great_score - poor_score) / 6;

        //Iron (For each player: negative infinity ~ poor_score)
        Achievement Iron = new Achievement("Iron", Integer.MIN_VALUE, poor_score*numPlayers, numPlayers);
        achievementsList.add(Iron);

        //Bronze (For each player: poor_score ~ poor_score + 1*interval)
        Achievement Bronze = new Achievement("Bronze", poor_score*numPlayers, (poor_score+1*interval)*numPlayers, numPlayers);
        achievementsList.add(Bronze);

        //Silver (For each player: poor_score + 1*interval ~ poor_score + 2*interval)
        Achievement Silver = new Achievement("Silver", (poor_score+1*interval)*numPlayers, (poor_score+2*interval)*numPlayers, numPlayers);
        achievementsList.add(Silver);

        //Gold (For each player: poor_score + 2*interval ~ poor_score + 3*interval)
        Achievement Gold = new Achievement("Gold", (poor_score+2*interval)*numPlayers, (poor_score+3*interval)*numPlayers, numPlayers);
        achievementsList.add(Gold);

        //Platinum (For each player: poor_score + 3*interval ~ poor_score + 4*interval)
        Achievement Platinum = new Achievement("Platinum", (poor_score+3*interval)*numPlayers, (poor_score+4*interval)*numPlayers, numPlayers);
        achievementsList.add(Platinum);

        //Diamond (For each player: poor_score + 4*interval ~ poor_score + 5*interval)
        Achievement Diamond = new Achievement("Diamond", (poor_score+4*interval)*numPlayers, (poor_score+5*interval)*numPlayers, numPlayers);
        achievementsList.add(Diamond);

        //Master (For each player: poor_score + 5*interval ~ great_score)
        Achievement Master = new Achievement("Master", (poor_score+5*interval)*numPlayers, great_score*numPlayers, numPlayers);
        achievementsList.add(Master);

        //Grandmaster (For each player: great_score ~ 100)
        Achievement Grandmaster = new Achievement("Grandmaster", great_score*numPlayers, Integer.MAX_VALUE, numPlayers);
        achievementsList.add(Grandmaster);


    }


    public String findLevel(int score) {

            for (int i = 0; i < achievementsList.size(); i++) {
                Achievement tempAchievement = achievementsList.get(i);
                if ((score >= tempAchievement.getLowerBound()) && (score <= tempAchievement.getUpperBound())) {
                    return tempAchievement.getName();
                }
            }
            return null;
    }






}
