import edu.kit.informatik.tessellation.*;

/**
  Test for Assignment 3 - Tessellation
  (KIT Programming WS16/17)

  @author Micha Hanselmann
  @author Luke Brocke
  @version 1.1.1
*/
public class TessellationTest {
  // counter
  int passed = 0;
  int failed = 0;

  // static warning string
  private static String warning = ", WARNING: ENDS WITH LINE BREAK!";
  // platform-dependent line seperator
  private String ls = System.getProperty("line.separator");

  // main method
  public static void main(String[] args) {
    new TessellationTest();
  }

  public TessellationTest() {
    // display header
    log("Test for Assignment 3 - Tessellation");
    log("-------------------------------------");

    // --- START ---

    // basic methods
    LineType[] lines1 = { LineType.NONE, LineType.YELLOW, LineType.RED,
                          LineType.NONE, LineType.RED,    LineType.YELLOW };
    Tile tile1 = new Tile(lines1);
    test(tile1.toString().equals("-YR-RY"), "toString() of -YR-RY", tile1.toString());
    test(tile1.getLineTypeAtIndex(4) == LineType.RED, "getLineTypeAtIndex(4) of -YR-RY", tile1.getLineTypeAtIndex(4).toString());
    test(tile1.getNumberOfColors() == 2, "getNumberOfColors of -YR-RY = 2", Integer.toString(tile1.getNumberOfColors()));
    test(tile1.isExactlyEqualTo(tile1), "isExactlyEqualTo reflexive test -YR-RY", "Returned false but should be true");

    log("--- working toString method needed for following tests ---");

    // copy
    Tile tile2 = tile1.copy();
    test(tile1.isExactlyEqualTo(tile2), "copy " + tile1.toString(), tile2.toString());

    // rotating
    tile1.rotateClockwise();
    test(tile1.toString().equals("Y-YR-R"), "rotateClockwise -YR-RY = Y-YR-R", tile1.toString());
    tile1.rotateCounterClockwise();
    test(tile1.toString().equals("-YR-RY"), "rotateCounterClockwise Y-YR-R = -YR-RY", tile1.toString());
    for (int i = 0; i < 6; i++) tile1.rotateClockwise();
    test(tile1.toString().equals("-YR-RY"), "6x rotateClockwise -YR-RY = -YR-RY", tile1.toString());

    // comparing
    tile2 = new Tile();
    test(tile2.isEmpty(), "newTile().isEmpty()", "Returned false but should be true");
    test(tile1.isRotationEqualTo(tile1), "isRotationEqualTo reflexive test " + tile1.toString(), "Returned false but should be true");
    tile2 = tile1.copy();
    tile2.rotateClockwise();
    test(tile1.isRotationEqualTo(tile2), tile1.toString() + " isRotationEqualTo " + tile2.toString(), "Returned false but should be true");
    for (int i = 0; i < 4; i++) tile2.rotateCounterClockwise();
    test(tile2.isRotationEqualTo(tile1), tile2.toString() + " isRotationEqualTo " + tile1.toString(), "Returned false but should be true");
    test(!tile2.isExactlyEqualTo(tile1), tile2.toString() + " isExactlyEqualTo " + tile1.toString() + " is false", "Returned true");
    test(tile1.canBeRecoloredTo(tile1), tile1.toString() + " canBeRecoloredTo " + tile1.toString(), "Returned false but should be true");
    test(tile1.canBeRecoloredTo(tile2), tile1.toString() + " canBeRecoloredTo " + tile2.toString(), "Returned false but should be true");

    LineType[] lines2 = { LineType.NONE, LineType.NONE, LineType.RED,
                          LineType.NONE, LineType.RED,    LineType.NONE };
    tile2 = new Tile(lines2);
    test(!tile1.canBeRecoloredTo(tile2), tile1.toString() + " canBeRecoloredTo " + tile2.toString() + " is false", "Returned true");

    test(tile1.dominates(tile2), tile1.toString() + " dominates " + tile2.toString(), "Returned false but should be true");
    test(!tile2.dominates(tile1), tile2.toString() + " does not dominate " + tile1.toString(), "Returned true");
    test(!tile1.dominates(tile1), "Tile cannot dominate itself", "Returned true");

    test(tile1.hasSameColorsAs(tile1), tile1.toString() + " hasSameColorsAs " + tile1.toString(), "Returned false but should be true");
    test(!tile2.hasSameColorsAs(tile1), tile2.toString() + " hasSameColorsAs " + tile1.toString() + " is false", "Returned true");

    tile2 = tile1.copy();
    tile2.rotateCounterClockwise();
    tile1.rotateClockwise();
    test(tile2.hasSameColorsAs(tile1), tile2.toString() + " hasSameColorsAs " + tile1.toString(), "Returned false but should be true");

    // example interactions with tiles
    log("--- example test of assignment (listing 2) ---");
    tile1 = new Tile();
    test(tile1.isEmpty(), tile1.toString() + " isEmpty", "Returned false but should be true");
    test(tile1.getNumberOfColors() == 0, tile1.toString() + " getNumberOfColors == 0", "Returned " + Integer.toString(tile1.getNumberOfColors()));
    test(tile1.toString().equals("------"), "toString of empty tile is ------", "Returned " + tile1.toString());
    tile2 = new Tile(new LineType[] { LineType.NONE, LineType.YELLOW, LineType.NONE, LineType.NONE, LineType.NONE, LineType.YELLOW });
    test(tile2.toString().equals("-Y---Y"), "toString of -Y---Y", "Returned " + tile1.toString());
    test(!tile1.canBeRecoloredTo(tile2), tile1.toString() + " canBeRecoloredTo " + tile2.toString() + " is false", "Returned true");
    Tile tile3 = new Tile(new LineType[] { LineType.NONE, LineType.YELLOW, LineType.RED, LineType.NONE, LineType.RED, LineType.YELLOW });
    test(tile3.dominates(tile1), tile3.toString() + " dominates " + tile1.toString(), "Returned false but should be true");
    test(tile3.dominates(tile2), tile3.toString() + " dominates " + tile2.toString(), "Returned false but should be true");
    test(!tile2.canBeRecoloredTo(tile3), tile2.toString() + " canBeRecoloredTo " + tile3.toString() + " is false", "Returned true");
    Tile tile4 = tile3.copy();
    test(tile4.isExactlyEqualTo(tile3), tile4.toString() + " isExactlyEqualTo " + tile3.toString(), "Returned false but should be true");
    for (int i = 0; i < 3; i++) tile4.rotateClockwise();
    test(tile4.toString().equals("-RY-YR"), "Rotate " + tile3.toString() + " 3 times clockwise is -RY-YR", "Returned " + tile4.toString());
    test(!tile4.isExactlyEqualTo(tile3), tile4.toString() + " isExactlyEqualTo " + tile3.toString() + " is false", "Returned true");
    test(tile4.isRotationEqualTo(tile3), tile4.toString() + " isRotationEqualTo " + tile3.toString(), "Returned false but should be true");
    test(tile4.canBeRecoloredTo(tile3), tile4.toString() + " canBeRecoloredTo " + tile3.toString(), "Returned false but should be true");

    // example tests for board class
    log("--- example test of assignment (listing 3) ---");
    Board b1 = new Board();
    test(b1.isEmpty(), "Board 1 isEmpty", "Returned false but should be true");

    b1.setTile(1, new Tile(new LineType[] {LineType.GREEN, LineType.GREEN, LineType.YELLOW, LineType.NONE, LineType.YELLOW, LineType.NONE }));
    b1.setTile(4, new Tile(new LineType[] {LineType.RED, LineType.GREEN, LineType.RED, LineType.GREEN, LineType.YELLOW, LineType.YELLOW }));

    test(!b1.isEmpty(), "Board 2 isEmpty is false", "Returned true but should be false");
    test(b1.isValid(), "Board 2 isValid", "Returned false but should be true");

    // allow toString() results that end with a line break, show a warning instead
    String b1string = "------;GGY-Y-;------;" + this.ls + "------;RGRGYY;------;" + this.ls + "------;------;------;" + this.ls + "------;------;------;";
    String b1info = (b1.toString().endsWith(this.ls)) ? TessellationTest.warning : "";
    test(b1.toString().equals(b1string) || b1.toString().equals(b1string + this.ls), "Board 2 toString is correct" + b1info, "toString produced wrong output: " + b1.toString());

    test(b1.getConnectedPathColor(new int[] {1, 4}) == LineType.YELLOW, "Board 2 getConnectedPathColor on tile 1 and 4 is YELLOW", "Wrong color, must be YELLOW");

    b1.rotateTileClockwise(1);
    test(!b1.isValid(), "Board 3 isValid is false", "Returned true but should be false");

    b1string = "------;-GGY-Y;------;" + this.ls + "------;RGRGYY;------;" + this.ls + "------;------;------;" + this.ls + "------;------;------;";
    b1info = (b1.toString().endsWith(this.ls)) ? TessellationTest.warning : "";
    test(b1.toString().equals(b1string) || b1.toString().equals(b1string + this.ls), "Board 3 toString is correct" + b1info, "toString produced wrong output: " + b1.toString());

    test(b1.getConnectedPathColor(new int[] {1, 4}) == LineType.NONE, "Board 3 getConnectedPathColor on tile 1 and 4 is NONE", "Wrong color, must be NONE");

    // some additional tests
    log("--- additional tests (validity not guaranteed!) ---");

    Tile tile5 = new Tile(new LineType[] {LineType.YELLOW, LineType.GREEN, LineType.RED, LineType.YELLOW, LineType.GREEN, LineType.RED});
    Tile tile6 = new Tile();

    test(tile5.getLineTypeAtIndex(3) == LineType.YELLOW, "getLineTypeAtIndex(3) of YGRYGR", tile5.getLineTypeAtIndex(3).toString());
    test(tile5.getNumberOfColors() == 3, "getNumberOfColors of YGRYGR = 3", Integer.toString(tile5.getNumberOfColors()));
    test(tile6.getNumberOfColors() == 0, "getNumberOfColors of ------ = 0", Integer.toString(tile6.getNumberOfColors()));

    tile5.rotateClockwise();
    test(tile5.toString().equals("RYGRYG"), "rotateClockwise YGRYGR = RYGRYG", tile5.toString());
    for(int i = 0; i < 3; i++)
      tile5.rotateCounterClockwise();
    test(tile5.toString().equals("RYGRYG"), "rotateClockwise YGRYGR = RYGRYG", tile5.toString());

    test(tile5.canBeRecoloredTo(tile5), tile5.toString() + " canBeRecoloredTo " + tile5.toString(), "Returned false but should be true");
    test(!tile6.canBeRecoloredTo(tile5), tile6.toString() + " canBeRecoloredTo " + tile5.toString() + " is false", "Returned true");
    test(!tile5.canBeRecoloredTo(tile6), tile5.toString() + " canBeRecoloredTo " + tile6.toString() + " is false", "Returned true");

    test(tile5.dominates(tile6), tile5.toString() + " dominates " + tile6.toString(), "Returned false but should be true");
    test(!tile6.dominates(tile5), tile6.toString() + " dominates " + tile6.toString() + " is false", "Returned true");

    // board from image 9
    Board b4 = new Board();
    b4.setTile(1, new Tile(new LineType[] {LineType.GREEN, LineType.GREEN, LineType.YELLOW, LineType.NONE, LineType.YELLOW, LineType.NONE}));
    b4.setTile(2, new Tile(new LineType[] {LineType.NONE, LineType.NONE, LineType.NONE, LineType.NONE, LineType.RED, LineType.RED}));
    b4.setTile(4, new Tile(new LineType[] {LineType.RED, LineType.GREEN, LineType.RED, LineType.GREEN, LineType.YELLOW, LineType.YELLOW}));
    b4.setTile(5, new Tile(new LineType[] {LineType.GREEN, LineType.GREEN, LineType.NONE, LineType.NONE, LineType.NONE, LineType.NONE}));
    b4.setTile(6, new Tile(new LineType[] {LineType.NONE, LineType.NONE, LineType.YELLOW, LineType.GREEN, LineType.GREEN, LineType.YELLOW}));
    b4.setTile(7, new Tile(new LineType[] {LineType.GREEN, LineType.NONE, LineType.NONE, LineType.RED, LineType.GREEN, LineType.RED}));
    b4.setTile(8, new Tile(new LineType[] {LineType.NONE, LineType.YELLOW, LineType.YELLOW, LineType.NONE, LineType.NONE, LineType.NONE}));
    b4.setTile(10, new Tile(new LineType[] {LineType.NONE, LineType.NONE, LineType.NONE, LineType.YELLOW, LineType.YELLOW, LineType.NONE}));
    b4.setTile(11, new Tile(new LineType[] {LineType.YELLOW, LineType.NONE, LineType.NONE, LineType.NONE, LineType.NONE, LineType.YELLOW}));

    String b4string = "------;GGY-Y-;----RR;" + this.ls + "------;RGRGYY;GG----;" + this.ls + "--YGGY;G--RGR;-YY---;" + this.ls + "------;---YY-;Y----Y;";
    String b4info = (b4.toString().endsWith(this.ls)) ? TessellationTest.warning : "";
    test(b4.toString().equals(b4string) || b4.toString().equals(b4string + this.ls), "Board 4 toString is correct" + b4info, "toString produced wrong output: " + b4.toString());

    test(b4.isValid(), "Board 4 isValid", "Returned false but should be true");

    test(b4.getConnectedPathColor(new int[]{0, 3}) == LineType.NONE, "Board 4 getConnectedPathColor on tile 0 and 3 is NONE", "Wrong color, must be NONE");
    test(b4.getConnectedPathColor(new int[]{1, 4}) == LineType.YELLOW, "Board 4 getConnectedPathColor on tile 1 and 4 is YELLOW", "Wrong color, must be YELLOW");
    test(b4.getConnectedPathColor(new int[]{1, 2, 4}) == LineType.NONE, "Board 4 getConnectedPathColor on tile 1, 2 and 4 is NONE", "Wrong color, must be NONE");
    test(b4.getConnectedPathColor(new int[]{4, 5, 7}) == LineType.GREEN, "Board 4 getConnectedPathColor on tile 4, 5 and 7 is GREEN", "Wrong color, must be GREEN");
    test(b4.getConnectedPathColor(new int[]{4, 5, 7, 6, 4, 5, 7}) == LineType.GREEN, "Board 4 getConnectedPathColor on tile 4, 5, 7, 6, 4, 5 and 7 is GREEN", "Wrong color, must be GREEN");
    test(b4.getConnectedPathColor(new int[]{8, 10, 11}) == LineType.YELLOW, "Board 4 getConnectedPathColor on tile 8, 10 and 11 is YELLOW", "Wrong color, must be YELLOW");
    test(b4.getConnectedPathColor(new int[]{8, 10, 11, 8}) == LineType.YELLOW, "Board 4 getConnectedPathColor on tile 8, 10, 11 and 8 is YELLOW", "Wrong color, must be YELLOW");

    // change to board from image 10
    b4.rotateTileClockwise(1);
    b4.rotateTileClockwise(2);
    b4.rotateTileClockwise(2);
    b4.rotateTileClockwise(6);
    b4.rotateTileClockwise(11);

    b4string = "------;-GGY-Y;RR----;" + this.ls + "------;RGRGYY;GG----;" + this.ls + "Y--YGG;G--RGR;-YY---;" + this.ls + "------;---YY-;YY----;";
    b4info = (b4.toString().endsWith(this.ls)) ? TessellationTest.warning : "";
    test(b4.toString().equals(b4string) || b4.toString().equals(b4string + this.ls), "Board 5 toString is correct" + b4info, "toString produced wrong output: " + b4.toString());

    test(!b4.isValid(), "Board 5 isValid is false", "Returned true but should be false");

    test(b4.getConnectedPathColor(new int[]{0, 3}) == LineType.NONE, "Board 5 getConnectedPathColor on tile 0 and 3 is NONE", "Wrong color, must be NONE");
    test(b4.getConnectedPathColor(new int[]{1, 4}) == LineType.NONE, "Board 5 getConnectedPathColor on tile 1 and 4 is NONE", "Wrong color, must be NONE");
    test(b4.getConnectedPathColor(new int[]{1, 2, 4}) == LineType.NONE, "Board 5 getConnectedPathColor on tile 1, 2 and 4 is NONE", "Wrong color, must be NONE");
    test(b4.getConnectedPathColor(new int[]{4, 5, 7}) == LineType.GREEN, "Board 5 getConnectedPathColor on tile 4, 5 and 7 is GREEN", "Wrong color, must be GREEN");
    test(b4.getConnectedPathColor(new int[]{4, 5, 7, 6, 4, 5, 7}) == LineType.NONE, "Board 5 getConnectedPathColor on tile 4, 5, 7, 6, 4, 5 and 7 is NONE", "Wrong color, must be NONE");
    test(b4.getConnectedPathColor(new int[]{8, 10, 11}) == LineType.YELLOW, "Board 5 getConnectedPathColor on tile 8, 10 and 11 is YELLOW", "Wrong color, must be YELLOW");
    test(b4.getConnectedPathColor(new int[]{8, 10, 11, 8}) == LineType.NONE, "Board 5 getConnectedPathColor on tile 8, 10, 11 and 8 is NONE", "Wrong color, must be NONE");

    // --- END ---

    // display end results
    log("-------------------------------------");
    int total = passed + failed;
    log(total + " tests finished. " + passed + " passed - " + failed + " failed (" + (int) ((float) passed / total * 100.0) + "%)");
  }

  // test condition logging description, if fails -> log error
  public void test(boolean condition, String desc, String err) {

    // log
    if (desc != "") desc += " - ";
    log("(" + (passed + failed + 1) + ") " + desc, false);

    // test
    if (condition) {

      log("PASS");
      passed++;

    } else {

      if (err != "")
        err = ": " + err;

      log("FAIL" + err);
      failed++;

    }

  }

  // test condition, if fails -> log error
  public void test(boolean condition, String err) {
    test(condition, "", err);
  }

  // test condition
  public void test(boolean condition) {
    test(condition, "", "");
  }

  // log sth with optional new line (println shortcut)
  public void log(String test, boolean newLine) {
    if (newLine) {
      System.out.println(test);
    } else {
      System.out.print(test);
    }
  }

  // log sth with new line
  public void log(String test) {
    log(test, true);
  }

}
