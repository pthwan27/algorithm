package com.swea.algorithm.week1107;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class sw_4193_수영대회결승전 {
	static int N;
	static int[][] map;
	static boolean[][] isVisited;

	static Point startPoint;
	static Point endPoint;
	
	static int result;

	static class Node implements Comparable<Node>{
		int r, c, count;

		public Node(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", count=" + count + "]";
		}

		@Override
		public int compareTo(Node o) {
			return this.count - o.count;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(in.readLine());

			map = new int[N][N];
			isVisited = new boolean[N][N];
			result = 0;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(in.readLine());
			startPoint = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			st = new StringTokenizer(in.readLine());
			endPoint = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			result = bfs();
			
			sb.append("#" + tc + " " + result + "\n");
		}
		System.out.println(sb);

	}

	private static int bfs() {
		final int[][] dir = new int[][] {{-1,0}, {0,1}, {1,0}, {0,-1}};
		Queue<Node> bfsQueue = new ArrayDeque<Node>();
		bfsQueue.offer(new Node(startPoint.x,startPoint.y, 0));
		
		while(!bfsQueue.isEmpty()) {
			Node pollNode = bfsQueue.poll();
			
			int r = pollNode.r;
			int c = pollNode.c;
			int count = pollNode.count;
			
			isVisited[r][c] = true;
			
			if(endPoint.x == r && endPoint.y == c) {
				return count;
			}
			
			for(int i = 0; i < 4; i++) {
				int dr = r + dir[i][0];
				int dc = c + dir[i][1];
				
				//범위 벗어나지않고, 1이 아닐 때
				if(!isIn(dr,dc)) continue;
				
				if(isVisited[dr][dc]) continue;
				
				if(map[dr][dc] == 2) {					
					bfsQueue.offer(new Node(dr, dc, 3 * (count / 3 + 1)));
				}

				if(map[dr][dc] == 0){
					bfsQueue.offer(new Node(dr,dc,count+1));
				}				
			}
		}
		return -1;
	}

	private static boolean isIn(int dr, int dc) {
		return dr >= 0 && dc >= 0 && dr < N && dc < N && map[dr][dc] != 1;
	}
}
