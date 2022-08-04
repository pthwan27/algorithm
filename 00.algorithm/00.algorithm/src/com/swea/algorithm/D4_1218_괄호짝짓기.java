package com.swea.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * @author 박태환 * 괄호는 총 4종류, 
 * boolean 을 4종류 만들고 열리면 false, 닫히면 true 처리 - > X 괄호의 숫자가 다를수도 있음.(마지막에만 닫히는거 오면 ?)
 *  스택에 담아놓고    하나씩 꺼내면서 검사.
 */
public class D4_1218_괄호짝짓기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;
		

		for (int tc = 1; tc <= 1; tc++) {
			int N = Integer.parseInt(in.readLine());

			Stack<Character> brackets = new Stack<>();
			String inputStr = in.readLine();
			for (int i = 0; i < inputStr.length(); i++) {
				brackets.push(inputStr.charAt(i));
			}

			int bracket1open = 0; // (
			int bracket1close = 0; // )
			
			int bracket2open = 0; // [
			int bracket2close = 0; // ]
			
			
			int bracket3open = 0; // {
			int bracket3close = 0; // }			

			int bracket4open = 0; // <
			int bracket4close = 0; // >
			
			while (!brackets.isEmpty()) {
				switch (brackets.pop()) {
				case '(':
					bracket1open++;
					break;
				case ')':
					bracket1close++;
					break;
				case '[':
					bracket2open++;
					break;
				case ']':
					bracket2close++;
					break;
				case '{':
					bracket3open++;
					break;
				case '}':
					bracket3close++;
					break;
				case '<':
					bracket4open++;
					break;
				case '>':
					bracket4close++;
					break;
				}
			}
			
			if(bracket1open == bracket1close && bracket2open == bracket2close 
					&& bracket3open == bracket3close && bracket4open == bracket4close) {				
				System.out.println("#" +tc + " " + 1);
				System.out.printf("( ) : %d개%n", bracket1close);
				System.out.printf("[ ] : %d개%n", bracket2close);
				System.out.printf("{ } : %d개%n", bracket3close);
				System.out.printf("< > : %d개%n", bracket4close);
			}
			else {
				System.out.println("#" +tc + " " + 0);
			}
		}
	}

}
