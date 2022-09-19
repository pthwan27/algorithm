package com.backjoon.algorithm.week0918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1584_게임 {
	static class Node {
		int r, c, count;

		public Node(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}
	}

	static int[][] map = new int[501][501];
	static boolean[][] isSelected = new boolean[501][501];

	static int minResult = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(in.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());

			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			makeRange(x1, x2, y1, y2, 1);
		}

		int M = Integer.parseInt(in.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());

			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			makeRange(x1, x2, y1, y2, 9);
		}

//		printMap(map);

		bfs();
		if (minResult == Integer.MAX_VALUE) {
			minResult = -1;
		}
		System.out.println(minResult);
	}

//	private static void printMap(int[][] map2) {
//		for (int i = 0; i < 500; i++) {
//			for (int j = 0; j < 500; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
//
//	}

	private static void bfs() {
		// 시계방향
		final int[] dx = new int[] { -1, 0, 1, 0 };
		final int[] dy = new int[] { 0, 1, 0, -1 };

		Queue<Node> bfsQueue = new LinkedList<>();
		bfsQueue.add(new Node(0,0,0));

		isSelected[0][0] = true;	

		while (!bfsQueue.isEmpty()) {
			Node tempNode = bfsQueue.poll();

			int cnt = tempNode.count;

			int r = tempNode.r;
			int c = tempNode.c;
			if (r == 500 && c == 500) {
				minResult = Math.min(cnt, minResult);
			}

			for (int i = 0; i < 4; i++) {
				int dr = r + dx[i];
				int dc = c + dy[i];

				if (isIn(dr, dc)) {
					if (isSelected[dr][dc])
						continue;

					isSelected[dr][dc] = true;
					bfsQueue.add(new Node(dr, dc, cnt + map[dr][dc]));
				}
			}
		}

	}

	private static boolean isIn(int dr, int dc) {
		if (dr >= 0 && dc >= 0 && dr <= 500 && dc <= 500 && map[dr][dc] != 9) {
			return true;
		}
		return false;
	}

	private static void makeRange(int x1, int x2, int y1, int y2, int status) {
		if (x1 < x2 && y1 < y2) {
			for (int r = x1; r <= x2; r++) {
				for (int c = y1; c <= y2; c++) {
					map[r][c] = status;
				}
			}
		} else if (x1 < x2 && y1 > y2) {
			for (int r = x1; r <= x2; r++) {
				for (int c = y2; c <= y1; c++) {
					map[r][c] = status;
				}
			}
		} else if (x1 > x2 && y1 < y2) {
			for (int r = x2; r <= x1; r++) {
				for (int c = y1; c <= y2; c++) {
					map[r][c] = status;
				}
			}
		} else if (x1 > x2 && y1 > y2) {
			for (int r = x2; r <= x1; r++) {
				for (int c = y2; c <= y1; c++) {
					map[r][c] = status;
				}
			}
		}

	}
}
