package com.backjoon.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17406_배열돌리기 {

	static int[][] area;

	static int N = 0;
	static int M = 0;

	static int MinLineSum = Integer.MAX_VALUE;	

	// 하 , 우 , 상 , 좌
	static int[] dx = { 1,0,-1,0};
	static int[] dy = { 0,1, 0,-1};

	static int r;
	static int c;
	static int s;
	
	static int[][] rcsarr;
	
	static int[][] solveArr;
	static boolean[] isSelected;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 배열 크기 N,M
		N = Integer.parseInt(st.nextToken())+1;
		M = Integer.parseInt(st.nextToken())+1;
		area = new int[N][M];

		// 회전연산 개수 K
		int K = Integer.parseInt(st.nextToken());

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 1; j < M; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// r, c, s -> (r-s, c-s) ~ (r+s, c+s) 를 시계방향으로 한 칸 돌린다.
		// 그리고 나서 각 행의 합 중 최소값을 찾는다.
		if(K == 1) {
			st = new StringTokenizer(in.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			rotate(r - s, c - s, r + s, c + s);
			CalcLineSum(area);
			
			sb.append(MinLineSum);
		}
		else {
			rcsarr = new int[K][3];
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(in.readLine());

				r = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				s = Integer.parseInt(st.nextToken());
				rcsarr[i][0] = r;
				rcsarr[i][1] = c;
				rcsarr[i][2] = s;
			}
			isSelected = new boolean[K];
			Arrays.fill(isSelected, true);
			solveArr = new int[K][3];
			
			solve(0, K);	
			
			sb.append(MinLineSum);
		}
		
		System.out.println(sb);
	}	
	
	private static void solve(int c, int K) {
		if(c == K) 
		{
			for(int i = 0; i < rcsarr.length; i++) {
				rotate(rcsarr[i][0] - rcsarr[i][2], rcsarr[i][1] - rcsarr[i][2], rcsarr[i][0] + rcsarr[i][2], rcsarr[i][1] + rcsarr[i][2]);
				CalcLineSum(area);
			}			
			return;
		}
		
		isSelected[c] = false;
		solveArr[c] = rcsarr[c];
		solve(c+1, K);
		isSelected[c] = true;		
	}
	private static void rotate(int startX, int startY, int endX, int endY) {
		//시작지점 저장해두기 , rotate하고나서 그 부분에 덮어쓰기 할 때 필요.		
		int sX = startX;
		int sY = startY;
		int eX = endX;
		int eY = endY;
		
		int[][] partArr = new int[endX - startX+1][endY - startY+1];

		for (int i = startX; i <= endX; i++) {
			for (int j = startY; j <= endY; j++) {
				partArr[i - startX][j - startY] = area[i][j];
			}	
		}
		
		int dir = 0;
		int startNum = 0;
		
		for(int i = 0; i < Math.min(partArr.length / 2 , partArr[0].length / 2); i++) {
			int x = i;
			int y = i;
			
			startNum = partArr[x][y];			
			while(dir < 4) {
				int GoX = x + dx[dir];
				int GoY = y + dy[dir];	
				if (GoX < i || GoX >= partArr.length-i || GoY < i || GoY >= partArr[0].length-i) {
					dir++;			
				}else {
					partArr[x][y] = partArr[GoX][GoY];
					x = GoX;
					y = GoY;
				}
			}
			partArr[i+1][i] = startNum;
		}
		
		
		//원래 자리에 넣기
		for(int i = sX; i <= eX; i++) {
			for(int j = sY; j <= eY; j++) {
				area[i][j] = partArr[i-sX][j-sY];
			}
		}
	}

	// 라인 중 최소값 구하기
	private static void CalcLineSum(int[][] arr) {
		int Sum = 0;
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				Sum += arr[i][j];
				if (Sum > MinLineSum) {
					break;
				}
			}
			if (Sum < MinLineSum) {
				MinLineSum = Sum;
			}
		}
	}

}
