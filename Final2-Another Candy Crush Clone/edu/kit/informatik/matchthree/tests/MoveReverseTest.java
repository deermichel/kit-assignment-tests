package edu.kit.informatik.matchthree.tests;

import edu.kit.informatik.matchthree.MoveFactoryImplementation;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.exceptions.TokenStringParseException;

import edu.kit.informatik.matchthree.framework.interfaces.Move;
import org.junit.Ignore;
import org.junit.Test;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Test for reversing moves
 *
 * @author Florian Pfisterer
 * Based on templates & inspiration from Micha Hanselmann & IPD Koziolek
 */
public class MoveReverseTest {

    @Test
    public void testBasicFlipReverseEquality01() {
        final Move move = new MoveFactoryImplementation().flipRight(Position.at(0, 0));
        final Move reversed = move.reverse();

        final String original = "AB;Cd";
        final Board board = new MatchThreeBoard(Token.set("ABCd"), original);

        assertTrue(move.canBeApplied(board));
        assertTrue(reversed.canBeApplied(board));

        move.apply(board);
        assertEquals("BA;Cd", board.toTokenString());

        reversed.apply(board);
        assertEquals(original, board.toTokenString());

        // flips should be the same as their reverse moves
        reversed.apply(board);
        assertEquals("BA;Cd", board.toTokenString());
    }


    @Test
    public void testRotateSquareReverse01() {

        final Move move = new MoveFactoryImplementation().rotateSquareClockwise(Position.at(1, 1));

        final String original = "ab;cd";
        final Board board = new MatchThreeBoard(Token.set("abcd"), original);

        this.testMoveAgainstReversed(move, original, board);
    }

    @Test
    public void testRotateColumnReverse01() {

        for (int column = 0; column < 5; column += 1) {
            final Move move = new MoveFactoryImplementation().rotateColumnDown(column);

            final String original = "abcde;fghij;klmno;pqrst";
            final Board board = new MatchThreeBoard(Token.set("abcdefghijklmnopqrst"), original);

            this.testMoveAgainstReversed(move, original, board);
        }
    }

    @Test
    public void testRotateRowReverse01() {

        for (int row = 0; row < 7; row += 1) {
            final Move move = new MoveFactoryImplementation().rotateRowRight(row);

            final String original = "ab;cd;ef;gh;hi;jk;lm";
            final Board board = new MatchThreeBoard(Token.set("abcdefghijklm"), original);

            this.testMoveAgainstReversed(move, original, board);
        }
    }

    // -- Helper Method(s)
    /**
     * IMPORTANT: Don't test boards who might be the same after applying a move on them with this method.
     */
    private void testMoveAgainstReversed(final Move move, final String originalTokenString,
                                         final Board board) {

        final Move reversed = move.reverse();

        assertTrue(move.canBeApplied(board));
        assertTrue(reversed.canBeApplied(board));

        move.apply(board);
        reversed.apply(board);
        assertEquals(originalTokenString, board.toTokenString());

        move.apply(board);
        move.apply(board);
        reversed.apply(board);
        reversed.apply(board);
        assertEquals(originalTokenString, board.toTokenString());

        move.apply(board);
        move.apply(board);
        reversed.apply(board);
        assertNotEquals(originalTokenString, board.toTokenString());

        move.apply(board);
        reversed.apply(board);
        assertNotEquals(originalTokenString, board.toTokenString());

        move.apply(board);
        assertNotEquals(originalTokenString, board.toTokenString());

        reversed.apply(board);
        reversed.apply(board);
        assertEquals(originalTokenString, board.toTokenString());
    }
}
