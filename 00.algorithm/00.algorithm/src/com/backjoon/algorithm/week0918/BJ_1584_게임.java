package com.backjoon.algorithm.week0918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1584_게임 {
	static class Node implements Comparable<Node> {
		int r, c, count;

		public Node(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}

		@Override
		public int compareTo(Node o) {
			if (this.count > o.count)
				return 1;
			else
				return -1;
		}
	}

	static int[][] map = new int[501][501];
	static boolean[][] isSelected = new boolean[501][501];

	static int minResult = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 입력 부분
		// 위험 구역 설정
		int N = Integer.parseInt(in.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());

			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			makeRange(x1, x2, y1, y2, 1);
		}

		
		// 죽음구역 설정
		int M = Integer.parseInt(in.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());

			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			makeRange(x1, x2, y1, y2, 9);
		}

		bfs();
		
		if (minResult == Integer.MAX_VALUE) {
			minResult = -1;
		}
		System.out.println(minResult);
	}

	private static void bfs() {
		// 시계방향
		final int[] dx = new int[] { -1, 0, 1, 0 };
		final int[] dy = new int[] { 0, 1, 0, -1 };

		PriorityQueue<Node> bfsQueue = new PriorityQueue<>();
		bfsQueue.add(new Node(0, 0, 0));

		isSelected[0][0] = true;

		while (!bfsQueue.isEmpty()) {
			Node tempNode = bfsQueue.poll();

			int r = tempNode.r;
			int c = tempNode.c;
			int cnt = tempNode.count;

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
		int startX = Math.min(x1, x2);
		int endX = Math.max(x1, x2);

		int startY = Math.min(y1, y2);
		int endY = Math.max(y1, y2);

		for (int r = startX; r <= endX; r++) {
			for (int c = startY; c <= endY; c++) {
				map[r][c] = status;
			}
		}
	}
}
