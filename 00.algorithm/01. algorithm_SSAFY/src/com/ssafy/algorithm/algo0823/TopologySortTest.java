package com.ssafy.algorithm.algo0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TopologySortTest {
	static class Node {
		int vertex;
		Node next;

		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}

	}

	static int V,E;
	static Node[] adjList;
	static int[] inDegree;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		V = Integer.parseInt(st.nextToken()); 
		E = Integer.parseInt(st.nextToken());

		adjList = new Node[V+1]; // 각 정점별 인접리스트
		inDegree = new int[V+1]; // 정점별 진입차수

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			// 유향 처리
			adjList[from] = new Node(to, adjList[from]);
			inDegree[to]++;

		}
		ArrayList<Integer> list = topolySort();
		if(list.size() == V) {//위상정렬 완성
			for(int i : list) {
				System.out.println(i + " ");
			}
			System.out.println();
		}
		else {
			System.out.println("Cycle...발생");
		}
	}
	
	private static ArrayList<Integer> topolySort(){
		ArrayList<Integer> list = new ArrayList<>();
		
		Queue<Integer> queue = new LinkedList<>();
		//진입차수가 0인 정점 큐에 넣기
		for(int i = 1; i <= V; i++) {
			if(inDegree[i] == 0) queue.offer(i);
		}
		
		//BFS
		while(!queue.isEmpty()) {
			//정점
			int cur = queue.poll();
			list.add(cur);
			//정점의 인접리스트를 보면서
			for(Node temp = adjList[cur]; temp != null; temp = temp.next) {
				//차수를 1줄였더니 0이 되면 -> 더이상 선행작업이 없다. -> 큐에 넣음 
				if(--inDegree[temp.vertex] == 0) {
					queue.offer(temp.vertex);
				}
			}
		}
		return list;
	}

}
