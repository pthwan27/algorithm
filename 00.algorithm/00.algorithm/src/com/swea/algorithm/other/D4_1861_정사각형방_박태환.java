package com.swea.algorithm.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1861_정사각형방_박태환 {

	static int N = 0;

	static int[][] roomArr;
	static boolean[][] isVisited;

	// 상 하 좌 우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int result = 1;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());

			roomArr = new int[N][N];
			isVisited = new boolean[N][N];

			int Max = Integer.MIN_VALUE;
			int StartNum = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					roomArr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					solve(i, j);
					// 이동할 수 있는 방의 최대 길이가 같을 때는, 시작한 곳 중 수가 가장 작은 곳 출력
					if (result == Max) {
						Max = result;
						if (StartNum > roomArr[i][j]) {
							StartNum = roomArr[i][j];
						}
					}
					// 최대값이 바뀌면 적힌 시작점도 변경
					else if (result > Max) {
						Max = result;
						StartNum = roomArr[i][j];
					}

					result = 1;
				}
			}
			System.out.printf("#%d %d %d\n", tc, StartNum, Max);
		}
	}

	private static void solve(int r, int c) {
		for (int i = 0; i < 4; i++) {
			// 주변에 갈 수 있는 곳이 있는지 확인 ( 배열의 범위 안에서 )
			if ((r + dx[i] >= 0 && r + dx[i] < N) && (c + dy[i] >= 0 && c + dy[i] < N)
					&& (roomArr[r][c] + 1 == roomArr[r + dx[i]][c + dy[i]])) {

				// 방문한 곳인지 확인
				if (isVisited[r + dx[i]][c + dy[i]] == true) {
					continue;
				}
				result++;
				isVisited[r][c] = true;
				solve(r + dx[i], c + dy[i]);
				isVisited[r][c] = false;
			}
		}
		// 갈 수 있는 곳 없으면 return
		return;
	}

}
