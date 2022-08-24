package com.backjoon.algorithm.temp0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class D4_1251_하나로_박태환 {

	// 간선클래스
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

	static void makeSet() {
		// 각 노드를 부모로하는 집합 만들기
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

	// 부모노드찾기
	static int findSet(int a) {
		if (parents[a] == a)
			return a;

		return parents[a] = findSet(parents[a]);
	}

	static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);

		// 사이클이다
		if (rootA == rootB) {
			return false;
		}

		// rootB의 부모를 rootA로 변경
		parents[rootB] = rootA;

		return true;
	}

	static int[] parents;
	static int N;
	static ArrayList<Edge> edgeList;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(in.readLine());

			ArrayList<Integer> rList = new ArrayList<>();
			ArrayList<Integer> cList = new ArrayList<>();

			st = new StringTokenizer(in.readLine());
			while (st.hasMoreTokens()) {
				rList.add(Integer.parseInt(st.nextToken()));
			}

			st = new StringTokenizer(in.readLine());
			while (st.hasMoreTokens()) {
				cList.add(Integer.parseInt(st.nextToken()));
			}

			// 세율
			double E = Double.parseDouble(in.readLine());

			parents = new int[N];
			edgeList = new ArrayList<>();
			//각 노드 만들고
			makeSet();
			
			//edgeList 값 넣기
			for (int i = 0; i < N; i++) {
				for (int j = 1; j < N; j++) {
					int distR = Math.abs(rList.get(i) - rList.get(j));
					int distC = Math.abs(cList.get(i) - cList.get(j));
					// 가중치
					long weight = (long) (Math.pow(distR, 2) + Math.pow(distC, 2));
				
					edgeList.add(new Edge(i, j, weight));
				}
			}
			
			Collections.sort(edgeList);
			
			long sum = 0;
			int cnt = N;
			
			for(Edge edge : edgeList) {
				if(union(edge.from, edge.to)) {
					sum += edge.weight;
					cnt--;
					
					if(cnt == 0) {
						break;
					}
				}
			}
			
			sb.append("#"+tc+ " " + Math.round(sum*E) + "\n");

		}
		System.out.println(sb);

	}
}
