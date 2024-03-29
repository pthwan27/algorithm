package com.swea.algorithm.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D2_1961_숫자배열회전 {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		int c = 0;
		for (c = 1; c <= T; c++) {

			int N = Integer.parseInt(br.readLine());

			int i = 0;
			int j = 0;
			int[][] arr = new int[N][N];
			int[][] roarr = new int[N][N]; // 90,180,270 순서대로 돌려 저장할 배열
			String[][] result = new String[N][3];

			for (i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			String temp = "";
			int len = N - 1;

			for (int a = 0; a < 3; a++) {
				roarr = new int[N][N];
				for (i = 0; i < N; i++) {
					for (j = 0; j < N; j++) {
						temp += String.valueOf(arr[len - j][i]);
					}

					result[i][a] = temp;
					temp = "";
				}

				/*
				 * 90도 회전 ex 3*3 배열 일 때 0,0 0,1 0,2 1,0 1,1 1,2 2,0 2,1 2,2
				 * 
				 * 2,0 1,0 0,0 2,1 1,1 0,1 2,2 1,2 0,2
				 */
//		              int [][] arr2 = new int[3][3];
//		              int temp1 = 2; int temp2 = 2;
//		              for(i = 0; i < 3; i++) {
//		                  for(j = 0; j < 3; j++) {
//		                      arr2[i][j] = arr[temp1-j][i];
//		                  }
//		              }

				// 90도 회전시키기
				for (i = 0; i < N; i++) {
					for (j = 0; j < N; j++) {
						roarr[i][j] = arr[len - j][i];
					}
				}
				arr = roarr;

			}
			System.out.println("#" + c + " ");
			for (i = 0; i < N; i++) {
				for (j = 0; j < 3; j++) {
					System.out.print(result[i][j] + " ");
				}
				System.out.println();
			}
		}

	}
}
