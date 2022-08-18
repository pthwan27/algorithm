package com.swea.algorithm.week0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_4012_요리사_박태환 {
	static int N;

	static int[][] foodArr;

	static boolean[] isSelected;

	static int[] selectedFood;
	static int[] unSelectedFood;

	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(in.readLine());
			foodArr = new int[N][N];
			result = Integer.MAX_VALUE;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					foodArr[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			//선택된거를 쓰기위해 생성
			isSelected = new boolean[N];
			//조합만들기
			comb(0, 0);
			System.out.println("#" + tc + " " + result);
		}
	}

	private static void comb(int cur, int start) {
		if (cur == N / 2) {
			result = Math.min(result, calc(isSelected));			
			return;
		}

		//조합만들기
		for (int i = start; i < N; i++) {
			isSelected[i] = true;
			comb(cur + 1, i + 1);
			isSelected[i] = false;
		}

	}

	//계산, 선택된 것과 안된 것 구분해서 계산하기
	private static int calc(boolean[] isSelected) {
		int selectedSum = 0;
		int unSelectedSum = 0;
		for (int i = 0, size = foodArr.length; i < size; i++) {
			for(int j = i; j < foodArr[i].length; j++) {
				if(isSelected[i] && isSelected[j]) {
					selectedSum += foodArr[i][j];
					selectedSum += foodArr[j][i];
				}else if(!isSelected[i] && !isSelected[j]){
					unSelectedSum += foodArr[i][j];
					unSelectedSum += foodArr[j][i];
				}
			}
		}
		return Math.abs(selectedSum - unSelectedSum);
	}
}
