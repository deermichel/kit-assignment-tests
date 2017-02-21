package edu.kit.informatik.tests;

import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.kit.informatik.Terminal;

/**
 * Test for C13 - find keywords
 *
 * @author Nico Weidmann
 * Based on templates & inspiration from Micha Hanselmann
 */
public class C13Test {

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
    
    @Test
    public void invalid() {

        TestHelper.expectError("find keywords");
        TestHelper.expectError(" find keywords");
        TestHelper.expectError("find keywords ");
        TestHelper.expectError("find keywords a,b");
        TestHelper.expectError("find keywords ,,");
        TestHelper.expectError("\tfind keywords a");
        TestHelper.expectError(" find keywords a");
        
        // illegal keywords
        TestHelper.expectError("find keywords ü");
        TestHelper.expectError("find keywords  ; ");
        TestHelper.expectError("find keywords Aa");
        TestHelper.expectError("find keywords aaa;15df");
        TestHelper.expectError("find keywords !§&");
        TestHelper.expectError("find keywords aa;;d");
        TestHelper.expectError("find keywords ab;cd;");
        
    }
    
    @Test
    public void valid() {
        
        // add conference series
        Terminal.addSingleLineOutputThatIsExactly("add conference series A", "Ok");

        // add conferences
        Terminal.addSingleLineOutputThatIsExactly("add conference A,1337,Testhausen", "Ok");

        // add journal
        Terminal.addSingleLineOutputThatIsExactly("add journal Bild,Kai Diekmann", "Ok");

        // add articles
        Terminal.addSingleLineOutputThatIsExactly("add article to series A:awesomearticle1,1337,An awesome Article", "Ok");
        Terminal.addSingleLineOutputThatIsExactly("add article to journal Bild:bsarticle2,1337,NACHWUCHS IM HAUSE LOMBARDI", "Ok");
        
        // adding some keywords
        TestHelper.expectOk("add keywords to series A:here;are;some;keywords");
        TestHelper.expectOk("add keywords to conference A,1337:more");
        TestHelper.expectOk("add keywords to pub awesomearticle1:for;this;article");
        
        TestHelper.expectOk("add keywords to journal Bild:this;is;trash");
        TestHelper.expectOk("add keywords to pub bsarticle2:for;this;article");
        
        // unknown keyword
        Terminal.addNoOutput("find keywords unknown");
        
        // inheritance of keywords for series
        Terminal.addSingleLineOutputThatMatches("find keywords here;are;some;more;keywords;for;this;article", is("awesomearticle1"));
        
        // inheritance of keywords for journals
        Terminal.addSingleLineOutputThatMatches("find keywords this;is;trash;for;this;article", is("bsarticle2"));
        
        // some other tests
        Terminal.addMultipleLineOutputThatMatches("find keywords for;this;article", containsInAnyOrder(new String[] {"awesomearticle1", "bsarticle2"}));
        Terminal.addSingleLineOutputThatMatches("find keywords some;for;this;article", is("awesomearticle1"));
    }

}
