package edu.kit.informatik.tests;

import static org.hamcrest.Matchers.*;
import org.junit.*;

import edu.kit.informatik.Terminal;

/**
 * Test for C3 - add conference series
 * 
 * @author Micha Hanselmann
 */
public class C3Test {

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
        
        Terminal.addSingleLineOutputThatMatches("\tadd conference series", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add-conference-series", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference-series", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches(" add conference series", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches(" add conferenceseries", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series ", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add  conference series", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add  conference  series", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series ,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series ,;", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series ;,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series Series,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series Ser,,Ies", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series Net;flix", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series ,News", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series ,,Test", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series This,Is,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series ,This,Is,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series This,Is,It", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series A,,serie", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series ser,,i;e", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series TafelwischenCon;", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series Tafelwisch;enCon", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series Tafelwisch,enCon", startsWith("Error,"));
        
    }
    
    @Test
    public void valid() {
        
        Terminal.addSingleLineOutputThatIsExactly("add conference series Küchenzeilen", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series schneebesen", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series klimaAnLagen2018", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series --dieUnendlicheGeschichte", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series TFA", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series pCfZs", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series hackCON", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series ICSA", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series    ", "Ok"); // TODO: throw new IliasPostException("really?");
        Terminal.addSingleLineOutputThatIsExactly("add conference series ?", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series WindowsConf", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series öäü - overrated?", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series \"tss\"", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series #makeTestsGreatAgain", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series TafelwischenCon", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series er:kann's", "Ok");
        
    }
    
    @Test
    public void duplicate() {

        Terminal.addSingleLineOutputThatIsExactly("add conference series ICSA", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series  ICSA", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add conference series IC SA", "Ok");
        
        Terminal.addSingleLineOutputThatMatches("add conference series ICSA", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series  ICSA", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add conference series IC SA", startsWith("Error,"));
        
    }


}
