package edu.kit.informatik.tests;

import org.junit.*;

import edu.kit.informatik.Terminal;

/**
 * Test for C16 - direct h-index
 *
 * @author Micha Hanselmann
 */
public class C16Test {

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

        TestHelper.expectError("direct");
        TestHelper.expectError("directhindex");
        TestHelper.expectError("direct hindex");
        TestHelper.expectError(" direct h-index");
        TestHelper.expectError("direct  h-index");
        TestHelper.expectError("direct h index");
        TestHelper.expectError("direcnh-index 3;4");
        TestHelper.expectError("direct h-index  4;3");
        TestHelper.expectError("direct h-index;");
        
        TestHelper.expectError("direct h-index 2-2");
        TestHelper.expectError("direct h-index 2,3");
        TestHelper.expectError("direct h-index -1");
        TestHelper.expectError("direct h-index (2)");
        TestHelper.expectError("direct h-index 1;3 3");
        TestHelper.expectError("direct h-index 1;3,2;1");
        TestHelper.expectError("direct h-index 1:2");
        TestHelper.expectError("direct h-index 3 2 7");
        TestHelper.expectError("direct h-index 2;5;1;3;-1;2");
        TestHelper.expectError("direct h-index 2;5;1;3;-(-1);2");
        TestHelper.expectError("direct h-index 2;5;1;3;2.22;2");
        TestHelper.expectError("direct h-index 2;5;1;-3;-2;2");
        TestHelper.expectError("direct h-index 2;5;2;");
        
    }

    @Test
    public void valid() {
        
        Terminal.addSingleLineOutputThatIsExactly("direct h-index 17;3;1;5", "3");
        Terminal.addSingleLineOutputThatIsExactly("direct h-index 8;6;8;4;8;6", "5");
        Terminal.addSingleLineOutputThatIsExactly("direct h-index 65;234;12;4;643;12", "5");
        Terminal.addSingleLineOutputThatIsExactly("direct h-index 65", "1");
        Terminal.addSingleLineOutputThatIsExactly("direct h-index 65;66", "2");
        Terminal.addSingleLineOutputThatIsExactly("direct h-index 2;2;2;2;2;2;2;2;2;2;8", "2");
        Terminal.addSingleLineOutputThatIsExactly("direct h-index 8;8;8;8;8;8;8;8;8", "8");
        Terminal.addSingleLineOutputThatIsExactly("direct h-index 8;8;8;8;8;8;8;8", "8");
        Terminal.addSingleLineOutputThatIsExactly("direct h-index 8;8;8;8;8;8;8", "7");
        Terminal.addSingleLineOutputThatIsExactly("direct h-index 345;546;234;45;234;876;56;57;87;765;34", "11");
        Terminal.addSingleLineOutputThatIsExactly("direct h-index 345;546;234;45;234;876;56;57;87;765;34;55", "12");

    }
    
}
