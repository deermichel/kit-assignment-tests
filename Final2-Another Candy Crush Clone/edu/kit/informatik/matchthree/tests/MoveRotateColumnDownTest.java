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
 * Tests for rotate column down move
 * 
 * @author Micha Hanselmann
 */
public class MoveRotateColumnDownTest {
    
    @Test
    public void apply() {
        
        Board board = new MatchThreeBoard(Token.set("*AXY="), "*AX;Y**;X*=");
        Move rotateCol = new MoveFactoryImplementation().rotateColumnDown(0);
        assertTrue(rotateCol.canBeApplied(board));
        rotateCol.apply(board);
        assertEquals("XAX;***;Y*=", board.toTokenString());
    }

    @Test
    public void applyWithEmpties() {

        Board board = new MatchThreeBoard(Token.set("*AXY="), " AX;Y**; *=");
        Move rotateCol = new MoveFactoryImplementation().rotateColumnDown(0);
        assertTrue(rotateCol.canBeApplied(board));
        rotateCol.apply(board);
        assertEquals(" AX; **;Y*=", board.toTokenString());
    }
    
    @Test
    public void reverseApply() {
        
        Board board = new MatchThreeBoard(Token.set("*AXY="), "XAX;***;Y*=");
        Move rotateColUp = new MoveFactoryImplementation().rotateColumnDown(0).reverse();
        assertTrue(rotateColUp.canBeApplied(board));
        rotateColUp.apply(board);
        assertEquals("*AX;Y**;X*=", board.toTokenString());
    }

    @Test
    public void reverseApplyWithEmpties() {

        Board board = new MatchThreeBoard(Token.set("*AXY="), " AX; **;Y*=");
        Move rotateColUp = new MoveFactoryImplementation().rotateColumnDown(0).reverse();
        assertTrue(rotateColUp.canBeApplied(board));
        rotateColUp.apply(board);
        assertEquals(" AX;Y**; *=", board.toTokenString());
    }

}
