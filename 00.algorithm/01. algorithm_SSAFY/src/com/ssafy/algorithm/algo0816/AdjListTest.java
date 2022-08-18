package com.ssafy.algorithm.algo0816;

import java.util.Scanner;

public class AdjListTest {
//연결리스트
	static class Node {
		int to; //상대정점 번호
		Node next; //넥스트필드(링크)
		//나중에 가중치 추가해도됌
		
		public Node(int to, Node next) {
			super();
			this.to = to;
			this.next = next;
		}
		
	}
	
	static Node[] adjList;
	static int N;
	static boolean visited[];
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int E = sc.nextInt();
		
		adjList = new Node[N];
		
		for(int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}
		
		dfs(0);
		
	}
	private static void dfs(int cur) {
		
		visited[cur] = true;
		
		// 현 정점의 인접정점들에 큐에 넣어서 차후 탐색하도록 만들기
		for(Node temp = adjList[cur]; temp != null; temp = temp.next) {
			if(!visited[temp.to]) { // 방문하지 않았으며 인접한 경우
				dfs(temp.to);
			}
		}
		
	}

}
