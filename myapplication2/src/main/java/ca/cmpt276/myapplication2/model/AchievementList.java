package ca.cmpt276.myapplication2.model;

import java.util.ArrayList;

/**
 * This class is for store all the Achievements
 */

public class AchievementList {

    public ArrayList<Achievement> achievementsList = new ArrayList<>();// Store all the achievements


    //We can create a new achievement list by poor score, great score and number of players.
    public AchievementList(int poor_score, int great_score, int numPlayers, String difficulty, String theme) {

        if(difficulty == "easy"){
            poor_score *= 0.75;
            great_score *= 0.75;
        }

        if(difficulty == "hard"){
            poor_score *= 1.25;
            great_score *= 1.25;
        }

        int interval = (great_score - poor_score) / 6;

        if(theme == "Leagues") {
            //Iron (For each player: negative infinity ~ poor_score)
            Achievement Iron = new Achievement("Iron", Integer.MIN_VALUE, poor_score * numPlayers, numPlayers);
            achievementsList.add(Iron);

            //Bronze (For each player: poor_score ~ poor_score + 1*interval)
            Achievement Bronze = new Achievement("Bronze", poor_score * numPlayers, (poor_score + 1 * interval) * numPlayers, numPlayers);
            achievementsList.add(Bronze);

            //Silver (For each player: poor_score + 1*interval ~ poor_score + 2*interval)
            Achievement Silver = new Achievement("Silver", (poor_score + 1 * interval) * numPlayers, (poor_score + 2 * interval) * numPlayers, numPlayers);
            achievementsList.add(Silver);

            //Gold (For each player: poor_score + 2*interval ~ poor_score + 3*interval)
            Achievement Gold = new Achievement("Gold", (poor_score + 2 * interval) * numPlayers, (poor_score + 3 * interval) * numPlayers, numPlayers);
            achievementsList.add(Gold);

            //Platinum (For each player: poor_score + 3*interval ~ poor_score + 4*interval)
            Achievement Platinum = new Achievement("Platinum", (poor_score + 3 * interval) * numPlayers, (poor_score + 4 * interval) * numPlayers, numPlayers);
            achievementsList.add(Platinum);

            //Diamond (For each player: poor_score + 4*interval ~ poor_score + 5*interval)
            Achievement Diamond = new Achievement("Diamond", (poor_score + 4 * interval) * numPlayers, (poor_score + 5 * interval) * numPlayers, numPlayers);
            achievementsList.add(Diamond);

            //Master (For each player: poor_score + 5*interval ~ great_score)
            Achievement Master = new Achievement("Master", (poor_score + 5 * interval) * numPlayers, great_score * numPlayers, numPlayers);
            achievementsList.add(Master);

            //Grandmaster (For each player: great_score ~ 100)
            Achievement Grandmaster = new Achievement("Grandmaster", great_score * numPlayers, Integer.MAX_VALUE, numPlayers);
            achievementsList.add(Grandmaster);
        }


        if(theme == "Vehicles") {
            //Iron (For each player: negative infinity ~ poor_score)
            Achievement Bike = new Achievement("Bike", Integer.MIN_VALUE, poor_score * numPlayers, numPlayers);
            achievementsList.add(Bike);

            //Bronze (For each player: poor_score ~ poor_score + 1*interval)
            Achievement Motorbike = new Achievement("Motorbike", poor_score * numPlayers, (poor_score + 1 * interval) * numPlayers, numPlayers);
            achievementsList.add(Motorbike);

            //Silver (For each player: poor_score + 1*interval ~ poor_score + 2*interval)
            Achievement Car = new Achievement("Car", (poor_score + 1 * interval) * numPlayers, (poor_score + 2 * interval) * numPlayers, numPlayers);
            achievementsList.add(Car);

            //Gold (For each player: poor_score + 2*interval ~ poor_score + 3*interval)
            Achievement Bus = new Achievement("Bus", (poor_score + 2 * interval) * numPlayers, (poor_score + 3 * interval) * numPlayers, numPlayers);
            achievementsList.add(Bus);

            //Platinum (For each player: poor_score + 3*interval ~ poor_score + 4*interval)
            Achievement Train = new Achievement("Train", (poor_score + 3 * interval) * numPlayers, (poor_score + 4 * interval) * numPlayers, numPlayers);
            achievementsList.add(Train);

            //Diamond (For each player: poor_score + 4*interval ~ poor_score + 5*interval)
            Achievement Yacht = new Achievement("Yacht", (poor_score + 4 * interval) * numPlayers, (poor_score + 5 * interval) * numPlayers, numPlayers);
            achievementsList.add(Yacht);

            //Master (For each player: poor_score + 5*interval ~ great_score)
            Achievement Helicopter = new Achievement("Helicopter", (poor_score + 5 * interval) * numPlayers, great_score * numPlayers, numPlayers);
            achievementsList.add(Helicopter);

            //Grandmaster (For each player: great_score ~ 100)
            Achievement Plane = new Achievement("Plane", great_score * numPlayers, Integer.MAX_VALUE, numPlayers);
            achievementsList.add(Plane);
        }

        if(theme == "Animals") {
            //Iron (For each player: negative infinity ~ poor_score)
            Achievement Bird = new Achievement("Bird", Integer.MIN_VALUE, poor_score * numPlayers, numPlayers);
            achievementsList.add(Bird);

            //Bronze (For each player: poor_score ~ poor_score + 1*interval)
            Achievement Dog = new Achievement("Dog", poor_score * numPlayers, (poor_score + 1 * interval) * numPlayers, numPlayers);
            achievementsList.add(Dog);

            //Silver (For each player: poor_score + 1*interval ~ poor_score + 2*interval)
            Achievement Sheep = new Achievement("Sheep", (poor_score + 1 * interval) * numPlayers, (poor_score + 2 * interval) * numPlayers, numPlayers);
            achievementsList.add(Sheep);

            //Gold (For each player: poor_score + 2*interval ~ poor_score + 3*interval)
            Achievement Zebra = new Achievement("Zebra", (poor_score + 2 * interval) * numPlayers, (poor_score + 3 * interval) * numPlayers, numPlayers);
            achievementsList.add(Zebra);

            //Platinum (For each player: poor_score + 3*interval ~ poor_score + 4*interval)
            Achievement Lion = new Achievement("Lion", (poor_score + 3 * interval) * numPlayers, (poor_score + 4 * interval) * numPlayers, numPlayers);
            achievementsList.add(Lion);

            //Diamond (For each player: poor_score + 4*interval ~ poor_score + 5*interval)
            Achievement Tiger = new Achievement("Tiger", (poor_score + 4 * interval) * numPlayers, (poor_score + 5 * interval) * numPlayers, numPlayers);
            achievementsList.add(Tiger);

            //Master (For each player: poor_score + 5*interval ~ great_score)
            Achievement Bear = new Achievement("Bear", (poor_score + 5 * interval) * numPlayers, great_score * numPlayers, numPlayers);
            achievementsList.add(Bear);

            //Grandmaster (For each player: great_score ~ 100)
            Achievement Elephant = new Achievement("Elephant", great_score * numPlayers, Integer.MAX_VALUE, numPlayers);
            achievementsList.add(Elephant);
        }
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
