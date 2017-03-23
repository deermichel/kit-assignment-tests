package edu.kit.informatik.matchthree.tests;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * TestUtils as an extension to junit's Assert lib
 *
 * @author Florian Pfisterer
 *         Based on templates & inspiration from Micha Hanselmann & IPD Koziolek
 */
public final class TestUtils {

    public static <T> void assertSetEquals(Set<T> expected, Set<T> actual) {
        assertTrue(TestUtils.setEquals(expected, actual));
    }

    public static <T> void assertSetOfSetsEquals(Set<Set<T>> expectedSets, Set<Set<T>> actualSets) {

        if (!expectedSets.equals(actualSets)) {

            System.out.println("EXPECTED: " + setToString(actualSets, "; ", TestUtils::setToString));
            System.out.println("ACTUAL: " + setToString(actualSets, "; ", TestUtils::setToString));

            fail();
        }
    }

    public static <T> void assertEmpty(final Collection<T> collection) {
        assertEquals(0, collection.size());
    }

    private static <T> boolean setEquals(Set<T> expected, Set<T> actual) {

        if (!expected.equals(actual)) {

            System.out.println("EXPECTED: " + setToString(expected));
            System.out.println("ACTUAL: " + setToString(actual));

            return false;
        }

        return true;
    }


    private static <T> String setToString(Set<T> actual) {
        return setToString(actual, ", ", Object::toString);
    }

    private static <T> String setToString(Set<T> actual, String delimiter, Function<T, String> function) {
        return actual.stream().map(function).collect(Collectors.joining(delimiter));
    }

    /**
     * Returns 'true' if entire {@link Board} is filled, meaning that there is not a
     * single {@link Position} without a {@link Token}.
     *
     * @param board The board to check
     * @return 'true' if completely filled, 'false' otherwise
     */
    public static boolean boardIsFilled(Board board) {
        for (int i = 0; i < board.getRowCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++) {
                if (board.getTokenAt(Position.at(j, i)) == null)
                    return false;
            }
        }

        return true;
    }

    /**
     * Returns a pretty "unicode-image" of the given board using box-drawing characters,
     * e.g. the board 'ABC;DEF;GHI' will print:
     * <p>
     * ┌───┬───┬───┐
     * │ A │ B │ C │
     * ├───┼───┼───┤
     * │ D │ E │ F │
     * ├───┼───┼───┤
     * │ G │ H │ I │
     * └───┴───┴───┘
     * <p>
     * Save this file with UTF8 encoding, else you'll probably see random characters!
     *
     * @param board Well, the board to print obviously
     * @return A pretty board :)
     * @see https://github.com/luk3b/prog-assignment-tests#pretty-print-for-board
     */
    public static String prettyPrint(Board board) {
        final String TL = "\u250c";             // top left corner
        final String TR = "\u2510";             // top right corner
        final String BR = "\u2518";             // bottom right corner
        final String BL = "\u2514";             // bottom left corner
        final String T0 = "\u252c";             // "T" character
        final String T90 = "\u2524";             // 90° flipped "T"
        final String T180 = "\u2534";             // 180° flipped "T"
        final String T270 = "\u251c";             // 270° flipped "T"
        final String VERT = "\u2500\u2500\u2500"; // vertical bar
        final String HORI = "\u2502";             // horizontal bar
        final String CROS = "\u253c";             // cross

        int width = board.getColumnCount();
        int height = board.getRowCount();
        StringBuilder b = new StringBuilder();

        b.append(TL + VERT);
        for (int i = 1; i < width; i++) b.append(T0 + VERT);
        b.append(TR + "\n");

        for (int y = 0; y < height; y++) {
            b.append(HORI);
            for (int x = 0; x < width; x++) {
                Token t = board.getTokenAt(Position.at(x, y));
                b.append(" " + (t != null ? t : " ") + " ");

                if (x < width - 1)
                    b.append(HORI);
            }
            b.append(HORI + "\n");

            if (y < height - 1) {
                b.append(T270 + VERT);
                for (int h = 1; h < width; h++) b.append(CROS + VERT);
                b.append(T90 + "\n");
            }
        }

        b.append(BL + VERT);
        for (int i = 1; i < width; i++) b.append(T180 + VERT);
        b.append(BR);
        return b.toString();
    }

    public static void assertBoardEquals(Board actual, Board expected) {
        if (!expected.toTokenString().equals(actual.toTokenString())) {
            fail(new StringBuilder().append("The board has changed unexpectedly: \n")
                    .append("EXPECTED: \n")
                    .append(prettyPrint(expected))
                    .append("\n")
                    .append("ACTUAL: \n")
                    .append(prettyPrint(actual))
                    .toString());
        }
    }
}
