package com.swea.algorithm;

import java.util.Arrays;
import java.util.Scanner;

class SWEA13428숫자조작 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		int T = sc.nextInt();

		// 한번만 바꾸는 거라면, 자릿수가 제일 중요!
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();

			max = N;
			min = N;

			String temp = String.valueOf(N);

			char[] numChar = new char[temp.length()];
			char[] originNumChar = new char[temp.length()];

			for (int i = 0; i < temp.length(); i++) {
				numChar[i] = temp.charAt(i);
				originNumChar[i] = temp.charAt(i);
			}

			for (int i = 0; i < temp.length(); i++) {
				for (int j = temp.length() - 1; j >= i; j--) {
					if (i == j) {
						continue;
					}

					// 숫자 한쌍 위치 바꾸기
					char c1 = numChar[i];
					numChar[i] = numChar[j];
					numChar[j] = c1;

					String changeNumStr = new String(numChar);
					int changeNum = Integer.parseInt(changeNumStr);
                    
                    //맨 앞자리가 0이 아닐 때, 최소값 최대값 구하기
					if (numChar[0] != '0' && changeNum > max) {
						max = changeNum;
					}

					if (numChar[0] != '0' && changeNum < min) {
						min = changeNum;
					}

					numChar = Arrays.copyOf(originNumChar, numChar.length);
				}
			}
			System.out.printf("#%d %d %d%n", tc, min, max);
		}
	}
}
