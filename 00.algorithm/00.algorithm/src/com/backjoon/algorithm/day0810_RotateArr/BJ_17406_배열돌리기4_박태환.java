package com.backjoon.algorithm.day0810_RotateArr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17406_배열돌리기4_박태환 {

	static int[][] area;

	static int N = 0;
	static int M = 0;

	static int MinLineSum = Integer.MAX_VALUE;

	// 하 , 우 , 상 , 좌
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int[][] rcsarr;
	static int[] solveArr;
	static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();

		// 배열 크기 N,M
		N = Integer.parseInt(st.nextToken()) + 1;
		M = Integer.parseInt(st.nextToken()) + 1;
		area = new int[N][M];

		// 회전연산 개수 K
		int K = Integer.parseInt(st.nextToken());

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 1; j < M; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// r, c, s -> (r-s, c-s) ~ (r+s, c+s) 를 시계방향으로 한 칸 돌린다.
		// 그리고 나서 각 행의 합 중 최소값을 찾는다.
		rcsarr = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());

			rcsarr[i][0] = Integer.parseInt(st.nextToken());
			rcsarr[i][1] = Integer.parseInt(st.nextToken());
			rcsarr[i][2] = Integer.parseInt(st.nextToken());
		}
		isSelected = new boolean[K];
		solveArr = new int[K];
		solve(0, K);

		System.out.println(MinLineSum);
	}

	/**
	 * 순열구하기
	 */
	private static void solve(int cur, int K) {
		if (cur == K) {
			/**
			 * k=2니까 여기서 연산 (회전 )
			
			 * 여기 위치에서 배열을 만들어주고 여기다가 연산 하는걸 전부 연결해 서 연산할 수 있도록 하는 배열이 필요하다.
			 *  항상 원본을 가지고 있어야함. -> 다른 수열안에 들어있는걸 계산할 때 원본이 필요하기 때문에
			 */

			// 원본을 복사하는 배열 만들기
			int[][] temp = new int[N][M];
			for (int r = 1; r < N; r++) {
				for (int c = 1; c < M; c++) {
					temp[r][c] = area[r][c];
				}
			}
			for (int i = 0; i < K; i++) {
				rotate(rcsarr[solveArr[i]][0] - rcsarr[solveArr[i]][2], // startX
						rcsarr[solveArr[i]][1] - rcsarr[solveArr[i]][2], // startY
						rcsarr[solveArr[i]][0] + rcsarr[solveArr[i]][2], // endX
						rcsarr[solveArr[i]][1] + rcsarr[solveArr[i]][2], // endY
						temp);
				// ***여기서 내가 원하는 데로 순열이 잘 되었는지를 확인하기 ! ★.
				// System.out.println(rcsarr[solveArr[i]][0]+" "+rcsarr[solveArr[i]][1]+"
				// "+rcsarr[solveArr[i]][2]);

				// printMap(temp);
			}
			// 다 하고 나서 최종 적인 배열의 값을 찾아주어여함

			// ** 여기서도 경우(회전연산 순서?)에 따른 최소값을 찾아 주어여함
			MinLineSum = Math.min(CalcLineSum(temp), MinLineSum);
			return;
		}

		for (int i = 0; i < K; i++) {
			if (isSelected[i]) {
				continue;
			}
			isSelected[i] = true; // 사용했다고 체크
			solveArr[cur] = i; // solveArr에 순열의 숫자들을 담아두고, 이를 이용해 계산에서 활용한다.
			solve(cur + 1, K);
			isSelected[i] = false; // 사용하지 않는 건 false로 바꿔줘야함! 
		}
	}

	private static void rotate(int startX, int startY, int endX, int endY, int[][] temp) {
		int sX = startX;
		int sY = startY;
		int eX = endX;
		int eY = endY;

		// 시작 R 행 = r-s
		// 시작 C 열 = c-s
		// 여기가 출발점이다!

		// area => 원본
		// 얘를 돌려버리면 다른 회전 조건에 대해서는 확인 할 수 없으니까
		// 얘랑 똑같은 배열을 하나 만들어줘야함 -> temp

		int startNum = 0;

		int inCount = (endX - startX + 1) / 2;
		// 배열길이 /2 ->내가 안쪽으로 몇번 들어가서 회전 시켜주어야하는지 알 수 있음
		// System.out.println(inCount+"inCount");
		for (int i = 0; i < inCount; i++) {
			// 회전을 해야하는 사각형 출발 위치
			int x = sX; // 1 (x,y) -> (y,x)
			int y = sY; // 1

			// 첫번째 값은 따로 저장
			startNum = temp[x][y];

			// 방향 확인
			int dir = 0;

			while (dir < 4) {
				int GoX = x + dx[dir];
				int GoY = y + dy[dir];
				// 다음 위치가 회전하는 사각형의 범위 안인지 확인
				if (GoX >= sX && GoX <= eX && GoY >= sY && GoY <= eY) {
					// System.out.println(temp[x][y]+" "+temp[GoX][GoY]);
					temp[x][y] = temp[GoX][GoY];
					// System.out.println("x:"+x+"y:"+y+ " "+"goX: " +GoX+"goY:"+GoY);
					x = GoX;
					y = GoY;
				} else {
					dir++;
				}
			}
			// 처음 저장해둔 값을 넣어준다
			temp[sX][sY + 1] = startNum;

			// 사각형의 범위를 줄인다~
			sX++;
			sY++;
			eX--;
			eY--;

		}

	}

	// 라인 중 최소값 구하기
	private static int CalcLineSum(int[][] arr) {
		int min = Integer.MAX_VALUE;
		for (int i = 1; i < N; i++) {
			int Sum = 0;
			for (int j = 1; j < M; j++) {
				Sum += arr[i][j];
			}
			// if (Sum < MinLineSum) {
			// MinLineSum = Sum;

			// }
			min = Math.min(min, Sum);
		}
		return min;
	}

	// 배열 출력해서 확인해보기 위한 함수
	private static void printMap(int[][] map) {
		for (int r = 1; r < N; r++) {
			for (int c = 1; c < M; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println("@@@@@@@@@@@@");
	}

}