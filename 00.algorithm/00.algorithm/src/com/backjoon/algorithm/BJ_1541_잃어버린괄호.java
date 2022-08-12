package com.backjoon.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ_1541_잃어버린괄호 {

	/**
	 * 잃어버린 괄호 괄호를 식에 추가해 최소값을 찾기 첫째 줄에 식이 주어진다.
	 * 
	 * sol - > 1. -가 오면 다음 -가 올때까지 괄호로 묶기 2. -가 왔는데 다음 연산자가 안나오고 끝나면 괄호 닫고 끝내기
	 * 
	 * @param 박태환
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String inputStr = in.readLine();
		ArrayList<String> inputArr = new ArrayList<>();

		String temp = "";
		for (int idx = 0; idx < inputStr.length(); idx++) {
			if (inputStr.charAt(idx) == '-' || inputStr.charAt(idx) == '+') {
				inputArr.add(temp);
				temp = "";
				inputArr.add(String.valueOf(inputStr.charAt(idx)));
			} else {
				temp += inputStr.charAt(idx);
			}
		}

		inputArr.add(temp);

		System.out.println(inputArr);
		
		int result = Integer.parseInt(inputArr.get(0));
		
		int Sum = 0;

		for (int i = 1; i < inputArr.size(); i++) {
			if(inputArr.get(i).equals("-")) {
				
			}
		}

		System.out.println(result);
	}

}
