package com.backjoon.algorithm.week0822;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class BabyShark {
	int r, c, size;

	public BabyShark(int r, int c, int size) {
		super();
		this.r = r;
		this.c = c;
		this.size = size;
	}

	@Override
	public String toString() {
		return "BabyShark [r=" + r + ", c=" + c + ", size=" + size + "]";
	}
}

class Fish {
	int r, c, size;

	public Fish(int r, int c, int size) {
		super();
		this.r = r;
		this.c = c;
		this.size = size;
	}

	@Override
	public String toString() {
		return "Fish [r=" + r + ", c=" + c + ", size=" + size + "]";
	}
}

public class BJ_16236_아기상어 {

	static ArrayList<Fish> fishList = new ArrayList<>();
	static BabyShark babyShark;

	static int[][] map;
	static boolean[][] isVisited;

	static int N;

	static int eatFishCount;

	// 0 빈칸, 1~6 물고기의 크기, 9 아기상어 위치
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(in.readLine());
		map = new int[N][N];

		// 아기상어 정보
		babyShark = new BabyShark(0, 0, 0);

		// 맵 입력
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < N; c++) {
				// 맵에 값 입력하면서, 아기상어, 물고기 정보 담아두기
				map[r][c] = Integer.parseInt(st.nextToken());

				if (map[r][c] == 9) {
					babyShark.r = r;
					babyShark.c = c;
					babyShark.size = 2;
				}

				if (map[r][c] > 0 && map[r][c] < 7) {
					Fish fish = new Fish(r, c, map[r][c]);
					fishList.add(fish);
				}
			}
		}
		// 입력 끝

		eatFishCount = 0;
		Simulation();
	}

	private static void Simulation() {

		int count = 0;
		// 잡아먹을 물고기가 있을 때 까지 반복하고
		// 거리를 늘려준다. -> count
		while (isPossible()) {
			isVisited = new boolean[N][N];
			int dist = bfs(babyShark.size);
		}
		System.out.println(count);
	}

	private static int bfs(int sharkSize) {
		// 상, 좌, 우, 하
		int[] dr = new int[] { -1, 0, 0, 1 };
		int[] dc = new int[] { 0, -1, 1, 0 };

		Queue<Point> babySharkQ = new LinkedList<Point>();

		babySharkQ.add(new Point(babyShark.r, babyShark.c));

		int cnt = 0;

		int aR = 0, aC = 0, minCnt = Integer.MAX_VALUE;
		boolean countCheck = false;

		while (!babySharkQ.isEmpty()) {
			int qSize = babySharkQ.size();
			for (int j = 0; j < qSize; j++) {
				Point shark = babySharkQ.poll();
				isVisited[shark.x][shark.y] = true;
				for (int i = 0; i < 4; i++) {
					int goR = shark.x + dr[i];
					int goC = shark.y + dc[i];

					// 들어갈수 있는지 검사
					if (isIn(goR, goC) && !isVisited[goR][goC] && map[goR][goC] <= babyShark.size) {
						isVisited[goR][goC] = true;// 방문처리

						if (map[goR][goC] != 0 && map[goR][goC] < sharkSize) {
							if (countCheck == true) { // 먹을 수 있는 물고기가 여러마리라면
								if (minCnt > cnt) {
									minCnt = cnt;
									aR = goR;
									aC = goC;
								}
							}
						}
					}
				}
			}
		}
		return minCnt;
	}

	// 잡아먹을 물고기가 있는 지 확인
	private static boolean isPossible() {
		for (int i = 0; i < fishList.size(); i++) {
			if (babyShark.size > fishList.get(i).size) {
				return true;
			}
		}
		return false;
	}

	// N*N 범위를 나가는지 확인
	private static boolean isIn(int goR, int goC) {
		if (goR >= 0 && goC >= 0 && goR < N && goC < N) {
			return true;
		}
		return false;
	}
}
