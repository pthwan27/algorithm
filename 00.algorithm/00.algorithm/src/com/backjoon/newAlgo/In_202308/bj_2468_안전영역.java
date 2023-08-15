package com.backjoon.newAlgo.In_202308;

import java.util.*;
import java.io.*;

public class bj_2468_안전영역 {

	static int N;
	static int[][] map;
	static boolean[][] isSelected;

	static ArrayList<rain> rainList;

	static int maxResult = Integer.MIN_VALUE;

	static class rain {
		int r, c, m;

		public rain(int r, int c, int m) {
			this.r = r;
			this.c = c;
			this.m = m;
		}
	}
	static boolean[] rainSelected = new boolean[101];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		rainList = new ArrayList<>();

		rainList.add(new rain(0, 0, 0));
		rainSelected[0] = true;

		for (int r = 0; r < N; r++) {
			String[] inputs = in.readLine().split(" ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(inputs[c]);

				if(!rainSelected[map[r][c]]) {
					rainList.add(new rain(r, c, map[r][c]));
					rainSelected[map[r][c]] = true;					
				}
			}
		}
		
		for(int i = 0; i < rainList.size(); i++) {
			isSelected = new boolean[N][N];
			maxResult = Math.max(maxResult, check(rainList.get(i).m));			
		}
		
		System.out.println(maxResult);

	}

	public static int check(int rain) {
		int count = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!isSelected[r][c] && map[r][c] > rain) {
					isSelected[r][c] = true;
					bfs(r, c, rain);
					count++;
				}
			}
		}
		return count;
	}

	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, 1, 0, -1 };

	public static void bfs(int r, int c, int rain) {
		Queue<int[]> bfsQ = new LinkedList<>();

		bfsQ.offer(new int[] { r, c });
		while (!bfsQ.isEmpty()) {
			int[] cur = bfsQ.poll();

			for (int i = 0; i < 4; i++) {
				int nextR = cur[0] + dr[i];
				int nextC = cur[1] + dc[i];

				if (isIn(nextR, nextC) && !isSelected[nextR][nextC] && map[nextR][nextC] > rain) {
					isSelected[nextR][nextC] = true;
					bfsQ.offer(new int[] { nextR, nextC });
				}
			}

		}
	}

	public static boolean isIn(int nR, int nC) {
		return nR >= 0 && nR < N && nC >= 0 && nC < N;
	}

}
