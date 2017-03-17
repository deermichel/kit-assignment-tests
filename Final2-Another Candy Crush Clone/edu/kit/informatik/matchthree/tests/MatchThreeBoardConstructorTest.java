package edu.kit.informatik.matchthree.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.exceptions.TokenStringParseException;

import org.junit.Ignore;
import org.junit.Test;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Test for the two board constructors (A.1.1, A.1.2)
 *
 * @author IPD Koziolek
 * @author Florian Pfisterer
 * @author Luke Brocke
 * 
 * Based on templates & inspiration from Micha Hanselmann & IPD Koziolek
 */
public class MatchThreeBoardConstructorTest {

    @Test
    public void testValidConstructorItemsSet() {
        Board board = new MatchThreeBoard(Token.set("AB"), 2, 3);

        assertEquals(2, board.getColumnCount());
        assertEquals(3, board.getRowCount());
        assertEquals(Token.set("AB"), board.getAllValidTokens());
    }

    @Test
    public void testManyTokensSucceeds() {
        new MatchThreeBoard(Token.set("asjdhgfoiwueciwquerpqwzcrqwpoervqwpepqZUAIHFUDIAOPUHRQPEIWURCQåƒ‚©∂ƒª†∫€€"
                + "∑µº†®Ω[ø∫~∑¢√«†πç®∑®⁄∑€®†∫«®≈«¶ø¢[{«¢¶©ø†ç{©π¥©√|«¶†«√≠¶[©†¡ç©¡üqwüe"
                + "ü##ÄYX-.C,FMEHOWVECQRWÜPIUW"), 2, 2);
    }

    // -- invalid dimensions
    @Test(expected = BoardDimensionException.class)
    public void testBoardDimensionsException1() {
        new MatchThreeBoard(Token.set("AB"), 1, 2);
    }

    @Test(expected = BoardDimensionException.class)
    public void testBoardDimensionsException2() {
        new MatchThreeBoard(Token.set("AB"), 2, 1);
    }

    @Test(expected = BoardDimensionException.class)
    public void testBoardDimensionsException3() {
        new MatchThreeBoard(Token.set("AB"), 1, 1);
    }

    @Test(expected = BoardDimensionException.class)
    public void testBoardDimensionsException() {
        new MatchThreeBoard(Token.set("AB"), -10, 3);
    }

    // -- invalid tokens
    @Test(expected = IllegalArgumentException.class)
    public void testOnlyOneTokenFails() {
        new MatchThreeBoard(Token.set("A"), 2, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTwoDuplicateTokensFails() {
        new MatchThreeBoard(Token.set("AA"), 2, 2);
    }

    // -- Board from tokenString
    @Test
    public void createValidBoardSucceeds() {
        new MatchThreeBoard(Token.set("A*sOX+Y="), "A*s;OX ;+Y=");
    }

    @Test
    public void create2x2BoardValidDimensions() {
        final Board board = new MatchThreeBoard(Token.set("AB"), "  ;  ");

        assertEquals(2, board.getColumnCount());
        assertEquals(2, board.getRowCount());
        assertEquals("  ;  ", board.toTokenString());
    }
    
    // dedicated to jcg, may the test-driven development be with you
    @Test
    public void ultimateWidthHeightConfusionTest() {
        Board board = new MatchThreeBoard(Token.set("AB"), 2, 3);
        
        assertEquals(2, board.getColumnCount());
        assertEquals(3, board.getRowCount());
    }

    // -- Invalid tokens
    @Test(expected = TokenStringParseException.class)
    public void testBoardInvalidTokensInStringException1() {
        new MatchThreeBoard(Token.set("ab"), "ab;bc");
    }

    @Test(expected = TokenStringParseException.class)
    public void testBoardInvalidTokensInStringException2() {
        new MatchThreeBoard(Token.set("xyz "), "xy z;xxxx;xyz+");
    }

    // -- invalid dimensions
    @Test(expected = TokenStringParseException.class)
    public void testBoardDimensionsException01() {
        new MatchThreeBoard(Token.set("abc"), "abc;abc;ab");
    }

    @Test(expected = TokenStringParseException.class)
    public void testBoardDimensionsException02() {
        new MatchThreeBoard(Token.set("abc"), "   ;  ;   ");
    }

    @Test(expected = TokenStringParseException.class)
    public void testBoardDimensionsException03() {
        new MatchThreeBoard(Token.set("abc"), "aa;bb;");
    }

    @Test(expected = TokenStringParseException.class)
    public void testBoardDimensionsException04() {
        new MatchThreeBoard(Token.set("abc"), "aa;bb;cc;");
    }

    @Test(expected = TokenStringParseException.class)
    @Ignore("throw new IliasPostException - maybe *insert random exception* is more applicable here?")
    public void testBoardDimensionsException05() {
        new MatchThreeBoard(Token.set("abc"), "nothing here");
    }

    @Ignore("throw new IliasPostException - maybe BoardDimensionException is more applicable here?")
    @Test(expected = TokenStringParseException.class)
    public void testBoardDimensionsException06() {
        new MatchThreeBoard(Token.set("abc"), "");
    }

    @Ignore("throw new IliasPostException - maybe BoardDimensionException is more applicable here?")
    @Test(expected = TokenStringParseException.class)
    public void testBoardDimensionsException07() {
        new MatchThreeBoard(Token.set("abc"), ";;;");
    }

    @Test(expected = TokenStringParseException.class)
    public void testBoardDimensionsException08() {
        new MatchThreeBoard(Token.set("abc"), "  ;;");
    }

    @Test(expected = TokenStringParseException.class)
    public void testBoardDimensionsException09() {
        new MatchThreeBoard(Token.set("abc"), "ab;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;"
                + "abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;"
                + "abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc;abc");
    }

    // -- Matches the right setup
    @Test
    public void testBoardValidParsing01() {
        final Board board = new MatchThreeBoard(Token.set("xo"), "ox; x; x");

        assertEquals(2, board.getColumnCount());
        assertEquals(3, board.getRowCount());

        assertEquals("ox; x; x", board.toTokenString());

        final Token o = new Token("o");
        final Token x = new Token("x");

        assertEquals(o, board.getTokenAt(Position.at(0, 0)));
        assertEquals(x, board.getTokenAt(Position.at(1, 0)));
        assertNull(board.getTokenAt(Position.at(0, 1)));
        assertEquals(x, board.getTokenAt(Position.at(1, 1)));
        assertNull(board.getTokenAt(Position.at(0, 2)));
        assertEquals(x, board.getTokenAt(Position.at(1, 2)));
    }

    @Test
    public void testBoardValidParsing02() {
        final Board board = new MatchThreeBoard(Token.set("abc"), "aaaa;    ;bcaa");

        assertEquals(4, board.getColumnCount());
        assertEquals(3, board.getRowCount());

        assertEquals("aaaa;    ;bcaa", board.toTokenString());

        final Token a = new Token("a");
        final Token b = new Token("b");
        final Token c = new Token("c");

        assertEquals(a, board.getTokenAt(Position.at(0, 0)));
        assertEquals(a, board.getTokenAt(Position.at(1, 0)));
        assertEquals(a, board.getTokenAt(Position.at(2, 0)));
        assertEquals(a, board.getTokenAt(Position.at(3, 0)));

        assertNull(board.getTokenAt(Position.at(0, 1)));
        assertNull(board.getTokenAt(Position.at(1, 1)));
        assertNull(board.getTokenAt(Position.at(2, 1)));
        assertNull(board.getTokenAt(Position.at(3, 1)));

        assertEquals(b, board.getTokenAt(Position.at(0, 2)));
        assertEquals(c, board.getTokenAt(Position.at(1, 2)));
        assertEquals(a, board.getTokenAt(Position.at(2, 2)));
        assertEquals(a, board.getTokenAt(Position.at(3, 2)));
    }

    @Test
    public void testBoardValidParsing03() {

        final String alph = "abcdefghijklmnopqrstuvwxyz";

        final String[] rows = new String[100];  // over 9000 is also possible
        Arrays.fill(rows, alph);

        final String tokenString = Arrays.stream(rows).collect(Collectors.joining(";"));

        final Board board = new MatchThreeBoard(Token.set(alph), tokenString);

        assertEquals(alph.length(), board.getColumnCount());
        assertEquals(rows.length, board.getRowCount());

        assertEquals(tokenString, board.toTokenString());


        final char[] chars = alph.toCharArray();

        for (int x = 0; x < alph.length(); x += 1) {

            final char c = chars[x];
            final Token token = new Token(c);

            for (int y = 0; y < rows.length; y += 1) {

                final Position p = Position.at(x, y);
                assertEquals(token, board.getTokenAt(p));
            }
        }
    }
}
