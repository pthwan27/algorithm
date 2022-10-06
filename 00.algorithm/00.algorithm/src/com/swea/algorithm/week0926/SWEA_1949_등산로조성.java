package com.swea.algorithm.week0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1949_등산로조성 {
	static class Point {
		int r, c, count;

		public Point(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}
	}

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	static int N, K;
	static int[][] map;

	static ArrayList<Point> highList;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			highList = new ArrayList<>();

			int high = 0;
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					high = Math.max(map[r][c], high);
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] == high) {
						highList.add(new Point(r, c, K));
					}
				}
			}

			for (int i = 0; i < highList.size(); i++) {
				int result = bfs(highList.get(i));
			}
		}
	}

	private static int bfs(Point start) {
		final int[][] dArr = new int[][] { {-1,0} , {0,1}, {1,0}, {0,-1}};
		Queue<Point> bfsQ = new ArrayDeque<>();
		
		boolean[][] isSelected= new boolean[N][N];
		
		bfsQ.add(start);
		
		while(!bfsQ.isEmpty()) {
			Point point = bfsQ.poll();
			
			int r = point.r;
			int c = point.c;
			int count = point.count;
			
			
			for(int i = 0; i < 4; i++) {
				int dr = r+dArr[i][0];
				int dc = r+dArr[i][1];
				if(isIn(dr,dc) )
			}
		}
				
		return 9999;
	}
}
