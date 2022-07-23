package com.backjoon.algorithm;

import java.util.Scanner;

public class Backjoon2447별찍기_temp {
	static char[][] arr;

	// 재귀함수를 이용하여 별찍기
	// 1. 나갈수 있도록
	// 2. 규칙찾기
	
	// N -> 3이면
	//	***
	//	* *
	//	***	 		

	// 2중 for문 , 5번째일 때는 별 찍고, 아닐 땐 *

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();

		arr = new char[N][N];

		star(0, 0, N, false);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(arr[i][j]);
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}

	static void star(int x, int y, int N, boolean blank) {
		// 공백칸일 경우
		if (blank) {
			for (int i = x; i < x + N; i++) {
				for (int j = y; j < y + N; j++) {
					arr[i][j] = ' ';
				}
			}
			return;
		}

		// 더이상 쪼갤 수 없는 블록일 때
		if (N == 1) {
			arr[x][y] = '*';
			return;
		}

		/*
		 * N=27 일 경우 한 블록의 사이즈는 9이고, N=9 일 경우 한 블록의 사이즈는 3
		 *  해당 블록의 한 칸을 담을 변수를 의미 size 
		 * count는 별 출력 누적을 의미
		 */

		int size = N / 3;
		int count = 0;
		for (int i = x; i < x + N; i += size) {
			for (int j = y; j < y + N; j += size) {
				count++;
				if (count == 5) { // 공백 칸일 경우
					star(i, j, size, true);
				} else {
					star(i, j, size, false);
				}
			}
		}
	}
}
