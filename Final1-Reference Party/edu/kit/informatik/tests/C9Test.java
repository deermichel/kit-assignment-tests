package edu.kit.informatik.tests;

import static org.hamcrest.Matchers.*;
import org.junit.*;

import edu.kit.informatik.Terminal;

/**
 * Test for C9 - all publications
 *
 * @author Nico Weidmann
 * Based on templates & inspiration from Micha Hanselmann
 */
public class C9Test {

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
        Terminal.addSingleLineOutputThatIsExactly("quit", "Ok");
        Wrapper.main();

        // cleanup
        Terminal.flush();
        Terminal.reset();
    }

    @Test
    public void invalidSyntax() {

        Terminal.addSingleLineOutputThatMatches(" all publications", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("all publications ", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("allpublications", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("all   publications", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("all publication", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("list publications", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("all publicaitons", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("\tall publications", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("all publications param", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("all publications this,is,invalid", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("all publications,", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("all publications no;list;allowed", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("all publications ;;", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("all publications;", startsWith("Error, "));
    }

    @Test
    public void valid() {

        // no publications present
        Terminal.addNoOutput("all publications");

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

        // get list
        Terminal.addMultipleLineOutputThatMatches("all publications", containsInAnyOrder(
                new String[] {
                        "awesomearticle1", 
                        "awesomearticle2", 
                        "awesomearticle3", 
                        "awesomearticle4",
                        "bsarticle1",
                        "bsarticle2",
                        "bsarticle3"
                        }));
    }

}
