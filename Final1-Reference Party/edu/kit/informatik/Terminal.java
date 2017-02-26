package edu.kit.informatik;

import org.hamcrest.Matcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * A modified Terminal class which can also be used for testing. When not in
 * {@linkplain #enableTestingMode() testing mode}, this class behaves like
 * {@linkplain https://sdqweb.ipd.kit.edu/lehre/WS1617-Programmieren/Terminal.java
 * the SDQ’s Terminal class}. In {@linkplain #enableTestingMode() testing mode},
 * it returns predefined answers for a call to {@link #readLine()} and checks
 * the outputs made with {@link #printLine} to match certain criteria, expressed
 * in {@linkplain http://hamcrest.org/JavaHamcrest/ Hamcrest matchers}.
 * <p>
 * <p>
 * The idea is to first specify an input and a matcher that checks the output
 * which is printed by the tested program upon that input. When the tested
 * program calls {@link #readLine()}, the predefined input will be returned.
 * Until the next call to {@link #readLine()} all outputs will be stored. When
 * {@link #readLine()} is called, the printed lines are checked against the
 * defined matchers.
 * <p>
 * <p>
 * After the end of the tested program you have to call {@link #flush()} to
 * check the latest output lines by hand.
 * <p>
 * <p>
 * There is no method to provide input for the {@link #readFile(String)} method.
 * This is on purpose, as implementations are allowed to read files by other
 * means. There should always be a real (temporary) file to read when testing.
 * See for example
 * {@linkplain http://git.joshuagleitze.de/submissiontest/blob/master/src/main/java/test/Input.java
 * this Input helper class} for a handy way to create test input files.
 *
 * @author Moritz Halm
 * @author Joshua Gleitze
 * @author ITI, VeriAlg Group
 * @author IPD, SDQ Group
 */
public class Terminal {
    /**
     * Reads text from the "standard" input stream, buffering characters so as
     * to provide for the efficient reading of characters, arrays, and lines.
     * This stream is already open and ready to supply input data and
     * corresponds to keyboard input. It is only used when not in test mode.
     */
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    /**
     * The lines that will be issued by readLine.
     */
    private static LinkedList<String> inputLines = new LinkedList<String>();
    /**
     * The last read line.
     */
    private static String lastInput = "";
    /**
     * Stores all lines printed by the tested program.
     */
    private static List<String> outputBuffer = new ArrayList<String>();
    /**
     * Stores matchers which verify the printed lines match specific conditions.
     */
    private static LinkedList<Matcher<? super List<String>>> matchers = new LinkedList<Matcher<? super List<String>>>();
    /**
     * Indicates the beginning of an interaction with the tested program.
     */
    private static boolean isBeforeFirstLine = true;
    /**
     * Whether we operate in normal or in test mode.
     */
    private static boolean testMode = false;

    /**
     * Prints the string representation of an {@code Object} and then terminate
     * the line.
     * <p>
     * <p>
     * If the argument is {@code null}, then the string {@code "null"} is
     * printed, otherwise the object's string value {@code obj.toString()} is
     * printed.
     *
     * @param object the {@code Object} to be printed
     * @see String#valueOf(Object)
     */
    public static void printLine(Object object) {
        if (testMode) {
            outputBuffer.add(String.valueOf(object));
        } else {
            System.out.println(object);
        }
    }

    /**
     * Prints an array of characters and then terminate the line.
     * <p>
     * <p>
     * If the argument is {@code null}, then a {@code NullPointerException} is
     * thrown, otherwise the value of {@code
     * new String(charArray)} is printed.
     *
     * @param charArray an array of chars to be printed
     * @see String#valueOf(char[])
     */
    public static void printLine(final char[] charArray) {
        /*
         * Note: This method's sole purpose is to ensure that the Terminal-class
		 * behaves exactly as System.out regarding output.
		 * (System.out.println(char[]) calls String.valueOf(char[]) which itself
		 * returns 'new String(char[])' and is therefore the only method that
		 * behaves differently when passing the provided parameter to the
		 * System.out.println(Object) method.)
		 */
        if (testMode) {
            Terminal.printLine(String.valueOf(charArray));
        } else {
            System.out.println(charArray);
        }
    }

    /**
     * Reads a line of text. A line is considered to be terminated by any one of
     * a line feed ('\n'), a carriage return ('\r'), or a carriage return
     * followed immediately by a linefeed.
     *
     * @return a {@code String} containing the contents of the line, not
     * including any line-termination characters, or {@code null} if the
     * end of the stream has been reached
     */
    public static String readLine() {
        if (testMode) {
            // if we read the first line, we can't check any previous inputs.
            if (isBeforeFirstLine) {
                isBeforeFirstLine = false;
            } else {
                // check all previous output
                flush();
            }
            // the program to test should not try to read more lines than
            // specified.
            // Make sure, you always quit it.
            if (inputLines.isEmpty()) {
                fail("There is no input specified that could be read.");
            }
            // return the line to read.
            lastInput = inputLines.removeFirst();
            return lastInput;
        } else {
            try {
                return IN.readLine();
            } catch (final IOException e) {
                /*
                 * The IOException will not occur during tests executed by the
				 * Praktomat, therefore the following RuntimeException does not
				 * have to get handled.
				 */
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Prints the given error-{@code message} with the prefix "{@code Error, }".
     * <p>
     * <p>
     * More specific, this method behaves exactly as if the following code got
     * executed: <blockquote>
     * <p>
     * <pre>
     * Terminal.printLine("Error, " + message);
     * </pre>
     * <p>
     * </blockquote>
     *
     * @param message the error message to be printed
     * @see #printLine(Object)
     */
    public static void printError(final String message) {
        Terminal.printLine("Error, " + message);
    }

    /**
     * Adds an input (a line that can be read by the tested program) and a
     * matcher to check all lines that will be printed until the next cal of
     * readLine.
     *
     * @param input   The input (e.g. a command).
     * @param matcher A matcher which checks a list (or supertype) of strings.
     */
    public static void addMultipleLineOutputThatMatches(String input, Matcher<? super List<String>> matcher) {
        assertTestMode();
        inputLines.addLast(input);
        matchers.addLast(matcher);
    }

    /**
     * Convenience method. Specify several lines that should be exactly the
     * output of the program.
     *
     * @param input
     * @param expectedOutput
     */
    public static void addMultipleLinesOutputThatIsExactly(String input, List<String> expectedOutput) {
        addMultipleLinesOutputThatIsExactly(input, expectedOutput.toArray(new String[0]));
    }

    /**
     * Convenience method. Specify several lines that should be exactly the
     * output of the program.
     *
     * @param input
     * @param expectedOutput
     */
    public static void addMultipleLinesOutputThatIsExactly(String input, String... expectedOutput) {
        addMultipleLineOutputThatMatches(input, contains(expectedOutput));
    }

    /**
     * Convenience method. Specify a matcher that checks a single line of
     * output. The tested program is expected to print out only a single line.
     *
     * @param input
     * @param matcher
     */
    public static void addSingleLineOutputThatMatches(String input, Matcher<String> matcher) {
        addMultipleLineOutputThatMatches(input, allOf(iterableWithSize(1), hasItem(matcher)));
    }

    /**
     * Convenience method. Specify a single line of output that should be
     * printed upon a call of input.
     *
     * @param input
     * @param expectedOutput
     */
    public static void addSingleLineOutputThatIsExactly(String input, String expectedOutput) {
        addSingleLineOutputThatMatches(input, is(expectedOutput));
    }

    /**
     * Convenience method. Specify an input which expects no output.
     *
     * @param input
     */
    public static void addNoOutput(String input) {
        addMultipleLineOutputThatMatches(input, empty());
    }

    /**
     * Reads the file with the specified path and returns its content stored in
     * a {@code String} array, whereas the first array field contains the file's
     * first line, the second field contains the second line, and so on.
     *
     * @param path the path of the file to be read
     * @return the content of the file stored in a {@code String} array
     */
    public static String[] readFile(final String path) {
        try (final BufferedReader reader = new BufferedReader(new FileReader(path))) {
            return reader.lines().toArray(String[]::new);
        } catch (final IOException e) {
			/*
			 * You can expect that the praktomat exclusively provides valid
			 * file-paths. Therefore there will no IOException occur while
			 * reading in files during the tests, the following RuntimeException
			 * does not have to get handled.
			 */
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks the recently printed lines. Is done implicitly with every call of
     * readLine(). Use this method after the program was executed to check the
     * latest output lines.
     */
    public static void flush() {
        assertTestMode();
        if (!matchers.isEmpty()) {
            // checks the recently printed lines
            Matcher matcher = matchers.removeFirst();
            if (matcher.matches(outputBuffer)) {
                assertTrue(true);

            } else {
                System.err.println("We got wrong output!");
                System.err.println("We expected something that matches this Matcher:");
                System.err.println(matcher.toString());
                System.err.println("Instead we got this:");
                outputBuffer.forEach(System.err::println);
                System.err.println("The responsible input is:");
                System.err.println(lastInput);
                assertTrue(false);

            }
            outputBuffer.clear();
        }
    }

    /**
     * Use this method before you start a new interaction with the program to
     * test. It ensures that there won't be any output lines checked on the
     * first call of readLine.
     */
    public static void reset() {
        assertTestMode();
        inputLines.clear();
        matchers.clear();
        outputBuffer.clear();
        isBeforeFirstLine = true;
    }

    /**
     * Enables testing mode. This means that the class will not behave like the
     * normal Terminal class, but return predefined input and check the
     * program’s output.
     *
     * @see {@link #disableTestingMode()}
     */
    public static void enableTestingMode() {
        testMode = true;
    }

    /**
     * Disables testing mode. This means that the class will behave like
     * {@linkplain https://sdqweb.ipd.kit.edu/lehre/WS1617-Programmieren/Terminal.java
     * the SDQ’s Terminal class}.
     *
     * @see {@link #enableTestingMode()}
     */
    public static void disableTestingMode() {
        testMode = false;
    }

    /**
     * Throws an {@link IllegalStateException} if the class is not in testing
     * mode.
     */
    private static void assertTestMode() {
        if (!testMode) {
            throw new IllegalStateException("You must call #enableTestingMode to test with the Terminal class!");
        }
    }
}