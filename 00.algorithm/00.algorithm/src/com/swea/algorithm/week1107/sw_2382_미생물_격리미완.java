package com.swea.algorithm.week1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class sw_2382_미생물_격리미완 {
	static class Node {
		int r, c, count, dir;

		public Node(int r, int c, int count, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", count=" + count + ", dir=" + dir + "]";
		}
	}

	static int N, M, K;

	static Node[] nodeArr;

	static int[][] map;

	static final int[][] dir = { {}, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine());

			//N -> 크기, M-> 격리 시간 , K -> 군집의 갯수
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			nodeArr = new Node[K+1];
			map = new int[N][N];
			for (int i = 1; i <= K; i++) {
				st = new StringTokenizer(in.readLine());

				int r, c, cnt, dir;
				r = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				cnt = Integer.parseInt(st.nextToken());
				dir = Integer.parseInt(st.nextToken());

				nodeArr[i] = new Node(r, c, cnt, dir);

				map[r][c] = i;
			}

			for (int t = 0; t < M; t++) {
				for (int cnt = 1; cnt < K+1; cnt++) {
					if (nodeArr[cnt] == null)
						continue;

					int r = nodeArr[cnt].r;
					int c = nodeArr[cnt].c;
					int count = nodeArr[cnt].count;

					int dr = r + dir[nodeArr[cnt].dir][0];
					int dc = c + dir[nodeArr[cnt].dir][1];

					if (isInLine(dr, dc)) {
						int changeDir = 0;
						int changeCnt = count / 2;

						//방향 바꾸기
						if (nodeArr[cnt].dir == 1) {
							changeDir = 2;
						} else if (nodeArr[cnt].dir == 2) {
							changeDir = 1;
						} else if (nodeArr[cnt].dir == 3) {
							changeDir = 4;
						} else if (nodeArr[cnt].dir == 4) {
							changeDir = 3;
						}

						map[r][c] = 0;
						map[dr][dc] = cnt;

						nodeArr[cnt] = new Node(dr, dc, changeCnt, changeDir);
					}

					else if (isInNode(dr, dc)) {
						Node curNode = nodeArr[map[dr][dc]];
						Node inputNode = nodeArr[cnt];

						int changeDir = 0;
						int changeCnt = curNode.count + inputNode.count;

						if (curNode.count > inputNode.count) {
							map[inputNode.r][inputNode.c] = 0;
							nodeArr[cnt] = null;

							changeDir = curNode.dir;
						} else {
							nodeArr[map[dr][dc]] = null;
							map[inputNode.r][inputNode.c] = 0;
							map[curNode.r][curNode.c] = cnt;

							changeDir = inputNode.dir;
						}
						nodeArr[map[dr][dc]] = new Node(dr, dc, changeCnt, changeDir);
					}
					else {
						map[dr][dc] = cnt;
						nodeArr[cnt] = new Node(dr,dc,)
					}
					
				}
			}

		}

	}

	private static boolean isInNode(int dr, int dc) {
		return map[dr][dc] > 0;
	}

	private static boolean isInLine(int dr, int dc) {
		return dr == 0 || dr == N || dc == 0 || dc == N;
	}

}
