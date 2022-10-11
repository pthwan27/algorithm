package com.backjoon.algorithm.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1003_피보나치함수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		int[] Narr = new int[41];
		Narr[0] = 0;
		Narr[1] = 1;

		// TC 만큼 돌기위해
		for (int tc = 1; tc <= T; tc++) {

			// fibonachi를 for문으로 미리 구해놓기, 재귀함수 X
			for (int i = 2; i < Narr.length; i++) {
				Narr[i] = Narr[i - 1] + Narr[i - 2];
			}

			int N = Integer.parseInt(br.readLine());

			if (N == 1) {
				System.out.println("0 1");
			} else if (N == 0) {
				System.out.println("1 0");
			} else {
				System.out.printf("%d %d%n", Narr[N - 1], Narr[N]);
			}

		}
	}
}
