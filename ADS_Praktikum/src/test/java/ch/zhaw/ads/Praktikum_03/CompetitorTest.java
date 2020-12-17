package ch.zhaw.ads.Praktikum_03;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CompetitorTest {
    Competitor competitorDaniel;
    Competitor competitorOtt;

    @Before
    public void setUp() throws Exception {
        competitorDaniel = new Competitor(3, "Kiptum Daniel", 1978, "Reconvilier", "02:11:31.1");
        competitorOtt = new Competitor(53, "Ott Michael", 1982, "Kilchberg ZH", "02:33:48.9");
    }

    @Test
    public void testEqualsMethod() throws Exception {
        assertTrue(competitorDaniel.equals(competitorDaniel));
        assertFalse(competitorDaniel.equals(competitorOtt));
    }
}
