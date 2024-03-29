package com.swea.algorithm.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D2_2001_파리퇴치 {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		int c = 0;
		for (c = 1; c <= T; c++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[][] arr = new int[N][N];

			int i = 0;
			int j = 0;

			// 배열에 파리 마리 수 집어넣기
			for (i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int result = -99;
			int temp = -99;
			for (i = 0; i <= N - M; i++) {
				for (j = 0; j <= N - M; j++) {
					temp = total(i, j, M, arr);
					if (temp > result) {
						result = temp;
					}
				}

			}
			// 시작지점부터 시작지점 + M까지의
//	          0~ N
//	          arr[0][0] arr[0][1] arr[0][n]
//	          arr[1][0] arr[1][1] arr[1][n]
//	          arr[N][0] arr[N][1] arr[n][n]

			System.out.println("#" + c + " " + result);
		}

	}

	private static int total(int i, int j, int M, int[][] arr) {
		int sum = 0;
		for (int a = i; a < i + M; a++) {
			for (int b = j; b < j + M; b++) {
				sum += arr[a][b];
			}
		}
		return sum;
	}

}
