package ch.zhaw.ads.Praktikum_02;

import ch.zhaw.ads.Praktikum_02.BracketServer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class BracketServerTest {
	
	BracketServer bs;
	
	@Before
	public void setUp() throws Exception {
		bs = new BracketServer();
	}

	private void test(String s, boolean b) {
		assertEquals(s, b, bs.checkBrackets(s));
	}
	
	@Test
	public void testBracket() {
		// Aufgabe 1 (i)
		test(")",false);
		test("(",false);
		// Aufgabe 1 (ii)
		test("<(<>)>",true);
		test("<(<)>>",false);
		// Aufgabe 1 (iii)
		test("<*(<*<>*>)*>",true);
		test("<(<**>)*>",false);

		test("()",true);
		test("(()]",false);
		test("((([([])])))",true);
		test("[])",false);
		test("[(3 +3)* 35 +3]* {3 +2}",true) ;
		test("[({3 +3)* 35} +3]* {3 +2}",false);
	}

}
