package com.backjoon.algorithm.week0816;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17135_캐슬디펜스 {

	static int N, M, D;
	static int[][] map;

	static ArrayList<Point> enemyList;

	static int[] combNums;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		enemyList = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

				if (map[r][c] == 1) {
					Point enemyPoint = new Point(r, c);
					enemyList.add(enemyPoint);
				}
			}
		}

		// 궁수의 위치 구하기 (조합마다 제거할 수 있는 적 카운팅)
		combNums = new int[3];
		combArcher(0, 0);
	}

	private static void combArcher(int cur, int start) {
		if (cur == 3) {
			 System.out.println(Arrays.toString(combNums));
			int result = calc();
			return;
		}

		for (int i = start; i < M; i++) {
			combNums[cur] = i;
			combArcher(cur + 1, i + 1);
		}
	}

	private static int calc() {		
		// 궁수의 위치 받아옴 -> combNums
		// 1. 거리가 가까운 적
		// 2. 같은 거리일 땐 왼쪽 적부터
		// 
		// 적의 위치를 받아올 필요가 있나.?
		
		
		
		return 0;
	}
}
