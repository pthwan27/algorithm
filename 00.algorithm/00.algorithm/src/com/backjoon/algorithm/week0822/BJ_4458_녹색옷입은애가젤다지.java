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

	static class Node implements Comparable<Node>{
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

		N = Integer.parseInt(in.readLine());

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
		dijkstra();
	}

	private static void dijkstra() {
		final int[] dx = new int[] { -1, 0, 1, 0 };
		final int[] dy = new int[] { 0, -1, 0, 1 };

		Queue<Node> pQueue = new LinkedList<>();
		pQueue.offer(0,0,map[0][0]);
	}
}
