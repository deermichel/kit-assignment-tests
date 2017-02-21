package edu.kit.informatik.tests;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.kit.informatik.Terminal;

import static org.hamcrest.Matchers.*;

/**
 * Test for C21 - "direct print journal"
 * 
 * @author  Luke 'luk3b' Bro
 * @version 1.0.0
 * @see     https://sdqweb.ipd.kit.edu/lehre/WS1617-Programmieren/final01-1.1.pdf
 */
public class C21Test {
    private final static String ERR = "Error, ";
    
    @BeforeClass
    public static void enableTerminalTestingMode() {
        Terminal.enableTestingMode();
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
        Terminal.addSingleLineOutputThatMatches("direct print journal xxx:first last,,,Some title,Journal,2000", startsWith(ERR));
        
        // invalid authors
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first,,,Some title,Journal,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first ,,,Some title,Journal,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first last,another,,Some title,Journal,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first,another ,,Some title,Journal,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first,another name, ,Some title,Journal,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:  ,  ,  ,Some title,Journal,2000", startsWith(ERR));
        
        // invalid title
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first last,,,Some;title,Journal,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first last,,,Some,title,Journal,2000", startsWith(ERR));
        
        // invalid journal
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first last,,,Some title,Jou;nal,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first last,,,Some title,Jou,nal,2000", startsWith(ERR));
        
        // invalid year
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first last,,,Some title,Journal,999", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first last,,,Some title,Journal,0999", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first last,,,Some title,Journal,10000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first last,,,Some title,Journal,0000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first last,,,Some title,Journal,2+00", startsWith(ERR));
        
        // invalid syntax (missing or too many commas)
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first last,Some title,Journal,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first last,,Some title,Journal,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first last,,,,Some title,Journal,2000", startsWith(ERR));
        Terminal.addSingleLineOutputThatMatches("direct print journal ieee:first last,Some title,Journal,2000,", startsWith(ERR));
    }

    @Test
    public void ieeeValid() {
        // one author
        Terminal.addSingleLineOutputThatIsExactly(
            "direct print journal ieee:Edsger Dijkstra,,,Go To Statement Considered Harmful,Comm. of the ACM,1968",
            "[1] E. Dijkstra, \"Go To Statement Considered Harmful,\" Comm. of the ACM, 1968."
        );
        
        // two authors
        Terminal.addSingleLineOutputThatIsExactly(
            "direct print journal ieee:Edsger Dijkstra,Niklaus Wirth,,Go To Statement Considered Harmful,Comm. of the ACM,1968",
            "[1] E. Dijkstra and N. Wirth, \"Go To Statement Considered Harmful,\" Comm. of the ACM, 1968."
        );
        
        // three authors
        Terminal.addSingleLineOutputThatIsExactly(
            "direct print journal ieee:Edsger Dijkstra,Niklaus Wirth,Jacopini Guiseppe,Go To Statement Considered Harmful,Comm. of the ACM,1968",
            "[1] E. Dijkstra et al., \"Go To Statement Considered Harmful,\" Comm. of the ACM, 1968."
        );
    }
    
    @Test
    public void chicagoValid() {
        // one author
        Terminal.addSingleLineOutputThatIsExactly(
            "direct print journal chicago:Edsger Dijkstra,,,Go To Statement Considered Harmful,Comm. of the ACM,1968",
            "(Dijkstra, 1968) Dijkstra, Edsger. \"Go To Statement Considered Harmful.\" Comm. of the ACM (1968)."
        );
        
        // two authors
        Terminal.addSingleLineOutputThatIsExactly(
            "direct print journal chicago:Edsger Dijkstra,Niklaus Wirth,,Go To Statement Considered Harmful,Comm. of the ACM,1968",
            "(Dijkstra, 1968) Dijkstra, Edsger, and Wirth, Niklaus. \"Go To Statement Considered Harmful.\" Comm. of the ACM (1968)."
        );
        
        // three authors
        Terminal.addSingleLineOutputThatIsExactly(
            "direct print journal chicago:Edsger Dijkstra,Niklaus Wirth,Jacopini Guiseppe,Go To Statement Considered Harmful,Comm. of the ACM,1968",
            "(Dijkstra, 1968) Dijkstra, Edsger, Wirth, Niklaus, and Guiseppe, Jacopini. \"Go To Statement Considered Harmful.\" Comm. of the ACM (1968)."
        );
    }
}