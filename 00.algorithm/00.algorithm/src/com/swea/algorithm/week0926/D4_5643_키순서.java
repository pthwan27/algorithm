package com.swea.algorithm.week0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_5643_키순서 {
	static int N, M;
	static int[][] graph;

	static boolean[] isVisited;

	static int tallerCnt;
	static int shorterCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(in.readLine());
			M = Integer.parseInt(in.readLine());

			graph = new int[N + 1][N + 1];

			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());

				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());

				graph[start][end] = 1;
			}

			//			입력 확인
			//			for(int [] arr : graph) {
			//				System.out.println(Arrays.toString(arr));
			//			}


			int result = 0;

			for (int a = 1; a <= N; a++) {
				tallerCnt = 0;
				shorterCnt = 0;

				// 자신보다 큰애들 찾기, 작은애들 찾기
				taller(a, new boolean[N + 1]);
				shorter(a, new boolean[N + 1]);

				if (tallerCnt + shorterCnt == N - 1) {
					result++;
				}

			}
			System.out.println("#" + tc + " " + result);
		}

	}

	private static void shorter(int to, boolean[] visited) {
		visited[to] = true;
		for(int i=1; i<N+1; i++) {
			if(!visited[i] && graph[i][to] == 1) {
				shorter(i, visited);
				shorterCnt++;
			}
		}
	}

	private static void taller(int from, boolean[] visited) {
		visited[from] = true;
		for(int i=1; i<N+1; i++) {
			if(!visited[i] && graph[from][i] == 1) {
				taller(i, visited);
				tallerCnt++;
			}
		}
	}

}
