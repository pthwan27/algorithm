package com.backjoon.algorithm.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11660_구간합구하기5 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();

		// 표의 크기 N
		int N = Integer.parseInt(st.nextToken());
		// 구해야 하는 횟수 M
		int M = Integer.parseInt(st.nextToken());

		int[][] area = new int[N+1][N+1];

		// 값 넣기
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 1; j <= N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] sumArea = new int[N+1][N+1];	
		
		for (int i = 1; i <= N; i++) {			
			for (int j = 1; j <= N; j++) {
				if(j == 1) {
					sumArea[i][j] = area[i][j];
				}
				else {
					sumArea[i][j] = sumArea[i][j-1] + area[i][j];
				}				
			}						
		}
	
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			
			int x1 = Integer.parseInt(st.nextToken()); 
			int y1 = Integer.parseInt(st.nextToken());
			
			int x2 = Integer.parseInt(st.nextToken()); 
			int y2 = Integer.parseInt(st.nextToken());
			
			int sum = 0;
			
			//ex ) 
			// 2 2 ~ 3 4		
			// (24 - 21) + (34 - 31)					
			
			// 3 4 ~ 3 4
			// 3 4
			
			// 1 1 ~ 4 4		
			// (14 - 10) + (24 - 20) + ( 34 - 30 ) + ( 44 - 40)

			if(x1 == x2 && y1 == y2) {
				sb.append(area[x1][y1] + "\n");
			}
			else {
				for(int j = 0; j <= x2-x1; j++) {
					sum += sumArea[x1+j][y2];
					sum -= sumArea[x1+j][y1-1];
				}
				sb.append(sum + "\n");
			}
		}
		System.out.println(sb);
		
		
	}
}
