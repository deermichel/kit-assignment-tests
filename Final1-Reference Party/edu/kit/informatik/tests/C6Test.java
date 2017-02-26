package edu.kit.informatik.tests;

import org.junit.*;

import edu.kit.informatik.Terminal;

/**
 * Test for C6 - written-by
 *
 * @author Nico Weidmann
 */
public class C6Test {

    @BeforeClass
    public static void enableTerminalTestingMode() {
        Terminal.enableTestingMode();
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

    /**
     * adding some data to test with<br>
     * (failures caused by these statements come along with other failing tests)
     */
    @Before
    public void init() {
        
        Terminal.reset();

        TestHelper.expectOk("add journal Test,Test");

        TestHelper.expectOk("add article to journal Test:id1,1337,How to make a s**t load of money");
        TestHelper.expectOk("add article to journal Test:id2,1337,How to make a s**t load of money Vol. 2");
        TestHelper.expectOk("add article to journal Test:id3,1337,How to make a s**t load of money Vol. 3");

        TestHelper.expectOk("add author Apo,Red");
        TestHelper.expectOk("add author Leon,Machere");
        TestHelper.expectOk("add author Krappi,Whatelse");
    }

    @Test
    public void invalid() {

        TestHelper.expectError("written-by");
        TestHelper.expectError("written-by ");
        TestHelper.expectError("written-by  , ; ");
        TestHelper.expectError("written by id1,Thorsten Roll");
        TestHelper.expectError("written-by id1:Thorsten Roll");
        TestHelper.expectError(" written-by id1,Thorsten Roll");
        TestHelper.expectError("written-by id1,Thorsten Roll;Thorsten Roll");
        TestHelper.expectError("written-by  id1,Thorsten Roll");
        TestHelper.expectError("written-by id1, Thorsten Roll");
        TestHelper.expectError("written-by id1,Thorsten Roll;");
        TestHelper.expectError("written-by id1,Thorsten Roll ");
        TestHelper.expectError("written-by id1,;Thorsten Roll");
        TestHelper.expectError("written-by id1,Non Existent");
        TestHelper.expectError("written-by nonexistent,Thorsten Roll");
    }

    @Test
    public void valid() {

        TestHelper.expectOk("written-by id1,Apo Red");
        TestHelper.expectError("written-by id1,Apo Red");

        TestHelper.expectOk("written-by id1,Leon Machere");
        TestHelper.expectOk("written-by id2,Apo Red;Leon Machere;Krappi Whatelse");
        TestHelper.expectOk("written-by id3,Apo Red");
        TestHelper.expectError("written-by id3,Leon Machere;Apo Red");
        TestHelper.expectOk("written-by id3,Leon Machere");
    }

}
