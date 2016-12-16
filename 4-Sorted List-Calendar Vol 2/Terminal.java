package edu.kit.informatik;

/**
 * Test for Assignment 4 - Calendar Management
 * (KIT Programming WS16/17)
 * 
 * Replace original Terminal.java with this one for testing.
 * 
 * @author Micha Hanselmann
 * @version 1.0.0
 */
public class Terminal {
    
    /**
     * Line index
     */
    private static int i = 0;
    
    /**
     * Code to be tested
     * (lines starting with ← simulate user input)
     */
    private static final String[] code = {
            
            "← add appointment 29-11-2016T17:00:00 14-12-2016T13:00:00 Blatt 3", 
            "← add appointment 13-12-2016T17:00:00 11-01-2017T13:00:00 Blatt 4",
            "← add appointment 05-11-2016T13:00:00 05-11-2016T14:00:00 Kaffee",
            "← print appointments that start before 01-12-2016T00:00:00",
            "Kaffee 05-11-2016T13:00:00 05-11-2016T14:00:00",
            "Blatt 3 29-11-2016T17:00:00 14-12-2016T13:00:00",
            "← print appointments on 13-12-2016",
            "← print appointments on 05-11-2016",
            "Kaffee 05-11-2016T13:00:00 05-11-2016T14:00:00",
            "← print appointments in interval 15-11-2016T13:00:00 01-01-2017T00:00:00", 
            "Blatt 3 29-11-2016T17:00:00 14-12-2016T13:00:00",
            "← add appointment 24-12-2016T00:00:00 26-12-2016T23:59:59 Weihnachten",
            "← print appointments that conflict with Blatt 4",
            "Blatt 3 29-11-2016T17:00:00 14-12-2016T13:00:00",
            "Weihnachten 24-12-2016T00:00:00 26-12-2016T23:59:59", 
            "← print appointments that conflict with Blatt 6",
            "Appointment not found.",
            "← quit"

    };
    
    /**
     * Test Terminal.printLine()
     */
    public static void printLine(String s) {
        
        // log
        System.out.println("(" + (i + 1) + "/" + code.length + ") " + code[i]);
        
        // check if input expected
        if (code[i].startsWith("←"))
            System.err.println("Problem at line " + i++ + ": Input expected. Your output was too long.");
        
        // check if output matches
        if (!code[i++].equals(s))
            System.err.println("Problem at line " + i + ": Output is incorrect: " + s);
        
    }

    /**
     * Test Terminal.readLine()
     */
    public static String readLine() {
        
        // log
        System.out.println("(" + (i + 1) + "/" + code.length + ") " + code[i]);
        
        // check if output expected
        if (!code[i].startsWith("←"))
            System.err.println("Problem at line " + i++ + ": Output expected. Your output was too short.");
        
        // think like an user, act like an user
        return code[i++].substring(2);
    }
    
}
