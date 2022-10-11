package com.swea.algorithm.week1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

import com.swea.algorithm.week1010.SW_5656_벽돌깨기2.Node;

public class SW_5656_벽돌깨기_박태환 {

	static int N, W, H, minResult;
	static int[][] map;

	static int[] output;
	static boolean[] isSelected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine(), " ");

			//구슬 쏘는 횟수
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			minResult = Integer.MAX_VALUE;
			go(map, 0);

			System.out.println(minResult);
		}
	}

	private static void go(int[][] map, int cnt) { 
		//남은 벽돌 갯수 카운팅하고 최소값찾기
		if (cnt == N) {
			minResult = Math.min(counting(map), minResult);

			return;
		}

		//구슬 던지기 중복순열
		int[][] newMap = new int[H][W];
		for (int c = 0; c < W; c++) {

			// 구슬에 맞는 시작벽을 찾기
			int r = 0;

			while (r < H && map[r][c] == 0)
				++r;

			//맞는 시작벽돌 없을 때
			if (r == H) {
				go(map, cnt + 1);
			}
			//맞는 벽돌 있을 때
			else {
				copy(map, newMap);
				//제거될 벽 연쇄처리
				boom(newMap, r, c);

				//벽돌 중력 처리
				down(newMap);

				//다음 구슬 던지기
				go(newMap, cnt + 1);

			}

		}

	}

	private static int counting(int[][] map) {
		int result = 0;
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				if (map[r][c] > 0)
					result++;
			}

		}
		return result;
	}

	static class Point {
		int r, c, cnt;

		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	private static void boom(int[][] map, int r, int c) {//BFS
		int[][] dArr = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		Queue<Point> queue = new ArrayDeque<Point>();

		if (map[r][c] > 1) {
			queue.offer(new Point(r, c, map[r][c]));
		}

		//벽돌이 있던 자리를 0으로 변경, 빈칸으로 만들어서 방문처리
		map[r][c] = 0;

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			//벽돌의 크기 -1만큼 주변 벽돌 (4방) 연쇄 처리
			for (int i = 0; i < 4; i++) {
				int dr = p.r;
				int dc = p.c;
				for (int a = 1; a < p.cnt; a++) {
					dr += dArr[i][0];
					dc += dArr[i][1];

					if (dr >= 0 && dc >= 0 && dr < H && dc < W && map[dr][dc] > 0) {
						if (map[dr][dc] > 1) {
							queue.offer(new Point(dr, dc, map[dr][dc]));
						}
						// 벽돌이 있던 자리를 0으로 변경 : 빈칸으로 만들어서 방문처리
						map[dr][dc] = 0; // 방문처리 --> 제거 처리
					}

				}
			}
		}
	}

	//정렬편하게 하기위해 생성
	static Stack<Integer> stack = new Stack<>();

	private static void down(int[][] map) {
		for (int c = 0; c < W; c++) {
			for (int r = 0; r < H; r++) {
				if (map[r][c] > 0)
					stack.push(map[r][c]);

				map[r][c] = 0;
			}

			int er = H - 1;
			while (!stack.isEmpty()) {
				map[er--][c] = stack.pop();
			}

		}
	}

	private static void copy(int[][] map, int[][] newMap) {
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				newMap[r][c] = map[r][c];
			}

		}

	}

}
