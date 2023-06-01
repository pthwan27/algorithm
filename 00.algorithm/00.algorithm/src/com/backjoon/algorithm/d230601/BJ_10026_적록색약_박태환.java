package com.backjoon.algorithm.d230601;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_10026_적록색약_박태환 {
	static char[][] threeColorMap; // 색약맵
	static char[][] fourColorMap; // 색약X
	static boolean[][] isSelected;

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());

		fourColorMap = new char[N][N];
		threeColorMap = new char[N][N];

		// 그냥 맵과, 색약 맵에 따로 입력
		for (int r = 0; r < N; r++) {
			String inputStr = in.readLine();
			for (int c = 0; c < N; c++) {
				if (inputStr.charAt(c) == 'G') {
					threeColorMap[r][c] = 'R';
				} else {
					threeColorMap[r][c] = inputStr.charAt(c);
					fourColorMap[r][c] = inputStr.charAt(c);
				}
			}
		}
		// 입력 끝
		
		// 일반 맵 검사
		isSelected = new boolean[N][N];
		int count = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!isSelected[r][c]) {
					isSelected[r][c] = true;
					dfs(r, c, fourColorMap, fourColorMap[r][c]);
					count++;
				}
			}
		}
		sb.append(count + " ");

		// 색약 맵 검사
		isSelected = new boolean[N][N];
		count = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!isSelected[r][c]) {
					isSelected[r][c] = true;
					dfs(r, c, threeColorMap, threeColorMap[r][c]);
					count++;
				}
			}
		}
		sb.append(count);

		System.out.println(sb);
	}

	private static void dfs(int r, int c, char[][] Map, char color) {
		// 상 좌 하 우
		final int dx[] = new int[] { -1, 0, 1, 0 };
		final int dy[] = new int[] { 0, -1, 0, 1 };

		//4방 탐색 + 같은 색인지 확인 + 방문한 곳인지 확인
		for (int i = 0; i < 4; i++) {
			int dr = r + dx[i];
			int dc = c + dy[i];
			if (isIn(dr, dc) && Map[dr][dc] == color && !isSelected[dr][dc]) {
				isSelected[dr][dc] = true;
				dfs(dr, dc, Map, color);
			}
		}
	}

	private static boolean isIn(int dr, int dc) {
		if (dr >= 0 && dr < N && dc >= 0 && dc < N) {
			return true;
		}
		return false;
	}

}
