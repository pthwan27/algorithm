package com.backjoon.algorithm.d230601;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_9663_NQueen {
	static int N;
	static int[] QueenPostionArr;
	static int cnt;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		QueenPostionArr = new int[N];
		dfs(0);			
		System.out.println(cnt);
	}
	public static void dfs(int cur) {
		if(cur == N) {
			cnt++;
			return;			
		}
		for(int i = 0; i < N; i++) {
			QueenPostionArr[cur] = i;
			
			if(isPossible(cur)) {
				dfs(cur+1);
			}
		}
	}
	public static boolean isPossible(int col){
		for(int i =0 ; i < col; i++) {
			if(QueenPostionArr[i] == QueenPostionArr[col]) {
				return false;
			}
			
			else if(Math.abs(i - col) == Math.abs(QueenPostionArr[i] - QueenPostionArr[col])) {
				return false;
			}
		}
		
		
		return true;
	}
}
