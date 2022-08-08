package com.swea.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_9229_한빈이와SportMart_박태환 {
	// 마트에 N개의 과자봉지가 있고, 각 봉지는 ai그램의 무게를 가진다.
	// 2가지의 과자를 고르면서 M그램을 초과하지 않는 방법
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// Tc 갯수
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine());

			// 과자 개수
			int N = Integer.parseInt(st.nextToken());
			// 과자 2개의 최대 무게
			int M = Integer.parseInt(st.nextToken());

			int MaxWeight = Integer.MIN_VALUE;

			int[] snackArr = new int[N];
			st = new StringTokenizer(in.readLine());

			for (int i = 0; i < N; i++) {
				snackArr[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < N; i++) {
				if (snackArr[i] >= M) {
					continue;
				} else {
					for (int j = 0; j < N; j++) {
						if (i == j) {
							continue;
						}

						if (snackArr[i] + snackArr[j] <= M && snackArr[i] + snackArr[j] > MaxWeight) {
							MaxWeight = snackArr[i] + snackArr[j];
						}
					}
				}
			}

			if (MaxWeight < 0) {
				System.out.println("#" + tc + " " + "-1");
			} else {
				System.out.println("#" + tc + " " + MaxWeight);
			}
		}

	}

}
