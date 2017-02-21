package edu.kit.informatik.tests;

import org.junit.*;

import edu.kit.informatik.Terminal;

/**
 * Test for C4 - add conference
 *
 * @author Florian Pfisterer
 * Based on templates & inspiration from Micha Hanselmann
 */
public class C4Test {

    @BeforeClass
    public static void enableTerminalTestingMode() {
        Terminal.enableTestingMode();
    }

    @After
    public void run() {

        // run and terminate
        TestHelper.expectOk("quit");
        Wrapper.main();

        // cleanup
        Terminal.flush();
        Terminal.reset();
    }

    @Test
    public void invalidSyntax() {

        TestHelper.expectError("\tadd conference");
        TestHelper.expectError("add-conference-");
        TestHelper.expectError("add conference-");
        TestHelper.expectError(" add conference ");
        TestHelper.expectError(" add conference");
        TestHelper.expectError("add conference ");
        TestHelper.expectError("add  conference");
        TestHelper.expectError("add  conference");
        TestHelper.expectError("add Conference");
        TestHelper.expectError("add conference ,,");
        TestHelper.expectError("add conference ,;");
        TestHelper.expectError("add conference  ;,");
        TestHelper.expectError("add conference ,2017,");
        TestHelper.expectError("add conference Ser,,Ies");
        TestHelper.expectError("add conference ;,;,;");
        TestHelper.expectError("add conference ,News");
        TestHelper.expectError("add conference ,,Test");
        TestHelper.expectError("add conference This,Is,");
        TestHelper.expectError("add conference ,This,Is,");
        TestHelper.expectError("add conference This,s,It");
        TestHelper.expectError("add conference A,,serie");
        TestHelper.expectError("add conference ser,,i;e");
        TestHelper.expectError("add conference Tafelwisch;enCon,2017,California");

        TestHelper.expectError("add conference WWDC,,");
        TestHelper.expectError("add conference WWDC,2017,");
        TestHelper.expectError("add conference ,2017,CA");
        TestHelper.expectError("add conferenceWWDC,2017,CA");
    }

    @Test
    public void valid() {

        // add series
        TestHelper.expectOk("add conference series Apple WWDC");
        TestHelper.expectOk("add conference series Google IO");

        TestHelper.expectOk("add conference series Weird conference01 v      Series::yes");

        // add conferences
        TestHelper.expectOk("add conference Apple WWDC,2014,San Francisco CA");
        TestHelper.expectOk("add conference Google IO,2014,San Francisco CA");

        TestHelper.expectOk("add conference Apple WWDC,2015,San Francisco CA");
        TestHelper.expectOk("add conference Apple WWDC,2016,San Francisco CA");
        TestHelper.expectOk("add conference Apple WWDC,2017,San Jose CA");

        TestHelper.expectOk("add conference Weird conference01 v      Series::yes,1000,At home");
        TestHelper.expectOk("add conference Weird conference01 v      Series::yes,9999,At home once again");
    }

    @Test
    public void testYearValidation() {

        // year must be \in [1000, 9999]

        // add series
        TestHelper.expectOk("add conference series WWDC");

        TestHelper.expectError("add conference WWDC,0,SF");
        TestHelper.expectError("add conference WWDC,-2017,SJ");
        TestHelper.expectError("add conference WWDC,999,SF");
        TestHelper.expectError("add conference WWDC,10000,SF");

        TestHelper.expectOk("add conference WWDC,2016,San Francisco CA");
        TestHelper.expectOk("add conference WWDC,1000,San Francisco CA");
        TestHelper.expectOk("add conference WWDC,9999,San Francisco CA");
    }

    @Test
    public void duplicate() {

        // add series
        TestHelper.expectOk("add conference series WWDC");

        TestHelper.expectOk("add conference WWDC,2016,San Francisco CA");
        TestHelper.expectOk("add conference WWDC,2017,San Jose CA");

        TestHelper.expectError("add conference WWDC,2016,Another Place");
        TestHelper.expectError("add conference WWDC,2017,San Francisco CA");

    }
}
