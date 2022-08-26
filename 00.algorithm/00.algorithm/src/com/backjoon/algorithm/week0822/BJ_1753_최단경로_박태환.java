package com.backjoon.algorithm.week0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1753_최단경로_박태환 {
	static class Node implements Comparable<Node> {
		int to, weight;

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}

	}

	static ArrayList<Node>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int K = Integer.parseInt(in.readLine());// 시작 정점

		graph = new ArrayList[V + 1];

		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}

		// 그래프 완성
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			graph[from].add(new Node(to, weight));
		}

		int[] D = new int[V + 1]; // 출발지에서 자신으로 오는데 소요되는 최소 비용 저장
		
		Arrays.fill(D, Integer.MAX_VALUE);
		//출발지는 0으로 세팅
		D[K] = 0;

		PriorityQueue<Node> pQueue = new PriorityQueue<>();
		pQueue.offer(new Node(K, 0));
		
		while (!pQueue.isEmpty()) {
			Node curNode = pQueue.poll();
			int to = curNode.to;		
			for (Node temp : graph[to]) {
				//다음칸의 최소길이보다  현재까지의 길이 + 현재부터 다음노드까지의 길이가 작으면
				//값 갱신
				if (D[temp.to] > temp.weight + D[to]) {
					D[temp.to] = temp.weight + D[to];
					pQueue.add(new Node(temp.to, D[temp.to]));
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < D.length; i++) {
			if (D[i] < Integer.MAX_VALUE) {
				sb.append(D[i]).append("\n");
			} else {
				sb.append("INF").append("\n");
			}
		}

		System.out.println(sb);
	}
}
