package edu.kit.informatik.matchthree.tests;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.Token;
import edu.kit.informatik.matchthree.framework.interfaces.Board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * TestUtils as an extension to junit's Assert lib
 *
 * @author Florian Pfisterer
 * Based on templates & inspiration from Micha Hanselmann & IPD Koziolek
 */
public final class TestUtils {

    public static <T> void assertSetEquals(Set<T> expected, Set<T> actual) {
        assert TestUtils.setEquals(expected, actual);
    }

    public static <T> void assertSetOfSetsEquals(Set<Set<T>> expectedSets, Set<Set<T>> actualSets) {

        if (expectedSets.size() != actualSets.size()) {

            System.out.println("EXPECTED: " + expectedSets.stream().map(set -> set.stream().map(Object::toString)
                    .collect(Collectors.joining(", "))).collect(Collectors.joining("; ")));
            System.out.println("ACTUAL: " + actualSets.stream().map(set -> set.stream().map(Object::toString)
                    .collect(Collectors.joining(", "))).collect(Collectors.joining("; ")));

            fail();
        }

        for (Set<T> expected : expectedSets) {
            if (actualSets.stream().noneMatch(actual -> TestUtils.setEquals(expected, actual))) {
                fail();
            }
        }
    }

    public static <T> void assertEmpty(final Collection<T> collection) {
        assertEquals(0, collection.size());
    }

    // adapted from http://stackoverflow.com/questions/11888554/way-to-check-if-two-collections-contain-the-same-elements-independent-of-order
    private static <T> boolean setEquals(Set<T> expected, Set<T> actual) {

        if (expected.size() != actual.size()) {

            System.out.println("EXPECTED: " + expected.stream().map(Object::toString)
                    .collect(Collectors.joining(", ")));
            System.out.println("ACTUAL: " + actual.stream().map(Object::toString)
                    .collect(Collectors.joining(", ")));

            return false;
        }

        for (T object : expected) {
            if (actual.contains(object)) {
                actual.remove(object);
            } else {
                System.out.println("Actual set does not contain " + object.toString());
                return false;
            }
        }

        return true;
    }
    
    /**
     * Returns 'true' if entire {@link Board} is filled, meaning that there is not a
     *   single {@link Position} without a {@link Token}.
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
}
