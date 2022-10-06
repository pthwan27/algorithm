package com.swea.algorithm.week0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D6_1263_사람네트워크2 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] graph = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
					if (i != j && graph[i][j] == 0)
						graph[i][j] = 9999;
				}
			}
//			//입력확인
//			for (int[] arr : graph) {
//				System.out.println(Arrays.toString(arr));
//			}

			//경유 노드
			for (int k = 0; k < N; k++) {
				//시작 노드
				for (int i = 0; i < N; i++) {
					//마지막 노드					
					for (int j = 0; j < N; j++) {
						if (graph[i][j] > graph[i][k] + graph[k][j])
							graph[i][j] = graph[i][k] + graph[k][j];	 //경유하여 가는 것이 더 빠르면 그걸로 업데이트
					}
				}
			}

			int result = 9999; //모든 노드로부터 가장 가까운 사용자
			for (int r = 0; r < N; r++) { //각 노드의 최단경로구하기
				int cc = 0;
				for (int c = 0; c < N; c++) {					
					cc += graph[r][c];
//					System.out.printf("%d -> %d: %d\n", r, c, graph[r][c]);
				}
				result = Math.min(cc, result);
			}
			
			sb.append("#"+tc +" " +result+"\n");
		}
		System.out.println(sb);
	}
}
