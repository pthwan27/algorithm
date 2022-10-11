package com.backjoon.algorithm.week1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3055_탈출 {
	static class Node {
		int r, c;
		char state;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
			this.state = map[r][c];
		}

		@Override
		public String toString() {
			return "r:" + r + " c:" + c + " state:" + state;
		}

	}

	static int R, C;
	static char map[][];

	static Queue<Node> queue;

	static ArrayList<Node> disList;
	static Node Hero;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		disList = new ArrayList<>();

		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			String inputStr = in.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = inputStr.charAt(c);
				if (map[r][c] == '*') {
					disList.add(new Node(r, c));
				}

				if (map[r][c] == 'S') {
					Hero = new Node(r, c);
				}
			}
		}

		//입력 끝

		int result = bfs();
		System.out.println(result == -1 ? "KAKTUS" : result);
	}

	private static int bfs() {
		final int[][] dArr = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		queue = new ArrayDeque<>();

		for (int i = 0; i < disList.size(); i++) {
			queue.offer(disList.get(i));
		}

		queue.offer(Hero);
		//queue 에 넣기

		int count = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			while (size-- > 0) {
				Node curNode = queue.poll();

				int r = curNode.r;
				int c = curNode.c;
				char state = curNode.state;

				//탈출구일때
				if (state == 'D') {
					return count;
				}

				//물일때
				if (state == '*') {
					for (int i = 0; i < 4; i++) {
						int dr = r + dArr[i][0];
						int dc = c + dArr[i][1];

						if (isIn(dr, dc)) {
							if (map[dr][dc] == '.' || map[dr][dc] == 'S') {
								map[dr][dc] = '*';
								queue.offer(new Node(dr, dc));
							}
						}
					}
				}

				//고슴도치일 때
				else {
					for (int i = 0; i < 4; i++) {
						int dr = r + dArr[i][0];
						int dc = c + dArr[i][1];

						if (isIn(dr, dc)) {
							if (map[dr][dc] == '.') {
								map[dr][dc] = 'S';
								queue.offer(new Node(dr, dc));
							} else if (map[dr][dc] == 'D') {
								queue.offer(new Node(dr, dc));
							}
						}
					}
				}
			}
			count++;
		}
		return -1;
	}

	private static boolean isIn(int dr, int dc) {
		return dr >= 0 && dc >= 0 && dr < R && dc < C;
	}
}
