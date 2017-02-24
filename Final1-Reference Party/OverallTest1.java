package edu.kit.informatik.tests;

import static org.hamcrest.Matchers.*;
import org.junit.*;

import edu.kit.informatik.Terminal;

/**
 * Test for Everything (1) - should only succeed if all other tests don't fail
 *
 * @author Micha Hanselmann
 */
public class OverallTest1 {

    @BeforeClass
    public static void enableTerminalTestingMode() {
        Terminal.enableTestingMode();
    }
    
    @Before
    public void reset() {
        Terminal.reset();
    }

    @After
    public void run() {

        // run and terminate
        TestHelper.expectOk("quit");
        Wrapper.main();

        // cleanup
        Terminal.flush();
        Terminal.reset();
    }

    @Test
    public void test() {
        
        // add author
        TestHelper.expectOk("add author Malte,Moer");
        TestHelper.expectOk("add author Malte,Nagy");
        TestHelper.expectOk("add author Nico,Moer");
        TestHelper.expectOk("add author Andi,Bagger");
        TestHelper.expectOk("add author Jaris,Endres");
        TestHelper.expectOk("add author Fugaard,Morgan");
        TestHelper.expectOk("add author Lila,Antjanne");
        
        // add journal
        TestHelper.expectOk("add journal Physik aktuell,Verlegt");
        TestHelper.expectOk("add journal Informatik aktuell,Verlegt");
        TestHelper.expectOk("add journal e^x,Würth");
        TestHelper.expectOk("add journal NeClixt,Nagold");
        
        // add conference series
        TestHelper.expectOk("add conference series Years'con");
        TestHelper.expectOk("add conference series Mastrophia");
        TestHelper.expectOk("add conference series (//build#conf)");
        TestHelper.expectOk("add conference series CeBIT");
        
        // add conference
        TestHelper.expectOk("add conference Years'con,2015,Göppingen");
        TestHelper.expectOk("add conference Years'con,1996,Würselen");
        TestHelper.expectOk("add conference Years'con,2017,Oslo");
        TestHelper.expectOk("add conference Mastrophia,2016,Berlin");
        TestHelper.expectOk("add conference Mastrophia,2014,Stuttgart");
        TestHelper.expectOk("add conference (//build#conf),2016,Teheran");
        TestHelper.expectOk("add conference (//build#conf),2017,Treppendorf");
        TestHelper.expectOk("add conference (//build#conf),2015,Candelária");
        TestHelper.expectOk("add conference CeBIT,2016,Hannover");
        TestHelper.expectOk("add conference CeBIT,2017,Hannover");
        
        // add article
        TestHelper.expectOk("add article to series Years'con:cc2015,2015,Climate change in contrast");
        TestHelper.expectOk("add article to series Years'con:mc2015,2015,My cat and me");
        TestHelper.expectOk("add article to series Years'con:li1996,1996,My idea about indoor lights");
        TestHelper.expectOk("add article to series Years'con:pb2017,2017,Paper bin's hidden treasures");
        TestHelper.expectOk("add article to series Mastrophia:sy2016,2016,Audio synthesis by using particle systems");
        TestHelper.expectOk("add article to series Mastrophia:bi2014,2014,An introcution into binaural beats");
        TestHelper.expectOk("add article to series Mastrophia:cd2014,2014,Buying CD's won't help 'em either");
        TestHelper.expectOk("add article to series (//build#conf):make2016,2016,Make programming great again!");
        TestHelper.expectOk("add article to series (//build#conf):soad2017,2017,Social advantages");
        TestHelper.expectOk("add article to series (//build#conf):reac2017,2017,Build reactive apps - NOW.");
        TestHelper.expectOk("add article to series (//build#conf):brea2015,2015,The importance of breaks in test-driven development");
        TestHelper.expectOk("add article to series (//build#conf):test2015,2015,Testing could be fun... if you do it right");
        TestHelper.expectOk("add article to series CeBIT:sf2016,2016,Security flaws #123-hacked");
        TestHelper.expectOk("add article to series CeBIT:ch2016,2016,Why China will buy your job");
        TestHelper.expectOk("add article to journal Physik aktuell:qua2015,2015,Quantenphysik lässt Forscher rätseln");
        TestHelper.expectOk("add article to journal Physik aktuell:sam2015,2015,SAM errechnet Formel zur Weltherrschaft");
        TestHelper.expectOk("add article to journal Informatik aktuell:lin2002,2002,Warum Linux das Ding sein wird");
        TestHelper.expectOk("add article to journal Informatik aktuell:lin2003,2003,Warum Linux das Ding ist");
        TestHelper.expectOk("add article to journal Informatik aktuell:lin2004,2004,Warum Linux das Ding gewesen sein wird");
        TestHelper.expectOk("add article to journal Informatik aktuell:lin2005,2005,Linux - die Zukunft");
        TestHelper.expectOk("add article to journal Informatik aktuell:lin2008,2008,Linux - warum ...");
        TestHelper.expectOk("add article to journal e^x:fp2014,2014,Fixpunkte im Visir");
        TestHelper.expectOk("add article to journal e^x:st2014,2014,Stetigkeit - was wie jetzt wissen müssen");
        TestHelper.expectOk("add article to journal e^x:st2017,2017,-1 Trick & der Trick auf den alle gewartet haben");
        TestHelper.expectOk("add article to journal NeClixt:me2017,2017,Der Mensch");
        TestHelper.expectOk("add article to journal NeClixt:ma2017,2017,Der Maschine");
        TestHelper.expectOk("add article to journal NeClixt:ge2017,2017,Der Gerät");
        
        // written-by
        TestHelper.expectOk("written-by cc2015,Malte Moer");
        TestHelper.expectOk("written-by cc2015,Malte Nagy");
        TestHelper.expectOk("written-by mc2015,Malte Nagy;Nico Moer");
        TestHelper.expectOk("written-by li1996,Jaris Endres;Fugaard Morgan;Fugaard Morgan;Lila Antjanne");
        TestHelper.expectOk("written-by pb2017,Malte Nagy");
        TestHelper.expectOk("written-by sy2016,Andi Bagger");
        TestHelper.expectOk("written-by bi2014,Andi Bagger");
        TestHelper.expectOk("written-by cd2014,Andi Bagger");
        TestHelper.expectOk("written-by make2016,Nico Moer;Fugaard Morgan");
        TestHelper.expectOk("written-by soad2017,Malte Moer");
        TestHelper.expectOk("written-by reac2017,Malte Moer;Fugaard Morgan");
        TestHelper.expectOk("written-by brea2015,Malte Moer");
        TestHelper.expectOk("written-by sf2016,Malte Moer");
        TestHelper.expectOk("written-by ch2016,Malte Nagy");
        TestHelper.expectOk("written-by qua2015,Jaris Endres");
        TestHelper.expectOk("written-by sam2015,Jaris Endres");
        TestHelper.expectOk("written-by lin2002,Lila Antjanne");
        TestHelper.expectOk("written-by lin2003,Lila Antjanne");
        TestHelper.expectOk("written-by lin2008,Lila Antjanne");
        TestHelper.expectOk("written-by fp2014,Malte Moer;Nico Moer");
        TestHelper.expectOk("written-by st2014,Malte Moer;Nico Moer");
        TestHelper.expectOk("written-by st2017,Malte Moer;Nico Moer;Malte Nagy;Fugaard Morgan");
        TestHelper.expectOk("written-by me2017,Lila Antjanne");
        TestHelper.expectOk("written-by ge2017,Lila Antjanne");
        
        // cites
        TestHelper.expectOk("cites cc2015,li1996");
        TestHelper.expectOk("cites pb2017,li1996");
        TestHelper.expectOk("cites pb2017,sy2016");
        TestHelper.expectOk("cites pb2017,qua2015");
        TestHelper.expectOk("cites pb2017,fp2014");
        TestHelper.expectOk("cites me2017,sy2016");
        TestHelper.expectOk("cites me2017,qua2015");
        TestHelper.expectOk("cites me2017,fp2014");
        TestHelper.expectOk("cites sf2016,qua2015");
        TestHelper.expectOk("cites sf2016,fp2014");
        TestHelper.expectOk("cites bi2014,lin2002");
        TestHelper.expectOk("cites bi2014,lin2003");
        TestHelper.expectOk("cites bi2014,lin2008");
        TestHelper.expectOk("cites cd2014,lin2002");
        TestHelper.expectOk("cites cd2014,lin2003");
        TestHelper.expectOk("cites cd2014,lin2008");
        TestHelper.expectOk("cites make2016,lin2003");
        TestHelper.expectOk("cites make2016,lin2002");
        TestHelper.expectOk("cites make2016,lin2008");
        TestHelper.expectOk("cites make2016,bi2014");
        
        // add keywords
        TestHelper.expectOk("add keywords to series Years'con:year;conference;important;hashtag");
        TestHelper.expectOk("add keywords to series Mastrophia:audio;synthesis;daw;coolstuff;andi");
        TestHelper.expectOk("add keywords to series (//build#conf):conference;development;build;windoof;linux");
        TestHelper.expectOk("add keywords to series CeBIT:computer;business;viellaufen");
        TestHelper.expectOk("add keywords to journal Physik aktuell:physik;einstein;knapp;nobelpreis");
        TestHelper.expectOk("add keywords to journal Informatik aktuell:technik;computer;linux;windoof");
        TestHelper.expectOk("add keywords to journal e^x:mathematik;bambus;integral;ichhassela");
        TestHelper.expectOk("add keywords to journal NeClixt:mensch;natur;maschine;muskelschmiede");
        TestHelper.expectOk("add keywords to conference Years'con,2015:climate;fakenews;cat");
        TestHelper.expectOk("add keywords to conference Years'con,1996:light;indoor;smarthome"); // 1996 - really?
        TestHelper.expectOk("add keywords to conference Mastrophia,2016:audio;synthesis;particle");
        TestHelper.expectOk("add keywords to conference Mastrophia,2014:stereo;illusion;binaural;cd;spotify;streaming");
        TestHelper.expectOk("add keywords to conference (//build#conf),2016:programming");
        TestHelper.expectOk("add keywords to conference (//build#conf),2015:programming;test");
        TestHelper.expectOk("add keywords to conference (//build#conf),2017:programming;app;react;social");
        TestHelper.expectOk("add keywords to conference CeBIT,2016:security;hack;fakenews");
        TestHelper.expectOk("add keywords to conference CeBIT,2017:nothing");
        TestHelper.expectOk("add keywords to pub cc2015:change");
        TestHelper.expectOk("add keywords to pub mc2015:cat;me");
        TestHelper.expectOk("add keywords to pub li1996:lights");
        TestHelper.expectOk("add keywords to pub pb2017:bin;treasure");
        TestHelper.expectOk("add keywords to pub bi2014:introduction;beat");
        TestHelper.expectOk("add keywords to pub cd2014:doomed");
        TestHelper.expectOk("add keywords to pub make2016:impact");
        TestHelper.expectOk("add keywords to pub soad2017:impact");
        TestHelper.expectOk("add keywords to pub brea2015:bug;error");
        TestHelper.expectOk("add keywords to pub qua2015:quant;light;heisenberg");
        TestHelper.expectOk("add keywords to pub lin2002:kernel;bug");
        TestHelper.expectOk("add keywords to pub fp2014:gbi");
        TestHelper.expectOk("add keywords to pub ge2017:muscle");
        
        // all publications
        Terminal.addMultipleLineOutputThatMatches("all publications", containsInAnyOrder("cc2015", "mc2015", "li1996", "pb2017", "sy2016", "bi2014",
                "cd2014", "make2016", "soad2017", "reac2017", "brea2015", "test2015", "sf2016", "ch2016", "qua2015", "sam2015", "lin2002", "lin2003",
                "lin2004", "lin2005", "lin2008", "fp2014", "st2014", "st2017", "me2017", "ma2017", "ge2017"));
        
        // list invalid publications
        Terminal.addMultipleLineOutputThatMatches("list invalid publications", containsInAnyOrder("test2015", "lin2004", "lin2005", "ma2017"));
        
        // publications by
        Terminal.addMultipleLineOutputThatMatches("publications by Malte Moer", containsInAnyOrder("cc2015", "soad2017", "reac2017", "brea2015",
                "sf2016", "fp2014", "st2014", "st2017"));
        Terminal.addMultipleLineOutputThatMatches("publications by Malte Moer;Lila Antjanne", containsInAnyOrder("cc2015", "soad2017", "reac2017", "brea2015",
                "sf2016", "fp2014", "st2014", "st2017", "li1996", "lin2002", "lin2003", "lin2008", "me2017", "ge2017"));
        Terminal.addMultipleLineOutputThatMatches("publications by Jaris Endres", containsInAnyOrder("li1996", "qua2015", "sam2015"));
        Terminal.addMultipleLineOutputThatMatches("publications by Fugaard Morgan;Andi Bagger", containsInAnyOrder("li1996",
                "reac2017", "make2016", "st2017", "sy2016", "bi2014", "cd2014"));
        
        // in proceedings
        Terminal.addMultipleLineOutputThatMatches("in proceedings Years'con,2015", containsInAnyOrder("cc2015", "mc2015"));
        Terminal.addMultipleLineOutputThatMatches("in proceedings Mastrophia,2016", containsInAnyOrder("sy2016"));
        Terminal.addMultipleLineOutputThatMatches("in proceedings (//build#conf),2017", containsInAnyOrder("soad2017", "reac2017"));
        Terminal.addNoOutput("in proceedings CeBIT,2017");
        
        // find keywords
        Terminal.addMultipleLineOutputThatMatches("find keywords audio;synthesis", containsInAnyOrder("sy2016", "bi2014", "cd2014"));
        Terminal.addMultipleLineOutputThatMatches("find keywords audio;synthesis;stereo", containsInAnyOrder("bi2014", "cd2014"));
        Terminal.addMultipleLineOutputThatMatches("find keywords programming", containsInAnyOrder("make2016", "soad2017", "reac2017", "brea2015", "test2015"));
        Terminal.addMultipleLineOutputThatMatches("find keywords programming;development", containsInAnyOrder("make2016", "soad2017", "reac2017", "brea2015", "test2015"));
        Terminal.addMultipleLineOutputThatMatches("find keywords muscle", containsInAnyOrder("ge2017"));
        Terminal.addMultipleLineOutputThatMatches("find keywords fakenews", containsInAnyOrder("cc2015", "mc2015", "sf2016", "ch2016"));
        Terminal.addMultipleLineOutputThatMatches("find keywords fakenews;climate", containsInAnyOrder("cc2015", "mc2015"));
        Terminal.addMultipleLineOutputThatMatches("find keywords fakenews;climate;change", containsInAnyOrder("cc2015"));
        Terminal.addMultipleLineOutputThatMatches("find keywords conference", containsInAnyOrder("cc2015", "mc2015", "li1996", "pb2017", "make2016",
                "soad2017", "reac2017", "brea2015", "test2015"));
        
        // similarity
        Terminal.addSingleLineOutputThatIsExactly("similarity sf2016,fp2014", "0.000");
        Terminal.addSingleLineOutputThatIsExactly("similarity sf2016,st2017", "0.000");
        Terminal.addSingleLineOutputThatIsExactly("similarity cc2015,li1996", "0.333");
        Terminal.addSingleLineOutputThatIsExactly("similarity cc2015,mc2015", "0.777");
        Terminal.addSingleLineOutputThatIsExactly("similarity sy2016,cd2014", "0.384");
        Terminal.addSingleLineOutputThatIsExactly("similarity bi2014,cd2014", "0.785");
        Terminal.addSingleLineOutputThatIsExactly("similarity make2016,soad2017", "0.700");
        Terminal.addSingleLineOutputThatIsExactly("similarity make2016,test2015", "0.750");
        Terminal.addSingleLineOutputThatIsExactly("similarity lin2003,lin2004", "1.000");
        
        // h-index
        Terminal.addSingleLineOutputThatIsExactly("h-index Malte Moer", "1");
        Terminal.addSingleLineOutputThatIsExactly("h-index Malte Nagy", "0");
        Terminal.addSingleLineOutputThatIsExactly("h-index Nico Moer", "1");
        Terminal.addSingleLineOutputThatIsExactly("h-index Andi Bagger", "1");
        Terminal.addSingleLineOutputThatIsExactly("h-index Jaris Endres", "2");
        Terminal.addSingleLineOutputThatIsExactly("h-index Fugaard Morgan", "1");
        Terminal.addSingleLineOutputThatIsExactly("h-index Lila Antjanne", "3");
        
        // coauthors of
        Terminal.addMultipleLineOutputThatMatches("coauthors of Malte Moer", containsInAnyOrder("Fugaard Morgan", "Malte Nagy", "Nico Moer"));
        Terminal.addMultipleLineOutputThatMatches("coauthors of Malte Nagy", containsInAnyOrder("Fugaard Morgan", "Malte Moer", "Nico Moer"));
        Terminal.addMultipleLineOutputThatMatches("coauthors of Nico Moer", containsInAnyOrder("Fugaard Morgan", "Malte Nagy", "Malte Moer"));
        Terminal.addNoOutput("coauthors of Andi Bagger");
        Terminal.addMultipleLineOutputThatMatches("coauthors of Jaris Endres", containsInAnyOrder("Fugaard Morgan", "Lila Antjanne"));
        Terminal.addMultipleLineOutputThatMatches("coauthors of Fugaard Morgan", containsInAnyOrder("Jaris Endres", "Lila Antjanne", "Malte Moer", "Malte Nagy", "Nico Moer"));
        Terminal.addMultipleLineOutputThatMatches("coauthors of Lila Antjanne", containsInAnyOrder("Jaris Endres", "Fugaard Morgan"));
        
        // foreign citations of
        Terminal.addMultipleLineOutputThatMatches("foreign citations of Malte Moer", containsInAnyOrder("me2017"));
        Terminal.addNoOutput("foreign citations of Malte Nagy");
        Terminal.addMultipleLineOutputThatMatches("foreign citations of Nico Moer", containsInAnyOrder("me2017"));
        Terminal.addMultipleLineOutputThatMatches("foreign citations of Andi Bagger", containsInAnyOrder("make2016", "me2017", "pb2017"));
        Terminal.addMultipleLineOutputThatMatches("foreign citations of Jaris Endres", containsInAnyOrder("sf2016", "cc2015", "pb2017"));
        Terminal.addNoOutput("foreign citations of Fugaard Morgan");
        Terminal.addMultipleLineOutputThatMatches("foreign citations of Lila Antjanne", containsInAnyOrder("bi2014", "cc2015", "cd2014", "pb2017"));
        
        // print bibliography
        Terminal.addMultipleLinesOutputThatIsExactly("print bibliography ieee:me2017;me2017;cc2015;cd2014;pb2017", 
                "[1] L. Antjanne, \"Der Mensch,\" NeClixt, 2017.",
                "[2] A. Bagger, \"Buying CD's won't help 'em either,\" in Proceedings of Mastrophia, Stuttgart, 2014.",
                "[3] M. Moer and M. Nagy, \"Climate change in contrast,\" in Proceedings of Years'con, Göppingen, 2015.",
                "[4] M. Nagy, \"Paper bin's hidden treasures,\" in Proceedings of Years'con, Oslo, 2017.");
        
        Terminal.addMultipleLinesOutputThatIsExactly("print bibliography chicago:me2017;me2017;cc2015;cd2014;pb2017", 
                "(Antjanne, 2017) Antjanne, Lila. \"Der Mensch.\" NeClixt (2017).",
                "(Bagger, 2014) Bagger, Andi. \"Buying CD's won't help 'em either.\" Paper presented at Mastrophia, 2014, Stuttgart.",
                "(Moer, 2015) Moer, Malte, and Nagy, Malte. \"Climate change in contrast.\" Paper presented at Years'con, 2015, Göppingen.",
                "(Nagy, 2017) Nagy, Malte. \"Paper bin's hidden treasures.\" Paper presented at Years'con, 2017, Oslo.");
        
        Terminal.addMultipleLinesOutputThatIsExactly("print bibliography ieee:fp2014;lin2008;qua2015;brea2015;make2016;li1996;li1996", 
                "[1] L. Antjanne, \"Linux - warum ...,\" Informatik aktuell, 2008.",
                "[2] J. Endres, \"Quantenphysik lässt Forscher rätseln,\" Physik aktuell, 2015.",
                "[3] J. Endres et al., \"My idea about indoor lights,\" in Proceedings of Years'con, Würselen, 1996.",
                "[4] M. Moer, \"The importance of breaks in test-driven development,\" in Proceedings of (//build#conf), Candelária, 2015.",
                "[5] M. Moer and N. Moer, \"Fixpunkte im Visir,\" e^x, 2014.",
                "[6] N. Moer and F. Morgan, \"Make programming great again!,\" in Proceedings of (//build#conf), Teheran, 2016.");
        
        Terminal.addMultipleLinesOutputThatIsExactly("print bibliography chicago:fp2014;lin2008;qua2015;brea2015;make2016;li1996;li1996", 
                "(Antjanne, 2008) Antjanne, Lila. \"Linux - warum ....\" Informatik aktuell (2008).",
                "(Endres, 2015) Endres, Jaris. \"Quantenphysik lässt Forscher rätseln.\" Physik aktuell (2015).",
                "(Endres, 1996) Endres, Jaris, Morgan, Fugaard, Morgan, Fugaard, and Antjanne, Lila. \"My idea about indoor lights.\" Paper presented at Years'con, 1996, Würselen.",
                "(Moer, 2015) Moer, Malte. \"The importance of breaks in test-driven development.\" Paper presented at (//build#conf), 2015, Candelária.",
                "(Moer, 2014) Moer, Malte, and Moer, Nico. \"Fixpunkte im Visir.\" e^x (2014).",
                "(Moer, 2016) Moer, Nico, and Morgan, Fugaard. \"Make programming great again!.\" Paper presented at (//build#conf), 2016, Teheran.");
        
    }
    
}
