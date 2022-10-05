package com.backjoon.algorithm.week0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17143_낚시왕 {
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
		for (int i = 0; i < sharkList.size(); i++) {
			System.out.println(sharkList.get(i));
		}

		int sum = 0;
		for (int t = 1; t <= C; t++) {
			System.out.println();

			for (int r = 1; r <= R; r++) {
				int catchShark = -1;
				if (map[r][t] > 0) {

					for (int i = 0; i < sharkList.size(); i++) {
						if (sharkList.get(i) == null)
							continue;

						if (map[r][t] == sharkList.get(i).idx) {
							catchShark = i;
							map[r][t] = 0;
							sum += sharkList.get(catchShark).size;

							sharkList.set(catchShark, null);
							break;
						}
					}
				}
				System.out.println("cs : " + catchShark);
				if (catchShark >= 0)
					break;
			}
//			for (int idx = 0; idx < sharkList.size(); idx++) {
//				// 물고기 잡고 BFS로
//				if (sharkList.get(idx) == null)
//					continue;
//
//				if (sharkList.get(idx).c == t) {
//					sum += sharkList.get(idx).size;
//					int r = sharkList.get(idx).r;
//					int c = sharkList.get(idx).c;
//					map[r][c] = 0;
//					sharkList.set(idx, null);
//
//					break;
//				}
//			}

			System.out.println("bfs");
			bfs();
		}
		return sum;
	}

	private static void bfs() {
		// 1-> 상 , 2-> 하 , 3-> 우 , 4 -> 좌
		int[][] dArr = new int[][] { {}, { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

		Queue<Shark> sharkQ = new LinkedList<>();
		int[][] newMap = new int[R + 1][C + 1];

		for (int idx = 0; idx < sharkList.size(); idx++) {
			if (sharkList.get(idx) == null)
				continue;
			sharkQ.add(sharkList.get(idx));
		}

		int size = sharkQ.size();

		while (size-- > 0) {
			Shark pollShark = sharkQ.poll();

			int pollIdx = pollShark.idx;

			int pollR = pollShark.r;
			int pollC = pollShark.c;
			int pollSpeed = pollShark.speed;
			int pollD = pollShark.d;
			int pollSize = pollShark.size;

			int dr = pollR;
			int dc = pollC;

			// 1, 2 상하
			if (pollD == 1 || pollD == 2) {
				for (int i = 0; i < pollSpeed % ((R - 1) * 2); i++) {
					if (isInR(dr + dArr[pollD][0])) {
						pollD = changeD(pollD);
					}
					dr += dArr[pollD][0];
					dc += dArr[pollD][1];
				}
			}
			// 3, 4 우좌
			else {
				for (int i = 0; i < pollSpeed % ((C - 1) * 2); i++) {
					if (isInC(dc + dArr[pollD][1])) {
						pollD = changeD(pollD);
					}
					dr += dArr[pollD][0];
					dc += dArr[pollD][1];
				}
			}

			System.out.println();
			for (int[] arr : newMap) {
				System.out.println(Arrays.toString(arr));
			}

			if (newMap[dr][dc] > 0) {
				int inputSharkIdx = 0;
				int pollSharkIdx = 0;

				for (int i = 0; i < sharkList.size(); i++) {
					if (sharkList.get(i) == null)
						continue;

					if (newMap[dr][dc] == sharkList.get(i).idx) {
						inputSharkIdx = i;
					}

					if (pollIdx == sharkList.get(i).idx) {
						pollSharkIdx = i;
					}

					if (inputSharkIdx != 0 && pollSharkIdx != 0)
						break;
				}

				//여기
				if (sharkList.get(inputSharkIdx).size > sharkList.get(pollSharkIdx).size) {
					sharkList.set(pollSharkIdx, null);
					
					newMap[dr][dc] = sharkList.get(inputSharkIdx).idx;
				} else {
					sharkList.set(inputSharkIdx, null);
					newMap[dr][dc] = sharkList.get(pollSharkIdx).idx;
				}
			}
			
			// 상어 없을 때
			else {
				int pollSharkIdx = 0;
				for (int i = 0; i < sharkList.size(); i++) {
					if (sharkList.get(i) == null)
						continue;
					if (pollIdx == sharkList.get(i).idx) {
						pollSharkIdx = i;
					}
				}

				sharkList.set(pollSharkIdx, new Shark(pollIdx, dr, dc, pollSpeed, pollD, pollSize));
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
