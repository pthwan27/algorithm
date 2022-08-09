package com.swea.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1861_정사각형방 {

	static int N = 0; 
	
	static int[][] roomArr;
	static boolean[][] isVisited;
	
	//상 하 좌 우
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static int Max = Integer.MIN_VALUE;
	static int result = 1;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		for (int idx = 1; idx <= T; idx++) {

			N = Integer.parseInt(br.readLine());

			roomArr = new int[N][N];
			isVisited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					roomArr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; i++) {				
				for (int j = 0; j < N; j++) {
					dfs(i,j);		
					if(result > Max) {
						Max = result;
					}
					result = 1;
				}
			}
			System.out.println(Max);
		}
		
	}

	private static void dfs(int r, int c) {			
		for(int i = 0; i < 4; i++) {
			if( (r+dx[i] >= 0 && r+dx[i] < N )
					&& ( c+dy[i] >= 0 && c+dy[i] < N )
						&& (roomArr[r][c] + 1 == roomArr[r+dx[i]][c+dy[i]])) {
				if(isVisited[r+dx[i]][c+dy[i]] == true) {
					continue;
				}
				result++;
				isVisited[r][c] = true;
				dfs(r+dx[i],c+dy[i]);
				isVisited[r][c] = false;
							
			}
		}
		return;
	}
}
