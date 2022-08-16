package com.backjoon.algorithm.NMseries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_15655_N과M6 {

	static int N;
	static int M;

	static ArrayList<Integer> inputArr;

	static int[] nums;
	static boolean[] isSelected;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		inputArr = new ArrayList<>();
		st = new StringTokenizer(in.readLine());

		for (int idx = 0; idx < N; idx++) {
			inputArr.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(inputArr, Collections.reverseOrder());

		nums = new int[M];
		isSelected = new boolean[N];

		comb(0);

		System.out.println(sb);
	}

	private static void comb(int cur) {
		if (cur == M) {
			int start = nums[0];
			for (int i = 1; i < M; i++) {
				if (start > nums[i]) {
					return;
				}else {
					start = nums[i];
				}
			}

			for (int i = 0; i < M; i++) {
				sb.append(nums[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = N - 1; i >= 0; i--) {
			if (isSelected[i])
				continue;

			nums[cur] = inputArr.get(i);

			isSelected[i] = true;
			comb(cur + 1);

			isSelected[i] = false;

		}

	}
}
