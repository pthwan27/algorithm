package com.swea.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1979_어디에단어가들어갈수있을까 {
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		int c = 0;
		for (c = 1; c <= T; c++) {
			int n = 0;
			int k = 0;

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			// 1, 0 배열 생성
			int[][] arr = new int[n][n];

			int i = 0;
			int j = 0;

			for (i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int result = 0;

			int count = 0;
			boolean chk = false;

			// 가로검사
			for (i = 0; i < n; i++) {
				chk = false;
				for (j = 0; j < n; j++) {
					// 가로 확인하며 0, 1 인지 확인
					if (arr[i][j] == 1) {
						count++;
						chk = true;
					}

					else {
						chk = false;
					}

					if (j == (n - 1)) {
						chk = false;
					}

					// 0이 나왔을 때 1이 이어졌던 갯수와 K 비교하여 result++

					// 0이 나왔을 때 1의 갯수와 k가 같을 때 ? -> result++, count 초기화
					if (chk == false && count == k) {
						result++;
						count = 0;
					}
					// 0이 나왔는데 K와 같지 않으면 ? -> count 초기화
					else if (chk == false && count != k) {
						count = 0;
					} else
						;
				}
			}

			// 세로 검사
			for (i = 0; i < n; i++) {
				chk = false;
				for (j = 0; j < n; j++) {
					// 세로 확인하며 0, 1 인지 확인
					if (arr[j][i] == 1) {
						count++;
						chk = true;
					}

					else {
						chk = false;
					}

					if (j == (n - 1)) {
						chk = false;
					}

					// 0이 나왔을 때 1이 이어졌던 갯수와 K 비교하여 result++

					// 0이 나왔을 때 1의 갯수와 k가 같을 때 ? -> result++, count 초기화
					if (chk == false && count == k) {
						result++;
						count = 0;
					}
					// 0이 나왔는데 K와 같지 않으면 ? -> count 초기화
					else if (chk == false && count != k) {
						count = 0;
					} else
						;
				}
			}

			System.out.println("#" + c + " " + result);
		}

	}

}
