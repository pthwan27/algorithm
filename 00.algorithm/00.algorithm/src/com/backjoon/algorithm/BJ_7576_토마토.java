package com.backjoon.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Tomato {
	int tomatoR;
	int tomatoC;
	int dayCount;

	public Tomato(int r, int c, int dayCount) {
		super();
		tomatoR = r;
		tomatoC = c;
		this.dayCount = dayCount;
	}
}

public class BJ_7576_토마토 {

	// 배열의 크기가 주어진다.
	// 1 익은 토마토, 0은 안익은 토마토, -1은 빈 곳
	// 1 은 상,하,좌,우를 1로 바꾼다. -1은 갈 수 없다.
	// 전체가 1로 변하는데 걸리는 최소 일 수

	static int N, M;

	static int[][] tomatoArr;
	static boolean[][] isSelected;

	static Queue<Tomato> tomaQueue = new LinkedList<>();

	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		tomatoArr = new int[N][M];
		isSelected = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < M; c++) {
				tomatoArr[r][c] = Integer.parseInt(st.nextToken());
				// 1인 것들 queue에 넣기
				if (tomatoArr[r][c] == 1) {
					tomaQueue.offer(new Tomato(r, c, 0));
				}
			}
		}

		// bfs 시작
		bfs();

		// 변하지 않는 토마토가 있다면 -1로 변경 후 , break;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (tomatoArr[r][c] == 0) {
					result = -1;
					break;
				}
			}			
		}
		System.out.println(result);
	}

	private static void bfs() {
		// bfs 진행방향
		int[] dx = new int[] { -1, 1, 0, 0 };
		int[] dy = new int[] { 0, 0, -1, 1 };

		// queue가 비어있을 때 까지
		while (!tomaQueue.isEmpty()) {
			Tomato tmt = tomaQueue.poll();

			int goR = tmt.tomatoR;
			int goC = tmt.tomatoC;
			int dayCnt = tmt.dayCount + 1;

			for (int i = 0; i < 4; i++) {
				int nextR = goR + dx[i];
				int nextC = goC + dy[i];
				if (nextR >= 0 && nextC >= 0 && nextR < N && nextC < M) {					
					if (tomatoArr[nextR][nextC] == 0) {
						result = Math.max(result, dayCnt);// 걸린 일 수 
						tomaQueue.offer(new Tomato(nextR, nextC, dayCnt));
						tomatoArr[nextR][nextC] = 1;
					}
				}
			}

		}
	}
}
