package com.ssafy.algorithm;

import java.util.Scanner;

public class MoveEight {

	// 상, 우상, 우, 우하, 하, 좌하, 좌, 좌상
	static int[][] deltas = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			// 배열 크기 X, Y
			int X = sc.nextInt();
			int Y = sc.nextInt();

			// 참가자 수
			int N = sc.nextInt();
			int[][] area = new int[X][Y];

			// 시작 위치
			int startX = 0;
			int startY = 0;

			// 한 플레이어 당 게임 참가 횟수
			int num = 0;

			// 움직일 방향
			int dirX = 0;
			int dirY = 0;

			// 이동한 배열 위치의 다음 진행될 방향, 거리
			int direction = 0;
			int distance = 0;
			
			int result = 0;

			// 배열 값 넣기
			for (int i = 0; i < X; i++) {
				for (int j = 0; j < Y; j++) {
					area[i][j] = sc.nextInt();
				}
			}
			boolean outCheck = false;
			// 참가자 수 만큼 반복
			for (int i = 0; i < N; i++) {			
				startX = sc.nextInt()-1;
				startY = sc.nextInt()-1;

				num = sc.nextInt();
				
				// 게임 참가 횟수 만큼 반복
				for (int j = 0; j < num; j++) {
					
					dirX = 0;
					dirY = 0;
					
					int temp = area[startX][startY];
					
					direction = temp / 10;
					distance = temp % 10;
					
					//방향 정하기
					dirX = deltas[direction - 1][0];
					dirY = deltas[direction - 1][1];
					
					outCheck = true; 
					for(int c = 0; c < distance; c++) {
						if (startX + dirX >= 0 && startX + dirX < X && startY + dirY >= 0 && startY + dirY < Y )
						{
							startX = startX + dirX;
							startY = startY + dirY;
						}
						else 
						{
							outCheck = false;
							break;
						}
					}			
					
				}
				if(outCheck == false) {
					result += -1000;
				}
				else {
					result += (area[startX][startY] * 100) - 1000;
				}
			}
			System.out.printf("#%d %d%n", tc, result);
			result = 0;
		}

	}

}
