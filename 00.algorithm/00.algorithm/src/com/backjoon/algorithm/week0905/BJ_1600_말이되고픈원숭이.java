package com.backjoon.algorithm.week0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1600_말이되고픈원숭이 {

	static class Monkey{
		int r, c, cnt, k;

		public Monkey(int r, int c, int cnt, int k) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.k = k;
		}
	}
	
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(in.readLine());
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int c = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		map = new int[r][c];
		for(int a = 0; a < r; a++) {
			st = new StringTokenizer(in.readLine());
			for(int b = 0; b < c; b++) {
				map[a][b] = Integer.parseInt(st.nextToken());				
			}
		}
		
		BFS(0,0,0, k);
	}
	
	
	//말의 이동 반경
	static int [] horseDr = new int[] {-1,-2,-2,-1, 1, 2, 2, 1};
	static int [] horseDc = new int[] {-2,-1, 1, 2, 2, 1,-1,-2};
	
	//인간의 이동 반경
	static int [] dr = new int[] {-1, 0, 1, 0};
	static int [] dc = new int[] {0, 1, 0, -1};
			
	private static void BFS(int i, int j, int cnt, int k) {
		Queue<Monkey> bfsQ = new LinkedList<>();	
		
		bfsQ.add(new Monkey(0,0,0,k));
			
		
		
	}

}
