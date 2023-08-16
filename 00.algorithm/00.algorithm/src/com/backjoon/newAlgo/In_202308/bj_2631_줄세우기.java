package com.backjoon.newAlgo.In_202308;

import java.io.*;
import java.util.*;

public class bj_2631_줄세우기 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());

		int[] arr = new int[N + 1];

		int maxLen = 0;

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		int[] dp = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			dp[arr[i]] = 1;

			for (int j = 1; j < arr[i]; j++) {
				if (dp[j] + 1 > dp[arr[i]]) {
					dp[arr[i]] = dp[j] + 1;
					maxLen = Math.max(dp[arr[i]], maxLen);
				}
			}
		}
		System.out.println(N - maxLen);
	}
}
