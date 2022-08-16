package com.backjoon.algorithm.NMseries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_15664_N과M10 {

	static int N;
	static int M;

	static ArrayList<Integer> inputArr;

	static int[] nums;
	static boolean[] isSelected;

	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb2 = new StringBuilder();
	
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
		Collections.sort(inputArr);

		nums = new int[M];
		isSelected = new boolean[N];

		comb(0, 0);

		System.out.println(sb);
		
	}

	private static void comb(int cur, int start) {
		if (cur == M) {			
			for (int i = 0; i < M; i++) {				
				sb.append(nums[i] + " ");
			}
			sb.append("\n");
			return;
		}
		int past = -1;
		for (int i = start; i < N; i++) {
			int now = inputArr.get(i);
			if (isSelected[i] || now == past)
				continue;

			past = now;
			isSelected[i] = true;
			nums[cur] = inputArr.get(i);

			comb(cur + 1, i+1);
			isSelected[i] = false;

		}
	}
}
// 1 7 9 9
// 1 7 , 1 9 , 
