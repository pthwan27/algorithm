package com.backjoon.algorithm.week0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17472_다리만들기2 {
	static class IsLand {
		int r, c, num;

		public IsLand(int r, int c, int num) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
		}

		@Override
		public String toString() {
			return "IsLand [r=" + r + ", c=" + c + ", num=" + num + "]";
		}
	}

	static class Bridge {
		int start, end, len;

		public Bridge(int start, int end, int len) {
			super();
			this.start = start;
			this.end = end;
			this.len = len;
		}

		@Override
		public String toString() {
			return "Bridge [start=" + start + ", end=" + end + ", len=" + len + "]";
		}
	}

	static int N, M;
	static int[][] map;
	static int islandCnt;

	static ArrayList<IsLand> islandList;
	static ArrayList<Bridge> bridgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		islandList = new ArrayList<>();
		bridgeList = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 섬에 번호 마킹
		islandCounting();

		for (int i = 0; i < islandList.size(); i++) {
			makeBridge(islandList.get(i));

//			System.out.println(islandList.get(i));
		}

		int result = kruskal(bridgeList);
		System.out.println(result == 0 ? -1 : result);
	}

	static int[][] dArr = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private static void makeBridge(IsLand isLand) {
		int r = isLand.r;
		int c = isLand.c;
		int num = isLand.num;

		for (int i = 0; i < 4; i++) {
			int dr = r + dArr[i][0];
			int dc = c + dArr[i][1];

			int count = 0;

			while (isIn(dr, dc)) {
				if (map[dr][dc] == 0) {
					dr += dArr[i][0];
					dc += dArr[i][1];
					count++;
				} else if (map[dr][dc] != num) {
					if (count < 2)
						break;
					bridgeList.add(new Bridge(num, map[dr][dc], count));
					count = 0;
					break;
				} else {
					break;
				}
			}
		}
	}

	private static void islandCounting() {
		Queue<IsLand> islandQ = new ArrayDeque<>();

		boolean[][] isSelected = new boolean[N][M];

		islandCnt = 0;
		for (int mr = 0; mr < N; mr++) {
			for (int mc = 0; mc < M; mc++) {
				if (map[mr][mc] == 1 && !isSelected[mr][mc]) {
					islandQ.add(new IsLand(mr, mc, islandCnt));
					islandCnt++;
					while (!islandQ.isEmpty()) {
						IsLand pollIsland = islandQ.poll();

						int r = pollIsland.r;
						int c = pollIsland.c;

						for (int i = 0; i < 4; i++) {
							int dr = r + dArr[i][0];
							int dc = c + dArr[i][1];

							if (isIn(dr, dc) && !isSelected[dr][dc] && map[dr][dc] == 1) {
								isSelected[dr][dc] = true;
								map[dr][dc] = islandCnt;
								IsLand newIsLand = new IsLand(dr, dc, islandCnt);
								islandQ.offer(newIsLand);
								islandList.add(newIsLand);
							}
						}
					}
				}
			}
		}
	}

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	static ArrayList<Edge> edgeList;
	static int[] parent;
	
	static boolean union(int a, int b) {
		int roota = find(a);
		int rootb = find(b);

		if (roota == rootb) {
			return false;
		}
		parent[rootb] = roota;
		return true;
	}

	private static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}

	private static int kruskal(ArrayList<Bridge> bridgeList) {
		edgeList = new ArrayList<>();
		for(int i = 0; i < bridgeList.size(); i++) {
			Edge bridgeEdge = new Edge(bridgeList.get(i).start,bridgeList.get(i).end, bridgeList.get(i).len);
			
			edgeList.add(bridgeEdge);
		}
		Collections.sort(edgeList);
		
		parent = new int[islandCnt+1];

		for (int i = 1; i <= 4; i++) {
			parent[i] = i;
		}
		int result = 0;
		for(int i = 0; i < edgeList.size(); i++) {
			if(union(edgeList.get(i).from, edgeList.get(i).to)) {
				result+= edgeList.get(i).weight;
			}
		}

		return result;
	}

	private static boolean isIn(int dr, int dc) {
		return dr >= 0 && dc >= 0 && dr < N && dc < M;
	}

}
