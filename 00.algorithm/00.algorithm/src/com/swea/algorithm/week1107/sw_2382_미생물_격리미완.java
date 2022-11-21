package com.swea.algorithm.week1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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

				for(int i = 0; i < N; i++) {
					System.out.println(Arrays.toString(map[i]));					
				}
				System.out.println();
				
				for (int c = 1; c < K+1; c++) {
					if (nodeArr[c].count == 0)
						continue;

					int curR = nodeArr[c].r;
					int curC = nodeArr[c].c;
					int curCnt = nodeArr[c].count;
					int curDir= nodeArr[c].dir;
					int dr = curR + dir[nodeArr[c].dir][0];
					int dc = curC + dir[nodeArr[c].dir][1];

					if (isInLine(dr, dc)) {
						int changeDir = 0;
						int changeCnt = curCnt / 2;

						//방향 바꾸기
						if (nodeArr[c].dir == 1) {
							changeDir = 2;
						} else if (nodeArr[c].dir == 2) {
							changeDir = 1;
						} else if (nodeArr[c].dir == 3) {
							changeDir = 4;
						} else if (nodeArr[c].dir == 4) {
							changeDir = 3;
						}

						map[curR][curC] = 0;
						map[dr][dc] = c;

						nodeArr[c] = new Node(dr, dc, changeCnt, changeDir);
					}

					else if (isInNode(dr, dc)) {
						Node curNode = nodeArr[map[dr][dc]];
						Node inputNode = nodeArr[c];

						int changeDir = 0;
						int changeCnt = curNode.count + inputNode.count;

						if (curNode.count > inputNode.count) {
							map[inputNode.r][inputNode.c] = 0;
							nodeArr[c].count = 0;

							changeDir = curNode.dir;
						} else {
							nodeArr[map[dr][dc]].count = 0;
							map[inputNode.r][inputNode.c] = 0;
							map[curNode.r][curNode.c] = c;

							changeDir = inputNode.dir;
						}
						nodeArr[map[dr][dc]] = new Node(dr, dc, changeCnt, changeDir);
					}
					else {
						map[dr][dc] = c;
						nodeArr[c] = new Node(dr,dc,curCnt, curDir);
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
