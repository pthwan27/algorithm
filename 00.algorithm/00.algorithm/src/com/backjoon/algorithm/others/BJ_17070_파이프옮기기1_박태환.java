package com.backjoon.algorithm.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17070_파이프옮기기1_박태환 {
	static int[][] pipeMap;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());

		pipeMap = new int[N][N];

		// 파이프가 갈 수 있는 방법을 담은 dpArr
		// 3차원 배열의 0,1,2를 통해 가로 세로 대각선 카운팅
		int[][][] dpArr = new int[N][N][3];

		// 파이프 맵 ( 장애물 표시 )
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int c = 0; c < N; c++) {
				pipeMap[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 처음 파이프의 위치에 대한 값 dpArr 에 설정
		dpArr[0][1][0] = 1;
		for (int r = 0; r < N; r++) {
			// 0,1 열은 체크 안함 , 처음 파이프 시작이 가로이고, 갈 수 있는 방향을 고려하면
			// 0,1 열은 갈수 없음
			for (int c = 2; c < N; c++) {
				if (pipeMap[r][c] == 0) {
					if (isCheckC(c))
						dpArr[r][c][0] = dpArr[r][c - 1][0] + dpArr[r][c - 1][2];
					if (isCheckR(r))
						dpArr[r][c][1] = dpArr[r - 1][c][1] + dpArr[r - 1][c][2];
					//대각선을 벽을 긁을 수 있는 상황이 있으므로 추가로 벽에 닿는지 체크해줘야함.
					if (isCheckRC(r, c))
						dpArr[r][c][2] = dpArr[r - 1][c - 1][2] + dpArr[r - 1][c-1][1] + dpArr[r-1][c - 1][0];
				}
			}
		}
		
		System.out.println(dpArr[N-1][N-1][0] + dpArr[N-1][N-1][1] + dpArr[N-1][N-1][2]);
	}

	private static boolean isCheckR(int r) {
		return r - 1 >= 0;
	}

	private static boolean isCheckC(int c) {
		return c - 1 >= 0;
	}

	private static boolean isCheckRC(int r, int c) {
		return r - 1 >= 0 && c - 1 >= 0 && pipeMap[r][c-1] != 1 && pipeMap[r-1][c] != 1;
	}
}
