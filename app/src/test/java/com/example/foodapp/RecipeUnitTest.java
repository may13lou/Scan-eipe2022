package com.example.foodapp;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RecipeUnitTest
{
    @Test
    public void recipeSumTest()
    {
        ArrayList<Integer> testNums = new ArrayList<Integer>(Arrays. asList(1, 2, 3, 4, 5, 6));
        int output = 0;
        int expectedOutput = 21;

        FavoriteFragment sumTest = new FavoriteFragment();
        output = sumTest.sum(testNums);

        assertEquals(expectedOutput,output);
    }

    @Test
    public void recipeParsingTest()
    {
        String input2 = "beef, rice, salt, tomato";
        String[] output2;
        String[] expectedOutput2 = {"beef", "rice", "salt", "tomato"};

        FavoriteFragment parsingTest = new FavoriteFragment();
        output2 = parsingTest.parse_ingredient_list(input2);

        assertArrayEquals(expectedOutput2,output2);
    }
}