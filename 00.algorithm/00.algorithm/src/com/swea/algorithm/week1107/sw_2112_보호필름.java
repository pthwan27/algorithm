package com.swea.algorithm.week1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sw_2112_보호필름 {

	// D -> R / W -> C / K 합격기준
	static int D, W, K;

	static int map[][];

	static int testMap[][];

	static int minResult;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());

			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[D][W];
			testMap = new int[D][W];
			minResult = Integer.MAX_VALUE;

			for (int r = 0; r < D; r++) {
				st = new StringTokenizer(in.readLine());

				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					testMap[r][c] = map[r][c];
				}
			}

			dfs(0, 0);
			sb.append("#" + tc + " " + minResult + "\n");

		}
		System.out.println(sb);
	}

	private static void dfs(int cur, int cnt) {
		if (passCheck(testMap)) {
			minResult = Math.min(minResult, cnt);
			return;
		}

		if (cnt > minResult) {
			return;
		}
		
		if (cur == D) {
			return;
		}
		
		//약품을 안썻을 때
		dfs(cur + 1, cnt);

		
		//a 썻을 때, b 썻을 때
		for (int i = 0; i < W; i++) {
			testMap[cur][i] = 0;
		}

		dfs(cur + 1, cnt + 1);

		for (int i = 0; i < W; i++) {
			testMap[cur][i] = 1;
		}

		dfs(cur + 1, cnt + 1);		
		
		for (int i = 0; i < W; i++) {
			testMap[cur][i] = map[cur][i];
		}

	}

	private static boolean passCheck(int[][] map) {
		boolean passCheck = true;
		boolean lineCheck = false;
		
		int cnt = 0;

		int start = 0;

		for (int c = 0; c < W; c++) {
			lineCheck = false;
			

			cnt = 0;
			for (int r = 0; r < D; r++) {
				if(r == 0) {
					start = map[r][c];
					cnt++;
				}
				else {
					if(start == map[r][c]) cnt++;
					
					else {
						start = map[r][c];
						cnt = 1;
					}
				}
				
				if(cnt == K	) {
					lineCheck = true;
					break;
				}
				
			}
			if (lineCheck == false) {
				passCheck = false;
				break;
			}

		}
		return passCheck;
	}

}
