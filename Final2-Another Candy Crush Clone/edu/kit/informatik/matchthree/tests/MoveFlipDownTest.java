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
 * Tests for flip down move
 * 
 * @author Micha Hanselmann
 */
public class MoveFlipDownTest {

    @Test
    public void canBeApplied() {
        
        Move flipRight = new MoveFactoryImplementation().flipDown(Position.at(1, 1));
        
        assertFalse(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 2, 2)));
        assertTrue(flipRight.canBeApplied(new MatchThreeBoard(Token.set("AB"), 2, 3)));
    }

    @Test (expected = BoardDimensionException.class)
    public void applyException() {
        
        Move flipDown = new MoveFactoryImplementation().flipDown(Position.at(0, 1));
        flipDown.apply(new MatchThreeBoard(Token.set("AB"), 2, 2));
    }
    
    @Test
    public void applyAndReverse() {
        
        Board board = new MatchThreeBoard(Token.set("AB"), 4, 4);
        Move flipDown = new MoveFactoryImplementation().flipDown(Position.at(1, 2));
        
        assertEquals("    ;    ;    ;    ", board.toTokenString());
        flipDown.apply(board);
        assertEquals("    ;    ;    ;    ", board.toTokenString());
        flipDown.apply(board);
        assertEquals("    ;    ;    ;    ", board.toTokenString());
        
        board.setTokenAt(Position.at(1, 2), new Token("A"));
        assertEquals("    ;    ; A  ;    ", board.toTokenString());
        flipDown.apply(board);
        assertEquals("    ;    ;    ; A  ", board.toTokenString());
        flipDown.apply(board);
        assertEquals("    ;    ; A  ;    ", board.toTokenString());
        flipDown.reverse().apply(board);
        assertEquals("    ;    ;    ; A  ", board.toTokenString());
        flipDown.reverse().reverse().apply(board);
        assertEquals("    ;    ; A  ;    ", board.toTokenString());
        
        board.setTokenAt(Position.at(1, 3), new Token("B"));
        assertEquals("    ;    ; A  ; B  ", board.toTokenString());
        flipDown.apply(board);
        assertEquals("    ;    ; B  ; A  ", board.toTokenString());
        flipDown.reverse().apply(board);
        assertEquals("    ;    ; A  ; B  ", board.toTokenString());
    }
    
    @Test
    public void getAffectedPositions() {

        Board board = new MatchThreeBoard(Token.set("AB"), 3, 3);
        Move flipDown = new MoveFactoryImplementation().flipDown(Position.at(0, 2));
        
        Set<Position> p = flipDown.getAffectedPositions(board);
        assertEquals(p.size(), 2);
        assertTrue(p.contains(Position.at(0, 2)));
        assertTrue(p.contains(Position.at(0, 3)));
    }
    
    @Ignore("throw new IliasPostException - same procedure as every year")
    @Test (expected = BoardDimensionException.class)
    public void getAffectedPositionsBoardDimException() {

        Board board = new MatchThreeBoard(Token.set("AB"), 3, 3);
        Move flipDown = new MoveFactoryImplementation().flipDown(Position.at(2, 2));
        flipDown.getAffectedPositions(board);
    }

}
