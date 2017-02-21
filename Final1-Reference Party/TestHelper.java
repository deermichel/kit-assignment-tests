package edu.kit.informatik.tests;

import static org.hamcrest.Matchers.*;

import edu.kit.informatik.Terminal;

/**
 * Test Helper for the Terminal Test Class Based on Common Outputs
 *
 * @author Florian Pfisterer
 * Based on templates & inspiration from Micha Hanselmann
 */

public class TestHelper {

    public static void expectOk(final String input) {
        Terminal.addSingleLineOutputThatIsExactly(input, "Ok");
    }

    public static void expectError(final String input) {
        Terminal.addSingleLineOutputThatMatches(input, startsWith("Error, "));
    }
}
