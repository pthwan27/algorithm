package com.backjoon.algorithm.day0811_Tree_순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2961_도영이가만든맛있는음식 {

	/**
	 * 도영이에게는 N개의 재료가 있다. 각 재료는 신맛 S, 쓴맛 B가 있고, 도영이는 알고 있다. 여러 재료를 이용해 요리할 때, 음식의
	 * 신맛은 재료의 신맛의 곱한 결과이고, 쓴맛은 재료의 쓴맛의 합이다.
	 * 
	 * 재료는 적어도 1가지 이상. 은 사용해야 요리라고 할 수 있다.
	 * 
	 * 이때 신맛과 쓴맛의 차이가 가장 작은 요리를 찾아보자 *
	 * 
	 * 여러개의 재료 중 n개 선택하는 부분집합을 만들어두고, 이 중에 가장 작은 값을 가져옴
	 * 
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	static int[][] foodArr;
	static int N;

	static boolean[] isChecked;
	static int[] comb;

	static int MinValue = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 재료의 수
		N = Integer.parseInt(br.readLine());

		// 재료의 쓴맛, 신맛 저장
		foodArr = new int[N][2];

		// N개의 재료 받아오기.
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			foodArr[i][0] = Integer.parseInt(st.nextToken());
			foodArr[i][1] = Integer.parseInt(st.nextToken());
		}

		// 재귀함수 구현
		// 2~N개 까지의 음식의 조합을 봐야함.
		isChecked = new boolean[N];
		comb = new int[N];

		if (N == 1) {
			MinValue = Math.abs(foodArr[0][0] - foodArr[0][1]);
		} else {
			solve(0);
		}

		System.out.println(MinValue);
	}

	private static void solve(int cur) {
		if (cur == N) {
//			System.out.println(Arrays.toString(isChecked));
			System.out.println(Arrays.toString(isChecked) + " 계산");
			int result = calc();
			MinValue = Math.min(result, MinValue);

			return;
		}

		isChecked[cur] = true;		
		System.out.println(Arrays.toString(isChecked) +" "+ cur);		
		solve(cur + 1);
		
		isChecked[cur] = false;
		System.out.println(Arrays.toString(isChecked)+" "+ cur);
		solve(cur + 1);
		System.out.println(Arrays.toString(isChecked)+" "+ cur);


	}

	private static int calc() {

		int SumS = 1;
		int SumB = 0;

		int cnt = 0;
		for (int i = 0; i < N; i++) {

			if (isChecked[i] == true) {
				cnt++;
				SumS *= foodArr[i][0];
				SumB += foodArr[i][1];
			}
		}
		if (cnt == 0)
			return Integer.MAX_VALUE;

		return Math.abs(SumS - SumB);
	}

//	private static void print(int[][] foodArr) {
//		for (int i = 0; i < foodArr.length; i++) {
//			System.out.println(Arrays.toString(foodArr[i]));
//		}
//	}

}
