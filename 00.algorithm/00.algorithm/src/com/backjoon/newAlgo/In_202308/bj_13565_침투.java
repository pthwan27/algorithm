package com.backjoon.newAlgo.In_202308;

import java.io.*;
import java.util.*;

public class bj_13565_침투 {
	static class Node {
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int M, N;
	static int[][] map;
	static boolean[][] isSelected;

	static ArrayList<Node> whiteList;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		M = Integer.parseInt(inputs[0]);
		N = Integer.parseInt(inputs[1]);

		map = new int[M][N];

		whiteList = new ArrayList<>();

		for (int r = 0; r < M; r++) {
			String input = in.readLine();
			for (int c = 0; c < N; c++) {
				map[r][c] = input.charAt(c) - '0';
				if (r == 0 && map[r][c] == 0) {
					whiteList.add(new Node(r, c));
				}
			}
		}

		String result = "NO";

		isSelected = new boolean[M][N];
		for (int i = 0; i < whiteList.size(); i++) {
			Node cur = whiteList.get(i);

			if (!isSelected[cur.r][cur.c]) {
				if (bfs(cur)) {
					result = "YES";
					break;
				}
			}
		}

		System.out.println(result);

	}

	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, -1, 0, 1 };

	private static boolean bfs(Node n) {
		Queue<Node> bfsQ = new LinkedList<>();
		bfsQ.offer(n);
		isSelected[n.r][n.c] = true;

		while (!bfsQ.isEmpty()) {
			Node cur = bfsQ.poll();

			if (cur.r == M-1) {
				return true;
			}

			for (int i = 0; i < 4; i++) {
				int nR = cur.r + dr[i];
				int nC = cur.c + dc[i];

				if (isIn(nR, nC) && map[nR][nC] == 0 && !isSelected[nR][nC]) {
					bfsQ.offer(new Node(nR, nC));
					isSelected[nR][nC] = true;
				}
			}

		}
		return false;
	}

	private static boolean isIn(int nR, int nC) {
		return nR >= 0 && nR < M && nC >= 0 && nC < N;
	}

}
