package edu.kit.informatik.matchthree.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.MatchThreeGame;
import edu.kit.informatik.matchthree.MaximumDeltaMatcher;
import edu.kit.informatik.matchthree.MoveFactoryImplementation;
import edu.kit.informatik.matchthree.framework.Delta;
import edu.kit.informatik.matchthree.framework.DeterministicStrategy;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.RandomStrategy;
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
    // A FillingStrategy will be set for the board, the MatchThreeGame constructor MUST NOT do that
    // see https://ilias.studium.kit.edu/ilias.php?ref_id=583580&cmdClass=ilobjforumgui&thr_pk=85450&cmd=viewThread&cmdNode=75:r6&baseClass=ilrepositorygui
    
    // quick and dirty iterator for DeterministicStrategy
    private class RepeatingToken implements Iterator<Token> {
        private char c;
        
        public RepeatingToken(char c) {
            this.c = c;
        }
        
        @Override
        public boolean hasNext() {
            return true;
        }
        
        @Override
        public Token next() {
            return new Token(this.c);
        }  
    }
    
    /**
     * {@link Game#acceptMove(Move)} should throw a {@link BoardDimensionException} if the given
     *   {@link Move} cannot be applied
     */
    @Test (expected = BoardDimensionException.class)
    public void moveAcceptExceptionTest() {
        Board board = new MatchThreeBoard(Token.set("AB"), 2, 2);
        board.setFillingStrategy(new RandomStrategy());
        
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
        board.setFillingStrategy(new RandomStrategy());
        
        MoveFactoryImplementation factory = new MoveFactoryImplementation();
        Move flipRight = factory.flipRight(Position.at(0, 0));
        
        Matcher matcher = new MaximumDeltaMatcher(new HashSet<>(Arrays.asList(Delta.dxy(0, 1))));
        
        MatchThreeGame game = new MatchThreeGame(board, matcher);
        game.acceptMove(flipRight);
    }
    
    /**
     * {@link Board} should get filled entirely on initialization.
     */
    @Test
    public void initializeFillBoardTest() {
        Board board = new MatchThreeBoard(Token.set("AB"), 5, 5);
        board.setFillingStrategy(new RandomStrategy());
        Matcher matcher = new MaximumDeltaMatcher(new HashSet<>(Arrays.asList(Delta.dxy(0, 1))));
        
        MatchThreeGame game = new MatchThreeGame(board, matcher);
        game.initializeBoardAndStart();
        
        assertTrue(TestUtils.boardIsFilled(board));
    }
    
    @Test
    public void initializeMatchAndScoreTest() {
        Board board = new MatchThreeBoard(Token.set("ABCDX"), "ADBC;AABC;BBCD;CABC");
        
        DeterministicStrategy strat = new DeterministicStrategy();
        strat.setTokenIteratorForColumn(0, new RepeatingToken('D'));
        strat.setTokenIteratorForColumn(1, new RepeatingToken('A'));
        strat.setTokenIteratorForColumn(2, new RepeatingToken('X'));
        strat.setTokenIteratorForColumn(3, new RepeatingToken('X'));
        board.setFillingStrategy(strat);
        
        Set<Delta> deltas = new HashSet<>();
        deltas.add(Delta.dxy(1, 0));
        deltas.add(Delta.dxy(0, 1));
        Matcher matcher = new MaximumDeltaMatcher(deltas);
        
        Game game = new MatchThreeGame(board, matcher);
        game.initializeBoardAndStart();
        
        // tokenString of board should be
        //   DABC;DABC;BBCD;CABC
        // now
        
        assertEquals(9, game.getScore());
    }
}
