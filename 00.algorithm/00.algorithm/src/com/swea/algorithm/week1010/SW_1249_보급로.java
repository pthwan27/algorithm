package com.swea.algorithm.week1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SW_1249_보급로 {
	static class Node implements Comparable<Node> {
		int r, c, cnt;

		public Node(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			return this.cnt - o.cnt;
		}

	}

	static int[][] map;
	static boolean[][] isVisited;
	static int N;

	static int minResult;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int i = 1; i <= T; i++) {
			N = Integer.parseInt(in.readLine());

			map = new int[N][N];

			for (int r = 0; r < N; r++) {
				String inputStr = in.readLine();
				for (int c = 0; c < N; c++) {
					map[r][c] = inputStr.charAt(c) - '0';
				}
			}
			isVisited = new boolean[N][N];
			minResult = Integer.MAX_VALUE;
			// 입력 끝

			bfs(new Node(0, 0, 0));
			// 로직 끝

			System.out.println("#" + i + " " + minResult);
			// 결과
		}
	}

	static int[] dx = new int[] { -1, 0, 1, 0 };
	static int[] dy = new int[] { 0, -1, 0, 1 };

	private static void bfs(Node node) {
		//최단경로를 구하는 것이 아니라 , 복구시간이 가장 짧은 것을 구하는 것이기 때문에 우선순위 큐를 이용함
		PriorityQueue<Node> pQue = new PriorityQueue<>();
		pQue.add(node);

		while (!pQue.isEmpty()) {
			Node pollNode = pQue.poll();

			int r = pollNode.r;
			int c = pollNode.c;
			int cnt = pollNode.cnt;

			if (r == N - 1 && c == N - 1) {
				minResult = Math.min(minResult, cnt);
			}

			for (int i = 0; i < 4; i++) {
				int dr = r + dx[i];
				int dc = c + dy[i];
				if (isIn(dr, dc) && !isVisited[dr][dc]) {
					isVisited[dr][dc] = true;
					pQue.add(new Node(dr, dc, cnt + map[dr][dc]));
				}
			}
		}

	}

	private static boolean isIn(int dr, int dc) {
		return dr >= 0 && dc >= 0 && dr < N && dc < N;
	}

}
