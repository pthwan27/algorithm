package com.swea.algorithm.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * @author 박태환 * 괄호는 총 4종류, 
 * boolean 을 4종류 만들고 열리면 false, 닫히면 true 처리 - > X 괄호의 숫자가 다를수도 있음.(마지막에만 닫히는거 오면 ?)
 *  스택에 담아놓고    하나씩 꺼내면서 검사.
 */
public class D4_1218_괄호짝짓기_다시해보기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;
		int answer;

		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(in.readLine());

			Stack<Character> brackStack = new Stack<>();
			String inputStr = in.readLine();
			
			if(inputStr.length() % 2 == 1) {
				answer = 0;
			}else {
				answer = 1;
			
			
			for (int i = 0; i < inputStr.length(); i++) {
				char ch = inputStr.charAt(i);
				
				
				if(ch == '(' || ch == '{' || ch == '<' || ch == '[') {
					brackStack.push(ch);					
				}else {
					
				}
					
			}

			}
		}
	}

}
