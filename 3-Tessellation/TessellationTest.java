import edu.kit.informatik.tessellation.*;

/**
  Test for Assignment 3 - Tessellation
  (KIT Programming WS16/17)

  @author Micha Hanselmann
  @version 1.0.0
*/
public class TessellationTest {

  // counter
  int passed = 0;
  int failed = 0;


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

    Tile tile2 = tile1.copy();
    test(tile1.isExactlyEqualTo(tile2), "copy " + tile1.toString(), tile2.toString());

    tile1.rotateClockwise();
    test(tile1.toString().equals("Y-YR-R"), "rotateClockwise -YR-RY = Y-YR-R", tile1.toString());
    tile1.rotateCounterClockwise();
    test(tile1.toString().equals("-YR-RY"), "rotateCounterClockwise Y-YR-R = -YR-RY", tile1.toString());
    for (int i = 0; i < 6; i++) tile1.rotateClockwise();
    test(tile1.toString().equals("-YR-RY"), "6x rotateClockwise -YR-RY = -YR-RY", tile1.toString());

    tile2 = new Tile();
    test(tile2.isEmpty(), "newTile().isEmpty()", "Returned false but should be true");

    test(tile1.isRotationEqualTo(tile1), "isRotationEqualTo reflexive test " + tile1.toString(), "Returned false but should be true");
    tile2 = tile1.copy();
    tile2.rotateClockwise();
    test(tile1.isRotationEqualTo(tile2), tile1.toString() + " isRotationEqualTo " + tile2.toString(), "Returned false but should be true");
    for (int i = 0; i < 4; i++) tile2.rotateCounterClockwise();
    test(tile2.isRotationEqualTo(tile1), tile2.toString() + " isRotationEqualTo " + tile1.toString(), "Returned false but should be true");
    test(!tile2.isExactlyEqualTo(tile1), tile2.toString() + " isExactlyEqualTo " + tile1.toString() + " is false", "Returned true");

    log("--- more tests tba ---");
    //log("--- additional tests (validity not guaranteed!) ---");
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
