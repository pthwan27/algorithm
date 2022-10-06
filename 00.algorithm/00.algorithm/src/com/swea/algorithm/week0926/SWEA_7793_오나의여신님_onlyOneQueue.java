package com.swea.algorithm.week0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7793_오나의여신님_onlyOneQueue {
	static class Node {
		int r, c;
		char state;

		public Node(int r, int c, char state) {
			super();
			this.r = r;
			this.c = c;
			this.state = state;
		}
	}

	static int N, M;

	static char[][] map;
	static boolean[][] isVisited;
	static boolean[][] isDistVisited;

	static Node heroNode;

	static int result;

	static Deque<Node> bfsQueue;

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

			bfsQueue = new LinkedList<>();
			for (int r = 0; r < N; r++) {
				String inputStr = in.readLine();
				for (int c = 0; c < M; c++) {
					map[r][c] = inputStr.charAt(c);

					if (map[r][c] == '*') {
						bfsQueue.addFirst(new Node(r, c, '*'));
					}

					else if (map[r][c] == 'S') {
						bfsQueue.addLast(new Node(r, c, 'S'));
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
		int count = 1; //몇초에 도착했는지 확인하기 위한 메서드

		while (true) {
			//악마의 손아귀 범위 확장
			int distQsize = bfsQueue.size();
			int newS = 0;
			while (distQsize-- > 0) {
				Node pollNode = bfsQueue.poll();

				int r = pollNode.r;
				int c = pollNode.c;
				char state = pollNode.state;

				for (int i = 0; i < 4; i++) {
					int dr = r + dArr[i][0];
					int dc = c + dArr[i][1];
					if (isIn(dr, dc)) {
						if (state == '*' && (map[dr][dc] == '.' || map[dr][dc] == 'S')) {
							map[dr][dc] = '*';
							bfsQueue.offer(new Node(dr, dc, '*'));
						}

						else if (state == 'S') {
							if (map[dr][dc] == '.') {
								map[dr][dc] = 'S';
								bfsQueue.offer(new Node(dr, dc, 'S'));
								newS++;
							} else if (map[dr][dc] == 'D') {
								return count;
							}
						}
					}
				}
			}
			if (newS == 0) {
				break;
			}
			count++;
		}
		return -1;
	}

	private static boolean isIn(int dr, int dc) {
		return dr >= 0 && dc >= 0 && dr < N && dc < M;
	}
}
