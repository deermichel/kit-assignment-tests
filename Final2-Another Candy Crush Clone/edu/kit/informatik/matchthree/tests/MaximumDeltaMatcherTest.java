package edu.kit.informatik.matchthree.tests;

import static edu.kit.informatik.matchthree.tests.TestUtils.*;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.MaximumDeltaMatcher;
import edu.kit.informatik.matchthree.framework.Delta;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import org.junit.Test;

import java.util.*;

/**
 * Test for the maximum delta matcher
 *
 * @author Florian Pfisterer
 * Based on templates & inspiration from Micha Hanselmann & IPD Koziolek
 */
public class MaximumDeltaMatcherTest {

    @Test
    public void testDeltaMatchAbb14() {

        final Board board = new MatchThreeBoard(Token.set("An"), "An;An;AA");
        final Set<Delta> deltas = new HashSet<>(Collections.singletonList(Delta.dxy(0, 1)));

        final Set<Set<Position>> actualMatches = new MaximumDeltaMatcher(deltas).match(board, Position.at(0, 2));

        final Set<Set<Position>> expectedMatches = new HashSet<>();
        final Set<Position> match = new HashSet<>(Arrays.asList(
                Position.at(0, 0),
                Position.at(0, 1),
                Position.at(0, 2)
        ));
        expectedMatches.add(match);

        assertSetOfSetsEquals(expectedMatches, actualMatches);
    }

    @Test
    public void testDeltaMatchAbb15() {

        final Board board = new MatchThreeBoard(Token.set("nO"), "nOO;OOO;nnO");
        final Set<Delta> deltas = new HashSet<>(Collections.singletonList(Delta.dxy(0, -1)));

        final Set<Set<Position>> actualMatches = new MaximumDeltaMatcher(deltas).match(board, Position.at(2, 1));

        final Set<Set<Position>> expectedMatches = new HashSet<>();
        final Set<Position> match = new HashSet<>(Arrays.asList(
                Position.at(2, 0),
                Position.at(2, 1),
                Position.at(2, 2)
        ));
        expectedMatches.add(match);

        assertSetOfSetsEquals(expectedMatches, actualMatches);
    }

    @Test
    public void testDeltaMatchAbb16() {

        final Board board = new MatchThreeBoard(Token.set("nO"), "nOO;OOO;nnO");
        final Set<Delta> deltas = new HashSet<>(Collections.singletonList(Delta.dxy(1, 0)));

        final Set<Set<Position>> actualMatches = new MaximumDeltaMatcher(deltas).match(board, Position.at(2, 1));

        final Set<Set<Position>> expectedMatches = new HashSet<>();
        final Set<Position> match = new HashSet<>(Arrays.asList(
                Position.at(0, 1),
                Position.at(1, 1),
                Position.at(2, 1)
        ));
        expectedMatches.add(match);

        assertSetOfSetsEquals(expectedMatches, actualMatches);
    }

    @Test
    public void testDeltaMatchAbb17() {

        final Board board = new MatchThreeBoard(Token.set("nO*"), "nOO;O*O;nnO");
        final Set<Delta> deltas = new HashSet<>(Arrays.asList(Delta.dxy(1, 1), Delta.dxy(1, -1)));

        final Set<Set<Position>> actualMatches = new MaximumDeltaMatcher(deltas).match(board, Position.at(2, 1));

        final Set<Set<Position>> expectedMatches = new HashSet<>();
        final Set<Position> match = new HashSet<>(Arrays.asList(
                Position.at(0, 1),
                Position.at(1, 0),
                Position.at(2, 1)
        ));
        expectedMatches.add(match);

        assertSetOfSetsEquals(expectedMatches, actualMatches);
    }

    @Test
    public void testDeltaMatchAbb18() {

        final Board board = new MatchThreeBoard(Token.set("nO"), "nOO;OOO;nnO");
        final Set<Delta> deltas = new HashSet<>(Arrays.asList(Delta.dxy(1, 0), Delta.dxy(0, 1)));

        final Set<Set<Position>> actualMatches = new MaximumDeltaMatcher(deltas).match(board, Position.at(2, 1));

        final Set<Set<Position>> expectedMatches = new HashSet<>();
        final Set<Position> match = new HashSet<>(Arrays.asList(
                Position.at(0, 1),
                Position.at(1, 0),
                Position.at(1, 1),
                Position.at(2, 0),
                Position.at(2, 1),
                Position.at(2, 2)
        ));
        expectedMatches.add(match);

        assertSetOfSetsEquals(expectedMatches, actualMatches);
    }

    @Test
    public void testDeltaMatchAbb19() {

        final Board board = new MatchThreeBoard(Token.set("nO"), "O ;n ;O ;n ;O ");
        final Set<Delta> deltas = new HashSet<>(Collections.singletonList(Delta.dxy(0, -2)));

        final Set<Set<Position>> actualMatches = new MaximumDeltaMatcher(deltas).match(board, Position.at(0, 0));

        final Set<Set<Position>> expectedMatches = new HashSet<>();
        final Set<Position> match = new HashSet<>(Arrays.asList(
                Position.at(0, 0),
                Position.at(0, 2),
                Position.at(0, 4)
        ));
        expectedMatches.add(match);

        assertSetOfSetsEquals(expectedMatches, actualMatches);
    }

    // -- Own Tests (specifically testing cases where not all reachable positions magically have the same token)
    @Test
    public void testDeltaMatch01() {

        final Board board = new MatchThreeBoard(Token.set("ABC"), "ACAC;CACC;ACBC;ACAA");
        final Set<Delta> deltas = new HashSet<>(Arrays.asList(
                Delta.dxy(2, 0),
                Delta.dxy(0, 2),
                Delta.dxy(1, 1)
        ));

        final Position initial = Position.at(0, 0);
        final Set<Set<Position>> actualMatches = new MaximumDeltaMatcher(deltas).match(board, initial);

        final Set<Set<Position>> expectedMatches = new HashSet<>();
        final Set<Position> match = new HashSet<>(Arrays.asList(
                Position.at(0, 0),
                Position.at(0, 2),
                Position.at(2, 0),
                Position.at(1, 1)
        ));
        expectedMatches.add(match);

        assertSetOfSetsEquals(expectedMatches, actualMatches);
    }

    @Test
    public void testDeltaMatch02() {

        final Board board = new MatchThreeBoard(Token.set("ABC*"), "A*A*;*A*C;ABBB;AACA");
        final Set<Delta> deltas = new HashSet<>(Arrays.asList(
                Delta.dxy(2, 0),
                Delta.dxy(-1, -1)
        ));

        final Position initial = Position.at(1, 1);
        final Set<Set<Position>> actualMatches = new MaximumDeltaMatcher(deltas).match(board, initial);

        final Set<Set<Position>> expectedMatches = new HashSet<>();
        final Set<Position> match = new HashSet<>(Arrays.asList(
                Position.at(1, 1),
                Position.at(0, 0),
                Position.at(2, 0)
        ));
        expectedMatches.add(match);

        assertSetOfSetsEquals(expectedMatches, actualMatches);
    }

    /**
     * this test ensures that the matcher works with very large boards that
     * contain a very large match (in this case, a board with 5000 tokens that
     * all match)
     */
    @Test(timeout = 5000)
    public void testDeltaMatchLargeBoard1() {

        // used to build a huge-ass token string
        StringBuilder tokenString = new StringBuilder();

        for (int i = 1; i < 5000; ++i) {
            tokenString.append("A");
            if (i % 500 == 0) {
                tokenString.append(';');
            }
        }
        tokenString.append("A");

        final Board board = new MatchThreeBoard(Token.set("AB"), tokenString.toString());
        final Set<Delta> deltas = new HashSet<>(Arrays.asList(Delta.dxy(1, 0), Delta.dxy(0, 1)));

        new MaximumDeltaMatcher(deltas).match(board, Position.at(0, 0));
    }

    /**
     * this test ensures that the matcher works somewhat efficiently. Although
     * the board is very large, test should be very quick as only one token is
     * matched.
     */
    @Test(timeout = 2000)
    public void testDeltaMatchLargeBoard2() {

        // used to build a huge-ass token string
        StringBuilder tokenString = new StringBuilder();

        for (int i = 1; i < 10000; ++i) {
            tokenString.append("B");
            if (i % 1000 == 0) {
                tokenString.append(';');
            }
        }
        tokenString.append("A");

        final Board board = new MatchThreeBoard(Token.set("AB"), tokenString.toString());
        final Set<Delta> deltas = new HashSet<>(Arrays.asList(Delta.dxy(1, 0), Delta.dxy(0, 1)));
        
        new MaximumDeltaMatcher(deltas).match(board, Position.at(999, 9));
    }
}