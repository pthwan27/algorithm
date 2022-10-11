package com.backjoon.algorithm.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ_2239_스도쿠 {
	static int N = 9;
	static int[][] sdk = new int[N][N];

	static class Zero {
		int r, c;

		public Zero(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static ArrayList<Zero> zeroList;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		sdk = new int[N][N];
		zeroList = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			String inputStr = in.readLine();
			for (int c = 0; c < N; c++) {
				sdk[r][c] = inputStr.charAt(c) - '0';

				if (sdk[r][c] == 0) {
					zeroList.add(new Zero(r, c));
				}
			}
		}

		sol(0);
		
		for(int r = 0; r < N; r++) {
			for(int c= 0; c < N; c++) {
				sb.append(sdk[r][c]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}

	private static boolean sol(int cnt) {
		//모두 완성하면 return;
		if (cnt == zeroList.size()) {
			return true;
		}

		int r = zeroList.get(cnt).r;
		int c = zeroList.get(cnt).c;

		//1~9까지 넣어보기
		for (int i = 1; i < 10; i++) {
			sdk[r][c] = i;

			//그 위치가 가능한지, 다음 재귀 값이 true인지 확인
			if (isValid(r, c) && sol(cnt + 1)) {
				return true;
			}
		}
		sdk[r][c] = 0;

		return false;
	}

	private static boolean isValid(int r, int c) {
		for (int i = 0; i < 9; i++) {
			if(r != i && sdk[r][c] == sdk[i][c]) return false;//세로 일치하는지 
			if(c != i && sdk[r][c] == sdk[r][i]) return false;//가로 일치하는지
		}
		
		//3*3 일치하는지
		for (int a = (r / 3) * 3, i = 0; i < 3; a++, i++) {
			for (int b = (c / 3) * 3, j = 0; j < 3; b++, j++) {
				if(a != r && b != c && sdk[a][b] == sdk[r][c]) return false;
			}
		}

		return true;
	}
}
