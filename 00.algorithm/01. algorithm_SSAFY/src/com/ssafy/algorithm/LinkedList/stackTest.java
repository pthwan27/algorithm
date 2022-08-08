package com.ssafy.algorithm.LinkedList;

public class stackTest {

	public static void main(String[] args) {
		SsafyStack<String> sstack = new SsafyStack<>();
		
		System.out.println(sstack.isEmpty());
		sstack.push("A");
		sstack.push("B");
		sstack.push("C");
		
		System.out.println(sstack.peek());		
		System.out.println(sstack.size());
		
		sstack.pop();
		sstack.pop();
		
		System.out.println(sstack.peek());		
		System.out.println(sstack.size());
	}

}
