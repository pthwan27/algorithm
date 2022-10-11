package com.backjoon.algorithm.week1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17143_낚시왕2 {
	static class Shark {
		// 위치, 속력, 방향, 크기
		int idx;
		int r, c, speed, d, size;

		public Shark(int idx, int r, int c, int speed, int d, int size) {

			super();
			this.idx = idx;
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.d = d;
			this.size = size;
		}

		@Override
		public String toString() {
			return "Shark [idx=" + idx + ", r=" + r + ", c=" + c + ", speed=" + speed + ", d=" + d + ", size=" + size
					+ "]";
		}
	}

	// map 크기, 상어의 수
	static int R, C, M;

	static int[][] map;

	static ArrayList<Shark> sharkList;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[R + 1][C + 1];
		sharkList = new ArrayList<>();
		for (int idx = 1; idx <= M; idx++) {
			st = new StringTokenizer(in.readLine());

			int r, c, speed, d, size;

			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			speed = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			size = Integer.parseInt(st.nextToken());

			map[r][c] = idx;

			sharkList.add(new Shark(idx, r, c, speed, d, size));
		}

		System.out.println(solution());
	}

	private static int solution() {
		int sum = 0;
		for (int c = 1; c <= C; c++) {
			for (int r = 1; r <= R; r++) {
				if (map[r][c] > 0) {
					if (sharkList.get(map[r][c] - 1) == null)
						continue;

					sum += sharkList.get(map[r][c] - 1).size;
					sharkList.set(map[r][c] - 1, null);
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

		for (int idx = 0; idx < sharkList.size(); idx++) {
			if (sharkList.get(idx) == null)
				continue;

			Shark pollShark = sharkList.get(idx);

			int pollIdx = pollShark.idx;

			int pollR = pollShark.r;
			int pollC = pollShark.c;
			int pollSpeed = pollShark.speed;
			int pollD = pollShark.d;
			int pollSize = pollShark.size;

			int dr = pollR;
			int dc = pollC;

			int moveCnt;
			// 1, 2 상하
			if (pollD == 1 || pollD == 2) {
				moveCnt = pollSpeed % ((R - 1) * 2);
				for (int i = 0; i < moveCnt; i++) {
					if (isInR(dr + dArr[pollD][0])) {
						pollD = changeD(pollD);
					}
					dr += dArr[pollD][0];
					dc += dArr[pollD][1];
				}
			}
			// 3, 4 우좌
			else {
				moveCnt = pollSpeed % ((C - 1) * 2);
				for (int i = 0; i < moveCnt; i++) {
					if (isInC(dc + dArr[pollD][1])) {
						pollD = changeD(pollD);
					}
					dr += dArr[pollD][0];
					dc += dArr[pollD][1];
				}
			}

			int preSharkIdx = 0;
			int newSharkIdx = 0;

			if (newMap[dr][dc] > 0) {
				preSharkIdx = newMap[dr][dc] - 1;
				newSharkIdx = sharkList.get(pollIdx-1).idx - 1;

				//여기
				if (sharkList.get(preSharkIdx).size > sharkList.get(newSharkIdx).size) {
					sharkList.set(newSharkIdx, sharkList.get(preSharkIdx));

					newMap[dr][dc] = sharkList.get(preSharkIdx).idx;
				} else {
					sharkList.set(preSharkIdx, sharkList.get(newSharkIdx));

					newMap[dr][dc] = sharkList.get(newSharkIdx).idx;
				}
			}

			// 상어 없을 때
			else {
				sharkList.set(pollIdx-1, new Shark(pollIdx, dr, dc, pollSpeed, pollD, pollSize));
				newMap[dr][dc] = pollIdx;
			}
		}
		saveMap(newMap);
	}

	private static void saveMap(int[][] newMap) {
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				map[r][c] = 0;
				map[r][c] = newMap[r][c];
			}
		}
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
