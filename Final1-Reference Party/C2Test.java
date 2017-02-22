package edu.kit.informatik.tests;

import static org.hamcrest.Matchers.*;
import org.junit.*;

import edu.kit.informatik.Terminal;

/**
 * Test for C2 - add journal
 * 
 * @author Micha Hanselmann
 */
public class C2Test {

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
        
        Terminal.addSingleLineOutputThatMatches("\tadd journal", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add-journal", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches(" add journal", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal ", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add  journal", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal ,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal ,;", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal ;,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal Fake,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal Fake,,News", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal Fake;News", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal Fake;News,Publisher", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal FakeNews,Publi;sher", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal Fake:News", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal ,News", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal ,,Test", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal This,Is,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal ,This,Is,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal This,Is,It", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal c't,,heise", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal c't,,he;se", startsWith("Error,"));
        
    }
    
    @Test
    public void valid() {
        
        Terminal.addSingleLineOutputThatIsExactly("add journal Ajournal,publisher", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add journal bJournal,PUBLISHER", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add journal C_JOURNAL,publisher", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add journal TSE,IEEE", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add journal banane,kirsch", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add journal HöhereMathematik,Bambusverlag", "Ok");
        // Terminal.addSingleLineOutputThatIsExactly("add journal \n\nWarum?\n,::Yes::", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add journal Daimler-,Benz", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add journal ---,@home", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add journal 23/&§(/$10--,@˛÷˛ÅÍ™∏#", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add journal Café,Sr", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add journal  keine ideen ,  mehr  ", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add journal c't,heise", "Ok");
        
    }
    
    @Test
    public void duplicate() {

        Terminal.addSingleLineOutputThatIsExactly("add journal Ajournal,publisher", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add journal bjournal,publisher", "Ok");
        // Terminal.addSingleLineOutputThatIsExactly("add journal \n\nWarum?\n,::Yes::", "Ok");
        
        Terminal.addSingleLineOutputThatMatches("add journal Ajournal,publisher", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal Ajournal,other", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add journal bjournal,publisher", startsWith("Error,"));
        // Terminal.addSingleLineOutputThatMatches("add journal \n\nWarum?\n,::No::", startsWith("Error,"));
        
    }


}
