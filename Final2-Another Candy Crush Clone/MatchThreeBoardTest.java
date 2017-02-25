package edu.kit.informatik.matchthree.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.exceptions.IllegalTokenException;
import edu.kit.informatik.matchthree.framework.exceptions.TokenStringParseException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;

/**
 * Tests for MatchThreeBoard
 * 
 * @author Micha Hanselmann
 */
public class MatchThreeBoardTest {

    @Test
    public void basicConstructorTokensWidthHeight1() {
        
        Board board = new MatchThreeBoard(Token.set("AB"), 2, 2);
        
        assertEquals(2, board.getColumnCount());
        assertEquals(2, board.getRowCount());
        assertEquals(Token.set("AB"), board.getAllValidTokens());
    }

    @Test
    public void basicConstructorTokensWidthHeight2() {
        
        Board board = new MatchThreeBoard(Token.set("AAABBBCDEfg"), 20, 322);
        
        assertEquals(20, board.getColumnCount());
        assertEquals(322, board.getRowCount());
        assertEquals(Token.set("gfEDCBA"), board.getAllValidTokens());
    }

    @Test (expected = BoardDimensionException.class)
    public void basicConstructorInvalidColumns() {
        
        new MatchThreeBoard(Token.set("AB"), 1, 4);
    }

    @Test (expected = BoardDimensionException.class)
    public void basicConstructorInvalidRows() {
        
        new MatchThreeBoard(Token.set("AB"), 4, 1);
    }

    @Test
    public void tokenStringConstructor1() {
        
        Board board = new MatchThreeBoard(Token.set("AB"), "  ;  ");
        
        assertEquals(2, board.getColumnCount());
        assertEquals(2, board.getRowCount());
    }

    @Test
    public void tokenStringConstructor2() {
        
        Board board = new MatchThreeBoard(Token.set("AB"), " A ; B ;A B;   ; A ");
        
        assertEquals(3, board.getColumnCount());
        assertEquals(5, board.getRowCount());
    }

    @Test (expected = BoardDimensionException.class)
    public void tokenStringConstructorInvalidSize() {
        
        new MatchThreeBoard(Token.set("AB"), "");
    }

    @Test (expected = TokenStringParseException.class)
    public void tokenStringConstructorInvalidString1() {
        
        new MatchThreeBoard(Token.set("AB"), "AB; ");
    }

    @Test (expected = TokenStringParseException.class)
    public void tokenStringConstructorInvalidString2() {
        
        new MatchThreeBoard(Token.set("AB"), "AB;BA;AB;");
    }

    @Test (expected = TokenStringParseException.class)
    public void tokenStringConstructorInvalidString3() {
        
        new MatchThreeBoard(Token.set("AB"), "AB;BA;;AB;");
    }

    @Test (expected = TokenStringParseException.class)
    public void tokenStringConstructorInvalidString4() {
        
        new MatchThreeBoard(Token.set("AB"), "BB;AAA");
    }

    @Test (expected = TokenStringParseException.class)
    public void tokenStringConstructorInvalidToken() {
        
        new MatchThreeBoard(Token.set("AB"), "AB ; C ;AB ");
    }
    
    @Test
    public void containsPosition() {
        
        Board board = new MatchThreeBoard(Token.set("AB"), 3, 4);
        
        assertTrue(board.containsPosition(Position.at(0, 0)));
        assertTrue(board.containsPosition(Position.at(2, 0)));
        assertTrue(board.containsPosition(Position.at(0, 3)));
        assertTrue(board.containsPosition(Position.at(2, 3)));
        
        assertFalse(board.containsPosition(Position.at(3, 3)));
        assertFalse(board.containsPosition(Position.at(2, 4)));
        assertFalse(board.containsPosition(Position.at(30, 3)));
        assertFalse(board.containsPosition(Position.at(-1, 0)));
        assertFalse(board.containsPosition(Position.at(0, -1)));
        assertFalse(board.containsPosition(Position.at(-1, -2)));
    }
    
    @Test (expected = NullPointerException.class)
    public void containsPositionNullPointerException() {
        
        new MatchThreeBoard(Token.set("AB"), 3, 4).containsPosition(null);
    }
    
    @Test
    public void toTokenString() {
        
        assertEquals(" A ; B ;A B;   ; A ", 
                new MatchThreeBoard(Token.set("AB"), " A ; B ;A B;   ; A ").toTokenString());
        assertEquals("ABC ;   A;    ;CBBA;    ;A  B",
                new MatchThreeBoard(Token.set("ABC"), "ABC ;   A;    ;CBBA;    ;A  B").toTokenString());
        assertEquals("A   A  A B B B;BBB A  A B B B;A   A  BBB B B",
                new MatchThreeBoard(Token.set("AB"), "A   A  A B B B;BBB A  A B B B;A   A  BBB B B").toTokenString());
        assertEquals("   ;   ;   ;   ;   ;  A", 
                new MatchThreeBoard(Token.set("AB"), "   ;   ;   ;   ;   ;  A").toTokenString());
    }

}
