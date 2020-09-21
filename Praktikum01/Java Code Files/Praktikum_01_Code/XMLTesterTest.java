package Praktikum_01_Code;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class XMLTesterTest {
    XMLTester xmlTester;

    @Before
    public void setUp() throws Exception{
       xmlTester = new XMLTester();
    }

    @Test
    public void testTokens(){
        assertTrue(xmlTester.checkWellformed("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>"));
        assertTrue(xmlTester.checkWellformed("<token></token>"));
        assertTrue(xmlTester.checkWellformed("<token><okay>Bla</okay></token>"));
        assertFalse(xmlTester.checkWellformed("<token></not>"));
        assertTrue(xmlTester.checkWellformed("<Scan Code=\"-1\" Stop = \"false\">IV</Scan>"));
    }

    @Test
    public void testXMLFile() {
        String xml = "<root>\n" +
                "    <note>\n" +
                "        <to>Tove</to>\n" +
                "        <from>Jani</from>\n" +
                "        <heading>Reminder</heading>\n" +
                "        <to>To</to>\n" +
                "        <body>Don't forget me this weekend!</body>\n" +
                "    </note>\n" +
                "</root>\n";
        String xml2 = "<Scans xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" Stop = \"false\">\n" +
                "        <ScanListe>\n" +
                "            <Scan Code=\"-1\" Stop = \"false\">IV</Scan>\n" +
                "            <Scan Code=\"-4\" Stop = \"false\">6666000000001</Scan>\n" +
                "            <Scan Code=\"-30\" Stop = \"false\" WaitTime = \"500\">15</Scan>\n" +
                "            <Scan Code=\"-1\" Stop = \"false\"  Close = \"true\">IVENDE</Scan>\n" +
                "        </ScanListe>\n" +
                "    </Scans>";
        assertTrue(xmlTester.checkWellformed(xml));
        assertTrue(xmlTester.checkWellformed(xml2));
    }
}