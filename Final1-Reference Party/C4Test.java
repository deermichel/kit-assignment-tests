package tests;

import static org.hamcrest.Matchers.*;
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
        Terminal.addSingleLineOutputThatIsExactly("quit", "Ok");
        Wrapper.main();

        // cleanup
        Terminal.flush();
        Terminal.reset();
    }

    @Test
    public void invalidSyntax() {

        Terminal.addSingleLineOutputThatMatches("\tadd conference", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add-conference-", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference-", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches(" add conference ", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches(" add conference", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference ", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add  conference", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add  conference", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add Conference", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference ,,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference ,;", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference  ;,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference ,2017,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference Ser,,Ies", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference ;,;,;", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference ,News", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference ,,Test", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference This,Is,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference ,This,Is,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference This,s,It", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference A,,serie", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference ser,,i;e", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference Tafelwisch;enCon,2017,California", startsWith("Error,"));

        Terminal.addSingleLineOutputThatMatches("add conference WWDC,,", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("add conference WWDC,2017,", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("add conference ,2017,CA", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("add conferenceWWDC,2017,CA", startsWith("Error, "));
    }

    @Test
    public void valid() {

        // add series
        Terminal.addSingleLineOutputThatIsExactly("add conference series Apple WWDC", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series Google IO", "Ok");

        Terminal.addSingleLineOutputThatIsExactly("add conference series Weird conference01 v      Series::yes", "Ok");

        // add conferences
        Terminal.addSingleLineOutputThatIsExactly("add conference Apple WWDC,2014,San Francisco CA", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference Google IO,2014,San Francisco CA", "Ok");

        Terminal.addSingleLineOutputThatIsExactly("add conference Apple WWDC,2015,San Francisco CA", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference Apple WWDC,2016,San Francisco CA", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference Apple WWDC,2017,San Jose CA", "Ok");

        Terminal.addSingleLineOutputThatIsExactly("add conference Weird conference01 v      Series::yes,1000,At home", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference Weird conference01 v      Series::yes,9999,At home once again", "Ok");
    }

    @Test
    public void testYearValidation() {

        // year must be \in [1000, 9999]

        // add series
        Terminal.addSingleLineOutputThatIsExactly("add conference series WWDC", "Ok");

        Terminal.addSingleLineOutputThatMatches("add conference WWDC,0,SF", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("add conference WWDC,-2017,SJ", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("add conference WWDC,999,SF", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("add conference WWDC,10000,SF", startsWith("Error, "));

        for (int year = 1000; year <= 9999; year += 1) {
            Terminal.addSingleLineOutputThatIsExactly("add conference WWDC," + year + ",San Francisco CA", "Ok");
        }
    }

    @Test
    public void duplicate() {

        // add series
        Terminal.addSingleLineOutputThatIsExactly("add conference series WWDC", "Ok");

        Terminal.addSingleLineOutputThatIsExactly("add conference WWDC,2016,San Francisco CA", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference WWDC,2017,San Jose CA", "Ok");

        Terminal.addSingleLineOutputThatMatches("add conference WWDC,2016,Another Place", startsWith("Error, "));
        Terminal.addSingleLineOutputThatMatches("add conference WWDC,2017,San Francisco CA", startsWith("Error, "));

    }
}
