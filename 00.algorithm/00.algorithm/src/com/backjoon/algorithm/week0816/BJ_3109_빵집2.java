package com.backjoon.algorithm.week0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3109_빵집2 {
	static int R, C;
	static char[][] map;
	static boolean[][] isSelected;

	static int result = 0;

	// 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선
	static int[] dx = new int[] { -1, 0, 1 };
	static int[] dy = new int[] { 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		isSelected = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String inputStr = in.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = inputStr.charAt(j);
				if (map[i][j] == 'x') {
					isSelected[i][j] = true;
				}
			}
		}

		for (int r = 0; r < R; r++) {
			findFromStart(r, 0);
		}
		System.out.println(result);
	}

	// 탐색한 길은 체크 헤제할 필요가 없다 -> 막히는 부분이 있다면, 다른 것이 와도 막힐 것이기 때문에 다시 해제시킬 필요 없다.
	private static void findFromStart(int goX, int goY) {
		print(isSelected);

		isSelected[goX][goY] = true;

		if (goY == C - 1) {
			result++;
			return;
		}

		// 3가지 방향 확인 (오른쪽 위, 오른쪽, 오른쪽 아래 순서로 재귀) -> 그럼 방문한 곳은 true로 바뀜.
		if (goX + dx[0] >= 0 && goY + dy[0] < C) {
			if (!isSelected[goX + dx[0]][goY + dy[0]]) {
				findFromStart(goX + dx[0], goY + dy[0]);
			}
			return;
		}
		
		if (goX + dx[1] >= 0 && goY + dy[1] < C) {
			if (!isSelected[goX + dx[1]][goY + dy[1]]) {
				findFromStart(goX + dx[1], goY + dy[1]);
			}
			return;
		}
		
		if (goX + dx[2] < R && goY + dy[2] < C) {
			if (!isSelected[goX + dx[2]][goY + dy[2]]) {
				findFromStart(goX + dx[2], goY + dy[2]);
			}
			return;
		}

	}

	private static void print(boolean[][] isSelected2) {
		for (int r = 0; r < isSelected2.length; r++) {
			for (int c = 0; c < isSelected2[r].length; c++) {
				if (isSelected2[r][c])
					System.out.print("1 ");
				else
					System.out.print("0 ");
			}
			System.out.println();
		}

		System.out.println();

	}

}
