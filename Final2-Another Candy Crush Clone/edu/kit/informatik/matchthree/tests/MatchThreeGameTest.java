package edu.kit.informatik.matchthree.tests;

import java.util.Arrays;
import java.util.HashSet;
import org.junit.Test;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.MatchThreeGame;
import edu.kit.informatik.matchthree.MaximumDeltaMatcher;
import edu.kit.informatik.matchthree.MoveFactoryImplementation;
import edu.kit.informatik.matchthree.framework.Delta;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Matcher;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

/**
 * Tests for {@link MatchThreeGame}
 *
 * @author Luke Brocke
 */
public class MatchThreeGameTest {
    /**
     * Game#acceptMove(Move) should throw a {@link BoardDimensionException} if the given
     *   {@link Move} cannot be applied
     */
    @Test (expected = BoardDimensionException.class)
    public void moveAcceptExceptionTest() {
        Board board = new MatchThreeBoard(Token.set("AB"), 2, 2);
        
        MoveFactoryImplementation factory = new MoveFactoryImplementation();
        Move flipRight = factory.flipRight(Position.at(1, 0));
        
        Matcher matcher = new MaximumDeltaMatcher(new HashSet<>(Arrays.asList(Delta.dxy(0, 1))));
        
        MatchThreeGame game = new MatchThreeGame(board, matcher);
        game.acceptMove(flipRight);
    }
    
    public void moveAcceptTest() {
        Board board = new MatchThreeBoard(Token.set("AB"), 2, 2);
        
        MoveFactoryImplementation factory = new MoveFactoryImplementation();
        Move flipRight = factory.flipRight(Position.at(0, 0));
        
        Matcher matcher = new MaximumDeltaMatcher(new HashSet<>(Arrays.asList(Delta.dxy(0, 1))));
        
        MatchThreeGame game = new MatchThreeGame(board, matcher);
        game.acceptMove(flipRight);
    }
}
