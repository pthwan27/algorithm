package com.backjoon.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_7576_토마토_미완 {

	// 배열의 크기가 주어진다.
	// 1 익은 토마토, 0은 안익은 토마토, -1은 빈 곳
	// 1 은 상,하,좌,우를 1로 바꾼다. -1은 갈 수 없다.
	// 전체가 1로 변하는데 걸리는 최소 일 수
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		boolean[][] isSelected = new boolean[M][N];		
		int[][] tomatoArr = new int[M][N];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < N; j++) {
				tomatoArr[i][j] = Integer.parseInt(st.nextToken());				
				if(tomatoArr[i][j] == 1) {
					//일 수 체크를 어떤식으로 해야할까..?					
				}
			}
		}
		//입력 끝
	}

	public static boolean checkZero(int [][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				if(arr[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
}
