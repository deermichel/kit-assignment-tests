package edu.kit.informatik;

/**
 * Test for Assignment 5 - Campus Management
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
     * (lines starting with > simulate user input)
     */
    private static final String[] code = {
            
            // add-professor
            "> add-professor roman;forst;ddf",
            "Ok",
            "> add-professor dominik;saller;xxc",
            "Ok",
            "> add-professor robert;amsel;ifd",
            "Ok",
            "> add-professor maximiliane;frei;hgs",
            "Ok",
            "> add-professor andreas;kappel;sww",
            "Ok",
            "> add-professor angelika;huffman;itc",
            "Ok",
            "> add-professor angelika;huffman;abg",
            "Ok",
            
            // add-professor errors
            "> add-professor angelika;huffman;itc",
            "Error,",
            "> add-professor heinz;thorsten;abg;1.0",
            "Error,",
            "> add-professor Dieter;huffman;reg",
            "Error,",
            "> add-professor heinz;thorsten",
            "Error,",
            "> add-professor stiehl",
            "Error,",
            "> add-professor",
            "Error,",
            
            // add-student
            "> add-student thorsten;doernbach;123455",
            "Ok",
            "> add-student micha;hanselmann;197882",
            "Ok",
            "> add-student max;maxtermann;817292",
            "Ok",
            "> add-student der;fabi;201526",
            "Ok",
            "> add-student jan;lang;127844",
            "Ok",
            
            // add-student errors
            "> add-student thorsten;doernbach;123455",
            "Error,",
            "> add-student fritz;doernbach;123455",
            "Error,",
            "> add-student hans;doernbach;99999",
            "Error,",
            "> add-student hans;doernbach;3456732",
            "Error,",
            "> add-student hans;doernbach;099999",
            "Error,",
            "> add-student hans;doernbach;-13255",
            "Error,",
            "> add-student hans;doernbach;abc123",
            "Error,",
            "> add-student hans;doernbach;345323;ab",
            "Error,",
            "> add-student hans;Horst;345323",
            "Error,",
            "> add-student hans;ohnem",
            "Error,",
            "> add-student christian",
            "Error,",
            "> add-student",
            "Error,",
            
            // add-module
            "> add-module algos",
            "Ok",
            "> add-module betriebssys",
            "Ok",
            "> add-module softwaretech",
            "Ok",
            
            // add-module errors
            "> add-module RO",
            "Error,",
            "> add-module",
            "Error,",
            "> add-module ro;t",
            "Error,",
            
            // add-lecture
            "> add-lecture proggen;3;roman;forst;ddf;9",
            "Ok",
            "> add-lecture irgendwas;2;robert;amsel;ifd;5",
            "Ok",
            "> add-lecture zocken;3;roman;forst;ddf;9",
            "Ok",
            "> add-lecture undnochmalproggen;3;roman;forst;ddf;9",
            "Ok",
            "> add-lecture einevl;3;roman;forst;ddf;9",
            "Ok",
            "> add-lecture letztevlimmodul;3;maximiliane;frei;hgs;8",
            "Ok",
            "> add-lecture genaumaxetcs;3;roman;forst;ddf;1",
            "Ok",
            
            // add-lecture errors
            "> add-lecture Proggen;2;roman;forst;ddf;9",
            "Error,",
            "> add-lecture zuvieletcs;3;roman;forst;ddf;1",
            "Error,",
            "> add-lecture mirgehndieideenaus;2;roman;forst;tgi;1",
            "Error,",
            "> add-lecture mirgehndieideenaus;2;roman;forst;ddf;1;2",
            "Error,",
            "> add-lecture mirgehndieideenaus;2;roman;forst;ddf",
            "Error,",
            "> add-lecture mirgehndieideenaus;2;roman;forst",
            "Error,",
            "> add-lecture mirgehndieideenaus;2;roman",
            "Error,",
            "> add-lecture mirgehndieideenaus;2",
            "Error,",
            "> add-lecture mirgehndieideenaus",
            "Error,",
            "> add-lecture",
            "Error,",
            "> add-lecture mirgehndieideenaus;4;roman;forst;ddf;1",
            "Error,",
            "> add-lecture proggen;1;roman;forst;ddf;10",
            "Error,",
            "> add-lecture dieter;1;roman;forst;ddf;-1",
            "Error,",
            "> add-lecture loman;1;roman;forst;ddf;0",
            "Error,",
            "> add-lecture loman;a;roman;forst;ddf;1",
            "Error,",
            "> add-lecture loman;1;roman;forst;ddf;b",
            "Error,",
            
            // examination-marking
            "> examination-marking 4;197882;1.105",
            "Ok",
            "> examination-marking 5;197882;3.45",
            "Ok",
            "> examination-marking 4;201526;2.00",
            "Ok",
            "> examination-marking 6;127844;2.5",
            "Ok",
            "> examination-marking 4;127844;5.0",
            "Ok",
            "> examination-marking 6;817292;2.595",
            "Ok",
            
            // examination-marking errors
            "> examination-marking 4;197882;1.1",
            "Error,",
            "> examination-marking 8;197881;1.1",
            "Error,",
            "> examination-marking 34;197882;1.1",
            "Error,",
            "> examination-marking bb;197882;1.1",
            "Error,",
            "> examination-marking 8;197882;1,1",
            "Error,",
            "> examination-marking 8;197882;5.1",
            "Error,",
            "> examination-marking 8;197882;0.9",
            "Error,",
            "> examination-marking 8;197882;1;2",
            "Error,",
            "> examination-marking 8;197882",
            "Error,",
            "> examination-marking 8",
            "Error,",
            "> examination-marking",
            "Error,",
            
            // list-student
            "> list-student",
            "123455 thorsten doernbach none",
            "127844 jan lang 3.75",
            "197882 micha hanselmann 1.94",
            "201526 der fabi 2.00",
            "817292 max maxtermann 2.60",
            
            // summary-student
            "> summary-student max;maxtermann;817292",
            "6 zocken 2.60",
            
            // summary-student errors
            "> summary-student micha;hanselmann;197881",
            "Error,",
            "> summary-student micha;hufmann;197882",
            "Error,",
            "> summary-student dieter;hanselmann;197882;1",
            "Error,",
            "> summary-student dieter;hanselmann",
            "Error,",
            "> summary-student dieter",
            "Error,",
            "> summary-student",
            "Error,",
            
            // list-module
            "> list-module",
            "1 algos 0 none",
            "2 betriebssys 5 3.45",
            "3 softwaretech 45 2.62",
            
            // summary-module
            "> summary-module 2",
            "5 irgendwas 5 3.45",
            
            // summary-module errors
            "> summary-module abc",
            "Error,",
            "> summary-module 1;a",
            "Error,",
            "> summary-module 42",
            "Error,",
            "> summary-module",
            "Error,",
            
            // list-lecture
            "> list-lecture",
            "4 proggen 9 2.70",
            "5 irgendwas 5 3.45",
            "6 zocken 9 2.55",
            "7 undnochmalproggen 9 none",
            "8 einevl 9 none",
            "9 letztevlimmodul 8 none",
            "10 genaumaxetcs 1 none",
            
            // summary-lecture
            "> summary-lecture 5",
            "197882 micha hanselmann 3.45",
            
            // summary-lecture errors
            "> summary-lecture 2",
            "Error,",
            "> summary-lecture 4;a",
            "Error,",
            "> summary-lecture",
            "Error,",
            
            // list-professor
            "> list-professor",
            "sww andreas kappel none",
            "abg angelika huffman none",
            "itc angelika huffman none",
            "xxc dominik saller none",
            "hgs maximiliane frei none",
            "ifd robert amsel 3.45",
            "ddf roman forst 2.62",
            
            // summary-professor
            "> summary-professor robert;amsel;ifd",
            "5 irgendwas 3.45",
            
            // summary-professor errors
            "> summary-professor robert;amsel;ddf",
            "Error,",
            "> summary-professor roBert;amsel;ifd",
            "Error,",
            "> summary-professor robert;amsel;ifd;d",
            "Error,",
            "> summary-professor robert;amsel",
            "Error,",
            "> summary-professor robert",
            "Error,",
            "> summary-professor ",
            "Error,",
            
            // reset
            "> reset ds",
            "Error,",
            "> reset",
            "Ok",
            "> list-student",
            "> list-module",
            "> list-professor",
            "> list-lecture",
            
            // misc
            "> a",
            "Error,",
            "> a;a;B",
            "Error,",
            "> List-professor",
            "Error,",
            
            // quit ^^
            "> Quit",
            "Error,",
            "> quit ;",
            "Error,",
            "> quit"

    };
    
    /**
     * Test Terminal.printLine()
     */
    public static void printLine(String s) {
        
        // log
        System.out.println("printLine: " + s);
        System.out.println("(" + (i + 1) + "/" + code.length + ") " + code[i]);
        
        // check if input expected
        if (code[i].startsWith(">"))
            System.out.println("!!! Problem at line " + i + ": Input expected. Your output was too long.");
        
        // check if output matches
        if (!code[i].equals(s))
            if (!(code[i].equals("Error,") && s.startsWith(code[i])))
                System.out.println("!!! Problem at line " + (i + 1) + ": Output is incorrect: " + s);
        
        i++;
    }
    
    /**
     * Test Terminal.printError()
     */
    public static void printError(String s) {
        printLine("Error, " + s);
    }

    /**
     * Test Terminal.readLine()
     */
    public static String readLine() {
        
        // log
        System.out.println("(" + (i + 1) + "/" + code.length + ") " + code[i]);
        
        // check if output expected
        if (!code[i].startsWith(">"))
            System.out.println("!!! Problem at line " + i++ + ": Output expected. Your output was too short.");
        
        // think like an user, act like an user
        return code[i++].substring(2);
    }
    
}
