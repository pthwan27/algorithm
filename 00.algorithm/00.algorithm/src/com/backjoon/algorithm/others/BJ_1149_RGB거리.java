package com.backjoon.algorithm.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1149_RGB거리 {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(in.readLine());

		// rgbArr-> Dp배열
		int[][] rgbArr = new int[N][3];

		st = new StringTokenizer(in.readLine());
		for (int i = 0; st.hasMoreTokens(); i++) {
			rgbArr[0][i] = Integer.parseInt(st.nextToken());
		}

		// 1번집부터 ~ N번집까지
		// 현재 번호의 R,G,B와 다른 2개의 색의 값을 확인하면서
		// 둘 중 작은 값을 더해준다.
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int a = 0; a < 3; a++) {
				rgbArr[i][a] = Integer.parseInt(st.nextToken());
			}
			rgbArr[i][0] += Math.min(rgbArr[i-1][1], rgbArr[i-1][2]);
			rgbArr[i][1] += Math.min(rgbArr[i-1][0], rgbArr[i-1][2]);
			rgbArr[i][2] += Math.min(rgbArr[i-1][0], rgbArr[i-1][1]);		
		}

		int minValue = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			minValue = Math.min(minValue, rgbArr[N-1][i]);
		}

		System.out.println(minValue);
	}

}
