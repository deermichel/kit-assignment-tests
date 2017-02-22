package edu.kit.informatik.tests;

import edu.kit.informatik.Terminal;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

/**
 * Test for C19 - foreign citations
 * 
 * @author Max Nagy
 * @author Micha Hanselmann
 */
public class C19Test {
    
    private static final String ok = "Ok";
    private static final String error = "Error,";
    
    
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
    public void invalid() {
        
        Terminal.addSingleLineOutputThatMatches("foreign citations of a b", startsWith(error)); // author nonexistent
        
        Terminal.addSingleLineOutputThatIsExactly("add author ab,ab", ok);
        
        Terminal.addSingleLineOutputThatMatches("foreign citations of ab,ab", startsWith(error));
        Terminal.addSingleLineOutputThatMatches("foreign citations of abab", startsWith(error));
        Terminal.addSingleLineOutputThatMatches("foreign citations of ab;ab", startsWith(error));
        Terminal.addSingleLineOutputThatMatches(" foreign citations of ab ab", startsWith(error));
        Terminal.addSingleLineOutputThatMatches("foreign citations of ab ab ", startsWith(error));
        Terminal.addSingleLineOutputThatMatches("foreign citations of ab  ab", startsWith(error));
        Terminal.addSingleLineOutputThatMatches("foreign citations of ab\tab", startsWith(error));
        
    }
    
    @Test
    public void test2() {
        
        // preps
        Terminal.addSingleLineOutputThatIsExactly("add author Alpha,Anton", ok);
        Terminal.addSingleLineOutputThatIsExactly("add author Beton,Bieber", ok);
        Terminal.addSingleLineOutputThatIsExactly("add author Corny,Cornelius", ok);
        Terminal.addSingleLineOutputThatIsExactly("add author Danny,Douglas", ok);
        
        Terminal.addSingleLineOutputThatIsExactly("add journal Blatt,Herausg", ok);
        
        Terminal.addSingleLineOutputThatIsExactly("add article to journal Blatt:id1,2016,Hello World!", ok);
        Terminal.addSingleLineOutputThatIsExactly("add article to journal Blatt:id2,2017,Hello Trump!", ok);
        Terminal.addSingleLineOutputThatIsExactly("add article to journal Blatt:id3,2018,Hello Mars!", ok);
        Terminal.addSingleLineOutputThatIsExactly("add article to journal Blatt:id4,2019,Hello Pluto!", ok);
        Terminal.addSingleLineOutputThatIsExactly("add article to journal Blatt:id5,9999,Hello Future!", ok);
        
        Terminal.addSingleLineOutputThatIsExactly("written-by id1,Alpha Anton", ok);
        Terminal.addSingleLineOutputThatIsExactly("written-by id2,Beton Bieber", ok);
        Terminal.addSingleLineOutputThatIsExactly("written-by id3,Corny Cornelius", ok);
        Terminal.addSingleLineOutputThatIsExactly("written-by id4,Danny Douglas", ok);
        
        Terminal.addSingleLineOutputThatIsExactly("cites id2,id1", ok);
        Terminal.addSingleLineOutputThatIsExactly("cites id3,id1", ok);
        Terminal.addSingleLineOutputThatIsExactly("cites id4,id1", ok);
        
        // tests   
        Terminal.addMultipleLineOutputThatMatches("foreign citations of Alpha Anton", containsInAnyOrder("id2", "id3", "id4"));
        // id4 should not be listed anymore, although Danny Douglas published together with Alpha Anton after citing him in his "Hello Pluto!" article
        Terminal.addSingleLineOutputThatIsExactly("written-by id5,Danny Douglas;Alpha Anton", ok);
        Terminal.addMultipleLineOutputThatMatches("foreign citations of Alpha Anton", containsInAnyOrder("id2", "id3"));
        Terminal.addNoOutput("foreign citations of Beton Bieber");
        Terminal.addNoOutput("foreign citations of Corny Cornelius");
        
        Terminal.addSingleLineOutputThatIsExactly("written-by id2,Alpha Anton", ok);
        Terminal.addMultipleLineOutputThatMatches("foreign citations of Alpha Anton", containsInAnyOrder("id3"));
        Terminal.addNoOutput("foreign citations of Beton Bieber");
        Terminal.addNoOutput("foreign citations of Corny Cornelius");
        
        Terminal.addSingleLineOutputThatIsExactly("written-by id3,Beton Bieber", ok);
        Terminal.addNoOutput("foreign citations of Alpha Anton");
        Terminal.addNoOutput("foreign citations of Beton Bieber");
        Terminal.addNoOutput("foreign citations of Corny Cornelius");
        
    }

    @Test
    public void givenExample() {
        
        // preps
        Terminal.addSingleLineOutputThatIsExactly("add author a,a", ok);
        Terminal.addSingleLineOutputThatIsExactly("add author b,b", ok);
        Terminal.addSingleLineOutputThatIsExactly("add author c,c", ok);
        Terminal.addSingleLineOutputThatIsExactly("add author d,d", ok);

        Terminal.addSingleLineOutputThatIsExactly("add journal j,j", ok);
        Terminal.addSingleLineOutputThatIsExactly("add conference series s", ok);
        Terminal.addSingleLineOutputThatIsExactly("add conference s,2000,c", ok);

        Terminal.addSingleLineOutputThatIsExactly("add article to journal j:p0,1999,k", ok);
        Terminal.addSingleLineOutputThatIsExactly("add article to series s:p1,2000,s", ok);
        Terminal.addSingleLineOutputThatIsExactly("add article to series s:p2,2000,kk", ok);
        Terminal.addSingleLineOutputThatIsExactly("add article to series s:p3,2000,skkk", ok);

        Terminal.addSingleLineOutputThatIsExactly("written-by p0,a a", ok);
        Terminal.addSingleLineOutputThatIsExactly("written-by p1,a a", ok);
        Terminal.addSingleLineOutputThatIsExactly("written-by p1,b b", ok);
        Terminal.addSingleLineOutputThatIsExactly("written-by p2,b b", ok);
        Terminal.addSingleLineOutputThatIsExactly("written-by p2,c c", ok);
        Terminal.addSingleLineOutputThatIsExactly("written-by p3,c c", ok);
        Terminal.addSingleLineOutputThatIsExactly("written-by p3,d d", ok);
        
        Terminal.addNoOutput("foreign citations of a a");

        Terminal.addSingleLineOutputThatIsExactly("cites p1,p0", ok);
        Terminal.addSingleLineOutputThatIsExactly("cites p2,p0", ok);
        Terminal.addSingleLineOutputThatIsExactly("cites p3,p0", ok);

        // test
        Terminal.addSingleLineOutputThatIsExactly("foreign citations of a a", "p3");

    }

}
