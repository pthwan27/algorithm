package com.backjoon.newAlgo.In_202309;

import java.io.*;

public class bj_1019_책페이지 {

	static int N;
	static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		isSelected = new boolean[N];

		for (int i = 0; i < N; i++) {
			if (!isSelected[i]) {
				findNum(i);
			}
		}
	}

	public static int[] findNum(int num) {
		int[] nums = new int[9];
		
		if (num > N) {
			return nums;
		}
		
		
		return nums;
	}
}
