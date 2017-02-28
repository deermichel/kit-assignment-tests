package edu.kit.informatik.matchthree.tests;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

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

        if ( expectedSets.size() != actualSets.size() ) {
            assert false;
        }

        for (Set<T> expected : expectedSets) {
            if (actualSets.stream().noneMatch(actual -> TestUtils.setEquals(expected, actual))) {
                assert false;
            }
        }
    }

    public static <T> void assertEmpty(final Collection<T> collection) {
        assertEquals(0, collection.size());
    }

    // adapted from http://stackoverflow.com/questions/11888554/way-to-check-if-two-collections-contain-the-same-elements-independent-of-order
    private static <T> boolean setEquals(Set<T> expected, Set<T> actual) {

        if (expected.size() != actual.size()) {
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
}
