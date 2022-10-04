package com.swea.algorithm.week0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기 {

	static int N, W, H;
	static int[][] map;

	static int[] output;
	static boolean[] isSelected;

	static class Node {
		int r, c, power;

		public Node(int r, int c, int power) {
			super();
			this.r = r;
			this.c = c;
			this.power = power;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException  {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine(), " ");
			

			//구슬 쏘는 횟수
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			output = new int[N];
			isSelected = new boolean[W];
			perm(0);
		}
	}

	private static void perm(int cur) {
		if (cur == N) {
			drop(output);
			return;
		}

		for (int i = 0; i < W; i++) {
			output[cur] = i;

			perm(cur + 1);
		}

	}

	static int[][] dArr = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private static void drop(int[] output) {
		Queue<Node> queue = new LinkedList<>();

		for (int n = 0; n < output.length; n++) {
			for (int r = 0; r < H; r++) {
				if (map[r][output[n]] == 1) {
					map[r][output[n]] = 0;
				} else if (map[r][output[n]] > 1) {
					queue.add(new Node(r, output[n], 0));
				}
			}

			while (!queue.isEmpty()) {
				Node pollNode = queue.poll();

				int r = pollNode.r;
				int c = pollNode.c;
				int power = pollNode.power;
				System.out.println(r+ " "+ c + " " +power);

				for (int a = 0; a < power; a++) {
					for (int i = 0; i < 4; i++) {
						int dr = r + (dArr[i][0] * power);
						int dc = c + (dArr[i][1] * power);

						System.out.println(dr + " " + dc);
					}
				}

			}

		}
	}
}
