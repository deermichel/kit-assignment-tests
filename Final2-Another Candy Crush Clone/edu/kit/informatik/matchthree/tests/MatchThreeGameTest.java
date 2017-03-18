package edu.kit.informatik.matchthree.tests;

import static org.junit.Assert.*;

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
import edu.kit.informatik.matchthree.framework.interfaces.Game;
import edu.kit.informatik.matchthree.framework.interfaces.Matcher;
import edu.kit.informatik.matchthree.framework.interfaces.Move;

/**
 * Tests for {@link MatchThreeGame}
 *
 * @author Luke Brocke
 */
public class MatchThreeGameTest {
    /**
     * {@link Game#acceptMove(Move)} should throw a {@link BoardDimensionException} if the given
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
    
    /**
     * Valid test for {@link Game#acceptMove(Move)}
     */
    @Test
    public void moveAcceptTest() {
        Board board = new MatchThreeBoard(Token.set("AB"), 2, 2);
        
        MoveFactoryImplementation factory = new MoveFactoryImplementation();
        Move flipRight = factory.flipRight(Position.at(0, 0));
        
        Matcher matcher = new MaximumDeltaMatcher(new HashSet<>(Arrays.asList(Delta.dxy(0, 1))));
        
        MatchThreeGame game = new MatchThreeGame(board, matcher);
        game.acceptMove(flipRight);
    }
    
    /**
     * Initial score of a new {@link MatchThreeGame} is 0.
     *
     * As sb pointed out, the score could be > 0 after initialization because
     *   some matches could have already been found after the random fill
     */
    @Ignore
    @Test
    public void initializeScoreTest() {
        Board board = new MatchThreeBoard(Token.set("AB"), 5, 5);
        Matcher matcher = new MaximumDeltaMatcher(new HashSet<>(Arrays.asList(Delta.dxy(0, 1))));
        
        MatchThreeGame game = new MatchThreeGame(board, matcher);
        game.initializeBoardAndStart();
        
        assertEquals(0, game.getScore());
    }
    
    /**
     * {@link Board} should get filled entirely on initialization.
     */
    @Test
    public void initializeFillBoardTest() {
        Board board = new MatchThreeBoard(Token.set("AB"), 5, 5);
        Matcher matcher = new MaximumDeltaMatcher(new HashSet<>(Arrays.asList(Delta.dxy(0, 1))));
        
        MatchThreeGame game = new MatchThreeGame(board, matcher);
        game.initializeBoardAndStart();
        
        assertTrue(TestUtils.boardIsFilled(board));
    }
}
