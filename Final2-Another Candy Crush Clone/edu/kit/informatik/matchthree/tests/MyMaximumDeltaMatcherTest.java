package edu.kit.informatik.matchthree.tests;

import static edu.kit.informatik.matchthree.tests.TestUtils.*;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.MaximumDeltaMatcher;
import edu.kit.informatik.matchthree.framework.Delta;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;

import org.junit.Ignore;
import org.junit.Test;

import java.util.*;

/**
 * Test for the maximum delta matcher
 *
 * @author Florian Pfisterer
 * Based on templates & inspiration from Micha Hanselmann & IPD Koziolek
 */
public class MyMaximumDeltaMatcherTest {

    
    
    @Test
    public void myTest1() {
        final Board board = new MatchThreeBoard(Token.set("ABC"), "BBB;CAB;ABA");
        Delta delta1 = new Delta(1,1);
        Delta delta2 = new Delta(1,-1);
        Set<Delta> deltas = new HashSet<Delta>();
        deltas.add(delta1);
        deltas.add(delta2);
        Set<Position> initial = new HashSet<Position>();
        initial.add(new Position(0,2));
        initial.add(new Position(2,2));
        final Set<Set<Position>> actualMatches = new MaximumDeltaMatcher(deltas).matchAll(board, initial);

        final Set<Set<Position>> expectedMatches = new HashSet<>();
        final Set<Position> match = new HashSet<>(Arrays.asList(
                Position.at(0, 2),
                Position.at(1, 1),
                Position.at(2, 2)
        ));
        expectedMatches.add(match);
        assertSetOfSetsEquals(expectedMatches, actualMatches);
    }
    
    
    @Test
    public void myTest2() {
        final Board board = new MatchThreeBoard(Token.set("ABC"), "BBB;CAB;ABA");
        Delta delta1 = new Delta(1,1);
        Delta delta2 = new Delta(1,-1);
        Set<Delta> deltas = new HashSet<Delta>();
        deltas.add(delta1);
        deltas.add(delta2);
        Set<Position> initial = new HashSet<Position>();
        initial.add(new Position(0,2));
        initial.add(new Position(2,1));
        final Set<Set<Position>> actualMatches = new MaximumDeltaMatcher(deltas).matchAll(board, initial);

        final Set<Set<Position>> expectedMatches = new HashSet<>();
        final Set<Position> match = new HashSet<>(Arrays.asList(
                Position.at(0, 2),
                Position.at(1, 1),
                Position.at(2, 2)
        ));
        expectedMatches.add(match);
        final Set<Position> match2 = new HashSet<>(Arrays.asList(
                Position.at(1, 0),
                Position.at(2, 1),
                Position.at(1, 2)
        ));
        expectedMatches.add(match2);
        assertSetOfSetsEquals(expectedMatches, actualMatches);
    }
    
    @Test
    public void myTest3() {
        final Board board = new MatchThreeBoard(Token.set("ABC"), "B B;CAB;ABA");
        Delta delta1 = new Delta(1,1);
        Delta delta2 = new Delta(1,-1);
        Set<Delta> deltas = new HashSet<Delta>();
        deltas.add(delta1);
        deltas.add(delta2);
        Set<Position> initial = new HashSet<Position>();
        initial.add(new Position(0,2));
        initial.add(new Position(1,0));
        final Set<Set<Position>> actualMatches = new MaximumDeltaMatcher(deltas).matchAll(board, initial);

        Set<Set<Position>> expectedMatches = new HashSet<>();
        final Set<Position> match = new HashSet<>(Arrays.asList(
                Position.at(0, 2),
                Position.at(1, 1),
                Position.at(2, 2)
        ));
        expectedMatches.add(match);
        
        // see Issue #39
//        Set<Position> match2 = new HashSet<Position>();
//        expectedMatches.add(match2);
        assertSetOfSetsEquals(expectedMatches, actualMatches);
    }
    
    @Test
    public void myTest4() {
        final Board board = new MatchThreeBoard(Token.set("ABC"), "B B;CAB;ABA");
        Delta delta1 = new Delta(1,1);
        Delta delta2 = new Delta(1,-1);
        Set<Delta> deltas = new HashSet<Delta>();
        deltas.add(delta1);
        deltas.add(delta2);
        Set<Position> initial = new HashSet<Position>();
        initial.add(new Position(0,2));
        initial.add(new Position(1,0));
        initial.add(new Position(1,2));
        final Set<Set<Position>> actualMatches = new MaximumDeltaMatcher(deltas).matchAll(board, initial);

        Set<Set<Position>> expectedMatches = new HashSet<>();
        final Set<Position> match = new HashSet<>(Arrays.asList(
                Position.at(0, 2),
                Position.at(1, 1),
                Position.at(2, 2)
        ));
        expectedMatches.add(match);
        Set<Position> match2 = new HashSet<Position>();
        expectedMatches.add(match2);
        final Set<Position> match3 = new HashSet<>(Arrays.asList(
                Position.at(1, 2),
                Position.at(2, 1)
        ));
        expectedMatches.add(match3);
        assertSetOfSetsEquals(expectedMatches, actualMatches);
    }
    
    public void myTest5() {
        final Board board = new MatchThreeBoard(Token.set("ABC"), "B B; AB;ABA");
        Delta delta1 = new Delta(1,1);
        Delta delta2 = new Delta(1,-1);
        Set<Delta> deltas = new HashSet<Delta>();
        deltas.add(delta1);
        deltas.add(delta2);
        Set<Position> initial = new HashSet<Position>();
        initial.add(new Position(0,2));
        initial.add(new Position(1,0));
        initial.add(new Position(1,2));
        final Set<Set<Position>> actualMatches = new MaximumDeltaMatcher(deltas).matchAll(board, initial);

        Set<Set<Position>> expectedMatches = new HashSet<>();
        final Set<Position> match = new HashSet<>(Arrays.asList(
                Position.at(0, 2),
                Position.at(1, 1),
                Position.at(2, 2)
        ));
        expectedMatches.add(match);
        Set<Position> match2 = new HashSet<Position>();
        expectedMatches.add(match2);
        final Set<Position> match3 = new HashSet<>(Arrays.asList(
                Position.at(1, 2),
                Position.at(2, 1)
        ));
        expectedMatches.add(match3);
        assertSetOfSetsEquals(expectedMatches, actualMatches);
    }
    
    
}