package ch.zhaw.ads.Praktikum_01;

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
		assertEquals(bs.checkBrackets(s), b);
	}

	@Test
	public void testBracket() {
		test("()",true);
		test("(()]",false);
		test("((([([])])))",true);
		test("[(])",false);
		test("[(3 +3)* 35 +3]* {3 +2}",true);
		test("[({3 +3)* 35} +3]* {3 +2}",false);
	}
}
