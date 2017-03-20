package edu.kit.informatik.matchthree.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.LinkedList;
import org.junit.Test;
import edu.kit.informatik.matchthree.framework.*;
import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.MultiMatcher;
import edu.kit.informatik.matchthree.MatchThreeGame;
import edu.kit.informatik.matchthree.MaximumDeltaMatcher;
import edu.kit.informatik.matchthree.MoveFactoryImplementation;
import edu.kit.informatik.matchthree.framework.Delta;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import edu.kit.informatik.matchthree.framework.interfaces.Game;
import edu.kit.informatik.matchthree.framework.interfaces.Matcher;
import edu.kit.informatik.matchthree.framework.interfaces.Move;
import edu.kit.informatik.matchthree.framework.interfaces.MoveFactory;

/**
 * Tests for {@link MatchThreeGame}
 *
 * @author Vera Chekan
 */
public class MyGameTest {
    
    /**
     * Valid test for {@link Game#acceptMove(Move)}, {@link Game#initializeBoardAndStart()}
     */
    
    @Test
    public void initializeFillBoardTest1() {
        Board board = new MatchThreeBoard(Token.set("ABCD"), "BAB;BAA;BAC");
        Matcher matcher = new MaximumDeltaMatcher(new HashSet<>(Arrays.asList(Delta.dxy(0, 1))));
        DeterministicStrategy d = new DeterministicStrategy();
        LinkedList<Token> tokens0 = new LinkedList<Token>();
        tokens0.add(new Token("C"));
        tokens0.add(new Token("C"));
        tokens0.add(new Token("C"));
        tokens0.add(new Token("D"));
        tokens0.add(new Token("D"));
        tokens0.add(new Token("D"));
        tokens0.add(new Token("A"));
        tokens0.add(new Token("B"));
        tokens0.add(new Token("A"));
        Iterator<Token> it0 = tokens0.iterator();
        d.setTokenIteratorForColumn(0, it0);
        LinkedList<Token> tokens1 = new LinkedList<Token>();
        tokens1.add(new Token("A"));
        tokens1.add(new Token("B"));
        tokens1.add(new Token("A"));
        tokens1.add(new Token("C"));
        tokens1.add(new Token("C"));
        tokens1.add(new Token("C"));
        tokens1.add(new Token("B"));
        tokens1.add(new Token("A"));
        tokens1.add(new Token("A"));
        tokens1.add(new Token("D"));
        tokens1.add(new Token("D"));
        tokens1.add(new Token("D"));
        tokens1.add(new Token("A"));
        tokens1.add(new Token("B"));
        tokens1.add(new Token("A"));
        Iterator<Token> it1 = tokens1.iterator();
        d.setTokenIteratorForColumn(1, it1);
        LinkedList<Token> tokens2 = new LinkedList<Token>();
        tokens2.add(new Token("C"));
        tokens2.add(new Token("A"));
        tokens2.add(new Token("C"));
        Iterator<Token> it2 = tokens2.iterator();
        d.setTokenIteratorForColumn(2, it2);
        board.setFillingStrategy(d);
        MatchThreeGame game = new MatchThreeGame(board, matcher);
        game.initializeBoardAndStart();
        assertTrue(TestUtils.boardIsFilled(board));
        assertEquals(27, game.getScore());
        assertEquals(board.toTokenString(), "AAB;BBA;AAC");
        MoveFactory mf = new MoveFactoryImplementation();
        Move move = mf.flipRight(new Position(1,1));
        game.acceptMove(move.reverse());
        assertTrue(TestUtils.boardIsFilled(board));
        assertEquals(36, game.getScore());
        assertEquals(board.toTokenString(), "AAB;BAB;ABC");
        Move move2 = mf.rotateRowRight(2);
        game.acceptMove(move2);
        assertTrue(TestUtils.boardIsFilled(board));
        assertEquals(54, game.getScore());
        assertEquals(board.toTokenString(), "AAC;BBA;CAC");
    }
    
    /**
     * Test from Praktomat
     */
    
    @Test
    public void initializeFillBoardTest2() {
        Board b = new MatchThreeBoard(Token.set("AXO*"), "O*O;***;O*O;O*O");
        DeterministicStrategy d = new DeterministicStrategy();
        LinkedList<Token> tokens0 = new LinkedList<Token>();
        tokens0.add(new Token("A"));
        tokens0.add(new Token("O"));
        tokens0.add(new Token("A"));
        tokens0.add(new Token("*"));
        tokens0.add(new Token("*"));
        Iterator<Token> it0 = tokens0.iterator();
        d.setTokenIteratorForColumn(0, it0);
        LinkedList<Token> tokens1 = new LinkedList<Token>();
        tokens1.add(new Token("A"));
        tokens1.add(new Token("X"));
        tokens1.add(new Token("A"));
        tokens1.add(new Token("X"));
        tokens1.add(new Token("A"));
        Iterator<Token> it1 = tokens1.iterator();
        d.setTokenIteratorForColumn(1, it1);
        LinkedList<Token> tokens2 = new LinkedList<Token>();
        tokens2.add(new Token("A"));
        tokens2.add(new Token("*"));
        tokens2.add(new Token("*"));
        tokens2.add(new Token("A"));
        tokens2.add(new Token("*"));
        Iterator<Token> it2 = tokens2.iterator();
        d.setTokenIteratorForColumn(2, it2);
        b.setFillingStrategy(d);
        Delta delta1 = new Delta(1,0);
        Set<Delta> deltas1 = new HashSet<Delta>();
        deltas1.add(delta1);
        MaximumDeltaMatcher m1 = new MaximumDeltaMatcher(deltas1);
        Delta delta2 = new Delta(0,1);
        Set<Delta> deltas2 = new HashSet<Delta>();
        deltas2.add(delta2);
        MaximumDeltaMatcher m2 = new MaximumDeltaMatcher(deltas2);
        Matcher m = new MultiMatcher(m1, m2);
        Game g = new MatchThreeGame(b, m);
        g.initializeBoardAndStart();
        assertEquals(b.toTokenString(), "*A*;*XA;AA*;OX*");
        assertEquals(g.getScore(), 49);
    }
    
}