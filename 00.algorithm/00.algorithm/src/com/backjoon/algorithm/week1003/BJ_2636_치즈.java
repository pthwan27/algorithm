package com.backjoon.algorithm.week1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2636_치즈 {
	static class Cheese {
		int r, c;

		public Cheese(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static int R, C;

	static int cheeseCnt;
	static int[][] map;
	static boolean[][] isVisited;

	static Queue<Cheese> bfsQ;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					cheeseCnt++;
				}
				
			}
		}
		
		int time = 0;
		int cnt = 0;
		bfsQ = new ArrayDeque<>();

		//치즈가 0개 될때 까지 시간 카운팅하면서 반복
		while(cheeseCnt > 0) {
			//전 턴에 남은 치즈갯수 저장해둠
			cnt = cheeseCnt;
			bfs();
			time++;			
		}
		
		System.out.println(time);
		System.out.println(cnt);
	}

	private static void bfs() {
		final int[][] dArr = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		
		//방문초기화 해줘야 다시 0,0부터 갈 수 있음
		isVisited = new boolean[R][C];
		bfsQ.offer(new Cheese(0, 0));

		while (!bfsQ.isEmpty()) {
			Cheese chs = bfsQ.poll();

			int r = chs.r;
			int c = chs.c;

			for (int i = 0; i < 4; i++) {
				int dr = r + dArr[i][0];
				int dc = c + dArr[i][1];

				//1일때 (치즈일 때) -> 0으로 만들고 치즈카운팅--(다음것들은 queue에 안넣음)
				if (isIn(dr, dc) && !isVisited[dr][dc] && map[dr][dc] == 1) {
					isVisited[dr][dc] = true;
					map[dr][dc] = 0;
					cheeseCnt--;
				}
				//0일때 (공기)
				else if (isIn(dr, dc) && !isVisited[dr][dc] && map[dr][dc] == 0) {
					isVisited[dr][dc] = true;
					bfsQ.offer(new Cheese(dr, dc));
				}
			}
		}
	}

	private static boolean isIn(int dr, int dc) {
		return dr >= 0 && dc >= 0 && dr < R && dc < C;
	}
}
