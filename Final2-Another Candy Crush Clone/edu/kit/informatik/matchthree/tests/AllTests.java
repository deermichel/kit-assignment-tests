package edu.kit.informatik.matchthree.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    BoardMoveTokensToBottomTest.class,
    MatchThreeBoardConstructorTest.class,
    MatchThreeBoardTest.class,
    MatchThreeGameConstructorTest.class,
    MatchThreeGameTest.class,
    MaximumDeltaMatcherTest.class,
    MoveFlipDownTest.class,
    MoveFlipRightTest.class,
    MoveReverseTest.class,
    MoveRotateColumnDownTest.class,
    MoveRotateRowRightTest.class,
    MoveRotateSquareClockwiseMoveTest.class,
    MyGameTest.class,
    MyMaximumDeltaMatcherTest.class,
})

public class AllTests { }
