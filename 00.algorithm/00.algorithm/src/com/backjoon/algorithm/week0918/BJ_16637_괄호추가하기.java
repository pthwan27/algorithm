package com.backjoon.algorithm.week0918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ_16637_괄호추가하기 {

	static ArrayList<Integer> numList;
	static ArrayList<Character> operList;
	static int maxResult = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());
		numList = new ArrayList<>();
		operList = new ArrayList<>();

		String inputStr = in.readLine();
		for (int i = 0; i < N; i++) {
			if (i % 2 == 1)
				operList.add(inputStr.charAt(i));

			else {
				numList.add(inputStr.charAt(i) - '0');
			}
		}

		// 첫번째 수
		dfs(0, numList.get(0));
	}

	private static void dfs(int cur, int num) {		
		if(cur >= operList.size()) {
			maxResult = Math.max(maxResult, num);
			return;
		}

		int sum = calc(num, operList.get(cur), numList.get(cur+1));
		dfs(cur+1, sum);
		
		//괄호칠때
		if(cur+1 < operList.size()) {
			System.out.println(num);
			int bracketSum = calc(numList.get(cur+1), operList.get(cur+1),numList.get(cur+2));
			
//			System.out.println(bracket);
		}
		
	}

	private static int calc(int num1, char oper, int num2) {
		switch (oper) {
		case '+':
			return num1 + num2;
		case '-':
			return num1-num2;			
		case '*':
			return num1*num2;
		}
		return 0;
	}
}