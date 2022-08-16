package com.backjoon.algorithm.NMseries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_15649_N과M1 {
	static int[] Nums;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Nums = new int[M];
		boolean[] isChecked = new boolean[N];
		//재귀 시작
		comb(0, N, M, isChecked);
	}

	private static void comb(int cur, int N, int M, boolean[] isChecked) {
		//원하는 길이의 수열 출력
		if (cur == M) {
			for (int i = 0; i < Nums.length; i++) {
				sb.append(Nums[i] + " ");
			}
			sb.append("\n");
			
			System.out.print(sb);
			sb = new StringBuilder();
			return;
		}
		
		// 중복되지 않는 수열 구하기.
		for (int i = 0; i < N; i++) {
			if (isChecked[i])
				continue;

			Nums[cur] = i + 1;
			isChecked[i] = true;

			comb(cur + 1, N, M, isChecked);
			isChecked[i] = false;
		}
	}
}
