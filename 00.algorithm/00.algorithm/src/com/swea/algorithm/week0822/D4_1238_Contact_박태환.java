package com.swea.algorithm.week0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class D4_1238_Contact_박태환 {
	static int[][] contactMap;

	static int[] visit;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());

			int L = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			contactMap = new int[101][101];

			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < L / 2; i++) {
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());

				contactMap[A][B] = 1;
			}
			visit = new int[101];
			sb.append("#"+tc+" "+ bfs(v) + "\n");			
		}
		System.out.println(sb);
	}

	private static int bfs(int v) {
		Queue<Integer> bfsQ = new LinkedList<>();
		bfsQ.offer(v);
		
		//마지막층 찾기 위해
		int depth = 1;		

		visit[v] = depth;

		while (!bfsQ.isEmpty()) {
			v = bfsQ.poll();
			for (int i = 0; i <= 100; i++) {
				if (contactMap[v][i] == 1 && visit[i] == 0) {
					bfsQ.offer(i);
					visit[i] = visit[v] + 1;
				}
			}
			//마지막층을 찾고
			depth = Math.max(depth, visit[v]);
		}

		//거꾸로 돌면서 마지막 층에 있는 가장 큰 값을 return
		for (int i = 100; i >= 0; i--) {
			if (visit[i] == depth) {
				return i;
			}
		}
		return -1;

	}
}
