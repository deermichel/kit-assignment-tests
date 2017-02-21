package edu.kit.informatik.tests;

import net.luk3b.literature.Management;

/**
 * Wrapper class for testing
 * 
 * @author Micha Hanselmann
 */
public class Wrapper {

    private static final String[] DEFAULT_ARGS = {};
    
    
    public static void main(String[] args) {
        
        // adjust to fit your project setup
        Management.main(args);
        
    }
    
    public static void main() {
        main(DEFAULT_ARGS);
    }

}
