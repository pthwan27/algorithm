package com.swea.algorithm.week1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import com.swea.algorithm.week1010.SW_5653_줄기세포배양2.Cell;

public class SW_5653_줄기세포배양 {

	// 맵 r, 맵c, 배양 시간
	static int N, M, K;

	static int mapN, mapM;

	static int[][] hpMap;
	static int[][] cntMap;

	static class Cell {
		int r, c, hp, cnt;

		public Cell(int r, int c, int hp, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.hp = hp;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "[r=" + r + ", c=" + c + ", hp=" + hp + ", cnt=" + cnt + "]";
		}

	}

	static Queue<Cell> pQue = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			hpMap = new int[N][M];

			int temp = (K / 2) % 2 == 1 ? K + 1 : K;

			mapN = N + temp;
			mapM = M + temp;

			//맵 준비
			hpMap = new int[mapN][mapM];
			cntMap = new int[mapN][mapM];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < M; c++) {
					int nextHp = Integer.parseInt(st.nextToken());

					if (nextHp > 0) {
						hpMap[temp / 2 + r][temp / 2 + c] = nextHp;
						Cell cell = new Cell(temp / 2 + r, temp / 2 + c, nextHp, 0);
						pQue.offer(cell);
					}
				}
			}

			for (int i = 0; i < K; i++) {
				bfs();
			}

		}
	}

	static int[][] dArr = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private static void bfs() {
		int size = pQue.size();
		while (size-- > 0) {
			Cell cell = pQue.poll();

			int r = cell.r;
			int c = cell.c;

			int hp = cell.hp;
			int cnt = cell.cnt;

			//죽은상태
			if(cntMap[r][c] >= hpMap[r][c] * 2 )
			{
				cntMap[r][c] = hpMap[r][c]*2;
				continue;
			}
			
			//비활성화 상태
			if (cntMap[r][c] < hpMap[r][c]) {
				pQue.add(new Cell(r, c, hp, cnt + 1));
			}
			//확산
			else if (cntMap[r][c] == hpMap[r][c]) {
				for (int i = 0; i < 4; i++) {
					int dr = r + dArr[i][0];
					int dc = c + dArr[i][1];
					if (isIn(dr, dc) && hpMap[dr][dc] == 0) {

						hpMap[dr][dc] = hp;

						pQue.offer(new Cell(dr, dc, hp, 0));
					}
				}
			} else {

			}

			cntMap[r][c]++;

		}
	}

	private static boolean isIn(int dr, int dc) {
		return dr >= 0 && dc >= 0 && dr < mapN && dc < mapM;
	}
}
