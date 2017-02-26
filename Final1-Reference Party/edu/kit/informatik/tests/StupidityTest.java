package edu.kit.informatik.tests;

import static org.hamcrest.Matchers.*;
import org.junit.*;

import edu.kit.informatik.Terminal;

/**
 * Test for stupidity
 * 
 * @see http://www.urbandictionary.com/define.php?term=stupidity
 * 
 * @author Micha Hanselmann
 */
public class StupidityTest {

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
    public void nothing() {
        
        Terminal.addSingleLineOutputThatMatches("", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches(" ", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("  ", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("\t", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("\r", startsWith("Error,"));
        // Terminal.addSingleLineOutputThatMatches("\n", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("\f", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("-", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches(" - ", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("Ok", startsWith("Error,"));
        
    }

}
