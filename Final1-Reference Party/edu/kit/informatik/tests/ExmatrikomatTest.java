package edu.kit.informatik.tests;

import org.junit.*;

import edu.kit.informatik.Terminal;

/**
 * Tests for behavior when adding a series called something like 'series [^,;]+'
 * 
 * @see https://www.sle.kit.edu/imstudium/exmatrikulation.php
 *
 * @author Nico Weidmann Based on templates & inspiration from Micha Hanselmann
 */
public class ExmatrikomatTest {

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
    public void doYouEvenCheckBorderCases() {
        
        // adding these series is legit, should be Ok
        TestHelper.expectOk("add conference series series test");
        TestHelper.expectOk("add conference series series");
        TestHelper.expectOk("add conference series series ");
        
        // adding conferences to them should be legit as well
        TestHelper.expectOk("add conference series test,2017,Karlsruhe");
        TestHelper.expectOk("add conference series,1337,L33T City");
        TestHelper.expectOk("add conference series ,1337,L33T City");
    }

}
