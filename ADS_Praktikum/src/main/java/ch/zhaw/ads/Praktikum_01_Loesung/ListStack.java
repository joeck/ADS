/**
 * @(#)ListStack.java
 *
 *
 * @author 
 * @version 1.00 2017/8/30
 */


package ch.zhaw.ads.Praktikum_01_Loesung;

import java.util.*;

public class ListStack {

	private List list = new LinkedList();

	public void push(Object o){
		list.add(0,o);
	}
	
	public Object pop(){
		if (isEmpty()) return null;
		return list.remove(0);
	}
	
	public Object peek(){
		return list.get(0);
	}
	
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	public void removeAll(){
		list.clear();
	}
	
	public boolean isFull(){
		return false;
	}	
		
	public int size() {
		return list.size();
	}
}