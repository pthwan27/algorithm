package com.swea.algorithm.week0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW_4012_요리사_뻘짓 {

	static int N;

	static int result;
	static int[][] foodArr;
	static ArrayList<int[]> divList;

	static int[] nums = new int[2];

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
			divList = new ArrayList<>();
			// 2개씩 나누기
			comb(0, 0);

			for(int i = 0; i < divList.size(); i++) {
				System.out.println(divList.get(i)[0] + " " + divList.get(i)[1]);
			}
			System.out.println("------------------");		
			findMin(0, 0);

			System.out.println(result);
		}
	}

	private static void comb(int cur, int start) {
		if (cur == 2) {
			divList.add(new int[] { nums[0], nums[1] });
			return;
		}

		for (int i = start; i < N; i++) {
			nums[cur] = i;
			comb(cur + 1, i + 1);
		}

	}

	private static void findMin(int cur, int start) {
		if (cur == 2) {			
			
			int firstfoodA = divList.get(nums[0])[0];
			int firstfoodB = divList.get(nums[0])[1];

			int secondfoodA = divList.get(nums[1])[0];
			int secondfoodB = divList.get(nums[1])[1];

			System.out.println(firstfoodA + " " + firstfoodB + " " + secondfoodA + " " + secondfoodB);
			
			if (firstfoodA != secondfoodA && firstfoodA != secondfoodB && firstfoodB != secondfoodA
					&& firstfoodB != secondfoodB) {
				// 2개를 가져와 빼기
//				System.out.println(divList.get(nums[0])[0] + " " + divList.get(nums[0])[1]);
//				System.out.println(divList.get(nums[1])[0] + " " + divList.get(nums[1])[1]);
				int A = foodArr[firstfoodA][firstfoodB] + foodArr[firstfoodB][firstfoodA];
						
				int B = foodArr[secondfoodA][secondfoodB] + foodArr[secondfoodB][secondfoodA];
						
				System.out.println(A - B);
				result = Math.min(result, Math.abs(A - B));
			}
			return;
		}
		for (int i = start; i < divList.size(); i++) {

			nums[cur] = i;
			findMin(cur + 1, i + 1);
		}

	}
}
