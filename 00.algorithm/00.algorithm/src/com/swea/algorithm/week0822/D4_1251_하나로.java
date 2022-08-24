package com.swea.algorithm.week0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class D4_1251_하나로 {

	static int[] parent;
	static int N;
	static double E;

	static ArrayList<Edge> edgeList;

	static class Edge implements Comparable<Edge> {
		int from, to;
		long weight;

		public Edge(int from, int to, long weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}

	// 크기1, 서로소 집합
	// 모든 노드가 자기자신을 부모로 하는(대표 ) 집합으로 만듦
	static void makeSet() {
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}

	// a 의 대표자 찾기
	static int findSet(int a) {
		if (a == parent[a])
			return a;

		return parent[a] = findSet(parent[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false;

		parent[bRoot] = aRoot;

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			// N 간선의 개수
			N = Integer.parseInt(in.readLine());
			ArrayList<Integer> xlist = new ArrayList<>();
			ArrayList<Integer> ylist = new ArrayList<>();

			parent = new int[N];

			st = new StringTokenizer(in.readLine());
			while (st.hasMoreTokens()) {
				xlist.add(Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(in.readLine());
			while (st.hasMoreTokens()) {
				ylist.add(Integer.parseInt(st.nextToken()));
			}

			E = Double.parseDouble(in.readLine());
			edgeList = new ArrayList<>();

			// 간선 정보 저장하기
			for (int i = 0; i < N; i++) {
				for (int j = 1; j < N; j++) {
					// 거리 구하기
					int a = Math.abs(xlist.get(i) - xlist.get(j));
					int b = Math.abs(ylist.get(i) - ylist.get(j));
					
					// weight값
					long v = (long) (Math.pow(a, 2) + Math.pow(b, 2));

					edgeList.add(new Edge(i, j, v));
				}
			}
			Collections.sort(edgeList);
			makeSet();

			int count = 0;
			int result = 0;
			for (Edge edge : edgeList) {
				// 싸이클이 형성되지 않으면
				if (union(edge.from, edge.to)) {
					result += edge.weight;

					if (++count == N - 1)
						break;
				}
			}

			System.out.println(result);

		}
	}
}
