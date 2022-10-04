package com.swea.algorithm.week0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_탈주범검거 {
	static class Pipe {
		int r, c, pipeType;

		public Pipe(int r, int c) {
			super();
			this.r = r;
			this.c = c;
			pipeType = map[r][c];
		}
	}

	static int[][] pipeTypes = { {}, // 1부터 시작하기때문에 추가
			{ 0, 1, 2, 3 }, { 0, 3 }, { 1, 2 }, { 0, 1 }, { 3, 1 }, { 3, 2 }, { 0, 2 } 
			};

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, R, C, L;

	static int[][] map;
	static int pipeCount = 0;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(in.readLine());

		for (int i = 1; i <= T; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < M; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			//			for(int [] arr : map) {
			//				System.out.println(Arrays.toString(arr));				
			//			}
			// 입력 끝

			// 로직 
			pipeCount = 0;
			bfs();

			//결과
			System.out.println("#" + i + " " + pipeCount);

		}
	}

	// 상, 우, 좌, 하 (대칭)
	static int[][] deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 0 } };

	private static void bfs() {
		Queue<Pipe> pipeQue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		pipeQue.offer(new Pipe(R, C));		
		visited[R][C] = true;
		
		L--;
		pipeCount = 1;
		while (L-- > 0) {
			int size = pipeQue.size();
			while (size-- > 0) {
				Pipe pollPipe = pipeQue.poll();
				int r = pollPipe.r;
				int c = pollPipe.c;

				int[] entrances = pipeTypes[pollPipe.pipeType];

				for (int i = 0; i < entrances.length; i++) {
					int dr = r + deltas[entrances[i]][0];
					int dc = c + deltas[entrances[i]][1];

					if (isIn(dr, dc) && !visited[dr][dc] && isConn(dr, dc, 3 - entrances[i])) {
						visited[dr][dc] = true;
						pipeCount++;
						pipeQue.add(new Pipe(dr, dc));
					}
				}
			}
		}
	}

	private static boolean isConn(int dr, int dc, int entrance) {
		int[] pipeType = pipeTypes[map[dr][dc]];
		for (int i = 0; i < pipeType.length; i++) {
			if (entrance == pipeType[i]) {
				return true;
			}
		}
		return false;
	}

	private static boolean isIn(int dr, int dc) {
		return dr >= 0 && dc >= 0 && dr < N && dc < M;
	}
}
