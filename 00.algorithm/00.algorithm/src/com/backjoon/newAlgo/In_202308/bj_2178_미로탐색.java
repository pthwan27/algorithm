package com.backjoon.newAlgo.In_202308;

import java.io.*;
import java.util.*;

public class bj_2178_미로탐색 {

	static int N, M;
	static Node[][] map;
	static int[][] isSelected;

	static class Node implements Comparable<Node> {
		int r, c, cnt;

		public Node(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			return this.cnt - o.cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		map = new Node[N + 1][M + 1];
		isSelected = new int[N + 1][M + 1];

		for (int r = 1; r <= N; r++) {
			String inputStr = in.readLine();
			for (int c = 1; c <= M; c++) {
				int nextC = inputStr.charAt(c-1) - '0';
				map[r][c] = new Node(r, c, nextC);
				isSelected[r][c] = nextC;
			}
		}
		bfs();
		
		System.out.println(isSelected[N][M]);
	}

	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, -1, 0, 1 };

	public static void bfs() {
		PriorityQueue<Node> bfsQ = new PriorityQueue<>();

		bfsQ.offer(map[1][1]);
		isSelected[1][1] = 1;
		
		while (!bfsQ.isEmpty()) {
			Node cur = bfsQ.poll();
			
			for(int i = 0; i < 4; i++) {
				int nextR= cur.r + dr[i];
				int nextC= cur.c + dc[i];
				int cnt = cur.cnt;
				
				if(isIn(nextR, nextC) && isSelected[nextR][nextC] == 1) {
					isSelected[nextR][nextC] = cnt + 1;
					bfsQ.offer(new Node(nextR, nextC, cnt+1));
				}
			}
		}
	}

	public static boolean isIn(int nR, int nC) {
		return nR >= 1 && nR <= N && nC >= 1 && nC <= M;
	}

}
