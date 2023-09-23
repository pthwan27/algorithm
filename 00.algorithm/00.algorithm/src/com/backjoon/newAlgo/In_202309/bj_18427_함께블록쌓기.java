package com.backjoon.newAlgo.In_202309;

import java.io.*;
import java.util.*;

public class bj_18427_함께블록쌓기 {

	static int N, M, H;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		H = Integer.parseInt(inputs[2]);

		ArrayList<Integer>[] stdBlockList = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			stdBlockList[i] = new ArrayList<>();

			StringTokenizer st = new StringTokenizer(in.readLine());

			while (st.hasMoreTokens()) {
				stdBlockList[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		int[][] dp = new int[N + 1][1000 + 1];

		for (int i = 0; i <= N; i++) {
			dp[i][0] = 1;
		}

		for (int a = 1; a <= N; a++) {
			for (int b = 1; b <= H; b++) {
				for (int block : stdBlockList[a]) {
					// 쌓을 수 있을 때 
					// 이전 학생이 쌓아 둔 높이에서 현재 블록으로 b를 만들 수 있을 때
					if (b >= block) {
						dp[a][b] += dp[a - 1][b - block];
						dp[a][b] %= 10007;
					}
				}
				//이전 학생 블록으로 만들 수 있던 것들 더해주기(바로윗칸)
				dp[a][b] += dp[a - 1][b];
				dp[a][b] %= 10007;
			}
		}
		
		System.out.println(dp[N][H]);
		
	}
}
