package com.backjoon.algorithm.week0822;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16236_아기상어2 {

	static int[][] map;
	static boolean[][] isSelected;

	static int N;
	static int r, c;

	static int eatFishCount;
	static int result;

	// 0 빈칸, 1~6 물고기의 크기, 9 아기상어 위치
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(in.readLine());
		map = new int[N][N];

		// 맵 입력
		for (int a = 0; a < N; a++) {
			st = new StringTokenizer(in.readLine());
			for (int b = 0; b < N; b++) {
				// 맵에 값 입력하면서, 아기상어, 물고기 정보 담아두기
				map[a][b] = Integer.parseInt(st.nextToken());

				if (map[a][b] == 9) {
					r = a;
					c = b;
				}

			}
			// 입력확인
			// System.out.println(Arrays.toString(map[r]));
		}
		// 입력 끝

		eatFishCount = 0;
		result = 0;
		Simulation();

		System.out.println(result);
	}

	private static void Simulation() {
		int size = 2;

		while (true) {
			isSelected = new boolean[N][N];

			int count = findFish(size);

			for (int i = 0; i < map.length; i++) {
				System.out.println(Arrays.toString(map[i]));
			}
			System.out.println();

			if (count != 0) {
				eatFishCount++;
				result += count;
				if (eatFishCount == size) {
					size++;
					eatFishCount = 0;
				}
			} else
				break; // 물고기를 못먹으면
		}
	}

	private static int findFish(int size) {
		// 상, 좌, 우, 하
		final int[] dr = new int[] { -1, 0, 0, 1 };
		final int[] dc = new int[] { 0, -1, 1, 0 };

		Queue<Point> babysharkQ = new LinkedList<>();
		babysharkQ.offer(new Point(r, c));
		int cnt = 0;

		boolean countCheck = false;
		int minR = N, minC = N, minCnt = Integer.MAX_VALUE;

		while (!babysharkQ.isEmpty()) {
			int Qsize = babysharkQ.size();
			for (int j = 0; j < Qsize; j++) {
				Point shark = babysharkQ.poll();

				for (int i = 0; i < 4; i++) {
					int goR = shark.x + dr[i];
					int goC = shark.y + dc[i];

					// 범위 안에 있고, 이동할 수 있는 곳이라면
					if (isIn(goR, goC) && !isSelected[goR][goC] && map[goR][goC] <= size) {
						// 방문체크
						isSelected[goR][goC] = true;

						if (map[goR][goC] != 0 && map[goR][goC] < size) {
							if (countCheck == true) { // 먹을 수 있는 물고기가 2개 이상일때
								if (minCnt > cnt) { // 거리가 같을 때는 짧은 물고기로
									minCnt = cnt;
									minR = goR;
									minC = goC;
								} else if (minCnt == cnt) { // 거리가 같지만 높이가 다를 때 위에 물고기로
									if (minR > goR) {
										minCnt = cnt;
										minR = goR;
										minC = goC;
									} else if (minR == goR) { // 거리같고, 높이같을 땐 왼쪽 물고기로
										if (minC > goC) {
											minCnt = cnt;
											minR = goR;
											minC = goC;
										}
									}
								}
							} else {
								minCnt = cnt;
								minR = goR;
								minC = goC;
								countCheck = true; // 다음엔 2개 이상으로 들어감
							}
						}
						babysharkQ.add(new Point(goR, goC));
					}
				}
			}

			cnt++;
			if (countCheck) {
				minCnt++;
				map[r][c] = 0;
				r = minR;
				c = minC;
				map[r][c] = 9;
				break;
			}
		}

		if (!countCheck)
			return 0;
		return minCnt;
	}

	// N*N 범위를 나가는지 확인
	private static boolean isIn(int goR, int goC) {
		if (goR >= 0 && goC >= 0 && goR < N && goC < N) {
			return true;
		} else
			return false;
	}
}
