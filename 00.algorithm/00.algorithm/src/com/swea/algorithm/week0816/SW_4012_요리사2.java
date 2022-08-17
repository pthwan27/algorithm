package com.swea.algorithm.week0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4012_요리사2 {
	static int N;

	static int result;
	static int[][] foodArr;
	
	static int[] nums;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(in.readLine());
			foodArr = new int[N][N];
			result = Integer.MAX_VALUE;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					foodArr[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			isSelected = new boolean[N];
			nums = new int[N/2];
			comb(0, 0);
			
			
		}
	}

	private static void comb(int cur, int start) {
		if(cur == N / 2) {
			for(int i =0, size = nums.length; i < size; i++) {
				System.out.print(nums[i]);
			}
			System.out.println();
			return;			
		}
		
		for(int i = start; i < N; i++) {
			isSelected[i] = true;
			nums[cur] = i;
			comb(cur+1, i+1);
			isSelected[i] = false;
		}

	}
}
