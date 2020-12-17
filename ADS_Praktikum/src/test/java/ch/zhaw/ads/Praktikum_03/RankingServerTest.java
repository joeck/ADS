package ch.zhaw.ads.Praktikum_03;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

public class RankingServerTest {
    RankingServer rankingServer;
    String sortedNameRankOutput =  "Nr Name,Vorname  JG   Wohnort   Zeit\n"  +
            "507 Abbenseth Ben 1980 03:13:55.0\n"  +
            "1567 Abderhalden Claude 1982 03:41:42.6\n";
    String sortedRankOutput = "Nr Name,Vorname  JG   Wohnort   Zeit\n"  +
            "1 Kiptum Daniel 1978 02:11:31.1\n" +
            "2 Hamd Mohamednur 1978 02:19:45.7\n";

    @Before
    public void setUp() throws Exception {
        rankingServer = new RankingServer();
    }

    @Test
    public void testRank() throws Exception {
        String rangZuerich = "/Users/tim/Google Drive/_Schule/Bachelor IT/Semester_3/Algorithmen und Datenstrukturen/Praktika und Loesungen/ADS_Praktikum/src/main/resources/RangZuerich.csv";
        String validFile = openFile(rangZuerich);
        rankingServer.insertCompetitor(validFile);
        rankingServer.sortRank();
        String testValue = rankingServer.getOutputData().toString();
        assertTrue(testValue.startsWith(sortedRankOutput));
    }

    @Test
    public void testRankName() throws Exception {
        String rangZuerich = "/Users/tim/Google Drive/_Schule/Bachelor IT/Semester_3/Algorithmen und Datenstrukturen/Praktika und Loesungen/ADS_Praktikum/src/main/resources/RangZuerich.csv";
        String validFile = openFile(rangZuerich);
        rankingServer.insertCompetitor(validFile);
        rankingServer.sortRank();
        rankingServer.sortName();
        String testValue = rankingServer.getOutputData().toString();
        assertTrue(testValue.startsWith(sortedNameRankOutput));
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
