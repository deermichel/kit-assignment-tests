package edu.kit.informatik.tests;

import org.junit.*;

import edu.kit.informatik.Terminal;

/**
 * Test for C5 - add␣article␣to␣<series/journal>:<id>,<year>,<title>
 *
 * @author Florian Pfisterer
 * Based on templates & inspiration from Micha Hanselmann
 */
public class C5Test {

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

        // add series & journal so these are not the reasons for the errors
        TestHelper.expectOk("add conference series WWDC");
        TestHelper.expectOk("add journal NYT,Publisher0815");

        TestHelper.expectError("add article to series,WWDC,article01,2017,Title");
        TestHelper.expectError("add article to series\nWWDC:article02,2017,Title");
        TestHelper.expectError("add article to :series,WWDC,article03,2017,Title");
        TestHelper.expectError("add article to series WWDC :article04,2017,Title");
        TestHelper.expectError("add article to series WWDC,:article05,2017,Title");
        TestHelper.expectError("add article to journal NYT :article06,2017,Title");
        TestHelper.expectError("add article to somethingelse NYT:article07,2017,Title");
        TestHelper.expectError("add article to entity WWDC:article08,2017,Title");
        TestHelper.expectError("add article to conference WWDC:article09,2017,Title");
        TestHelper.expectError("add article to pub NYT:article10,2017,Title");

        TestHelper.expectError("add article to series null:article11,2017,Title");
    }

    @Test
    public void valid() {

        // add series & conference & journal
        TestHelper.expectOk("add conference series WWDC");
        TestHelper.expectOk("add conference WWDC,2017,San Jose CA");
        TestHelper.expectOk("add journal NYT,Publisher0815");

        TestHelper.expectOk("add article to journal NYT:nr1,2017,Title");
        TestHelper.expectOk("add article to journal NYT:nr2,2017,Title");

        TestHelper.expectOk("add article to series WWDC:swift,2017,Title with spaces - and special characters like \n\t_äüö§$%/(/&%$§◊µ~√ç≈¥…∂ƒ∞∆ªº¨Ω†∑");
        TestHelper.expectOk("add article to series WWDC:swift4,2017,ABI support delayed :(");
    }

    @Test
    public void duplicate() {

        // add series & conference & journal
        TestHelper.expectOk("add conference series WWDC");
        TestHelper.expectOk("add conference WWDC,2014,San Francisco CA");
        TestHelper.expectOk("add conference WWDC,2017,San Jose CA");
        TestHelper.expectOk("add journal NYT,Publisher0815");

        TestHelper.expectOk("add article to journal NYT:nr1,2017,Title");
        TestHelper.expectError("add article to series WWDC:nr1,2016,Title2");   // same identifier, different venue

        TestHelper.expectOk("add article to series WWDC:swift1,2014,Swift 1.0 101");
        TestHelper.expectError("add article to series WWDC:swift1,2017,Ignore the identifier! It's another thing!");
    }
}
