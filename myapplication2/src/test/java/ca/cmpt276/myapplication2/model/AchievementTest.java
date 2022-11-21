package ca.cmpt276.myapplication2.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AchievementTest {

    @Test
    void getName() {
        Achievement testAchieve = new Achievement("name", -10, 10, 3);

        assertEquals("name", testAchieve.getName());
        assertEquals(-10, testAchieve.getLowerBound());
        assertEquals(10, testAchieve.getUpperBound());
        assertEquals(3, testAchieve.getNumPlayers());
    }

    @Test
    void getLowerBound() {

        //When the lower bound is less than 0
        Achievement testAchieve = new Achievement("name", -10, 10, 3);
        assertEquals(-10, testAchieve.getLowerBound());

        //When the lower bound is 0
        testAchieve = new Achievement("name", 0, 10, 3);
        assertEquals(0, testAchieve.getLowerBound());

        //When the lower bound is bigger than 0
        testAchieve = new Achievement("name", 5, 10, 3);
        assertEquals(5, testAchieve.getLowerBound());
    }

    @Test
    void getUpperBound() {

        //When the upper bound is less than 0
        Achievement testAchieve = new Achievement("name", -10, -5, 3);
        assertEquals(-5, testAchieve.getUpperBound());

        //When the upper bound is 0
        testAchieve = new Achievement("name", -10, 0, 3);
        assertEquals(0, testAchieve.getUpperBound());

        //When the upper bound is bigger than 0
        testAchieve = new Achievement("name", -10, 10, 3);
        assertEquals(10, testAchieve.getUpperBound());
    }

    @Test
    void getNumPlayers() {

        //When the number of players is 1
        Achievement testAchieve = new Achievement("name", -10, 10, 1);
        assertEquals(1, testAchieve.getNumPlayers());

        //When the number of players is bigger than 1
        testAchieve = new Achievement("name", -10, 10, 10);
        assertEquals(10, testAchieve.getNumPlayers());
    }
}