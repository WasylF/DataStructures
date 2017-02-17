package com.github.wslf.datastructures.set;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wsl_F
 */
public class TreeSetWslFTest {

    public TreeSetWslFTest() {
    }

    /**
     * Test of getFirstN method, of class TreeSetWslF.
     */
    @Test
    public void testGetFirstN() {
        TreeSetWslF<Integer> set = new TreeSetWslF<>(Arrays.asList(1, 2, 3, 4, 5));

        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(1, 2));
        ArrayList<Integer> result = set.getFirstN(2);

        assertEquals(expectedResult, result);
    }

    /**
     * Test of getFirstN method, of class TreeSetWslF.
     */
    @Test
    public void testGetFirstN2() {
        TreeSetWslF<Integer> set = new TreeSetWslF<>(Arrays.asList(1, 4, 5));

        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(1, 4));
        ArrayList<Integer> result = set.getFirstN(2);

        assertEquals(expectedResult, result);

        set.add(2);
        set.add(10);
        expectedResult = new ArrayList<>(Arrays.asList(1, 2, 4));
        result = set.getFirstN(3);

        assertEquals(expectedResult, result);
    }

    /**
     * Test of getFirstN method, of class TreeSetWslF.
     */
    @Test
    public void testGetFirstN3() {
        TreeSetWslF<Integer> set = new TreeSetWslF<>(Arrays.asList(1, 2, 3, 4, 5));

        ArrayList<Integer> expectedResult = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> result = set.getFirstN(5);
        assertEquals(expectedResult, result);

        result = set.getFirstN(10);
        assertEquals(expectedResult, result);
    }

}
