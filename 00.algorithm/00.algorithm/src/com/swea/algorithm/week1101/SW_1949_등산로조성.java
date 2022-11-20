package com.swea.algorithm.week1101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1949_등산로조성 {
	static int N, K;
	static int[][] map;
	static boolean[][] isVisited;

	static int result = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			result = -1;

			int max = -1;
			map = new int[N][N];
			isVisited = new boolean[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[r][c]);
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == max) {
						// 최대값 위치라면 경로탐색
						isVisited[r][c] = true;
						dfs(1, r, c, 1);
						isVisited[r][c] = false;
					}
				}
			}

			System.out.println(result);
		}

	}

	private static void dfs(int depth, int r, int c, int chance) {
		final int[][] dArr = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

		result = Math.max(depth, result);

		for (int i = 0; i < 4; i++) {
			int dr = r + dArr[i][0];
			int dc = c + dArr[i][1];

			if (!isIn(dr, dc)) {
				continue;
			}

			if (!isVisited[dr][dc]) {
				//다음 갈 곳이 갈 수 있는 곳이라면
				if (map[dr][dc] < map[r][c]) {
					isVisited[dr][dc] = true;
					dfs(depth + 1, dr, dc, chance);
					isVisited[dr][dc] = false;
				}
				//갈 수 없는 곳이라면
				else {
					if (chance == 0) {
						continue;
					}

					if (map[dr][dc] - K < map[r][c]) {
						int temp = map[dr][dc];
						
						map[dr][dc] = map[r][c] - 1;
						isVisited[dr][dc] = true;
						dfs(depth + 1, dr, dc, 0);
						
						map[dr][dc] = temp;
						isVisited[dr][dc] = false;
					}
				}
			}

		}
	}

	private static boolean isIn(int dr, int dc) {
		return dr >= 0 && dc >= 0 && dr < N && dc < N;
	}
}
