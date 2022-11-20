package com.swea.algorithm.week1101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_5656_break {
	static int N, W, H;
	static int[][] map;

	static int[] numArr;

	static int totalCount;
	static int tempCount;
	static int minResult;

	static class Node {
		int r, c, power;

		public Node(int r, int c, int power) {
			super();
			this.r = r;
			this.c = c;
			this.power = power;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", power=" + power + "]";
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());

		for (int i = 1; i <= T; i++) {

			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];

			totalCount = 0;
			tempCount = 0;
			minResult = Integer.MAX_VALUE;

			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] > 0)
						totalCount++;
				}
			}
			//중복순열
			numArr = new int[N];
			perm(0);
			sb.append("#" + i + " " + minResult+"\n");
		}
		System.out.println(sb);
	}

	private static void perm(int cur) {
		if (cur == N) {
			int[][] newMap = new int[H][W];
			tempCount = totalCount;
			newMap = copy(map);

			drop(newMap, numArr);
			minResult = Math.min(minResult, tempCount);

			return;
		}

		for (int i = 0; i < W; i++) {
			numArr[cur] = i;
			perm(cur + 1);
		}
	}

	private static void drop(int[][] newMap, int[] nums) {
		// N (구슬 갯수) 만큼 반복하면서 
		for (int i = 0; i < nums.length; i++) {
			int c = nums[i]; //구슬 떨어트릴 위치

			for (int r = 0; r < H; r++) {
				if (newMap[r][c] > 0) {
					if (newMap[r][c] == 1) {
						newMap[r][c] = 0;
						tempCount--;
						break;
					} else {
						newMap = boom(new Node(r, c, newMap[r][c]), newMap);
						newMap = gravity(newMap);
						break;
					}
				}
			}
		}
	}

	private static int[][] gravity(int[][] newMap) {
		Deque<Integer> queue = new ArrayDeque<>();
		for (int c = 0; c < W; c++) {
			for (int r = 0; r < H; r++) {
				if (newMap[r][c] > 0) {
					queue.offer(newMap[r][c]);
				}
				newMap[r][c] = 0;
			}

			int r = H - 1;
			while (!queue.isEmpty()) {
				newMap[r--][c] = queue.pollLast();
			}
		}
		return newMap;
	}

	private static int[][] boom(Node node, int[][] newMap) {
		final int[][] dir = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(node);

		newMap[node.r][node.c] = 0;
		tempCount--;

		while (!queue.isEmpty()) {
			Node pollNode = queue.poll();
			int r = pollNode.r;
			int c = pollNode.c;
			int power = pollNode.power;

			for (int i = 0; i < 4; i++) {
				for (int a = 1; a < power; a++) {
					int dr = r + (dir[i][0] * a);
					int dc = c + (dir[i][1] * a);

					if (isIn(dr, dc)) {
						if (newMap[dr][dc] > 0) {
							if (newMap[dr][dc] == 1) {
								newMap[dr][dc] = 0;
								tempCount--;

							} else {
								queue.offer(new Node(dr, dc, newMap[dr][dc]));
								newMap[dr][dc] = 0;
								tempCount--;
							}

						}
					}
				}
			}
		}
		return newMap;
	}

	private static boolean isIn(int dr, int dc) {
		return dr >= 0 && dc >= 0 && dr < H && dc < W;
	}

	private static int[][] copy(int[][] originMap) {
		int[][] newMap = new int[H][W];

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j] = originMap[i][j];
			}
		}
		return newMap;
	}
}
