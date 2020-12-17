package ch.zhaw.ads.Praktikum_01;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

/**
 * @(#)WellformedXMLServerTest.java
 * @author Tim Rhomberg
 * @version 1.0 2020/9/21
 */
public class WellformedXMLServerTest {
    WellformedXMLServer server;

    @Before
    public void setUp() throws Exception {
        server = new WellformedXMLServer();
    }

    @Test
    public void testXMLFile() throws Exception {
        String validXMLFilePath = "/Users/tim/Google Drive/_Schule/Bachelor IT/Semester_3/Algorithmen und Datenstrukturen/Praktika und Loesungen/ADS_Praktikum/src/test/resources/validXMLFile";
        String validFile = openFile(validXMLFilePath);
        String invalidXMLFilePath = "/Users/tim/Google Drive/_Schule/Bachelor IT/Semester_3/Algorithmen und Datenstrukturen/Praktika und Loesungen/ADS_Praktikum/src/test/resources/NotValidXMLFile";
        String invalidFile = openFile(invalidXMLFilePath);

        assertTrue(server.checkWellformed(validFile));
        assertFalse(server.checkWellformed(invalidFile));
    }

    private String openFile(String name)  throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(name), "ISO-8859-1"));
        StringBuffer b = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            b.append(line);
            b.append('\n');
        }
        return b.toString();
    }
}
