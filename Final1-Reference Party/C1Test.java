package edu.kit.informatik.tests;

import static org.hamcrest.Matchers.*;
import org.junit.*;

import edu.kit.informatik.Terminal;

/**
 * Test for C1 - add author
 * 
 * @author Micha Hanselmann
 */
public class C1Test {

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
        
        Terminal.addSingleLineOutputThatMatches("add", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("addauthor", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add-author", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches(" add author", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author ", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add  author", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author .", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author  ;", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author ,,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author ,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author add author", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author Dieter,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author ,Fisr", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author ,,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author Aldi,Sued,", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author Einfach,,Nein", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author Ronald dla", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author Weil", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author Marcel,Davis,von", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author Dieter,Mann ", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author  Dieter,Mann", startsWith("Error,"));
        
    }
    
    @Test
    public void invalidNames() {
        
        Terminal.addSingleLineOutputThatMatches("add author Throsten,Dörnbach", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author Throsten, ", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author  , ", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author    ,mensch", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author Dieter,_Wolf", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author Wolf_,Dieter", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author 12,ab", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author ab,21", startsWith("Error,"));
        // Terminal.addSingleLineOutputThatMatches("add author ',\n", startsWith("Error,"));
        // Terminal.addSingleLineOutputThatMatches("add author \nDJ\n,Trump", startsWith("Error,"));
        
    }
    
    @Test
    public void valid() {
        
        Terminal.addSingleLineOutputThatIsExactly("add author Dieter,Mann", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author Richard,Mann", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author Thorsten,Doernbach", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author kleiner,name", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author MANFRED,HUT", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author bernD,kArPfEn", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author Shashi,Afolabi", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author Eniola,Lowry", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author Richard,Rhinelander", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author Derek,Sivers", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz,"
                + "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", "Ok");
        
    }
    
    @Test
    public void duplicate() {

        Terminal.addSingleLineOutputThatIsExactly("add author Dieter,Mann", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author Dieter,mann", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add author dieter,mann", "Ok");
        
        Terminal.addSingleLineOutputThatMatches("add author Dieter,Mann", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author Dieter,mann", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("add author dieter,mann", startsWith("Error,"));
        
    }

}
