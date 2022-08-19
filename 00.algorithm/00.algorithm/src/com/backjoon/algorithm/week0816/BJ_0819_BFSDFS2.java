package com.backjoon.algorithm.week0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_0819_BFSDFS2 {	
	static int map[][];
	static boolean[] visited;
	static int N, M, V;

	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1]; //정점간 연결 정보 저장
		visited = new boolean[N+1]; // 방문 확인
		Arrays.fill(visited, false);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
		
			map[x][y] = map[y][x] = 1;
		}
		System.out.println("--------------");
		for(int[] m : map) System.out.println(Arrays.toString(m));
		
		dfs(V);
		
	}

	private static void dfs(int v) {
		if(visited[v]) {
			return;
		}
		visited[v] = true;
		
		answer.append(v + " ");
		
	}
}

		