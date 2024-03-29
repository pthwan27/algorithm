package com.backjoon.algorithm.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1012_유기농배추 {
	static StringTokenizer st = null;

	// 가로 세로 크기
	static int M = 0;
	static int N = 0;

	// 배추의 갯수
	static int K = 0;

	// 배추의 위치를 담을 배열
	static int[][] vegearr = new int[0][0];

	// 체크한 곳인지 확인하기 위해
	static boolean[][] chkarr = new boolean[0][0];

	static int result = 0;

	// 상, 하, 좌, 우 확인 해야함
	// 상 -> -1,0
	// 하 -> +1,0
	// 좌 -> 0,-1
	// 우 -> 0,+1
	private static void bfs(int i, int j) {
		chkarr[i][j] = true;

		int[] pos1 = { -1, 1, 0, 0 };
		int[] pos2 = { 0, 0, -1, +1 };

		int movX = 0;
		int movY = 0;

		for (int a = 0; a < 4; a++) {
			// 상,하,좌,우의 위치 값
			movX = i + pos1[a];
			movY = j + pos2[a];

			// 상,하,좌,우가 M,N 배열의 범위 안에 있도록
			if (movX >= 0 && movY >= 0 && movX < M && movY < N) {
				if (chkarr[movX][movY] == false && vegearr[movX][movY] == 1) {
					bfs(movX, movY);
				}
			}

		}
	}

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int c = 0; c < T; c++) {
			st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			K = Integer.parseInt(st.nextToken());

			result = 0;
			// 배추의 위치를 담을 배열
			vegearr = new int[M][N];
			chkarr = new boolean[M][N];

			for (int c1 = 0; c1 < K; c1++) {
				st = new StringTokenizer(br.readLine());

				int kx = Integer.parseInt(st.nextToken());
				int ky = Integer.parseInt(st.nextToken());

				vegearr[kx][ky] = 1;
			}

			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {

					// 1을 찾았고, 그 곳이 방문 한 적이 없는 경우
					if (vegearr[i][j] == 1 && chkarr[i][j] == false) {
						// 상 하 좌 우 4방향으로 연결되어 있는지 확인하기 위해
						bfs(i, j);

						result++;
					}
				}
			}

			System.out.println(result);
		}
	}

}
