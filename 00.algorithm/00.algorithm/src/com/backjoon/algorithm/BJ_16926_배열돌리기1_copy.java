package com.backjoon.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_16926_배열돌리기1_copy {

	// 바꾼 곳을 만나면 그만 돌리도록 하기 위해 생성.
	static boolean[][] IsChanged;
	static int[][] rotArr;
	// 임시
	// 꺽일때마다 바꿀 수 있도록 해야함.
	// 좌, 상, 우, 하
	static int dir;

	static int cnt;
	
	static int length;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int R = Integer.parseInt(st.nextToken());

		rotArr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				rotArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// N, M 은 배열 크기
		// R 은 반시계로 돌릴 횟수

		// R번 만큼, 배열의 크기를 양쪽 하나씩 줄여가면서 , 회전시킨다.

		cnt = 0;
		dir = 0;
		length = 0;
		IsChanged = new boolean[N][M];
		Rotation(N, M, R, rotArr);
	}

	private static void Rotation(int N, int M, int R, int[][] changeArr) {
//		0~ N
		/*
		 * 45 44 43 42 41 11 12 22 21
		 */

//		1~ N-1
//		2~ N-2	
		if (R == 0) {
			return;
		}

		boolean roationcheck = true;
		for (int i = 0; i < IsChanged.length; i++) {
			for (int j = 0; j < IsChanged.length; j++) {
				if (IsChanged[i][j] == false) {
					roationcheck = false;
					break;
				}
			}
		}

		if (roationcheck) {
			R--;
			Arrays.fill(IsChanged, false);
		}

		int i = N - 1;
		int j = M - 1;

		int start = changeArr[i][j];
		IsChanged[i][j] = true;
		cnt = 1;

		while ((N-length) * (M-length) > cnt) {
			dir = dir % 4;
			switch (dir) {
			case 0:
				cnt++;
				if (j - 1 + length >= 0 && IsChanged[i][j - 1] == false) {
					changeArr[i][j] = changeArr[i][--j];
				} else {
					changeArr[i][j] = changeArr[i - 1][j];
					dir++;
				}

				IsChanged[i][j] = true;

				break;
			case 1:
				cnt++;
				if (i - 1 + length>= 0 && IsChanged[i - 1][j] == false) {
					changeArr[i][j] = changeArr[--i][j];
				} else {
					changeArr[i][j] = changeArr[i][j + 1];
					dir++;
				}

				IsChanged[i][j] = true;

				break;
			case 2:
				cnt++;
				if (j + 1 < M && IsChanged[i][j + 1] == false) {
					changeArr[i][j] = changeArr[i][++j];
				} else {
					changeArr[i][j] = changeArr[i + 1][j];
					dir++;
				}

				IsChanged[i][j] = true;

				break;
			case 3:
				cnt++;
				if (i + 1 < N && IsChanged[i + 1][j] == false) {
					changeArr[i][j] = changeArr[++i][j];
				} else {
					dir++;
					changeArr[i][j] = start;
				}

				IsChanged[i][j] = true;

				break;
			}
			
		}
		length++;
		Rotation(N-length, M-length, R, changeArr);

	}

}
