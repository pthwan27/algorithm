package com.swea.algorithm.week1010;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW_1949_등산로조성_DFS {

	static int N, K;
	static int[][] map;
	static boolean[][] isVisited;

	static ArrayList<Point> startList;

	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			isVisited = new boolean[N][N];

			int max = 0;
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[r][c]);
				}
			}

			startList = new ArrayList<>();
			result = -1;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (max == map[r][c]) {
						startList.add(new Point(r, c));
					}
				}
			}
			for (int i = 0; i < startList.size(); i++) {
				isVisited[startList.get(i).x][startList.get(i).y] = true;
				dfs(1, startList.get(i), 1);
				isVisited[startList.get(i).x][startList.get(i).y] = false;
			}
			sb.append("#"+tc+" "+result+"\n");
		}
		System.out.println(sb);

	}

	static int[][] dArr = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private static void dfs(int cur, Point pos, int chance) {
		int r = pos.x;
		int c = pos.y;

		result = Math.max(result, cur);

		for (int i = 0; i < 4; i++) {
			int dr = r + dArr[i][0];
			int dc = c + dArr[i][1];
			if (!isIn(dr, dc))
				continue;

			if (!isVisited[dr][dc]) {
				if (map[dr][dc] < map[r][c]) {
					isVisited[dr][dc] = true;
					dfs(cur + 1, new Point(dr, dc), chance);
					isVisited[dr][dc] = false;
				}
				//다음 칸을 갈 수 없을 때, 찬스가 있으면
				else {
					if (chance == 0) {
						continue;
					}

					if (map[dr][dc] - K < map[r][c]) {
						int temp = map[dr][dc];
						isVisited[dr][dc] = true;
						map[dr][dc] = map[r][c] - 1;

						dfs(cur + 1, new Point(dr, dc), 0);

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
