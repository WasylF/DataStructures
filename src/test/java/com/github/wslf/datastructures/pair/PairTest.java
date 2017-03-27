package com.github.wslf.datastructures.pair;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wslf
 */
public class PairTest {

    public PairTest() {
    }

    /**
     * Test of getFirst method, of class Pair.
     */
    @Test
    public void testGetFirst() {
        System.out.println("getFirst");
        Pair instance = new Pair();
        Object result = instance.getFirst();
        assertNull(result);
    }

    /**
     * Test of getSecond method, of class Pair.
     */
    @Test
    public void testGetSecond() {
        System.out.println("getSecond");
        Pair instance = new Pair();
        Object result = instance.getSecond();
        assertNull(result);
    }

    /**
     * Test of equals method, of class Pair.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Pair<Integer, Integer>(1, 2);
        Pair<Integer, Integer> instance = new Pair<Integer, Integer>(2, 1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Pair.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals");
        Object obj = new Pair<String, Integer>("four", 4);
        Pair<String, Integer> instance = new Pair<>("four", 4);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Pair.
     */
    @Test
    public void testEquals3() {
        System.out.println("equals");
        Object obj = "2, 1";
        Pair<Integer, Integer> instance = new Pair<Integer, Integer>(2, 1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

}
