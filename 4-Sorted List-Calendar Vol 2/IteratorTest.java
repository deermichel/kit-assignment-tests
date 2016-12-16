import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

// modify as needed
import edu.kit.informatik.calendar.*;


/**
 * Test for Assignment 4 - Iterator
 * (KIT Programming WS16/17)
 * 
 * @author Micha Hanselmann
 * @version 1.0.0
 */
public class IteratorTest {

    @Test
    public void testExample3Top() {
        
        // create iterator shown in example 3 (top)
        FixedDeltaDateIterator iterator = new FixedDeltaDateIterator(new Date(2017, 1, 1), null, 3, 4, 10);
        
        // check iterator
        assertTrue("Iterator hasNext() returned false but should be true", iterator.hasNext());
        
        // get elements from iterator
        ArrayList<String> results = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            results.add(iterator.next().toString());
        
        // check results
        assertArrayEquals(new String[] {
                "01-01-2017",
                "11-05-2020",
                "21-09-2023",
                "01-02-2027",
                "11-06-2030"
        }, results.toArray(new String[results.size()]));
        
    }

    @Test
    public void testExample3Bottom() {
        
        // create iterators shown in example 3 (bottom)
        FixedDeltaDateIterator it1 = new FixedDeltaDateIterator(new Date(2016, 11, 15), new Date(2017, 1, 25), 0, 0, 14);
        FixedDeltaDateIterator it2 = new FixedDeltaDateIterator(new Date(2016, 10, 26), new Date(2016, 12, 28), 0, 0, 7);
        UnionSortedIterator<Date> unionIterator = new UnionSortedIterator<>(it1, it2);
        
        // check iterator
        assertTrue("Iterator hasNext() returned false but should be true", unionIterator.hasNext());
        
        // get elements from iterator
        ArrayList<String> results = new ArrayList<>();
        while (unionIterator.hasNext())
            results.add(unionIterator.next().toString());
        
        // check results
        assertArrayEquals(new String[] {
                "26-10-2016",
                "02-11-2016",
                "09-11-2016",
                "15-11-2016",
                "16-11-2016",
                "23-11-2016",
                "29-11-2016",
                "30-11-2016",
                "07-12-2016",
                "13-12-2016",
                "14-12-2016",
                "21-12-2016",
                "27-12-2016",
                "28-12-2016",
                "10-01-2017",
                "24-01-2017"
        }, results.toArray(new String[results.size()]));
        
    }

}
