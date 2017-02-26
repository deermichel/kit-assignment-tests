package edu.kit.informatik.tests;

import static org.hamcrest.Matchers.*;
import org.junit.*;

import edu.kit.informatik.Terminal;

/**
 * Test for C10 - list invalid publications
 *
 * @author Nico Weidmann
 * Based on templates & inspiration from Micha Hanselmann
 */
public class C10Test {

    @BeforeClass
    public static void enableTerminalTestingMode() {
        Terminal.enableTestingMode();
    }
    
    @Before
    public void reset() {
        Terminal.reset();
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

        TestHelper.expectError("list invalid publications ");
        TestHelper.expectError("list invalid   publications");
        TestHelper.expectError(" list invalid publications");
        TestHelper.expectError("list   invalid publicatations");
        TestHelper.expectError("invalid publications");
        
        // no parameters allowed
        TestHelper.expectError("list invalid publications param");
        TestHelper.expectError("list invalid publicationsText");
        TestHelper.expectError("list invalid publications unexpected;list");
        TestHelper.expectError("list invalid publications,");
        TestHelper.expectError("list invalid publications , ");
        TestHelper.expectError("list invalid publications ;;");
    }

    @Test
    public void valid() {

        // no publications present
        Terminal.addNoOutput("list invalid publications");

        // add conference series
        Terminal.addSingleLineOutputThatIsExactly("add conference series A", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series B", "Ok");

        // add conferences
        Terminal.addSingleLineOutputThatIsExactly("add conference A,1337,Testhausen", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference B,1337,Cool City", "Ok");

        // add journal
        Terminal.addSingleLineOutputThatIsExactly("add journal Bild,Kai Diekmann", "Ok");

        // add articles
        Terminal.addSingleLineOutputThatIsExactly("add article to series A:awesomearticle1,1337,An awesome Article", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add article to series A:awesomearticle2,1337,Another awesome Article", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add article to series B:awesomearticle3,1337,Not so awesome Article", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add article to series B:awesomearticle4,1337,Quite acceptable Article", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add article to journal Bild:bsarticle1,1337,Wer vom Kim Clan noch uebrig ist", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add article to journal Bild:bsarticle2,1337,NACHWUCHS IM HAUSE LOMBARDI", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add article to journal Bild:bsarticle3,1337,Pilot landet kopfueber in Wohnstrasse", "Ok");

        // get list, all articles are invalid
        Terminal.addMultipleLineOutputThatMatches("list invalid publications", containsInAnyOrder(
                new String[] {
                        "awesomearticle1", 
                        "awesomearticle2", 
                        "awesomearticle3", 
                        "awesomearticle4",
                        "bsarticle1",
                        "bsarticle2",
                        "bsarticle3"
                        }));
        
        // adding authors and adding those authors to some articles (can only cause failure if C1 and C6 fail too)
        TestHelper.expectOk("add author Peter,Zwegat");
        TestHelper.expectOk("add author Robert,Geiss");
        TestHelper.expectOk("written-by awesomearticle1,Peter Zwegat");
        TestHelper.expectOk("written-by awesomearticle3,Peter Zwegat;Robert Geiss");
        TestHelper.expectOk("written-by bsarticle1,Robert Geiss");
        TestHelper.expectOk("written-by bsarticle2,Robert Geiss");
        
        // get list, only some articles are invalid
        Terminal.addMultipleLineOutputThatMatches("list invalid publications", containsInAnyOrder(
                new String[] { 
                        "awesomearticle2",
                        "awesomearticle4",
                        "bsarticle3"
                        }));
    }

}
