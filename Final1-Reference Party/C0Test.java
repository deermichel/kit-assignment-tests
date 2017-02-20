package edu.kit.informatik.tests;

import static org.hamcrest.Matchers.*;
import org.junit.*;

import edu.kit.informatik.Terminal;

/**
 * Test for C0 - quit
 * 
 * @author Micha Hanselmann
 */
public class C0Test {

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
    public void invalid() {
        
        Terminal.addSingleLineOutputThatMatches("quita", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("quit-", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("quit ", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches(" quit", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("quit ;;", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("quit quit", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("\tquit", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("\nquit", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("QUIT", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("Quit", startsWith("Error,"));
        
    }

}
