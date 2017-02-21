package edu.kit.informatik.tests;

import edu.kit.informatik.Terminal;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.startsWith;

/**
 * Test for C19. Just uses the given example (yet)
 * @author Max Nagy
 */
public class C19Test {
    private final String ok = "Ok";
    private final String error = "Error,";
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
    public void givenExample() {
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

        Terminal.addSingleLineOutputThatIsExactly("cites p1,p0", ok);
        Terminal.addSingleLineOutputThatIsExactly("cites p2,p0", ok);
        Terminal.addSingleLineOutputThatIsExactly("cites p3,p0", ok);

        Terminal.addSingleLineOutputThatIsExactly("foreign citations of a a", "p3");





    }

}
