package com.swea.algorithm;

import java.util.Scanner;

public class D3_6913_동철이의프로그래밍대회 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		int N = 0;
		int M = 0;

		int[][] area;

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();

			area = new int[N][M];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					area[i][j] = sc.nextInt();
				}
			}
			// 1등한 사람 수 / 푼 문제 수
			int personCount = 0;
			int problemCount = 0;

			// 가장 많이 푼 사람의 문제 수 저장
			int maxProCount = -1;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (area[i][j] == 1) {
						problemCount++;
					}
				}

				// 푼 문제수가 같으면 사람수만 증가
				if (maxProCount == problemCount) {
					personCount++;

					maxProCount = problemCount;
					problemCount = 0;
				}
				// 더 많이 문제를 푼 사람이 나오면, 사람은 한명으로 바꾸고 그 사람이 푼 문제수가 최대값으로
				else if (problemCount > maxProCount) {
					personCount = 1;

					maxProCount = problemCount;
					problemCount = 0;
				} else {
					problemCount = 0;
				}

			}

			System.out.printf("#%d %d %d%n", tc, personCount, maxProCount);
		}
	}

}
