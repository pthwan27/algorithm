package com.ssafy.algorithm.algo0816;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

import com.sun.java_cup.internal.runtime.virtual_parse_stack;

public class AdjMatrixTest {

	static int[][] adjMaxtrix;
	static int N;
	
	static boolean[] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		int E = sc.nextInt();

		adjMaxtrix = new int[N][N];
		visited = new boolean[N];
		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();

			adjMaxtrix[from][to] = adjMaxtrix[to][from] = 1;
		}

//		bfs();
		
		dfs(0);
		
	}
	
	

	private static void dfs(int cur) {
		visited[cur] = true;

		System.out.println((char)(cur+'A'));
		for(int i = 0; i < N; i++) {
			if(!visited[i] && adjMaxtrix[cur][i] != 0) { //방문하지 않았으면 인접한 경우
				dfs(i);
			}
		}
		
	}



	private static void bfs() {
		//0정점 시작점
		Queue<Integer> queue = new ArrayDeque<>();	
		
		visited[0] = true;
		queue.offer(0);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			System.out.println((char)(cur+'A'));
			
			//현 정점의 인접정점들에 큐에 넣어서 차후 탐색하도록 만들기
			for (int i = 0; i < N; i++) {
				if(!visited[i] && adjMaxtrix[cur][i] != 0) { //방문하지 않았으면 인접한 경우
					visited[i] = true;
					queue.offer(i);
				}
				
			}
		}
		
	}

}
