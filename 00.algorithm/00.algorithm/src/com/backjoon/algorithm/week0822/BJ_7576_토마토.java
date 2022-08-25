package com.backjoon.algorithm.week0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7576_토마토 {
	static class Tomato {
		int r, c, dayCount;

		public Tomato(int r, int c, int dayCount) {
			super();
			this.r = r;
			this.c = c;
			this.dayCount = dayCount;
		}

		@Override
		public String toString() {
			return "Tomato [r=" + r + ", c=" + c + ", dayCount=" + dayCount + "]";
		}
	}

	static int N, M;
	static int[][] tomatoMap;
	static int[][] isSelectedMap;

	static Queue<Tomato> tomatoQ = new LinkedList<>();

	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		tomatoMap = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int c = 0; c < M; c++) {
				tomatoMap[r][c] = Integer.parseInt(st.nextToken());
				if (tomatoMap[r][c] == 1) {
					tomatoQ.offer(new Tomato(r, c, 0));
				}
			}
		}

		bfs();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (tomatoMap[r][c] == 0) {
					result = -1;
					break;
				}
			}
		}
		System.out.println(result);

	}

	private static void bfs() {
		final int[] dx = new int[] { -1, 0, 1, 0 };
		final int[] dy = new int[] { 0, -1, 0, 1 };

		while (!tomatoQ.isEmpty()) {
			Tomato tempTmt = tomatoQ.poll();
			int dr = tempTmt.r;
			int dc = tempTmt.c;
			int dayCount = tempTmt.dayCount + 1;

			for (int i = 0; i < 4; i++) {
				if (isIn(dr + dx[i], dc + dy[i])) {
					result = Math.max(dayCount, result);
					tomatoMap[dr + dx[i]][dc + dy[i]] = 1;
					tomatoQ.offer(new Tomato(dr + dx[i], dc + dy[i], dayCount));
				}
			}
		}

	}

	private static boolean isIn(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < M && tomatoMap[r][c] == 0) {
			return true;
		}
		return false;
	}

}
