import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

// modify as needed
import edu.kit.informatik.sortedlist.*;


/**
 * Test for Assignment 4 - Sorted List
 * (KIT Programming WS16/17)
 * 
 * @author Micha Hanselmann
 * @version 1.0.0
 */
public class SortedListTest {

    @Test
    public void testExample1() {
        
        // create list shown in example 1
        LinkedSortedAppendList<Integer> list = new LinkedSortedAppendList<>();
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
        assertArrayEquals(new Integer[] { 1, 2, 3, 5 }, results.toArray(new Integer[results.size()]));
        
    }

}
