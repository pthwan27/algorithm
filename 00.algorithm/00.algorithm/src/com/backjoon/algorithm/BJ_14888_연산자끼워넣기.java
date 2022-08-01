package com.backjoon.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 연산자끼워넣기, 숫자의 갯수를 알려주고, 숫자를 입력받는다.
 * 
 * 연산자들을 받는데, 숫자의 갯수 -1개 만큼 덧셈, 뺄셈, 곱셈, 나눗셈 순서대로 받는다.
 * 
 * @author parks
 */
public class BJ_14888_연산자끼워넣기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		// 숫자 갯수만큼 배열 생성
		int[] numArr = new int[N];

		// 숫자들 입력받기.
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int index = 0; index < N; index++) {
			numArr[index] = Integer.parseInt(st.nextToken());
		}

		// 연산자의 갯수 카운팅( 덧셈, 뺄셈, 곱셈, 나눗셈 )
		int[] operCount = new int[4];
		st = new StringTokenizer(br.readLine(), " ");
		for (int index = 0; index < 4; index++) {
			operCount[index] = Integer.parseInt(st.nextToken());
		}

		// 연산자를 저장하기 위해(+,-,*,/ 의 갯수만큼)
		char[] operChar = new char[N - 1];
		int count = 0;

		for (int i = 0; i < 4; i++) {
			while (operCount[i] > 0) {
				switch (i) {
				case 0:
					operChar[count] = '+';
					operCount[i]--;
					count++;
					break;
				case 1:
					operChar[count] = '-';
					operCount[i]--;
					count++;
					break;
				case 2:
					operChar[count] = '*';
					operCount[i]--;
					count++;
					break;
				case 3:
					operChar[count] = '/';
					operCount[i]--;
					count++;
					break;
				}
			}
		}
		
		

	}
}
