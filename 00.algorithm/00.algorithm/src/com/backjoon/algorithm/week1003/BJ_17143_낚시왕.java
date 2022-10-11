package com.backjoon.algorithm.week1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17143_낚시왕 {
	static class Shark {
		// 위치, 속력, 방향, 크기
		int r, c, speed, d, size;

		public Shark(int r, int c, int speed, int d, int size) {

			super();
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.d = d;
			this.size = size;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", speed=" + speed + ", d=" + d + ", size=" + size + "]";
		}
	}

	// map 크기, 상어의 수
	static int R, C, M;

	static int[][] map;

	static Shark[] sharkArr;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[R + 1][C + 1];
		sharkArr = new Shark[M + 1];
		for (int idx = 1; idx <= M; idx++) {
			st = new StringTokenizer(in.readLine());

			int r, c, speed, d, size;

			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			speed = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			size = Integer.parseInt(st.nextToken());

			map[r][c] = idx;

			sharkArr[idx] = new Shark(r, c, speed, d, size);
		}

		System.out.println(solution());
	}

	private static int solution() {
		int sum = 0;
		//낚시꾼 왼쪽 열부터 ~ 
		for (int c = 1; c <= C; c++) {
			for (int r = 1; r <= R; r++) {
				if (sharkArr[map[r][c]] == null)
					continue;

				if (map[r][c] > 0) {
					sum += sharkArr[map[r][c]].size;
					sharkArr[map[r][c]] = null;
					break;
				}
			}

			bfs();
		}
		return sum;
	}

	private static void bfs() {
		// 1-> 상 , 2-> 하 , 3-> 우 , 4 -> 좌
		int[][] dArr = new int[][] { {}, { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

		int[][] newMap = new int[R + 1][C + 1];

		for (int idx = 1; idx < sharkArr.length; idx++) {
			if (sharkArr[idx] == null)
				continue;

			Shark nowShark = sharkArr[idx];

			int nowR = nowShark.r;
			int nowC = nowShark.c;
			int nowSpeed = nowShark.speed;
			int nowD = nowShark.d;
			int nowSize = nowShark.size;

			int dr = nowR;
			int dc = nowC;

			int moveCnt;
			// 1, 2 상하
			if (nowD == 1 || nowD == 2) {
				moveCnt = nowSpeed % ((R - 1) * 2);
				for (int i = 0; i < moveCnt; i++) {
					if (isInR(dr + dArr[nowD][0])) {
						nowD = changeD(nowD);
					}
					dr += dArr[nowD][0];
					dc += dArr[nowD][1];
				}
			}
			// 3, 4 우좌
			else {
				moveCnt = nowSpeed % ((C - 1) * 2);
				for (int i = 0; i < moveCnt; i++) {
					if (isInC(dc + dArr[nowD][1])) {
						nowD = changeD(nowD);
					}
					dr += dArr[nowD][0];
					dc += dArr[nowD][1];
				}
			}

			//맵에 이미 박혀있는 상어
			int preSharkIdx = newMap[dr][dc];

			if (newMap[dr][dc] > 0) {
				if (sharkArr[preSharkIdx].size > sharkArr[idx].size) {
					// newMap[sharkArr[idx].r][sharkArr[idx].c] = 0; <<
					sharkArr[idx] = null;
				} else {
					sharkArr[preSharkIdx] = null;
					sharkArr[idx] = new Shark(dr, dc, nowSpeed, nowD, nowSize);

					newMap[dr][dc] = idx;
				}
			}

			// 상어 없을 때
			else {
				sharkArr[preSharkIdx] = null;
				sharkArr[idx] = new Shark(dr, dc, nowSpeed, nowD, nowSize);
				newMap[dr][dc] = idx;
			}
		}

		/*
		 * debug for (int i = 1; i < newMap.length; i++) { for (int j = 1; j <
		 * newMap[i].length; j++) { if (sharkArr[newMap[i][j]] == null)
		 * System.out.print(0 + " "); else {
		 * System.out.print(sharkArr[newMap[i][j]].size + " "); } }
		 * System.out.println(); }
		 * 
		 * System.out.println("--------------------------");
		 */

		map = newMap;
	}

	private static int changeD(int pollD) {
		if (pollD == 1) {
			return pollD = 2;
		}

		if (pollD == 2) {
			return pollD = 1;
		}

		if (pollD == 3) {
			return pollD = 4;
		}

		if (pollD == 4) {
			return pollD = 3;
		}
		return pollD;
	}

	private static boolean isInR(int dr) {
		return dr < 1 || dr > R;
	}

	private static boolean isInC(int dc) {
		return dc < 1 || dc > C;
	}

}
