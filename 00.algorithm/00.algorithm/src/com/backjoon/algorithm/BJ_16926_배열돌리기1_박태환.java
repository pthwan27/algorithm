package com.backjoon.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16926_배열돌리기1_박태환 {

	static int[][] rotArr;

	// 우 상 좌 하
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static int length;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		
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

		// 행,열중 낮은 값 /2 -> 돌려야하는 배열 수

		int T = Math.min(N, M) / 2;

		for (int t = 0; t < R; t++) {
			// 총 돌릴 배열 수만큼 반복
			for (int i = 0; i < T; i++) {
				// 시작위치 (마지막에 넣기 위해 구해둠)
				int startX = i;
				int startY = i;

				// 시작값을 배열 돌리고 넣어주기 위해 생성
				int startNum = rotArr[startX][startY];
				int dir = 0;
				
				// 4방향 다 돌릴 때 까지 반복
				while (dir < 4) {
					switch (dir) {
					//오른쪽을 왼쪽에 담을 때
					case 0:
						if (startY + dy[dir] < M - i && startY + dy[dir] >= i) {
							rotArr[startX][startY] = rotArr[startX][startY + dy[dir]];
							startY += dy[dir];
						} else {
							dir++;
						}
						break;
						
					//위에를 아래에 담을 때
					case 1:
						if (startX + dx[dir] < N - i && startX + dx[dir] >= i) {
							rotArr[startX][startY] = rotArr[startX + dx[dir]][startY];
							startX += dx[dir];
						} else {
							dir++;
						}
						break;
						
					//왼쪽을 오른쪽에 담을 떄
					case 2:
						if (startY + dy[dir] < M - i && startY + dy[dir] >= i) {
							rotArr[startX][startY] = rotArr[startX][startY + dy[dir]];
							startY += dy[dir];
						}
						else {
							dir++;
						}
						break;
						
					//아래껄 위에 담을 때
					case 3:
						if (startX + dx[dir] < N - i && startX + dx[dir] >= i) {
							rotArr[startX][startY] = rotArr[startX + dx[dir]][startY];
							startX += dx[dir];
						}
						else {
							dir++;
						}
						break;
					}
				}
				// 시작값을 마지막위치에 저장
				rotArr[i+1][i] = startNum;

			}
		}
		
		//출력		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(rotArr[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);

	}
}
