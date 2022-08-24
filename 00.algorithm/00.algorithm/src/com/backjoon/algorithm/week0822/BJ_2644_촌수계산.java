package com.backjoon.algorithm.week0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_2644_촌수계산 {
	static ArrayList<Integer>[] graph;

	static int N, M;

	static boolean[] isSelected;
	static int count;
	
	static int calcNum1;
	static int calcNum2;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine()); //전체사람의 수
		graph = new ArrayList[N+1];

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		calcNum1 = Integer.parseInt(st.nextToken());
		calcNum2 = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		M = Integer.parseInt(in.readLine()); // 관계의 수

		//그래프 만들기
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(in.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			graph[A].add(B);
			graph[B].add(A);
		}

		count = -1;
		isSelected = new boolean[N+1];
		//dfs start
		dfs(calcNum1, 0);
		
		System.out.println(count);
	}

	private static void dfs(int calcNum, int depth) {		
		if(calcNum == calcNum2) {
			count = depth;
			return;
		}
		isSelected[calcNum] = true;
		
		for(int nextNum : graph[calcNum]) {
			if(isSelected[nextNum]) continue;
			
			dfs(nextNum, depth+1);
		}
	}

}
