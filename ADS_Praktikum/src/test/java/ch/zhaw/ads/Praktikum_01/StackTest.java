package ch.zhaw.ads.Praktikum_01; /**
 * @(#)StackTest.java
 *
 *
 * @author 
 * @version 1.00 2017/8/30
 */

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {
	
	ListStack stack;
	
	@Before
	public void setUp() throws Exception {
		stack = new ListStack(5);
	}
    
	@Test
	public void testPush1() {
		stack.push("A");
		Object o = stack.pop();
		assertEquals(o, "A");
	}	
		
	@Test
	public void testPush2() {
		stack.push("A");
		stack.push("B");
		assertEquals(stack.pop(), "B");
		assertEquals(stack.pop(), "A");
	}	
		
	@Test
	public void testIsEmpty() {
		assertTrue(stack.isEmpty());
		stack.push("A");
		assertFalse(stack.isEmpty());
		stack.pop();
		assertTrue(stack.isEmpty());
	}	
		
	@Test
	public void testIsFull() {
		assertFalse(stack.isFull());
	}	
		
	@Test
	public void testEmptyPop() {
		assertEquals(stack.pop(), null);
	}	
}