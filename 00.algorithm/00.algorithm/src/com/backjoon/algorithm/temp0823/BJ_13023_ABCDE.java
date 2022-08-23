package com.backjoon.algorithm.temp0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_13023_ABCDE {
	static ArrayList<Integer>[] graph;
	static boolean[] isChecked;

	static boolean isSuccess;

	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		// 그래프 만들기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}

		// 친구관계를 가진 A ,B, C, D, E가 존재하는지 확인
		for (int i = 0; i < N; i++) {
			isSuccess = false;
			isChecked = new boolean[N];
			dfs(i, 1);
			
			if(isSuccess) {
				System.out.println(1);
				return;
			}			
		}
		if(!isSuccess) {
			System.out.println(0);
		}
	}

	private static void dfs(int idx, int depth) {
		// 친구관계가 5명
		if (depth == 5) {
			isSuccess = true;			
			return;			
		}

		isChecked[idx] = true;

		
		for (int nextN : graph[idx]) {
			// 이미 방문한 곳은 X
			if (isChecked[nextN]) {
				continue;
			}
			// 연결된 것이 있을 때 
			dfs(nextN, depth + 1);
		}

		//백트래킹
		isChecked[idx] = false;
	}
}
