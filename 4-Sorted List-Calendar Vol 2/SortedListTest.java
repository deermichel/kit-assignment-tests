package edu.kit.calendar.tests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import edu.kit.informatik.LinkedSortedAppendList;
import edu.kit.informatik.SortedIterator;


/**
 * Test for Assignment 4 - Sorted List
 * (KIT Programming WS16/17)
 * 
 * @author Micha Hanselmann & Julien Midedji
 * @version 1.0.1
 */
public class SortedListTest {

    @Test
    public void testExample1() {
        
        // create list shown in example 1
        LinkedSortedAppendList<Integer> list = new LinkedSortedAppendList<Integer>();
        list.addSorted(2);
        list.addSorted(5);
        list.addSorted(3);
        list.addSorted(1);
        
        // check iterator
        SortedIterator<Integer> iterator = list.iterator();
        assertTrue("Iterator hasNext() returned false but should be true", iterator.hasNext());
        
        // get items from list
        ArrayList<Integer> results = new ArrayList<>();
        while (iterator.hasNext())
            results.add(iterator.next());
        
        // check sorting of list
        assertArrayEquals(new Integer[] {1, 2, 3, 5 }, results.toArray(new Integer[results.size()]));
        
    }
    
    @Test
    public void testStrings() {
        
        // create a list with different strings
        LinkedSortedAppendList<String> list = new LinkedSortedAppendList<>();
        list.addSorted("cc");
        list.addSorted("da");
        list.addSorted("d");
        list.addSorted("aaa");
        list.addSorted("fa");
        list.addSorted("d");
        
        // check iterator
        SortedIterator<String> iterator = list.iterator();
        assertTrue("Iterator hasNext() returned false but should be true", iterator.hasNext());
        
        // get items from list
        ArrayList<String> results = new ArrayList<>();
        while (iterator.hasNext())
            results.add(iterator.next());
        
        // check sorting of list
        assertArrayEquals(new String[] { "aaa", "cc", "d", "d", "da", "fa" }, 
                results.toArray(new String[results.size()]));
        
    }
    
    @SuppressWarnings("deprecation")
    @Test
    public void testTime() {
        
        // create a list with different strings
        LinkedSortedAppendList<Date> list = new LinkedSortedAppendList<>();
        list.addSorted(new Date(2017,1,8));
        list.addSorted(new Date(2011,11,9));
        list.addSorted(new Date(1989,10,3));
        list.addSorted(new Date(1997,5,8));
        list.addSorted(new Date(1990,1,1));
        list.addSorted(new Date(2050,3,3));
        
        // check iterator
        SortedIterator<Date> iterator = list.iterator();
        assertTrue("Iterator hasNext() returned false but should be true", iterator.hasNext());
        
        // get items from list
        ArrayList<Date> results = new ArrayList<>();
        while (iterator.hasNext())
            results.add(iterator.next());
        
        // check sorting of list
        assertArrayEquals(new Date[] { new Date(1989,10,3),
                                       new Date(1990,1,1),
                                       new Date(1997,5,8),
                                       new Date(2011,11,9),
                                       new Date(2017,1,8),
                                       new Date(2050,3,3)}, 
                results.toArray(new Date[results.size()]));
    }
    
    @Test
    public void testEmpty() {
        LinkedSortedAppendList<Integer> list = new LinkedSortedAppendList<>();
        
        // check iterator
        SortedIterator<Integer> iterator = list.iterator();
        assertFalse("Iterator hasNext() returned true but should be false", iterator.hasNext());
        
        // get items from list
        ArrayList<Integer> results = new ArrayList<>();
        while (iterator.hasNext())
            results.add(iterator.next());
        
        // check sorting of list
        assertArrayEquals(new Integer[] {}, results.toArray(new Integer[results.size()]));
    }
    
    @Test
    public void testIteration() {
        
        // create list shown in example 1
        LinkedSortedAppendList<Integer> list = new LinkedSortedAppendList<Integer>();
        list.addSorted(2);
        list.addSorted(5);
        list.addSorted(3);
        list.addSorted(1);
        
        // check iterator
        SortedIterator<Integer> iterator = list.iterator();
        
        // correct order
        Integer[] results = new Integer[] {1, 2, 3, 5};
        
        // check each element iterator returns
        for(int i = 0; i < results.length; i++) {
            assertEquals(results[i], iterator.next());
        }
        
    }

}
