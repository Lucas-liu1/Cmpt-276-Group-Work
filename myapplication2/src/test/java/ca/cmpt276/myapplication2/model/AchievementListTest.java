package ca.cmpt276.myapplication2.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AchievementListTest {

    @Test
    void AchievementList(){

        // For poor_score and greate_score

        // poor_score -> negative, great_score -> negative
        AchievementList test_scores = new AchievementList(-40, -10, 1, "normal", "theme 1");

        assertEquals("Iron", test_scores.findLevel(-41));
        assertEquals("Bronze", test_scores.findLevel(-39));
        assertEquals("Silver", test_scores.findLevel(-34));
        assertEquals("Gold", test_scores.findLevel(-29));
        assertEquals("Platinum", test_scores.findLevel(-24));
        assertEquals("Diamond", test_scores.findLevel(-19));
        assertEquals("Master", test_scores.findLevel(-14));
        assertEquals("Grandmaster", test_scores.findLevel(-9));

        // poor_score -> negative, great_score -> 0
        test_scores = new AchievementList(-30, 0, 1, "normal", "theme 1");

        assertEquals("Iron", test_scores.findLevel(-31));
        assertEquals("Bronze", test_scores.findLevel(-29));
        assertEquals("Silver", test_scores.findLevel(-24));
        assertEquals("Gold", test_scores.findLevel(-19));
        assertEquals("Platinum", test_scores.findLevel(-14));
        assertEquals("Diamond", test_scores.findLevel(-9));
        assertEquals("Master", test_scores.findLevel(-4));
        assertEquals("Grandmaster", test_scores.findLevel(1));

        // poor_score -> 0, great_score -> positive
        test_scores = new AchievementList(0, 30, 1, "normal", "theme 1");

        assertEquals("Iron", test_scores.findLevel(-1));
        assertEquals("Bronze", test_scores.findLevel(1));
        assertEquals("Silver", test_scores.findLevel(6));
        assertEquals("Gold", test_scores.findLevel(11));
        assertEquals("Platinum", test_scores.findLevel(16));
        assertEquals("Diamond", test_scores.findLevel(21));
        assertEquals("Master", test_scores.findLevel(26));
        assertEquals("Grandmaster", test_scores.findLevel(31));

        // poor_score -> positive, great_score -> positive
        test_scores = new AchievementList(10, 40, 1, "normal", "theme 1");

        assertEquals("Iron", test_scores.findLevel(9));
        assertEquals("Bronze", test_scores.findLevel(11));
        assertEquals("Silver", test_scores.findLevel(16));
        assertEquals("Gold", test_scores.findLevel(21));
        assertEquals("Platinum", test_scores.findLevel(26));
        assertEquals("Diamond", test_scores.findLevel(31));
        assertEquals("Master", test_scores.findLevel(36));
        assertEquals("Grandmaster", test_scores.findLevel(41));



        // For difficulty

        // difficulty = "normal"
        AchievementList test_difficulty = new AchievementList(10, 40, 1, "normal", "theme 1");

        assertEquals("Iron", test_difficulty.findLevel(9));
        assertEquals("Bronze", test_difficulty.findLevel(11));
        assertEquals("Silver", test_difficulty.findLevel(16));
        assertEquals("Gold", test_difficulty.findLevel(21));
        assertEquals("Platinum", test_difficulty.findLevel(26));
        assertEquals("Diamond", test_difficulty.findLevel(31));
        assertEquals("Master", test_difficulty.findLevel(36));
        assertEquals("Grandmaster", test_difficulty.findLevel(41));

        // difficulty = "easy"
        test_difficulty = new AchievementList(10, 40, 1, "easy", "theme 1");

        assertEquals("Iron", test_difficulty.findLevel(7));
        assertEquals("Bronze", test_difficulty.findLevel(8));
        assertEquals("Silver", test_difficulty.findLevel(12));
        assertEquals("Gold", test_difficulty.findLevel(16));
        assertEquals("Platinum", test_difficulty.findLevel(19));
        assertEquals("Diamond", test_difficulty.findLevel(22));
        assertEquals("Master", test_difficulty.findLevel(27));
        assertEquals("Grandmaster", test_difficulty.findLevel(31));

        // difficulty = "hard"
        test_difficulty = new AchievementList(10, 40, 1, "hard", "theme 1");

        assertEquals("Iron", test_difficulty.findLevel(11));
        assertEquals("Bronze", test_difficulty.findLevel(14));
        assertEquals("Silver", test_difficulty.findLevel(20));
        assertEquals("Gold", test_difficulty.findLevel(26));
        assertEquals("Platinum", test_difficulty.findLevel(33));
        assertEquals("Diamond", test_difficulty.findLevel(39));
        assertEquals("Master", test_difficulty.findLevel(45));
        assertEquals("Grandmaster", test_difficulty.findLevel(51));


        // For theme

        // theme -> theme 1
        AchievementList test_theme = new AchievementList(10, 40, 1, "normal", "theme 1");

        assertEquals("Iron", test_theme.findLevel(9));
        assertEquals("Bronze", test_theme.findLevel(11));
        assertEquals("Silver", test_theme.findLevel(16));
        assertEquals("Gold", test_theme.findLevel(21));
        assertEquals("Platinum", test_theme.findLevel(26));
        assertEquals("Diamond", test_theme.findLevel(31));
        assertEquals("Master", test_theme.findLevel(36));
        assertEquals("Grandmaster", test_theme.findLevel(41));

        // theme -> theme 2
        test_theme = new AchievementList(10, 40, 1, "normal", "theme 2");

        assertEquals("Bike", test_theme.findLevel(9));
        assertEquals("Motorbike", test_theme.findLevel(11));
        assertEquals("Car", test_theme.findLevel(16));
        assertEquals("Bus", test_theme.findLevel(21));
        assertEquals("Train", test_theme.findLevel(26));
        assertEquals("Yacht", test_theme.findLevel(31));
        assertEquals("Helicopter", test_theme.findLevel(36));
        assertEquals("Plane", test_theme.findLevel(41));

        // theme -> theme 3
        test_theme = new AchievementList(10, 40, 1, "normal", "theme 3");

        assertEquals("Bird", test_theme.findLevel(9));
        assertEquals("Dog", test_theme.findLevel(11));
        assertEquals("Sheep", test_theme.findLevel(16));
        assertEquals("Zebra", test_theme.findLevel(21));
        assertEquals("Lion", test_theme.findLevel(26));
        assertEquals("Tiger", test_theme.findLevel(31));
        assertEquals("Bear", test_theme.findLevel(36));
        assertEquals("Elephant", test_theme.findLevel(41));
    }

    @Test
    void findLevel() {
        AchievementList test_level = new AchievementList(10, 40, 1, "normal", "theme 1");

        assertEquals("Iron", test_level.findLevel(9));
        assertEquals("Bronze", test_level.findLevel(11));
        assertEquals("Silver", test_level.findLevel(16));
        assertEquals("Gold", test_level.findLevel(21));
        assertEquals("Platinum", test_level.findLevel(26));
        assertEquals("Diamond", test_level.findLevel(31));
        assertEquals("Master", test_level.findLevel(36));
        assertEquals("Grandmaster", test_level.findLevel(41));
    }
}