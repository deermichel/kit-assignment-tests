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
 * Tests for rotate row right move
 * 
 * @author Micha Hanselmann
 */
public class MoveRotateRowRightTest {
    
    @Test
    public void apply() {
        
        Board board = new MatchThreeBoard(Token.set("*AXY="), "*AX;Y**;X*=");
        Move rotateRow = new MoveFactoryImplementation().rotateRowRight(0);
        assertTrue(rotateRow.canBeApplied(board));
        rotateRow.apply(board);
        assertEquals("X*A;Y**;X*=", board.toTokenString());
    }
    
    @Test
    public void reverseApply() {
        
        Board board = new MatchThreeBoard(Token.set("*AXY="), "X*A;Y**;X*=");
        Move rotateRowLeft = new MoveFactoryImplementation().rotateRowRight(0).reverse();
        assertTrue(rotateRowLeft.canBeApplied(board));
        rotateRowLeft.apply(board);
        assertEquals("*AX;Y**;X*=", board.toTokenString());
    }

}
