package com.swea.algorithm.week1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4014_활주로건설_박태환 {
	static int N, X;
	static int[][] map, map2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			map2 = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < N; j++) {
					int nextNum = Integer.parseInt(st.nextToken());
					map[i][j] = nextNum;
					map2[j][i] = nextNum;
				}
			}
			int result = process();
			sb.append("#" + tc + " " + result+"\n");

		}
		System.out.println(sb);
	}

	private static int process() {
		int count = 0;

		for (int r = 0; r < N; r++) {
			if (makeRoad(map[r])) {
				count++;
			}

			if (makeRoad(map2[r])) {
				count++;
			}
		}

		return count;
	}

	private static boolean makeRoad(int[] road) {
		int beforeHeight = road[0];
		int size = 0;

		int c = 0;

		while (c < N) {
			//동일 높이
			if (beforeHeight == road[c]) {
				size++;
				c++;

			}
			//오르막
			else if (beforeHeight + 1 == road[c]) {
				// X 보다 작으면 활주로 만들 수 없음.
				if (size < X) {
					return false;
				}

				beforeHeight++;
				size = 1;
				c++;
			}

			//내리막
			else if (beforeHeight - 1 == road[c]) {
				// X 보다 작으면 활주로 만들 수 없음.
				for (int a = c; a < c + X; a++) {
					if (a >= N) {
						return false;
					}

					if (road[a] != beforeHeight - 1) {
						return false;
					}
				}

				beforeHeight--;
				c += X;
				size = 0;
			}

			//높이 차이가 2이상일 때
			else {
				return false;
			}

		}

		return true;

	}
}
