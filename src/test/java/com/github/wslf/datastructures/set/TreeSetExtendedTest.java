package com.github.wslf.datastructures.set;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 *
 * @author Wsl_F
 */
public class TreeSetExtendedTest {

    public TreeSetExtendedTest() {
    }

    /**
     * Test of getFirstK method, of class TreeSetExtended.
     */
    @Test
    public void testGetFirstK() {
        TreeSetExtended<Integer> set = new TreeSetExtended<>(Arrays.asList(1, 2, 3, 4, 5));

        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(1, 2));
        ArrayList<Integer> result = set.getFirstK(2);

        assertEquals(expectedResult, result);
    }

    /**
     * Test of getFirstK method, of class TreeSetExtended.
     */
    @Test
    public void testGetFirstK2() {
        TreeSetExtended<Integer> set = new TreeSetExtended<>(Arrays.asList(1, 4, 5));

        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(1, 4));
        ArrayList<Integer> result = set.getFirstK(2);

        assertEquals(expectedResult, result);

        set.add(2);
        set.add(10);
        expectedResult = new ArrayList<>(Arrays.asList(1, 2, 4));
        result = set.getFirstK(3);

        assertEquals(expectedResult, result);
    }

    /**
     * Test of getFirstK method, of class TreeSetExtended.
     */
    @Test
    public void testGetFirstK3() {
        TreeSetExtended<Integer> set = new TreeSetExtended<>(Arrays.asList(1, 2, 3, 4, 5));

        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> result = set.getFirstK(5);
        assertEquals(expectedResult, result);

        result = set.getFirstK(10);
        assertEquals(expectedResult, result);
    }

    /**
     * Test of getLastK method, of class TreeSetExtended.
     */
    @Test
    public void testGetLastK() {
        TreeSetExtended<Integer> set = new TreeSetExtended<>(Arrays.asList(1, 2, 3, 4, 5));

        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(5, 4));
        ArrayList<Integer> result = set.getLastK(2);

        assertEquals(expectedResult, result);
    }

    /**
     * Test of getLastK method, of class TreeSetExtended.
     */
    @Test
    public void testGetLastK2() {
        TreeSetExtended<Integer> set = new TreeSetExtended<>(Arrays.asList(1, 4, 5));

        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(5, 4));
        ArrayList<Integer> result = set.getLastK(2);

        assertEquals(expectedResult, result);

        set.add(2);
        set.add(10);
        expectedResult = new ArrayList<>(Arrays.asList(10, 5, 4));
        result = set.getLastK(3);

        assertEquals(expectedResult, result);
    }

    /**
     * Test of getLastK method, of class TreeSetExtended.
     */
    @Test
    public void testGetLastK3() {
        TreeSetExtended<Integer> set = new TreeSetExtended<>(Arrays.asList(1, 2, 3, 4, 5));

        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
        ArrayList<Integer> result = set.getLastK(5);
        assertEquals(expectedResult, result);

        result = set.getLastK(10);
        assertEquals(expectedResult, result);
    }

}
