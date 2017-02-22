package edu.kit.informatik.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.kit.informatik.Terminal;

import static org.hamcrest.Matchers.*;

/**
 * Test for C20 - "direct print conference"
 * 
 * @author  Luke 'luk3b' Bro
 * @version 1.0.0
 * @see     https://sdqweb.ipd.kit.edu/lehre/WS1617-Programmieren/final01-1.1.pdf
 */
public class C20Test {
    private final static String ERR = "Error, ";
    
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
        Terminal.addSingleLineOutputThatIsExactly("quit", "Ok");
        Wrapper.main();
        
        Terminal.flush();
        Terminal.reset();
    }
    
    @Test
    public void invalid() {
        // invalid style
        Terminal.addSingleLineOutputThatMatches("direct print conference xxx:first last,,,Some title,WWW,Karlsruhe,2000", startsWith(ERR));
        
        // invalid authors
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first,,,Some title,WWW,Karlsruhe,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first ,,,Some title,WWW,Karlsruhe,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first last,another,,Some title,WWW,Karlsruhe,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first,another ,,Some title,WWW,Karlsruhe,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first,another name, ,Some title,WWW,Karlsruhe,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:  ,  ,  ,Some title,WWW,Karlsruhe,2000", startsWith(ERR));
        
        // invalid title
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first last,,,Some;title,WWW,Karlsruhe,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first last,,,Some,title,WWW,Karlsruhe,2000", startsWith(ERR));
        
        // invalid series
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first last,,,Some title,W;W,Karlsruhe,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first last,,,Some title,W,W,Karlsruhe,2000", startsWith(ERR));
        
        // invalid location
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first last,,,Some title,WWW,Karlsruhe;,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first last,,,Some title,W;W,Karls,uhe,2000", startsWith(ERR));
        
        // invalid year
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first last,,,Some title,WWW,Karlsruhe,999", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first last,,,Some title,WWW,Karlsruhe,0999", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first last,,,Some title,WWW,Karlsruhe,10000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first last,,,Some title,WWW,Karlsruhe,0000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first last,,,Some title,WWW,Karlsruhe,2+00", startsWith(ERR));
        
        // invalid syntax (missing or too many commas)
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first last,Some title,WWW,Karlsruhe,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first last,,Some title,WWW,Karlsruhe,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first last,,,,Some title,WWW,Karlsruhe,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print conference ieee:first last,Some title,WWW,Karlsruhe,2000,", startsWith(ERR));
    }

    @Test
    public void ieeeValid() {
        // one author
        Terminal.addSingleLineOutputThatIsExactly(
            "direct print conference ieee:Sergey Brin,,,The Anatomy of a Large-Scale Hypertextual Web Search Engine,WWW,Brisbane Australia,1998",
            "[1] S. Brin, \"The Anatomy of a Large-Scale Hypertextual Web Search Engine,\" in Proceedings of WWW, Brisbane Australia, 1998."
        );
        
        // two authors
        Terminal.addSingleLineOutputThatIsExactly(
            "direct print conference ieee:Sergey Brin,Lawrence Page,,The Anatomy of a Large-Scale Hypertextual Web Search Engine,WWW,Brisbane Australia,1998",
            "[1] S. Brin and L. Page, \"The Anatomy of a Large-Scale Hypertextual Web Search Engine,\" in Proceedings of WWW, Brisbane Australia, 1998."
        );
        
        // three authors
        Terminal.addSingleLineOutputThatIsExactly(
            "direct print conference ieee:Sergey Brin,Lawrence Page,Google Bot,The Anatomy of a Large-Scale Hypertextual Web Search Engine,WWW,Brisbane Australia,1998",
            "[1] S. Brin et al., \"The Anatomy of a Large-Scale Hypertextual Web Search Engine,\" in Proceedings of WWW, Brisbane Australia, 1998."
        );
    }
    
    @Test
    public void chicagoValid() {
        // one author
        Terminal.addSingleLineOutputThatIsExactly(
            "direct print conference chicago:Sergey Brin,,,The Anatomy of a Large-Scale Hypertextual Web Search Engine,WWW,Brisbane Australia,1998",
            "(Brin, 1998) Brin, Sergey. \"The Anatomy of a Large-Scale Hypertextual Web Search Engine.\" Paper presented at WWW, 1998, Brisbane Australia."
        );
        
        // two authors
        Terminal.addSingleLineOutputThatIsExactly(
            "direct print conference chicago:Sergey Brin,Lawrence Page,,The Anatomy of a Large-Scale Hypertextual Web Search Engine,WWW,Brisbane Australia,1998",
            "(Brin, 1998) Brin, Sergey, and Page, Lawrence. \"The Anatomy of a Large-Scale Hypertextual Web Search Engine.\" Paper presented at WWW, 1998, Brisbane Australia."
        );
        
        // three authors
        Terminal.addSingleLineOutputThatIsExactly(
            "direct print conference chicago:Sergey Brin,Lawrence Page,Google Bot,The Anatomy of a Large-Scale Hypertextual Web Search Engine,WWW,Brisbane Australia,1998",
            "(Brin, 1998) Brin, Sergey, Page, Lawrence, and Bot, Google. \"The Anatomy of a Large-Scale Hypertextual Web Search Engine.\" Paper presented at WWW, 1998, Brisbane Australia."
        );
    }
}