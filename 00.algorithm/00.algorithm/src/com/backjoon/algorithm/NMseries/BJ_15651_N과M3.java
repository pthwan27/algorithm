package com.backjoon.algorithm.NMseries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_15651_N과M3 {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		boolean[] isSelected = new boolean[N];

		int[] nums = new int[M];

		Comb(0, N, M, isSelected, nums);
		System.out.print(sb);
		sb.setLength(0);
	}

	private static void Comb(int cur, int n, int m, boolean[] isSelected, int[] nums) {
		if (cur == m) {
			for (int i = 0; i < nums.length; i++) {
				sb.append(nums[i]+ " ");
			}
			sb.append("\n");

			return;
		}

		for (int i = 0; i < n; i++) {

			isSelected[i] = true;
			nums[cur] = i + 1;
			Comb(cur + 1, n, m, isSelected, nums);
			isSelected[i] = false;
		}

	}

}
