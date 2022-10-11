package com.swea.algorithm.week1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_7793_오나의여신님 {
	static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static class Dist {
		int r, c;

		public Dist(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static int N, M;

	static char[][] map;
	static boolean[][] isVisited;
	static boolean[][] isDistVisited;

	static Node heroNode;
	static Dist dist;

	static int result;

	static Queue<Dist> distQueue;

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		int t = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new char[N][M];
			isVisited = new boolean[N][M];
			isDistVisited = new boolean[N][M];

			distQueue = new LinkedList<>();

			for (int r = 0; r < N; r++) {
				String inputStr = in.readLine();
				for (int c = 0; c < M; c++) {
					map[r][c] = inputStr.charAt(c);

					if (map[r][c] == '*') {
						dist = new Dist(r, c);
						distQueue.offer(dist);
					}

					else if (map[r][c] == 'S') {
						heroNode = new Node(r, c);
						isVisited[r][c] = true;
					}

					else if (map[r][c] == 'X') {
						isVisited[r][c] = true;
					}
				}
			}

			//입력 끝

			result = 0;

			result = bfs();

			sb.append("#").append(tc).append(" ");
			if (result == -1) {
				sb.append("GAME OVER").append("\n");
			} else {
				sb.append(result).append("\n");
			}
		}
		System.out.println(sb);
	}

	//상 우 하 좌
	static int[][] dArr = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private static int bfs() {
		Queue<Node> queue = new LinkedList<>();

		queue.offer(heroNode);

		int count = 0; //몇초에 도착했는지 확인하기 위한 메서드

		while (!queue.isEmpty()) {
			//악마의 손아귀 범위 확장
			int distQsize = distQueue.size();

			while (distQsize-- > 0) {
				Dist pollDist = distQueue.poll();

				int r = pollDist.r;
				int c = pollDist.c;

				for (int i = 0; i < 4; i++) {
					int dr = r + dArr[i][0];
					int dc = c + dArr[i][1];

					if (isInDist(dr, dc) && !isDistVisited[dr][dc]) {
						isDistVisited[dr][dc] = true;
						map[dr][dc] = '*';
						distQueue.offer(new Dist(dr, dc));
					}
				}
			}

			int size = queue.size();

			while (size-- > 0) {
				Node pollNode = queue.poll();

				int r = pollNode.r;
				int c = pollNode.c;

				if (map[r][c] == 'D') {
					return count;
				}

				for (int i = 0; i < 4; i++) {
					int dr = r + dArr[i][0];
					int dc = c + dArr[i][1];

					if (isIn(dr, dc) && !isVisited[dr][dc]) {
						isVisited[dr][dc] = true;
						queue.add(new Node(dr, dc));
					}
				}
			}
			count++;
		}
		return -1;
	}

	private static boolean isIn(int dr, int dc) {
		return dr >= 0 && dc >= 0 && dr < N && dc < M && map[dr][dc] != '*' && map[dr][dc] != 'X';
	}

	private static boolean isInDist(int dr, int dc) {
		return dr >= 0 && dc >= 0 && dr < N && dc < M && map[dr][dc] != 'D' && map[dr][dc] != 'X' && map[dr][dc] != '*';
	}
}
