package com.backjoon.algorithm.day0810_RotateArr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16935_배열돌리기3_박태환 {

	static int N;
	static int M;
	static int R;

	static int[][] area;

	static int[][] A;
	static int[][] B;
	static int[][] C;
	static int[][] D;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		area = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 끝
		st = new StringTokenizer(in.readLine());
		while (st.hasMoreTokens()) {
			switch (st.nextToken()) {
			case "1":
				area = calc1(area);
				break;

			case "2":
				area = calc2(area);
				break;

			case "3":
				area = calc3(area);
				break;

			case "4":
				area = calc4(area);
				break;

			case "5":
				area = calc5(area);
				break;

			case "6":
				area = calc6(area);				
				break;

			default:
				System.out.println("해당 번호의 연산은 없습니다.");
				break;
			}
		}
		for(int i = 0; i < area.length; i++) {
			for(int j = 0; j < area[i].length; j++) {
				sb.append(area[i][j] + " ");
			}	
			sb.append("\n");
		}
		System.out.println(sb);
		
	}

	// 상하 반전
	private static int[][] calc1(int[][] area) {
		int[][] returnArr = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				returnArr[i][j] = area[(N - 1 - i)][j];
			}
		}
		return returnArr;
	}

	// 좌우반전
	private static int[][] calc2(int[][] area) {
		int[][] returnArr = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				returnArr[i][j] = area[i][(M - 1 - j)];
			}
		}		
		return returnArr;		
	}

	// 오른쪽으로 90도
	private static int[][] calc3(int[][] area) {
		int[][] returnArr = new int[M][N];
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				returnArr[i][j] = area[N - 1 - j][cnt];
			}
			cnt++;
		}
		int temp = N;
		N = M;
		M = temp;
		return returnArr;
	}

	// 왼쪽으로 90도
	private static int[][] calc4(int[][] area) {
		int[][] returnArr = new int[M][N];
		int cnt = M - 1;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				returnArr[i][j] = area[j][cnt];
			}
			cnt--;
		}
		int temp = N;
		N = M;
		M = temp;
		return returnArr;
	}

	private static int[][] calc5(int[][] area) {
		divArr(area);
		return unitArr(D, A, B, C);
	}

	private static int[][] calc6(int[][] area) {
		divArr(area);
		return unitArr(B, C, D, A);
	}

	//4개로 나누기
	private static void divArr(int[][] area) {
		A = new int[N / 2][M / 2];
		B = new int[N / 2][M / 2];
		C = new int[N / 2][M / 2];
		D = new int[N / 2][M / 2];

		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				A[i][j] = area[i][j];
			}
		}

		for (int i = 0; i < N / 2; i++) {
			for (int j = M / 2; j < M; j++) {
				B[i][j-(M/2)] = area[i][j];
			}
		}

		for (int i = N / 2; i < N; i++) {
			for (int j = M / 2; j < M; j++) {
				C[i-(N/2)][j-(M/2)] = area[i][j];
			}
		}

		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				D[i-(N/2)][j] = area[i][j];
			}
		}
	}

	//4나눠진거 합치기
	private static int[][] unitArr(int[][] A1, int[][] B1, int[][] C1, int[][] D1) {
		int[][] returnArr = new int[N][M];
		
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				returnArr[i][j] = A1[i][j];
			}
		}		

		for (int i = 0; i < N / 2; i++) {
			for (int j = M / 2; j < M; j++) {
				returnArr[i][j] = B1[i][j - (M / 2)];
			}
		}

		for (int i = N / 2; i < N; i++) {
			for (int j = M / 2; j < M; j++) {
				returnArr[i][j] = C1[i - (N / 2)][j - (M / 2)];
			}
		}

		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				returnArr[i][j] = D1[i - (N / 2)][j];
			}
		}
		return returnArr;
	}

}
