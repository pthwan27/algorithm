package com.swea.algorithm.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 박태환
 *
 * 달팽이 숫자. N * N 크기의 배열
 */
public class D2_1954_달팽이숫자 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			int[][] arr = new int[N][N];
			boolean[][] checkArr = new boolean[N][N];

//			0  1  2  3 
//			11 12 13 4
//			10 15 14 5
//			9  8  7  6

			// dir 0 오른쪽으로, 1아래로, 2 왼쪽으로, 3 위로
			int dir = 0;
			int i = 0;
			int j = 0;
			int count = 1;
			// 1~16까지 나오도록 반복문 설정
			while (count <= N * N) {
				// 어느 방향으로 갈지 정해줘야함,
				// 위로가는 방향에서 오른쪽으로 바뀌면 다시 0이 되어야 하기 때문에 나머지로 값을 구해서 설정
				dir = dir % 3;

				// 각 방향으로 갈 때, 배열 범위를 넘거나, 이미 값이 들어가 있는 곳을 만나면 방향 전환
				// 방향이 바뀌기 전에, 배열에 값을 넣어주고 -1이나 +1을 해줬기 때문에 (==N) / (==-1) 로 확인
				// 값을 넣은 곳은 true로 변경

				// 오른쪽으로 갈 때
				if (dir == 0) {
					if (j == N || checkArr[i][j] == true) {
						dir++;
						i++;
						j--;
					} else {
						arr[i][j] = count++;
						checkArr[i][j] = true;
						j++;
					}
				}
				// 아래로 갈 때
				else if (dir == 1) {
					if (i == N || checkArr[i][j] == true) {
						dir++;
						j--;
						i--;
					} else {
						arr[i][j] = count++;
						checkArr[i][j] = true;
						i++;
					}
				}
				// 왼쪽으로 갈 때
				else if (dir == 2) {
					if (j == -1 || checkArr[i][j] == true) {
						dir++;
						i--;
						j++;
					} else {
						arr[i][j] = count++;
						checkArr[i][j] = true;
						j--;

					}
				}
				// 위로 갈 때
				else if (dir == 3) {
					if (i == -1 || checkArr[i][j] == true) {
						dir++;
						j++;
						i++;
					} else {
						arr[i][j] = count++;
						checkArr[i][j] = true;
						i--;
					}
				}
			}

			System.out.println("#"+tc);
			for (int[] temp : arr) {
				for (int c = 0; c < temp.length; c++) {
					System.out.print(temp[c] + " ");
				}
				System.out.println();
			}
		}
	}
}
