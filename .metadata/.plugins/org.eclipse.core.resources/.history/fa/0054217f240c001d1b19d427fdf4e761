package com.backjoon.algorithm;

import java.util.Scanner;

/**
 * 광주 5반 박태환 5번 문제
 */
public class Backjoon2615오목 {

	static int dx[] = new int[] { 0, 1, 1, -1 };
	static int dy[] = new int[] { 1, 0, 1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] area = new int[19][19];

		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				area[i][j] = sc.nextInt();
			}
		}

		boolean check = true;
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (area[j][i] == 1 && winnerCheck(j, i, area, area[j][i])) {
					// 승자의 위치를 보여주고 시스템종료 (뒤에 더 할 필요 없음, 0,0으로 검사했기 때문에 X,Y 위치에 +1)
					System.out.printf("%d%n%d %d", 1, j + 1, i + 1);
					check = false;
					break;
				}
				if (area[j][i] == 2 && winnerCheck(j, i, area, area[j][i])) {
					// 승자의 위치를 보여주고 시스템종료 (뒤에 더 할 필요 없음, 0,0으로 검사했기 때문에 X,Y 위치에 +1)
					System.out.printf("%d%n%d %d", 2, j + 1, i + 1);
					check = false;
					break;
				}
			}
			if (check == false) {
				break;
			}
		}
		if (check) {
			System.out.println(0);
		}

	}

	private static boolean winnerCheck(int r, int c, int[][] area, int color) {
		// 4방향(반대방향도 함께 탐색
		for (int i = 0; i < 4; i++) {
			int count = 1;
			int goX = r;
			int goY = c;

			// 정방향 검사
			while (true) {
				goX += dx[i];
				goY += dy[i];
				if (goX >= 0 && goX < area.length && goY >= 0 && goY < area.length) {
					if (area[goX][goY] == color) {
						count++;
					} else {
						break;
					}
				} else {
					break;
				}

			}
			goX = r;
			goY = c;
			// 역방향 검사
			while (true) {
				goX -= dx[i];
				goY -= dy[i];
				if (goX >= 0 && goX < area.length && goY >= 0 && goY < area.length) {

					if (area[goX][goY] == color) {
						count++;
					} else {
						break;
					}

				} else {
					break;
				}
			}

			if (count == 5) {
				return true;
			}
		}

		return false;
	}
}
