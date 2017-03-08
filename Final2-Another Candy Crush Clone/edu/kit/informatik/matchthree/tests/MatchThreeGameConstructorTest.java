package edu.kit.informatik.matchthree.tests;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.MatchThreeGame;
import edu.kit.informatik.matchthree.MaximumDeltaMatcher;
import edu.kit.informatik.matchthree.framework.Delta;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Game;
import edu.kit.informatik.matchthree.framework.interfaces.Matcher;

/**
 * Tests for {@link MatchThreeGame} constructor
 *
 * @author IPD Koziolek
 * @author Luke Brocke
 */
public class MatchThreeGameConstructorTest {
    /**
     * Valid {@link MatchThreeGame} test with sample {@link Board} and {@link Matcher}.
     */
    @Test
    public void validMatcherTest() {
        Board board = new MatchThreeBoard(Token.set("XY"), 2, 2);
        Matcher matcher = new MaximumDeltaMatcher(new HashSet<>(Arrays.asList(Delta.dxy(1, 1))));
        
        Game game = new MatchThreeGame(board, matcher);
    }
    
    /**
     * Expecting {@link NullPointerException} because of invalid {@link Matcher}.
     */
    @Test (expected = NullPointerException.class)
    public void invalidMatcherTest() {
        Board board = new MatchThreeBoard(Token.set("XY"), 2, 2);
        
        Game game = new MatchThreeGame(board, null);
    }
}