package com.swea.algorithm.week1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class sw_2382_미생물_격리미완2 {
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

	static ArrayList<Node> nodeList = new ArrayList<>();
	static int[][] cntMap;
	static int[][] BigMap;
	static int[][] dirMap;

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
			
			dirMap = new int[N][N];
			cntMap = new int[N][N];
			BigMap = new int[N][N];
			nodeList.clear();
			for (int i = 1; i <= K; i++) {
				st = new StringTokenizer(in.readLine());

				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());

				nodeList.add(new Node(r, c, cnt, dir));

			}
			for (int t = 0; t < M; t++) {
				init();
				nodeUpdate();
				mapUpdate();
			}
			
			System.out.println("#" + tc + " " + sum());
		}
	}

	private static void nodeUpdate() {
		// list 안에서 이동 처리
		for (Node m : nodeList) {
			if (m.dir == 1) { // 상
				if (--m.r == 0) {
					m.count /= 2;
					m.dir = 2;
				}
			} else if (m.dir == 2) { // 하
				if (++m.r == N - 1) {
					m.count /= 2;
					m.dir = 1;
				}
			} else if (m.dir == 3) { // 좌
				if (--m.c == 0) {
					m.count /= 2;
					m.dir = 4;
				}
			} else if (m.dir == 4) { // 우
				if (++m.c == N - 1) {
					m.count /= 2;
					m.dir = 3;
				}
			}
		}
	}

	private static void mapUpdate() {
		int size = nodeList.size();
		for (int i = size - 1; i >= 0; i--) {
			Node curNode = nodeList.get(i);
			if (curNode.count == 0) {
				nodeList.remove(i);
				continue;
			}

			int r = curNode.r;
			int c = curNode.c;
			int cnt = curNode.count;
			int dir = curNode.dir;

			if (cntMap[r][c] != 0) {
				cntMap[r][c] += cnt;
				if (BigMap[r][c] < cnt) {
					BigMap[r][c] = cnt;
					dirMap[r][c] = dir;
				}

			} else {
				cntMap[r][c] = cnt;
				BigMap[r][c] = cnt;
				dirMap[r][c] = dir;
			}
		}

		nodeList.clear();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (cntMap[i][j] != 0) {
					nodeList.add(new Node(i, j, cntMap[i][j], dirMap[i][j]));
				}
			}
		}
	}

	static void init() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cntMap[i][j] = 0;
				dirMap[i][j] = 0;
				BigMap[i][j] = 0;

			}
		}
	}

	static int sum() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (cntMap[i][j] != 0)
					sum += cntMap[i][j];
			}
		}
		return sum;
	}
}
