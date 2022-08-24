package com.backjoon.algorithm.temp0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_3289_서로소집합_박태환 {

	static void makeSet() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int a) {
		if (parents[a] == a) {
			return a;
		}

		return parents[a] = findSet(parents[a]);
	}

	static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);

		if (rootA == rootB) {
			return false;
		}

		parents[rootB] = rootA;
		return true;
	}

	static int[] parents;

	static int N, M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 0; tc++ < T;) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			sb.append("#").append(tc).append(" ");
			parents = new int[N + 1];
			makeSet();

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine());
				int comm = Integer.parseInt(st.nextToken());

				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				// 합집합 연산
				if (comm == 0) {
					union(a, b);
				}
				// a와 b가 같은 집합에 포함되어 있는지를 확인
				else {
					if (findSet(a) == findSet(b)) {
						sb.append(1);
					} else
						sb.append(0);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}

}
