package com.backjoon.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 연산자끼워넣기, 숫자의 갯수를 알려주고, 숫자를 입력받는다. * 
 * 연산자들을 받는데, 숫자의 갯수 -1개 만큼 덧셈, 뺄셈, 곱셈, 나눗셈 순서대로 받는다. * 
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
		for (int idx = 0; idx < N; idx++) {
			numArr[idx] = Integer.parseInt(st.nextToken());
		}

		// 연산자의 갯수 카운팅( 덧셈, 뺄셈, 곱셈, 나눗셈 )
		int[] operCountArr = new int[4];
		st = new StringTokenizer(br.readLine(), " ");
		
		//count 연산자 있는 것 만큼 카운팅하기 위해 생성
		int operCnt = 0; 
		for (int idx = 0; idx < 4; idx++) {			
			operCnt += operCountArr[idx] = Integer.parseInt(st.nextToken());			
		}		
		
		char[] operCharArr = new char[operCnt];
		
		int count = 0;
		for(int idx = 0; idx < 4; idx++) {
			while(operCountArr[idx]-- > 0) {
				switch (idx) {
				case 0:
					operCharArr[count++] = '+';
					break;
				case 1:
					operCharArr[count++] = '-';
					break;
				case 2:
					operCharArr[count++] = '*';
					break;
				case 3:
					operCharArr[count++] = '/';
					break;
				default:
					break;
				}
			}
		}
		
		//연산자의 순열을 만들고, 이를 활용해 계산해보기
		//숫자위치 고정 ( 연산자를 돌려가며 계산 해보기 )
		
	}
}
