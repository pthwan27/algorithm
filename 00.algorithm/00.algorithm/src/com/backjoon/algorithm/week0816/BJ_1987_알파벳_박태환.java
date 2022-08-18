package com.backjoon.algorithm.week0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//backtracking + dfs
public class BJ_1987_알파벳_박태환 {
	// input
	static int R, C;
	static char[][] map;

	// 상하좌우 확인
	static int[] dx = new int[] { -1, 1, 0, 0 };
	static int[] dy = new int[] { 0, 0, -1, 1 };

	// 알파벳 사용했는지 확인하기 위해 생성
	static boolean[] IsSelectedAlpha = new boolean[26];

	// 결과저장용 변수
	static int result = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		// input, map만들기
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];

		for (int r = 0; r < R; r++) {
			String inputStr = in.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = inputStr.charAt(c);
			}
		}
		// dfs 시작, 00도 포함이니 1로 시작
		dfs(0, 0, 1);

		System.out.println(result);
	}

	private static void dfs(int x, int y, int cnt) {
		// 최대 길이 구하기
		result = Math.max(result, cnt);
		
		IsSelectedAlpha[map[x][y] - 'A'] = true;
		

		// 상하좌우 확인
		for (int i = 0; i < 4; i++) {
			int goX = x + dx[i];
			int goY = y + dy[i];

			// 배멸 범위 넘지 않았는지 확인
			if (goX >= 0 && goY >= 0 && goX < R && goY < C) {
				if (!IsSelectedAlpha[map[goX][goY] - 'A']) {
					dfs(goX, goY, cnt + 1);
					IsSelectedAlpha[map[goX][goY] - 'A'] = false;
				}
			}
		}
	}

}
