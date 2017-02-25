package edu.kit.informatik.matchthree.tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.MoveFactoryImplementation;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

/**
 * Tests for flip right move
 * 
 * @author Micha Hanselmann
 */
public class MoveFlipRightTest {

    @Test
    public void canBeApplied1() {
        
        Move flipRight = new MoveFactoryImplementation().flipRight(Position.at(2, 2));
        
        assertFalse(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 2, 2)));
        assertFalse(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 2, 5)));
        assertFalse(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 3, 2)));
        assertFalse(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 4, 2)));
        assertTrue(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 4, 3)));
        assertTrue(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 4, 5)));
    }

    @Test
    public void canBeApplied2() {
        
        Move flipRight = new MoveFactoryImplementation().flipRight(Position.at(0, 2));

        assertFalse(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 4, 2)));
        assertTrue(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 2, 3)));
        assertTrue(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 2, 5)));
        assertTrue(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 3, 3)));
        assertTrue(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 4, 3)));
        assertTrue(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 4, 5)));
    }

    @Test
    public void canBeApplied3() {
        
        Move flipRight = new MoveFactoryImplementation().flipRight(Position.at(-1, 2));

        assertFalse(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 4, 2)));
        assertFalse(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 2, 3)));
        assertFalse(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 2, 5)));
        assertFalse(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 3, 3)));
        assertFalse(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 4, 3)));
        assertFalse(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 4, 5)));
    }

    @Test (expected = BoardDimensionException.class)
    public void applyException() {
        
        Move flipRight = new MoveFactoryImplementation().flipRight(Position.at(0, 2));
        flipRight.apply(new MatchThreeBoard(Token.set("AB"), 4, 2));
    }
    
    @Test
    public void apply1() {
        
        Board board = new MatchThreeBoard(Token.set("AB"), 4, 4);
        Move flipRight = new MoveFactoryImplementation().flipRight(Position.at(0, 2));
        
        assertEquals("    ;    ;    ;    ", board.toTokenString());
        flipRight.apply(board);
        assertEquals("    ;    ;    ;    ", board.toTokenString());
        flipRight.apply(board);
        assertEquals("    ;    ;    ;    ", board.toTokenString());
        
        board.setTokenAt(Position.at(1, 2), new Token("A"));
        assertEquals("    ;    ; A  ;    ", board.toTokenString());
        flipRight.apply(board);
        assertEquals("    ;    ;A   ;    ", board.toTokenString());
        flipRight.apply(board);
        assertEquals("    ;    ; A  ;    ", board.toTokenString());
        
        board.setTokenAt(Position.at(0, 2), new Token("B"));
        assertEquals("    ;    ;BA  ;    ", board.toTokenString());
        flipRight.apply(board);
        assertEquals("    ;    ;AB  ;    ", board.toTokenString());
        flipRight.apply(board);
        assertEquals("    ;    ;BA  ;    ", board.toTokenString());
    }
    
    @Test
    public void apply2() {
        
        Board board = new MatchThreeBoard(Token.set("AB"), 3, 3);
        Move flipRight1 = new MoveFactoryImplementation().flipRight(Position.at(0, 1));
        Move flipRight2 = new MoveFactoryImplementation().flipRight(Position.at(1, 1));
        
        assertEquals("   ;   ;   ", board.toTokenString());
        flipRight1.apply(board);
        assertEquals("   ;   ;   ", board.toTokenString());
        flipRight2.apply(board);
        assertEquals("   ;   ;   ", board.toTokenString());

        board.setTokenAt(Position.at(0, 1), new Token("A"));
        board.setTokenAt(Position.at(2, 1), new Token("B"));
        assertEquals("   ;A B;   ", board.toTokenString());
        flipRight1.apply(board);
        flipRight2.apply(board);
        assertEquals("   ; BA;   ", board.toTokenString());
        flipRight1.apply(board);
        assertEquals("   ;B A;   ", board.toTokenString());
    }
    
    @Test
    public void reverse() {
        
        Board board = new MatchThreeBoard(Token.set("AB"), 4, 4);
        Move flipRight = new MoveFactoryImplementation().flipRight(Position.at(0, 2));
        Move reverse = flipRight.reverse();
        
        assertEquals("    ;    ;    ;    ", board.toTokenString());
        flipRight.apply(board);
        assertEquals("    ;    ;    ;    ", board.toTokenString());
        reverse.apply(board);
        assertEquals("    ;    ;    ;    ", board.toTokenString());
        
        board.setTokenAt(Position.at(1, 2), new Token("A"));
        assertEquals("    ;    ; A  ;    ", board.toTokenString());
        flipRight.apply(board);
        assertEquals("    ;    ;A   ;    ", board.toTokenString());
        flipRight.apply(board);
        assertEquals("    ;    ; A  ;    ", board.toTokenString());
        reverse.apply(board);
        assertEquals("    ;    ;A   ;    ", board.toTokenString());
        reverse.apply(board);
        assertEquals("    ;    ; A  ;    ", board.toTokenString());
        
        board.setTokenAt(Position.at(0, 2), new Token("B"));
        assertEquals("    ;    ;BA  ;    ", board.toTokenString());
        flipRight.apply(board);
        assertEquals("    ;    ;AB  ;    ", board.toTokenString());
        flipRight.apply(board);
        assertEquals("    ;    ;BA  ;    ", board.toTokenString());
        reverse.apply(board);
        assertEquals("    ;    ;AB  ;    ", board.toTokenString());
        reverse.apply(board);
        assertEquals("    ;    ;BA  ;    ", board.toTokenString());
        
        flipRight.apply(board);
        reverse.apply(board);
        assertEquals("    ;    ;BA  ;    ", board.toTokenString());
    }
    
    @Test
    public void getAffectedPositions1() {

        Board board = new MatchThreeBoard(Token.set("AB"), 3, 3);
        Move flipRight = new MoveFactoryImplementation().flipRight(Position.at(0, 2));
        
        // i have to be careful ;)
        
        Set<Position> p = flipRight.getAffectedPositions(board);
        assertEquals(p.size(), 2);
        assertTrue(p.contains(Position.at(0, 2)));
        assertTrue(p.contains(Position.at(1, 2)));
    }
    
    @Test
    public void getAffectedPositions2() {

        Board board = new MatchThreeBoard(Token.set("AB"), 4, 4);
        Move flipRight = new MoveFactoryImplementation().flipRight(Position.at(2, 2));
        
        Set<Position> p = flipRight.getAffectedPositions(board);
        assertEquals(p.size(), 2);
        assertTrue(p.contains(Position.at(2, 2)));
        assertTrue(p.contains(Position.at(3, 2)));
    }
    
    @Ignore("throw new IliasPostException - nobody knows the trouble i've seen?")
    @Test (expected = BoardDimensionException.class)
    public void getAffectedPositionsBoardDimException() {

        Board board = new MatchThreeBoard(Token.set("AB"), 3, 3);
        Move flipRight = new MoveFactoryImplementation().flipRight(Position.at(2, 2));
        flipRight.getAffectedPositions(board);
    }

}
