package com.backjoon.algorithm.week0822;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17144_미세먼지안녕_박태환 {
	static class Dust {
		int r, c, size;

		public Dust(int r, int c, int size) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
		}
	}

	static int mapR, mapC, t;
	static int[][] map;

	static Point cleanerPosition;

	static Queue<Dust> dustQueue;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		mapR = Integer.parseInt(st.nextToken());
		mapC = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		map = new int[mapR + 1][mapC + 1];
		dustQueue = new LinkedList<>();

		for (int a = 1; a <= mapR; a++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int b = 1; b <= mapC; b++) {
				map[a][b] = Integer.parseInt(st.nextToken());
				if (map[a][b] >= 5) {
					dustQueue.offer(new Dust(a, b, map[a][b]));
				}
				if (map[a][b] == -1) {
					cleanerPosition = new Point(a, b);
				}
			}

		}
		int Sum = 0;
		for (int i = 0; i < t; i++) {
			extendsDust();
			cleanerWork();

			Sum = 0;
			for (int a = 1; a <= mapR; a++) {
				for (int b = 1; b <= mapC; b++) {
					if (map[a][b] >= 5) {
						dustQueue.offer(new Dust(a, b, map[a][b]));
					}
					
					if (map[a][b] == -1) {
						cleanerPosition = new Point(a, b);
					}else {						
						Sum += map[a][b];
					}
				}
			}
		}
		System.out.println(Sum);		
	}

	// 먼지 확장
	private static void extendsDust() {
		final int[] dr = new int[] { -1, 0, 1, 0 };
		final int[] dc = new int[] { 0, -1, 0, 1 };

		int count = dustQueue.size();
		while (count-- > 0) {
			Dust tempDust = dustQueue.poll();
			int curR = tempDust.r;
			int curC = tempDust.c;

			// 주변에 몇개 나눠줬는지, 나눠줄 값
			int divCount = 0;
			int divValue = tempDust.size / 5;

			for (int i = 0; i < 4; i++) {
				if (isPossible(curR + dr[i], curC + dc[i])) {
					divCount++;
					map[curR + dr[i]][curC + dc[i]] += divValue;
				}
			}

			map[curR][curC] -= (divValue * divCount);
		}
	}

	// 청소기 동장
	private static void cleanerWork() {
		int[] dr = new int[] { -1, 0, 1, 0 };
		int[] dc = new int[] { 0, 1, 0, -1 };

		// cleanerPosition -> 청소기의 아래칸, cleanerPostion.x + 1 -> 위칸
		// 아래칸은 시계방향, 위칸은 반시계

		// 위쪽 회전
		int startR = cleanerPosition.x - 1;
		int startC = cleanerPosition.y;

		int dir = 0;
		int goR = startR - 1;
		int goC = startC;
		while (dir < 4) {
			if (isIn1(goR + dr[dir], goC + dc[dir], startR)) {
				map[goR][goC] = map[goR + dr[dir]][goC + dc[dir]];
				goR = goR + dr[dir];
				goC = goC + dc[dir];
			} else {
				dir++;
			}
		}
		map[startR][startC + 1] = 0;

		dr = new int[] { 1, 0, -1, 0 };
		dc = new int[] { 0, 1, 0, -1 };

		// 아래쪽 회전
		startR = cleanerPosition.x;
		startC = cleanerPosition.y;

		dir = 0;
		goR = startR + 1;
		goC = startC;
		while (dir < 4) {
			if (isIn2(goR + dr[dir], goC + dc[dir], startR)) {
				map[goR][goC] = map[goR + dr[dir]][goC + dc[dir]];
				goR = goR + dr[dir];
				goC = goC + dc[dir];
			} else {
				dir++;
			}
		}
		map[startR][startC + 1] = 0;
	}

	private static boolean isPossible(int r, int c) {
		if (r >= 1 && r <= mapR && c >= 1 && c <= mapC && map[r][c] >= 0) {
			return true;
		}
		return false;
	}

	private static boolean isIn1(int r, int c, int startR) {
		if (r >= 1 && r <= startR && c >= 1 && c <= mapC) {
			return true;
		}
		return false;
	}

	private static boolean isIn2(int r, int c, int startR) {
		if (r >= startR && r <= mapR && c >= 1 && c <= mapC) {
			return true;
		}
		return false;
	}
}
