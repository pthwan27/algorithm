package com.backjoon.algorithm.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_2805_농작물수확하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// tc 수
		int T = Integer.parseInt(in.readLine());
		StringTokenizer st;

		for (int tc = 1; tc <= T; tc++) {
			// 농장의 크기
			int N = Integer.parseInt(in.readLine());
			int[][] farmArr = new int[N][N];

			for (int i = 0; i < N; i++) {
				String temp = in.readLine();
				for (int j = 0; j < N; j++) {
					farmArr[i][j] = temp.charAt(j) - '0';
				}
			}

			//농작물의 합 저장할 변수
			int Sum = 0;

			//0 ~ 중간줄까지의 합 (삼각형)
			for (int i = 0; i < N / 2 + 1; i++) {
				for (int j = N / 2 - i; j <= N / 2 + i; j++) {
					Sum += farmArr[i][j];
				}
			}

			// 역삼각형을 만들기 위해
			// 5,3,1 개씩 뽑기위해 만든 변수
			int c = N / 2 - 1;

			//중간부터 끝까지의 합 (역삼각형)
			for (int i = N / 2 + 1; i < N; i++) {
				for (int j = N / 2 - c; j <= N / 2 + c; j++) {
					Sum += (farmArr[i][j]);
				}
				c--;
			}
			System.out.printf("#%d %d%n", tc, Sum);
		}
	}
}
