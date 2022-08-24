package com.backjoon.algorithm.temp0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int idx, weight;

	public Node(int idx, int weight) {
		super();
		this.idx = idx;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
}

public class D4_3124_최소스패닝트리_박태환 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for (int tc = 0; tc++ < T;) {
			st = new StringTokenizer(in.readLine());

			// 정점의 개수, 간선의 개수
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			ArrayList<Node>[] nodeList = new ArrayList[v + 1];
			boolean[] isvisited = new boolean[v + 1];

			for (int i = 0; i < v + 1; i++) {
				nodeList[i] = new ArrayList<>();
			}
			int a = 0;
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(in.readLine());

				a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				nodeList[a].add(new Node(b, w));
				nodeList[b].add(new Node(a, w));
			}

			// Prim PriorityQueue// NlogN
			PriorityQueue<Node> pQ = new PriorityQueue<>();
			pQ.offer(new Node(a, 0));

			long result = 0;

			while (!pQ.isEmpty()) {
				Node curNode = pQ.poll();

				// 방문한 노드면 다음 노드
				if (isvisited[curNode.idx])
					continue;

				isvisited[curNode.idx] = true;

				result += curNode.weight;

				// 시작 노드가 가르키는 값들 검사 후 queue에 넣어줌
				for (int i = 0; i < nodeList[curNode.idx].size(); i++) {
					Node tempNode = nodeList[curNode.idx].get(i);
					if (!isvisited[tempNode.idx]) {
						pQ.offer(tempNode);
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}
