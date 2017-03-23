package edu.kit.informatik.matchthree.tests;

import edu.kit.informatik.matchthree.MatchThreeBoard;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static edu.kit.informatik.matchthree.tests.TestUtils.assertSetEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test for MatchThreeBoard#moveTokensToBottom
 *
 * @author Florian Pfisterer
 *         Based on templates & inspiration from Micha Hanselmann & IPD Koziolek
 */
public class BoardMoveTokensToBottomTest {

    @Test
    public void testEmptyBoardNoChanges() {
        this.testNoChangesForTokenString("  ;  ;  ");
        this.testNoChangesForTokenString("        ;        ;        ;        ;        ;        ;        ");
    }

    @Test
    public void testFullBoardNoChanges() {
        this.testNoChangesForTokenString("abc;abc;abc");
        this.testNoChangesForTokenString("aaaaaaaaaaaaaaa;bbbbbbbbbbbbbbb;ccccccccccccccc");
    }

    @Test
    public void testBottomFilledBoardNoChanges() {
        // playing Tetris in my mind here
        this.testNoChangesForTokenString("   a;a bb;cccc;cccc");
        this.testNoChangesForTokenString("    ;    ;    ;    ;a   ;a   ;a  b;a  b");
        this.testNoChangesForTokenString("a ;a ;ab;ab;ab;cc");
        this.testNoChangesForTokenString("    ; a  ; b  ;cc  ;ab a;abcc");
    }

    @Test
    public void testBoardMoveToBottom01() {

        final Board board = new MatchThreeBoard(Token.set("abc"), "aa;  ");

        final Position[] positions
                = new Position[]{Position.at(0, 0), Position.at(0, 1), Position.at(1, 0), Position.at(1, 1)};
        final Set<Position> expectedUpdatedPositions = new HashSet<>(Arrays.asList(positions));

        assertSetEquals(expectedUpdatedPositions, board.moveTokensToBottom());
        TestUtils.assertBoardEquals(board, new MatchThreeBoard(Token.set("abc"), "  ;aa"));
    }

    @Test
    public void testBoardMoveToBottom02() {

        final Board board = new MatchThreeBoard(Token.set("abcd"), "   ;abc;ab ;a  ");

        final Position[] positions = new Position[]
                {Position.at(1, 1), Position.at(2, 1), Position.at(1, 3), Position.at(2, 3), Position.at(1, 2)};
        final Set<Position> expectedUpdatedPositions = new HashSet<>(Arrays.asList(positions));

        assertSetEquals(expectedUpdatedPositions, board.moveTokensToBottom());
        TestUtils.assertBoardEquals(board, new MatchThreeBoard(Token.set("abcd"), "   ;a  ;ab ;abc"));
    }

    @Test
    public void testBoardMoveToBottom03() {

        // example from the task
        final Board board = new MatchThreeBoard(Token.set("A+*Y"), "A AA;++  ; *A*;Y  Y");

        final Position[] positions = new Position[]
                {Position.at(0, 0), Position.at(2, 0), Position.at(3, 0), Position.at(0, 1), Position.at(1, 1),
                        Position.at(3, 1), Position.at(0, 2), Position.at(1, 2), Position.at(2, 2), Position.at(1, 3),
                        Position.at(2, 3)};
        final Set<Position> expectedUpdatedPositions = new HashSet<>(Arrays.asList(positions));

        assertSetEquals(expectedUpdatedPositions, board.moveTokensToBottom());
        TestUtils.assertBoardEquals(board, new MatchThreeBoard(Token.set("A+*Y"), "    ;A  A;++A*;Y*AY"));
    }

    @Test
    public void testBoardMoveToBottom04() {

        final Board board = new MatchThreeBoard(Token.set("abcd"), "abcd;    ;a c ;    ");

        final Position[] positions = new Position[]
                {Position.at(0, 0), Position.at(1, 0), Position.at(2, 0), Position.at(3, 0),
                        Position.at(0, 2), Position.at(2, 2), Position.at(0, 3), Position.at(1, 3), Position.at(2, 3),
                        Position.at(3, 3)};
        final Set<Position> expectedUpdatedPositions = new HashSet<>(Arrays.asList(positions));

        assertSetEquals(expectedUpdatedPositions, board.moveTokensToBottom());
        TestUtils.assertBoardEquals(board, new MatchThreeBoard(Token.set("abcd"), "    ;    ;a c ;abcd"));
    }

    @Test
    public void testBoardMoveToBottom05() {

        final Board board = new MatchThreeBoard(Token.set("ab"), "ab;  ;  ;  ;  ;  ;  ; b;  ;  ;  ;  ;  ");

        final Position[] positions = new Position[]
                {Position.at(0, 0), Position.at(1, 0), Position.at(1, 7),
                        Position.at(1, 11), Position.at(0, 12), Position.at(1, 12)};
        final Set<Position> expectedUpdatedPositions = new HashSet<>(Arrays.asList(positions));

        assertSetEquals(expectedUpdatedPositions, board.moveTokensToBottom());
        TestUtils.assertBoardEquals(board, new MatchThreeBoard(Token.set("ab"), "  ;  ;  ;  ;  ;  ;  ;  ;  ;  ;  ; b;ab"));
    }

    // -- Helpers

    /**
     * IMPORTANT: only use the tokens a, b, c (or empty) in the tokenString (or expect an exception!)
     */
    private void testNoChangesForTokenString(final String tokenString) {
        Set<Token> tokens = Token.set("abc");
        final Board actual = new MatchThreeBoard(tokens, tokenString);
        final Board expected = new MatchThreeBoard(tokens, tokenString);

        Set<Position> changedPositions = actual.moveTokensToBottom();
        if (!changedPositions.isEmpty())
            fail("The set of changed positions of Board.moveToBottom() was not empty!");
        TestUtils.assertBoardEquals(actual, expected);
    }




}
