package com.swea.algorithm.week0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D4_3124_최소스패닝트리 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc++ < T;) {
			st = new StringTokenizer(in.readLine());

			// 정점의 개수, 간선의 개수
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			int[][] map = new int[V][V];
			boolean[] isVisited = new boolean[V];
			int[] minEdge = new int[V];

			for (int i = 0; i < map.length; i++) {
				Arrays.fill(map[i], 0);
			}

			for (int i = 0; i < V; i++) {
				st = new StringTokenizer(in.readLine());
				int r = Integer.parseInt(st.nextToken())-1;
				int c = Integer.parseInt(st.nextToken())-1;
				int wgt = Integer.parseInt(st.nextToken());

				map[r][c] = wgt;
				map[c][r] = wgt;

				minEdge[i] = Integer.MAX_VALUE;
			}

			int minVertex, min; // 최소 정점, 최소 정점의 간선 비용
			long result = 0; // Mst 비용
			minEdge[0] = 0; // 임의의 시작점 비용으로 초기화

			// PRIM 1단계 : 정점 중심 해결 : 모든 정점수만큼 반복하면서 모든 정점을 연결
			for (int c = 0; c < V; c++) {
				min = Integer.MAX_VALUE; // 초기값 최대값으로 설정
				minVertex = 0; // 임의정점 : 0 or -1

				// 정점수만큼 반복하면서
				for (int i = 0; i < V; i++) {
					if (!isVisited[i] && min > minEdge[i]) {
						min = minEdge[i];
						minVertex = i;
					}
				}

				isVisited[minVertex] = true;
				result += min;
				
				// PRIM 2단계 
				// 선택된 최소비용 정점과 신장트리 구성에 포함되어있지 않은 다른정점으로의 최소비용 갱신
				for(int i = 0; i < V; i++) {
					if(!isVisited[i]
							&& map[minVertex][i] != 0
							&& minEdge[i] > map[minVertex][i]) {
						minEdge[i] = map[minVertex][i];
					}
				}
			}
			sb.append("#"+tc + " " +result).append("\n");
		}
		System.out.println(sb);
	}
}
