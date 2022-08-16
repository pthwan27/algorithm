package com.backjoon.algorithm.NMseries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_15665_N과M11 {

	static int N;
	static int M;

	static ArrayList<Integer> inputArr;

	static int[] nums;
	static boolean[] isSelected;

	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb2 = new StringBuilder();
	
	static HashSet<String> hs = new HashSet<>();
	
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

		comb(0);

		System.out.println(sb);
		
	}

	private static void comb(int cur) {
		if (cur == M) {
			StringBuilder sb2 = new StringBuilder();
			for (int i = 0; i < M; i++) {				
				sb2.append(nums[i] + " ");
			}
			
			if(hs.contains(sb2.toString())) {
				return;
			}else {
				sb.append(sb2);
				sb.append("\n");
				hs.add(sb2.toString());					
			}
			return;
		}

		for (int i = 0; i < N; i++) {
			nums[cur] = inputArr.get(i);

			comb(cur + 1);
		}
	}
}
