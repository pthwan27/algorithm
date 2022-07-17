package com.swea.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1959_두개의숫자열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken()); // 테스트 케이스 개수

		int i = 0;

		for (i = 1; i <= T; i++) {
			int j = 0;

			st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[] an = new int[N];
			int[] am = new int[M];

			st = new StringTokenizer(br.readLine());
			// 각 배열에 값 넣기
			for (j = 0; j < N; j++) {
				an[j] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (j = 0; j < M; j++) {
				am[j] = Integer.parseInt(st.nextToken());
			}

			// 몇 번 계산할지 구해놓기
			int count = Math.abs(N - M);
			int result = -999999; // 최대값을 저장해두기 위해

			for (j = 0; j <= count; j++) {
				int temp = 0;
				if (N < M) // 위에 것이 더 작을 때 ex n = 3 / m = 5
				{
					for (int k = 0; k < N; k++) {
						temp += (an[k] * am[k + j]);
					}
					if (temp > result) {
						result = temp;
					}
					temp = 0;
				} else if (N == M) {
					result = 0; // 임시로 0처리
				} else // 아래 것이 더 작을 때 ex n = 5 / m = 3
				{
					for (int k = 0; k < M; k++) {
						temp += (am[k] * an[k + j]);
					}
					if (temp > result) {
						result = temp;
					}
					temp = 0;
				}
			}

			System.out.println("#" + i + " " + result);

		}
	}

}
