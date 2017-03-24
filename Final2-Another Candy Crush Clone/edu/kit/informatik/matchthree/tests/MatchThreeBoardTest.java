package edu.kit.informatik.matchthree.tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Ignore;
import org.junit.Test;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.framework.DeterministicStrategy;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.exceptions.IllegalTokenException;
import edu.kit.informatik.matchthree.framework.exceptions.NoFillingStrategyException;
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

    @Test(expected = IllegalArgumentExceptions.class)
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
    
    @Test
    public void getTokenAt() {
        
        Board board = new MatchThreeBoard(Token.set("AB"), "A B;  B; AA");
        
        assertEquals("A", board.getTokenAt(Position.at(0, 0)).toString());
        assertNull(board.getTokenAt(Position.at(1, 0)));
        assertEquals("B", board.getTokenAt(Position.at(2, 0)).toString());
        assertNull(board.getTokenAt(Position.at(0, 1)));
        assertNull(board.getTokenAt(Position.at(1, 1)));
        assertEquals("B", board.getTokenAt(Position.at(2, 1)).toString());
        assertNull(board.getTokenAt(Position.at(0, 2)));
        assertEquals("A", board.getTokenAt(Position.at(1, 2)).toString());
        assertEquals("A", board.getTokenAt(Position.at(2, 2)).toString());
    }
    
    @Test (expected = BoardDimensionException.class)
    public void getTokenAtInvalidPosition1() {
        new MatchThreeBoard(Token.set("AB"), 3, 3).getTokenAt(Position.at(0, 3));
    }
    
    @Test (expected = BoardDimensionException.class)
    public void getTokenAtInvalidPosition2() {
        new MatchThreeBoard(Token.set("AB"), 3, 3).getTokenAt(Position.at(-1, 1));
    }
    
    @Test
    public void setTokenAt() {
        
        Board board = new MatchThreeBoard(Token.set("AB"), 3, 3);
        Token a = new Token("A");
        Token b = new Token("B");
        
        board.setTokenAt(Position.at(2, 1), a);
        assertEquals(a, board.getTokenAt(Position.at(2, 1)));
        board.setTokenAt(Position.at(2, 1), a);
        assertEquals(a, board.getTokenAt(Position.at(2, 1)));
        board.setTokenAt(Position.at(2, 1), b);
        assertEquals(b, board.getTokenAt(Position.at(2, 1)));
        board.setTokenAt(Position.at(2, 1), null);
        assertNull(board.getTokenAt(Position.at(2, 1)));
        
        board.setTokenAt(Position.at(0, 0), b);
        assertEquals(b, board.getTokenAt(Position.at(0, 0)));
        board.setTokenAt(Position.at(2, 1), b);
        assertEquals(b, board.getTokenAt(Position.at(2, 1)));
        board.setTokenAt(Position.at(1, 0), a);
        assertEquals(a, board.getTokenAt(Position.at(1, 0)));
        board.setTokenAt(Position.at(1, 2), a);
        assertEquals(a, board.getTokenAt(Position.at(1, 2)));
    }
    
    @Test (expected = BoardDimensionException.class)
    public void setTokenAtInvalidPosition1() {
        new MatchThreeBoard(Token.set("AB"), 3, 3).setTokenAt(Position.at(0, 3), null);
    }
    
    @Test (expected = BoardDimensionException.class)
    public void setTokenAtInvalidPosition2() {
        new MatchThreeBoard(Token.set("AB"), 3, 3).setTokenAt(Position.at(-1, 1), null);
    }
    
    @Test (expected = IllegalTokenException.class)
    public void setTokenAtInvalidToken() {
        new MatchThreeBoard(Token.set("AB"), 3, 3).setTokenAt(Position.at(0, 0), new Token("C"));
    }
    
    @Test
    public void swapTokens() {
        
        Board board = new MatchThreeBoard(Token.set("AB"), 3, 3);
        Token a = new Token("A");
        Token b = new Token("B");

        board.setTokenAt(Position.at(0, 0), a);
        board.setTokenAt(Position.at(1, 1), b);
        assertEquals(a, board.getTokenAt(Position.at(0, 0)));
        assertEquals(b, board.getTokenAt(Position.at(1, 1)));
        
        board.swapTokens(Position.at(0, 0), Position.at(1, 1));
        assertEquals(b, board.getTokenAt(Position.at(0, 0)));
        assertEquals(a, board.getTokenAt(Position.at(1, 1)));
        
        board.swapTokens(Position.at(0, 0), Position.at(1, 1));
        assertEquals(a, board.getTokenAt(Position.at(0, 0)));
        assertEquals(b, board.getTokenAt(Position.at(1, 1)));
        
        board.swapTokens(Position.at(2, 0), Position.at(1, 1));
        assertEquals(a, board.getTokenAt(Position.at(0, 0)));
        assertEquals(b, board.getTokenAt(Position.at(2, 0)));
        assertNull(board.getTokenAt(Position.at(1, 1)));
    }
    
    @Test (expected = BoardDimensionException.class)
    public void swapTokensInvalidPosition1() {
        new MatchThreeBoard(Token.set("AB"), 3, 3).swapTokens(Position.at(-1, 1), Position.at(0, 0));
    }
    
    @Test (expected = BoardDimensionException.class)
    public void swapTokensInvalidPosition2() {
        new MatchThreeBoard(Token.set("AB"), 3, 3).swapTokens(Position.at(3, 1), Position.at(0, 0));
    }
    
    @Test (expected = NullPointerException.class)
    public void swapTokensInvalidPosition3() {
        new MatchThreeBoard(Token.set("AB"), 3, 3).swapTokens(Position.at(0, 1), null);
    }
    
    @Test (expected = NullPointerException.class)
    public void swapTokensInvalidPosition4() {
        new MatchThreeBoard(Token.set("AB"), 3, 3).swapTokens(null, null);
    }
    
    @Test
    public void removeTokensAt() {
        
        Board board = new MatchThreeBoard(Token.set("AB"), "A B;  B; AA");
        
        board.removeTokensAt(Stream.of(
                Position.at(0, 0), Position.at(2, 1), Position.at(1, 0)
                ).collect(Collectors.toSet()));
        assertEquals("  B;   ; AA", board.toTokenString());
        
        board.removeTokensAt(Stream.of(
                Position.at(0, 0), Position.at(2, 1), Position.at(1, 0)
                ).collect(Collectors.toSet()));
        assertEquals("  B;   ; AA", board.toTokenString());
        
        board.removeTokensAt(Stream.of(
                Position.at(2, 0)
                ).collect(Collectors.toSet()));
        assertEquals("   ;   ; AA", board.toTokenString());
        
        board.removeTokensAt(new HashSet<>());
        assertEquals("   ;   ; AA", board.toTokenString());
    }
    
    @Test (expected = NullPointerException.class)
    public void removeTokensAtInvalidPosition1() {
        new MatchThreeBoard(Token.set("AB"), "A B;  B; AA")
            .removeTokensAt(null);
    }
    
    @Test (expected = BoardDimensionException.class)
    public void removeTokensAtInvalidPosition2() {
        new MatchThreeBoard(Token.set("AB"), "A B;  B; AA")
            .removeTokensAt(Stream.of(
                    Position.at(-2, 0)
            ).collect(Collectors.toSet()));
    }
    
    @Test(expected = BoardDimensionException.class)
    public void removeTokensAtInvalidPosition3() {
        MatchThreeBoard board = new MatchThreeBoard(Token.set("AB"), "A B;  B; AA");
        try {
            new MatchThreeBoard(Token.set("AB"), "A B;  B; AA")
                    .removeTokensAt(Stream.of(
                            Position.at(0, 0), Position.at(0, 3)
                    ).collect(Collectors.toSet()));
        } catch (BoardDimensionException e) {
            assertEquals(board.getTokenAt(Position.at(0, 0)), new Token("A"));
            throw e;
        }
    }
    
    @Test (expected = NullPointerException.class)
    public void removeTokensAtInvalidPosition4() {
        new MatchThreeBoard(Token.set("AB"), "A B;  B; AA")
            .removeTokensAt(Stream.of(
                    Position.at(0, 0), Position.at(0, 1), null
            ).collect(Collectors.toSet()));
    }
    
    @Test
    public void moveTokensToBottom1() {
        
        Board board = new MatchThreeBoard(Token.set("AB"), "A B;  B; AA");
        Set<Position> positions = board.moveTokensToBottom();
        
        Set<Position> expected = Stream.of(
                Position.at(0, 0), /* -> */ Position.at(0, 2)
                ).collect(Collectors.toSet());

        assertEquals(expected, positions);
        assertEquals("  B;  B;AAA", board.toTokenString());
    }
    
    @Test
    public void moveTokensToBottom2() {
        
        Board board = new MatchThreeBoard(Token.set("AB"), "ABAB ;A    ;   BA; B  B; A  A");
        Set<Position> positions = board.moveTokensToBottom();
        
        Set<Position> expected = Stream.of(
                Position.at(0, 1), /* -> */ Position.at(0, 4),
                Position.at(0, 0), /* -> */ Position.at(0, 3),
                Position.at(1, 0), /* -> */ Position.at(1, 2),
                Position.at(2, 0), /* -> */ Position.at(2, 4),
                Position.at(3, 2), /* -> */ Position.at(3, 4),
                Position.at(3, 0), /* -> */ Position.at(3, 3)
                ).collect(Collectors.toSet());

        assertEquals(expected, positions);
        assertEquals("     ;     ; B  A;AB BB;AAABA", board.toTokenString());
    }
    
    @Test
    public void moveTokensToBottom3() {
        
        Board board = new MatchThreeBoard(Token.set("ABC"), "ABC ;    ;   A; B B;    ;  A ");
        Set<Position> positions = board.moveTokensToBottom();
        
        Set<Position> expected = Stream.of(
                Position.at(0, 0), /* -> */ Position.at(0, 5),
                Position.at(1, 3), /* -> */ Position.at(1, 5),
                Position.at(1, 0), /* -> */ Position.at(1, 4),
                Position.at(2, 0), /* -> */ Position.at(2, 4),
                Position.at(3, 3), /* -> */ Position.at(3, 5),
                Position.at(3, 2), /* -> */ Position.at(3, 4)
                ).collect(Collectors.toSet());

        assertEquals(expected, positions);
        assertEquals("    ;    ;    ;    ; BCA;ABAB", board.toTokenString());
    }
    
    @Test
    public void fillingStrategy1() {
        
        Board board = new MatchThreeBoard(Token.set("AB"), 3, 3);
        board.setFillingStrategy(new DeterministicStrategy(Token.iterator("AAA"), Token.iterator("ABA"), Token.iterator("BAA")));
        board.fillWithTokens();
        
        assertEquals("AAA;ABA;AAB", board.toTokenString());
    }
    
    @Test
    public void fillingStrategy2() {
        
        Board board = new MatchThreeBoard(Token.set("ABCD"), 4, 4);
        board.setFillingStrategy(new DeterministicStrategy(Token.iterator("BCAD"), Token.iterator("CCCD"), Token.iterator("DADD"), Token.iterator("ABCD")));
        board.fillWithTokens();
        
        assertEquals("DDDD;ACDC;CCAB;BCDA", board.toTokenString()); // back in black
    }
    
    @Test (expected = NoFillingStrategyException.class)
    public void noFillingStrategy() {
        new MatchThreeBoard(Token.set("ABCD"), 4, 4).fillWithTokens();
    }

}
