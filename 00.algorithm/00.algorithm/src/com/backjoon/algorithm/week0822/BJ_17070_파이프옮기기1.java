package com.backjoon.algorithm.week0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17070_파이프옮기기1 {
	static class Pipe {
		int r, c, dir;

		// dir = 0 , 가로
		// dir = 1 , 대각선
		// dir = 2 , 세로
		public Pipe(int r, int c, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	static int[][] pipeMap;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());

		pipeMap = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int c = 0; c < N; c++) {
				pipeMap[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(bfs());
	}

	private static int bfs() {
		final int dx[] = new int[] { 0, 1, 1 };
		final int dy[] = new int[] { 1, 1, 0 };
		Queue<Pipe> pipeQueue = new LinkedList<>();
		Pipe pipe = new Pipe(0, 1, 0);

		pipeQueue.offer(pipe);

		boolean[][] isSelected = new boolean[N][N];

		int result = 0;
		
		while (!pipeQueue.isEmpty()) {
			Pipe tempPipe = pipeQueue.poll();

			int r = tempPipe.r;
			int c = tempPipe.c;
			int dir = tempPipe.dir;
			
			if(r == N-1 && c == N-1) {
				result++;
			}
			
			
			//벽 체크 부분
			if(dir == 0) {
				if(pipeMap[r][c-1] == 1 || pipeMap[r][c] == 1) {
					continue;
				}
			}
			
			if(dir == 1) {
				if(pipeMap[r-1][c] == 1 || pipeMap[r][c-1] == 1 || pipeMap[r][c] == 1) {
					continue;
				}
			}
			
			if(dir == 2) {
				if(pipeMap[r-1][c] == 1 || pipeMap[r][c] == 1) {
					continue;
				}
			}

			for (int i = 0; i < 3; i++) {
				int dr = r + dx[i];
				int dc = c + dy[i];
				if (isIn(dr, dc)) {
					if (dir == 0) {
						if (i == 2) {
							continue;
						}
						pipeQueue.offer(new Pipe(dr, dc, i));
					}

					else if (dir == 2) {
						if (i == 0) {
							continue;
						}
						pipeQueue.offer(new Pipe(dr, dc, i));
					}
					
					else{
						pipeQueue.offer(new Pipe(dr, dc, i));
					}
				}

			}

		}
		return result;

	}

	private static boolean isIn(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N && pipeMap[r][c] != 1) {
			return true;
		}
		return false;
	}

}
