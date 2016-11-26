import edu.kit.informatik.calendar.*;

/**
  Test for Assignment 2 - Calendar
  (KIT Programming WS16/17)

  @author Micha Hanselmann
  @version 1.0.0
*/
public class CalendarTest {

  // counter
  int passed = 0;
  int failed = 0;


  // main method
  public static void main(String[] args) {
    new CalendarTest();
  }

  public CalendarTest() {

    // display header
    log("Test for Assignment 2 - Calendar");
    log("--------------------------------");


    // START


    // test toString
    Time t1 = new Time(1, 2, 4);
    test(t1.toString().equals("01:02:04"), "toString of Time 01:02:04", t1.toString());

    t1 = new Time(11, 59, 1);
    test(t1.toString().equals("11:59:01"), "toString of Time 11:59:01", t1.toString());

    Date d1 = new Date(2016, 5, 2);
    test(d1.toString().equals("02-05-2016"), "toString of Date 02-05-2016", d1.toString());

    d1 = new Date(2007, 12, 8);
    test(d1.toString().equals("08-12-2007"), "toString of Date 08-12-2007", d1.toString());

    DateTime dt1 = new DateTime(d1, t1);
    test(dt1.toString().equals("08-12-2007T11:59:01"), "toString of DateTime 08-12-2007T11:59:01", dt1.toString());

    DateTime dt2 = new DateTime(new Date(2014, 12, 13), new Time(1, 11, 0));
    Appointment a1 = new Appointment("HelloWorld", dt1, dt2);
    test(a1.toString().equals("HelloWorld 08-12-2007T11:59:01 13-12-2014T01:11:00"), "toString of Appointment HelloWorld 08-12-2007T11:59:01 13-12-2014T01:11:00", a1.toString());

    // Date methods
    d1 = new Date(2016, 11, 20);
    test(d1.getMonth() == Month.NOVEMBER, "getMonth of " + d1.toString(), "Returned " + d1.getMonth().toString() + " but should be NOVEMBER");
    test(d1.getDayOfYear() == 325, "getDayOfYear of " + d1.toString(), "Returned " + Integer.toString(d1.getDayOfYear()) + " but should be 325");
    test(d1.getDayOfWeek() == DayOfWeek.SUNDAY, "getDayOfWeek of " + d1.toString(),"Returned " + d1.getDayOfWeek() + " but should be SUNDAY");
    test(d1.isLeapYear(), d1.toString() + " is a leap year", "");

    d1 = new Date(2000, 11, 20);
    test(d1.isLeapYear(), d1.toString() + " is a leap year", "");

    d1 = new Date(1900, 11, 20);
    test(!d1.isLeapYear(), d1.toString() + " is not a leap year", "");

    // calculation
    t1 = new Time(12, 0, 0);
    Time t2 = new Time(12, 0, 0);
    Time t3 = t1.plus(t2);
    test(t3.toString().equals("00:00:00"), t1.toString() + " + " + t2.toString() + " = 00:00:00", t3.toString());

    t1 = new Time(23, 59, 59);
    t2 = new Time(0, 0, 1);
    t3 = t1.plus(t2);
    test(t3.toString().equals("00:00:00"), t1.toString() + " + " + t2.toString() + " = 00:00:00", t3.toString());

    t1 = new Time(11, 11, 11);
    t2 = new Time(11, 11, 11);
    t3 = t1.plus(t2);
    test(t3.toString().equals("22:22:22"), t1.toString() + " + " + t2.toString() + " = 22:22:22", t3.toString());

    t1 = new Time(12, 0, 0);
    t2 = new Time(12, 0, 0);
    t3 = t1.minus(t2);
    test(t3.toString().equals("00:00:00"), t1.toString() + " - " + t2.toString() + " = 00:00:00", t3.toString());

    t1 = new Time(0, 0, 0);
    t2 = new Time(0, 0, 1);
    t3 = t1.minus(t2);
    test(t3.toString().equals("23:59:59"), t1.toString() + " - " + t2.toString() + " = 23:59:59", t3.toString());

    t1 = new Time(11, 11, 11);
    t2 = new Time(1, 1, 1);
    t3 = t1.minus(t2);
    test(t3.toString().equals("10:10:10"), t1.toString() + " - " + t2.toString() + " = 10:10:10", t3.toString());

    d1 = new Date(1000, 1, 1);
    Date d2 = new Date(1000, 1, 1);
    Date d3 = d1.plus(d2);
    test(d3.toString().equals("02-02-2000"), d1.toString() + " + " + d2.toString() + " = 02-02-2000", d3.toString());

    d1 = new Date(1000, 1, 1);
    d2 = new Date(1000, 1, 28);
    d3 = d1.plus(d2);
    test(d3.toString().equals("01-03-2000"), d1.toString() + " + " + d2.toString() + " = 01-03-2000", d3.toString());

    d1 = new Date(1000, 1, 1);
    d2 = new Date(1001, 1, 28);
    d3 = d1.plus(d2);
    test(d3.toString().equals("01-03-2001"), d1.toString() + " + " + d2.toString() + " = 01-03-2001", d3.toString());

    d1 = new Date(2000, 2, 2);
    d2 = new Date(1000, 1, 1);
    d3 = d1.minus(d2);
    test(d3.toString().equals("01-01-1000"), d1.toString() + " - " + d2.toString() + " = 01-01-1000", d3.toString());

    d1 = new Date(2000, 2, 29);
    d2 = new Date(1000, 1, 28);
    d3 = d1.minus(d2);
    test(d3.toString().equals("01-01-1000"), d1.toString() + " - " + d2.toString() + " = 01-01-1000", d3.toString());

    d1 = new Date(2001, 3, 1);
    d2 = new Date(1001, 1, 28);
    d3 = d1.minus(d2);
    test(d3.toString().equals("01-01-1000"), d1.toString() + " - " + d2.toString() + " = 01-01-1000", d3.toString());

    dt1 = new DateTime(new Date(1000, 1, 1), new Time(12, 0, 0));
    dt2 = new DateTime(new Date(1000, 1, 1), new Time(12, 0, 0));
    DateTime dt3 = dt1.plus(dt2);
    test(dt3.toString().equals("03-02-2000T00:00:00"), dt1.toString() + " + " + dt2.toString() + " = 03-02-2000T00:00:00", dt3.toString());

    dt1 = new DateTime(new Date(1000, 1, 1), new Time(23, 59, 59));
    dt2 = new DateTime(new Date(1000, 1, 28), new Time(0, 0, 1));
    dt3 = dt1.plus(dt2);
    test(dt3.toString().equals("02-03-2000T00:00:00"), dt1.toString() + " + " + dt2.toString() + " = 02-03-2000T00:00:00", dt3.toString());

    dt1 = new DateTime(new Date(1000, 1, 1), new Time(11, 11, 11));
    dt2 = new DateTime(new Date(1001, 1, 28), new Time(11, 11, 11));
    dt3 = dt1.plus(dt2);
    test(dt3.toString().equals("01-03-2001T22:22:22"), dt1.toString() + " + " + dt2.toString() + " = 01-03-2001T22:22:22", dt3.toString());

    dt1 = new DateTime(new Date(2000, 1, 27), new Time(0, 0, 0));
    dt2 = new DateTime(new Date(0, 1, 5), new Time(0, 0, 0));
    dt3 = dt1.plus(dt2);
    test(dt3.toString().equals("01-03-2000T00:00:00"), dt1.toString() + " + " + dt2.toString() + " = 01-03-2000T00:00:00", dt3.toString());

    t1 = new Time(11, 11, 11);
    t2 = new Time(11, 11, 11);
    test(t1.isEqual(t2), t1.toString() + " == " + t2.toString(), "Returned false but should be true");

    t1 = new Time(11, 11, 11);
    t2 = new Time(11, 12, 11);
    test(!t1.isEqual(t2), t1.toString() + " != " + t2.toString(), "Returned true but should be false");

    t1 = new Time(11, 11, 11);
    t2 = new Time(12, 11, 11);
    test(t1.isBefore(t2), t1.toString() + " before " + t2.toString(), "Returned false but should be true");

    t1 = new Time(11, 13, 11);
    t2 = new Time(11, 11, 11);
    test(t1.isAfter(t2), t1.toString() + " after " + t2.toString(), "Returned false but should be true");

    d1 = new Date(2016, 9, 5);
    d2 = new Date(2016, 9, 5);
    test(d1.isEqual(d2), d1.toString() + " == " + d2.toString(), "Returned false but should be true");

    d1 = new Date(2016, 9, 5);
    d2 = new Date(2016, 9, 4);
    test(!d1.isEqual(d2), d1.toString() + " != " + d2.toString(), "Returned true but should be false");

    d1 = new Date(2015, 9, 5);
    d2 = new Date(2016, 9, 5);
    test(d1.isBefore(d2), d1.toString() + " before " + d2.toString(), "Returned false but should be true");

    d1 = new Date(2016, 10, 5);
    d2 = new Date(2016, 9, 5);
    test(d1.isAfter(d2), d1.toString() + " after " + d2.toString(), "Returned false but should be true");

    dt1 = new DateTime(new Date(1000, 1, 1), new Time(12, 0, 0));
    dt2 = new DateTime(new Date(1000, 1, 1), new Time(12, 0, 0));
    test(dt1.isEqual(dt2), dt1.toString() + " == " + dt2.toString(), "Returned false but should be true");

    dt1 = new DateTime(new Date(1000, 1, 1), new Time(12, 0, 0));
    dt2 = new DateTime(new Date(1000, 1, 1), new Time(12, 0, 1));
    test(!dt1.isEqual(dt2), dt1.toString() + " != " + dt2.toString(), "Returned true but should be false");

    dt1 = new DateTime(new Date(1000, 1, 1), new Time(12, 0, 0));
    dt2 = new DateTime(new Date(2000, 1, 1), new Time(12, 0, 0));
    test(dt1.isBefore(dt2), dt1.toString() + " before " + dt2.toString(), "Returned false but should be true");

    dt1 = new DateTime(new Date(1000, 1, 10), new Time(12, 0, 0));
    dt2 = new DateTime(new Date(1000, 1, 1), new Time(12, 0, 0));
    test(dt1.isAfter(dt2), dt1.toString() + " after " + dt2.toString(), "Returned false but should be true");

    // additional
    log("--- additional tests (validity not guaranteed!) ---");

    d1 = new Date(2008, 2, 29);
    d2 = d1.plusYears(1);
    test(d2.toString().equals("01-03-2009"), d1.toString() + " + 1 year = 01-03-2009", d2.toString());

    d1 = new Date(2016, 2, 29);
    d2 = d1.minusYears(4);
    test(d2.toString().equals("29-02-2012"), d1.toString() + " - 4 years = 29-02-2012", d2.toString());

    d1 = new Date(2011, 1, 31);
    d2 = d1.plusMonths(37);
    test(d2.toString().equals("03-03-2014"), d1.toString() + " + 37 months = 03-03-2014", d2.toString());

    d1 = new Date(1996, 4, 30);
    d2 = d1.minusMonths(2);
    test(d2.toString().equals("01-03-1996"), d1.toString() + " - 2 months = 01-03-1996", d2.toString());

    d1 = new Date(2008, 2, 28);
    d2 = d1.plusDays(365);
    test(d2.toString().equals("27-02-2009"), d1.toString() + " + 365 days = 27-02-2009", d2.toString());

    d1 = new Date(2017, 1, 3);
    d2 = d1.minusDays(4);
    test(d2.toString().equals("30-12-2016"), d1.toString() + " - 4 days = 30-12-2016", d2.toString());

    dt1 = new DateTime(new Date(2016, 12, 1), new Time(10, 45, 2));
    dt2 = new DateTime(new Date(1000, 3, 5), new Time(12, 2, 3));
    dt3 = dt1.minus(dt2);
    test(dt3.toString().equals("25-08-1016T22:42:59"), dt1.toString() + " - " + dt2.toString() + " = 25-08-1016T22:42:59", dt3.toString());

    d1 = new Date(1568, 3, 5);
    test(d1.getMonth() == Month.MARCH, "getMonth of " + d1.toString(), "Returned " + d1.getMonth().toString() + " but should be MARCH");
    test(d1.getDayOfYear() == 65, "getDayOfYear of " + d1.toString(), "Returned " + Integer.toString(d1.getDayOfYear()) + " but should be 65");
    test(d1.getDayOfWeek() == DayOfWeek.TUESDAY, "getDayOfWeek of " + d1.toString(),"Returned " + d1.getDayOfWeek() + " but should be TUESDAY");
    test(d1.isLeapYear(), d1.toString() + " is a leap year", "");


    // --- END ---


    // display end results
    log("--------------------------------");
    int total = passed + failed;
    log(total + " tests finished. " + passed + " passed - " + failed + " failed (" + (int) ((float) passed / total * 100.0) + "%)");

  }

  // test condition logging description, if fails -> log error
  public void test(boolean condition, String desc, String err) {

    // log
    if (desc != "") desc += " - ";
    log("(" + (passed + failed + 1) + ") " + desc, false);

    // test
    if (condition) {

      log("PASS");
      passed++;

    } else {

      if (err != "")
        err = ": " + err;

      log("FAIL" + err);
      failed++;

    }

  }

  // test condition, if fails -> log error
  public void test(boolean condition, String err) {
    test(condition, "", err);
  }

  // test condition
  public void test(boolean condition) {
    test(condition, "", "");
  }

  // log sth with optional new line (println shortcut)
  public void log(String test, boolean newLine) {
    if (newLine) {
      System.out.println(test);
    } else {
      System.out.print(test);
    }
  }

  // log sth with new line
  public void log(String test) {
    log(test, true);
  }

}
