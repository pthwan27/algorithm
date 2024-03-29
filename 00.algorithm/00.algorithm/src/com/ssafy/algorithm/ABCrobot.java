package com.ssafy.algorithm;

import java.util.Scanner;

public class ABCrobot {

	static char[][] arr;
	static int result = 0;

	// 오른쪽 확인
	private static int rightSearch(int r, int c, char[][] arr) {
		int length = 0;

		if (c + 1 != arr.length) {
			for (int i = c + 1; i < arr.length; i++) {
				if (arr[r][i] == 'S') {
					length++;
				} else {
					break;
				}
			}
		}
		return length;
	}

	// 왼쪽 확인
	private static int leftSearch(int r, int c, char[][] arr) {
		int length = 0;

		for (int i = 1; i <= c; i++) {
			if (arr[r][c - i] == 'S') {
				length++;
			} else {
				break;
			}
		}
		return length;
	}

	// 위쪽 확인
	private static int upSearch(int r, int c, char[][] arr) {
		int length = 0;

		for (int i = 1; i <= r; i++) {
			if (arr[r - i][c] == 'S') {
				length++;
			} else {
				break;
			}
		}
		return length;
	}

	// 아래쪽 확인
	private static int downSearch(int r, int c, char[][] arr) {
		int length = 0;

		if (r + 1 != arr.length) {
			for (int i = r + 1; i < arr.length; i++) {
				if (arr[i][c] == 'S') {
					length++;
				} else {
					break;
				}
			}
		}
		return length;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 0; tc < T; tc++) {
			// 배열 크기 N * N
			int N = sc.nextInt();
			arr = new char[N][N];
			result = 0;

			// 배열 값 넣기
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					arr[r][c] = sc.next().charAt(0);
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {

					// A , B , C 로봇을 찾았을 때
					// A는 오른쪽으로만 ,
					// B는 좌우,
					// C는 상 하 좌 우 로만 갈 수 있다.
					// 이 때 벽(W)은 통과 할 수없고
					// 다른 로봇은 서로 통과할 수 없다.

					if (arr[r][c] == 'A') {
						result += rightSearch(r, c, arr);
					} else if (arr[r][c] == 'B') {
						result += rightSearch(r, c, arr);
						result += leftSearch(r, c, arr);
					} else if (arr[r][c] == 'C') {
						result += rightSearch(r, c, arr);
						result += leftSearch(r, c, arr);
						result += upSearch(r, c, arr);
						result += downSearch(r, c, arr);
					}
				}
			}
			System.out.printf("#%d %d%n", tc, result);
		}
	}

}
