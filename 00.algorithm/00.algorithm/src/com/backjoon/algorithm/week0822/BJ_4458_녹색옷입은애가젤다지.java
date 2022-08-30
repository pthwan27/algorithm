package com.backjoon.algorithm.week0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_4458_녹색옷입은애가젤다지 {

	static int N;
	static int[][] map;
	static int D[][];

	static class Node implements Comparable<Node> {
		int r, c, w;

		public Node(int r, int c, int w) {
			super();
			this.r = r;
			this.c = c;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		while (true) {
			N = Integer.parseInt(in.readLine());
			if (N == 0)
				break;

			map = new int[N][N];

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			D = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(D[i], Integer.MAX_VALUE);
			}

			//최단거리 담을 배열
			D[0][0] = map[0][0];
			
			//다익스트라 ㄱㄱ
			dijkstra();

			sb.append("Problem " + tc++ +": " + D[N-1][N-1]).append("\n");
		}
		System.out.println(sb);
	}

	private static void dijkstra() {
		final int[] dx = new int[] { -1, 0, 1, 0 };
		final int[] dy = new int[] { 0, -1, 0, 1 };

		Queue<Node> pQueue = new LinkedList<>();
		pQueue.offer(new Node(0, 0, map[0][0]));
		
		while (!pQueue.isEmpty()) {
			Node tempNode = pQueue.poll();
			int r = tempNode.r;
			int c = tempNode.c;
			int w = tempNode.w;

			for (int i = 0; i < 4; i++) {
				// 범위 확인
				if (isIn(r + dx[i], c + dy[i])) {					
					// 현재위치의 최단경로 + 다음경로 < 다음위치의 최단경로 
					if (D[r][c] + map[r + dx[i]][c + dy[i]] < D[r + dx[i]][c + dy[i]]) {
						// 다음위치의 최단경로 = 현재위치의 최단경로 + 다음경로 
						D[r + dx[i]][c + dy[i]] = D[r][c] + map[r + dx[i]][c + dy[i]];//						
						pQueue.add(new Node(r + dx[i], c + dy[i], D[r + dx[i]][c + dy[i]]));						
					}
				}
			}
		}
	}

	private static boolean isIn(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N) {
			return true;
		}
		return false;
	}
}
