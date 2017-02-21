package edu.kit.informatik.tests;

/**
 * Wrapper class for testing
 * 
 * @author Micha Hanselmann
 */
public class Wrapper {

    private static final String[] DEFAULT_ARGS = {};
    
    
    public static void main(String[] args) {
        
        // adjust to fit your project setup
        MyMainClass.main(args);
        
    }
    
    public static void main() {
        main(DEFAULT_ARGS);
    }

}
