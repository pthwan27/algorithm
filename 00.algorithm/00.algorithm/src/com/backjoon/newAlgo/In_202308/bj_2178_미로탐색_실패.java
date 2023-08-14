package com.backjoon.newAlgo.In_202308;

import java.io.*;
import java.util.*;

public class bj_2178_미로탐색_실패{

	static int N, M;
	static int[][] map;
	static int[][] countMap;
	static boolean[][] isSelected;

	static int minResult = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		map = new int[N + 1][M + 1];
		countMap = new int[N + 1][M + 1];
		isSelected = new boolean[N + 1][M + 1];

		for (int r = 1; r <= N; r++) {
			String inputStr = in.readLine();
			for (int c = 1; c <= M; c++) {
				map[r][c]= inputStr.charAt(c - 1) - '0';
			}
		}
		
		isSelected[1][1] = true;
		countMap[1][1] = 1;
		dfs(1, 1);

		System.out.println(countMap[N][M]);
	}

	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, -1, 0, 1 };

	public static void dfs(int r, int c) {
		isSelected[r][c] = true;
		
		for (int i = 0; i < 4; i++) {
			int nextR = r + dr[i];
			int nextC = c + dc[i];
			if (isIn(nextR, nextC)) {
				if(!isSelected[nextR][nextC]) {
					countMap[nextR][nextC] = countMap[r][c] + 1;
					dfs(nextR,nextC);
					isSelected[nextR][nextC] = true;
				}			
			}
		}
	}

	public static boolean isIn(int nR, int nC) {
		return nR >= 1 && nR <= N && nC >= 1 && nC <= M && map[nR][nC] == 1;
	}

}
