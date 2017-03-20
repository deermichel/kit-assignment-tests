package edu.kit.informatik.matchthree;

import java.util.Set;
import java.util.HashSet;
import edu.kit.informatik.matchthree.framework.Position;
import edu.kit.informatik.matchthree.framework.exceptions.BoardDimensionException;
import edu.kit.informatik.matchthree.framework.interfaces.*;

/**
 * Multimatcher-class, which is used in Praktomat for testing
 *
 * @author Vera Chekan
 * Based on templates & inspiration from Micha Hanselmann & IPD Koziolek
 */

public class MultiMatcher implements Matcher{
    private MaximumDeltaMatcher matcher1;
    private MaximumDeltaMatcher matcher2;
    public MultiMatcher(MaximumDeltaMatcher matcher1, MaximumDeltaMatcher matcher2) {
        this.matcher1 = matcher1;
        this.matcher2 = matcher2;
    }
    
    public Set<Set<Position>> match(Board board, Position initial) throws BoardDimensionException {
        Set<Set<Position>> matchers = new HashSet<Set<Position>>();
        matchers.addAll(this.matcher1.match(board, initial));
        matchers.addAll(this.matcher2.match(board, initial));
        return matchers;
    }
    
    public Set<Set<Position>> matchAll(Board board, Set<Position> initial) throws BoardDimensionException {
        Set<Set<Position>> matchers = new HashSet<Set<Position>>();
        matchers.addAll(this.matcher1.matchAll(board, initial));
        matchers.addAll(this.matcher2.matchAll(board, initial));
        return matchers;
    }

}
