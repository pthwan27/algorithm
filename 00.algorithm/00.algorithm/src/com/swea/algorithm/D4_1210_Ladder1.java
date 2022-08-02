package com.swea.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *			100 * 100 사다리가 있다. 
 * 			2차원 배열 아래로 이동하면서, 좌/우 로 가는 길이 있다면 가게 됌 
 * 			-> 첫줄을 탐색하면서 , 1인 경우 경로를 탐색하도록 함. 
 * 			-> 마지막줄에서 2를 만나면 출발 위치의 X값 반환.
 */
public class D4_1210_Ladder1 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] ladderArr = new int[100][100];

		for (int tc = 1; tc <= 10; tc++) {
			int T = Integer.parseInt(in.readLine());

			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < 100; j++) {
					ladderArr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

//			for (int i = 0; i < 100; i++) {
//				for (int j = 0; j < 100; j++) {
//					System.out.print(ladderArr[i][j]);
//				}
//				System.out.println();
//			}

		
		// 입력 끝.

		int resultX = 0;
		int result = 0;
		
		for (int i = 0; i < 100; i++) {
			if (ladderArr[0][i] == 1) {
				// 시작이 1이면, 아래칸도 무조건 1이기 때문에 아래칸부터 탐색.
				// 탐색 시작
				result = findExit(1, i, ladderArr);
				if (result == 2) {
					resultX = i;
					break;
				}
			}
		}
		System.out.println("#" + tc + " "+resultX);
		
		}
	}

	// 좌, 우, 하
	static int[] goX = new int[] { 0, 0, 1 };
	static int[] goY = new int[] { -1, 1, 0 };

	private static int findExit(int startX, int startY, int[][] ladderArr) {
		boolean[][] checkArr = new boolean[100][100];	
		// startX -> 사다리 맨 밑 층까지 갈 때
		// 왔던 길 체크 추가, ( 좌 , 우로 탐색을 할 때, 좌를 먼저 하기 때문에 , 오른쪽으로 갔다가 다시 돌아가는 경우가 있음 )
		while (startX < 99) {
			for (int i = 0; i < 3; i++) {
				//사다리 범위에서 나가는지 확인
				if (startX + goX[i] >= 0 && startX + goX[i] < 100 && startY + goY[i] >= 0 && startY + goY[i] < 100) {
					//1이면 다음 경로 찾아 이동					
					if (ladderArr[startX + goX[i]][startY + goY[i]] == 1
							&& (checkArr[startX + goX[i]][startY + goY[i]] == false)	) {
						checkArr[startX][startY] = true;
						startX += goX[i];
						startY += goY[i];
						break;
					
					}
					//2이면 끝.
					else if (ladderArr[startX + goX[i]][startY + goY[i]] == 2) {
						return 2;
					}
				}
			}
		}
		return 1;
	}
}