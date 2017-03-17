package edu.kit.informatik.matchthree.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    MatchThreeBoardTest.class,
    MatchThreeBoardConstructorTest.class,
    MoveFlipRightTest.class,
    MoveReverseTest.class,
    BoardMoveTokensToBottomTest.class,
    MoveFlipDownTest.class,
    MoveRotateSquareClockwiseMoveTest.class,
    MoveRotateColumnDownTest.class,
    MoveRotateRowRightTest.class,
    MaximumDeltaMatcherTest.class,
    MatchThreeGameConstructorTest.class,
    MatchThreeGameTest.class
})

public class AllTests { }
