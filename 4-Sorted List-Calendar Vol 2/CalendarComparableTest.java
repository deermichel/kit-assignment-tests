import static org.junit.Assert.*;
import org.junit.Test;
import edu.kit.informatik.calendar.*;


/**
 * Test for Assignment 4 - Calendar Comparable
 * (KIT Programming WS16/17)
 * 
 * @author Micha Hanselmann
 * @version 1.0.0
 */
public class CalendarComparableTest {

    @Test
    public void testReflexive() {
        
        DateTime dt = new DateTime(new Date(2016, 12, 15), new Time(12, 23, 1));
        Appointment a = new Appointment("Demo", dt, dt.plusDays(20));
        
        assertTrue("appointment compared to itself should be 0", a.compareTo(a) == 0);
    
    }

    @Test
    public void testStartBefore() {
        
        DateTime dt = new DateTime(new Date(2016, 12, 15), new Time(12, 23, 1));
        Appointment a1 = new Appointment("Demo", dt, dt.plusDays(20));
        Appointment a2 = new Appointment("WWDC", dt.plusDays(2), dt.plusDays(22));
        
        assertTrue(a1.toString() + " compared to " + a2.toString() + " should be negative", a1.compareTo(a2) < 0);
        
    }

    @Test
    public void testStartAfter() {
        
        DateTime dt = new DateTime(new Date(2016, 12, 15), new Time(12, 23, 1));
        Appointment a1 = new Appointment("Demo", dt, dt.plusDays(20));
        Appointment a2 = new Appointment("Hello World", dt.plusDays(2), dt.plusDays(22));
        
        assertTrue(a2.toString() + " compared to " + a1.toString() + " should be positive", a2.compareTo(a1) > 0);
        
    }

    @Test
    public void testStartEqualEndBefore() {
        
        DateTime dt = new DateTime(new Date(2016, 12, 15), new Time(12, 23, 1));
        Appointment a1 = new Appointment("Demo", dt, dt.plusDays(20));
        Appointment a2 = new Appointment("Hello World", dt, dt.plusDays(22));
        
        assertTrue(a1.toString() + " compared to " + a2.toString() + " should be negative", a1.compareTo(a2) < 0);
        
    }

    @Test
    public void testStartEqualEndAfter() {
        
        DateTime dt = new DateTime(new Date(2016, 12, 15), new Time(12, 23, 1));
        Appointment a1 = new Appointment("Sleep", dt, dt.plusDays(20));
        Appointment a2 = new Appointment("Hello World", dt, dt.plusDays(22));
        
        assertTrue(a2.toString() + " compared to " + a1.toString() + " should be positive", a2.compareTo(a1) > 0);
        
    }

    @Test
    public void testStartEqualEndEqualNameBefore() {
        
        DateTime dt = new DateTime(new Date(2016, 12, 15), new Time(12, 23, 1));
        Appointment a1 = new Appointment("My Appointment 1", dt, dt.plusDays(20));
        Appointment a2 = new Appointment("My Appointment 2", dt, dt.plusDays(20));
        
        assertTrue(a1.toString() + " compared to " + a2.toString() + " should be negative", a1.compareTo(a2) < 0);
        
    }

    @Test
    public void testStartEqualEndEqualNameAfter() {
        
        DateTime dt = new DateTime(new Date(2016, 12, 15), new Time(12, 23, 1));
        Appointment a1 = new Appointment("My Appointment 1", dt, dt.plusDays(20));
        Appointment a2 = new Appointment("My Appointment 2", dt, dt.plusDays(20));
        
        assertTrue(a2.toString() + " compared to " + a1.toString() + " should be positive", a2.compareTo(a1) > 0);
        
    }

}
