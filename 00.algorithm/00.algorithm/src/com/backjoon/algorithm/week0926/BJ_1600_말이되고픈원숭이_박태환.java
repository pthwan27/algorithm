package com.backjoon.algorithm.week0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1600_말이되고픈원숭이_박태환 {
	static class Monkey {
		int r, c, horseCnt;

		public Monkey(int r, int c, int horseCnt) {
			super();
			this.r = r;
			this.c = c;
			this.horseCnt = horseCnt;
		}
	}

	static int k, w, h;
	static int result;

	static int[][] map;
	static boolean[][][] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 원숭이가 말 처럼 움직이는 횟수
		k = Integer.parseInt(in.readLine());

		StringTokenizer st = new StringTokenizer(in.readLine());

		// 가로 세로
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		map = new int[h][w];

		isVisited = new boolean[h][w][k + 1];

		for (int r = 0; r < h; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < w; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				for (int i = 0; i < k + 1; i++) {
					if (map[r][c] == 1) {
						isVisited[r][c][i] = true;
					}
				}
			}
		}

		// 로직
		bfs();
	}

	// 원숭이의 이동 범위
	static int[] monkeyDx = new int[] { -1, 0, 1, 0 };
	static int[] monkeyDy = new int[] { 0, -1, 0, 1 };

	// 말의 이동 범위
	static int[] horseDx = new int[] { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] horseDy = new int[] { -2, -1, 1, 2, 2, 1, -1, -2 };

	private static void bfs() {
		Queue<Monkey> monkeyQ = new LinkedList<>();

		monkeyQ.offer(new Monkey(0, 0, k));

		int count = 0;
		boolean passCheck = true;
		while (passCheck) {
			int size = monkeyQ.size();

			if(size == 0 && passCheck) {
				System.out.println(-1);
				break;
			}
			while (size-- > 0) {
				Monkey monkey = monkeyQ.poll();

				int dr = monkey.r;
				int dc = monkey.c;
				int horseCnt = monkey.horseCnt;

				//도착지에 갔을 때 종료되게
				if (dr == h - 1 && dc == w - 1) {
					passCheck = false;
					System.out.println(count);
					break;
				}

				//말처럼 이동할 수 있는 경우(나이트)
				if (horseCnt > 0) {
					for (int i = 0; i < 8; i++) {
						int horseDr = dr + horseDx[i];
						int horseDc = dc + horseDy[i];
						if (isIn(horseDr, horseDc) && !isVisited[horseDr][horseDc][horseCnt - 1]) {
							isVisited[horseDr][horseDc][horseCnt - 1] = true;
							monkey = new Monkey(horseDr, horseDc, horseCnt - 1);
							monkeyQ.add(monkey);
						}
					}
				}

				//원숭이처럼 이동(4방)
				for (int i = 0; i < 4; i++) {
					int monkeyDr = dr + monkeyDx[i];
					int monkeyDc = dc + monkeyDy[i];
					if (isIn(monkeyDr, monkeyDc) && !isVisited[monkeyDr][monkeyDc][horseCnt]) {
						isVisited[monkeyDr][monkeyDc][horseCnt] = true;
						monkey = new Monkey(monkeyDr, monkeyDc, horseCnt);
						monkeyQ.add(monkey);
					}
				}
			}
			
			count++;
		}
	}

	//범위 체크 + 장애물 체크
	private static boolean isIn(int dr, int dc) {
		return dr >= 0 && dc >= 0 && dr < h && dc < w && map[dr][dc] != 1;
	}

}
