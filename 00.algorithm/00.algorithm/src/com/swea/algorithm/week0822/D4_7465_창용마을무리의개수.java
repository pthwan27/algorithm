package com.swea.algorithm.week0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_7465_창용마을무리의개수 {
	static int[] parents;
	static int N;

	static void makeSet() {
		// 1부터 시작
		parents = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parents[i] = -1;
		}
	}

	// a의 대표자 찾기
	static int findSet(int a) {
		if (parents[a] == -1)
			return a;
		return parents[a] = findSet(parents[a]);
	}

	// 그룹만들기
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			// 모든 노드가 자신을 부모로하는(대표자) 집합으로 만듦
			makeSet();

			int cnt = N; // 최대 그룹갯수

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				// 그룹이 만들어지면 최대그룹갯수에서 --
				if (union(a, b))
					cnt--;
			}

			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}
