package edu.kit.informatik.tests;

import org.junit.*;

import edu.kit.informatik.Terminal;

/**
 * Test for C14 - jaccard
 *
 * @author Micha Hanselmann
 */
public class C14Test {

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
        TestHelper.expectOk("quit");
        Wrapper.main();

        // cleanup
        Terminal.flush();
        Terminal.reset();
    }

    @Test
    public void invalid() {

        // invalid syntax
        TestHelper.expectError("jaccard");
        TestHelper.expectError("-jaccard");
        TestHelper.expectError("jac-card");
        TestHelper.expectError("jaccard-");
        TestHelper.expectError("jaccard ");
        TestHelper.expectError(" jaccard");
        TestHelper.expectError("jaccard;a;a");
        TestHelper.expectError("jaccard;a,a");
        TestHelper.expectError("jaccard a,a");
        TestHelper.expectError(" jaccard a;a");
        TestHelper.expectError("jaccard;a;a b;b");
        TestHelper.expectError("jaccard a;a,b;b");
        TestHelper.expectError("jaccard a,a,b;b");
        TestHelper.expectError("jaccard a;a b;;b");
        TestHelper.expectError("jaccard a,a b,b");
        TestHelper.expectError("jaccard a,a;b,b");
        TestHelper.expectError("jaccard a;a  b;b");
        
        // invalid keywords
        TestHelper.expectError("jaccard a:;a b;b");
        TestHelper.expectError("jaccard aA;a b;b");
        TestHelper.expectError("jaccard aBB b;b");
        TestHelper.expectError("jaccard aBB bb");
        TestHelper.expectError("jaccard 234 aa");
        TestHelper.expectError("jaccard 234 aa;@");
        
    }

    @Test
    public void valid() {
        
        Terminal.addSingleLineOutputThatIsExactly("jaccard a a", "1.000");
        Terminal.addSingleLineOutputThatIsExactly("jaccard a;b a", "0.500");
        Terminal.addSingleLineOutputThatIsExactly("jaccard a b;a", "0.500");
        Terminal.addSingleLineOutputThatIsExactly("jaccard aa aa;b;b", "0.500");
        Terminal.addSingleLineOutputThatIsExactly("jaccard a;b;c d;e", "0.000");
        Terminal.addSingleLineOutputThatIsExactly("jaccard a d", "0.000");
        Terminal.addSingleLineOutputThatIsExactly("jaccard a;b;c;d;e b;c;d;e;f", "0.666");
        Terminal.addSingleLineOutputThatIsExactly("jaccard a;a;a;a;a a;a", "1.000");
        Terminal.addSingleLineOutputThatIsExactly("jaccard a;a;b;a;a b;a", "1.000");
        Terminal.addSingleLineOutputThatIsExactly("jaccard aa;bb;c;z;e;f;h;jj;d;x;z j;s;r;x;j;w;jj", "0.142");
        Terminal.addSingleLineOutputThatIsExactly("jaccard a;b;c;d;e b;c;e;f;h;i;z", "0.333");

    }
    
}
