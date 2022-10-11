package com.swea.algorithm.week1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1952_수영장 {
	static int[] tiketPrices = new int[4];
	static int[] monthPrices = new int[12];

	static int minSum = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < 4; i++) {
				tiketPrices[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < 12; i++) {
				monthPrices[i] = Integer.parseInt(st.nextToken());
			}

			minSum = tiketPrices[3];
			dfs(0, 0);
			System.out.println(minSum);
		}
	}

	private static void dfs(int month, int sum) {
		if(minSum < sum) {
			return;
		}
		
		if (month > 11) {
			minSum = Math.min(minSum, sum);
			return;
		}

		if (monthPrices[month] == 0) {
			dfs(month + 1, sum);
		} else {
			dfs(month + 1,  sum + monthPrices[month] * tiketPrices[0]);
			
			dfs(month + 1,  sum + tiketPrices[1]);
			
			dfs(month + 3,  sum + tiketPrices[2]);
			
//			sum += monthPrices[month] * tiketPrices[0];
//			dfs(month + 1,  sum);
//			sum -= monthPrices[month] * tiketPrices[0];
//			
//			sum +=tiketPrices[1];
//			dfs(month + 1,  sum);
//			sum -=tiketPrices[1];
//			
//			sum +=tiketPrices[2];
//			dfs(month + 3,  sum);
//			sum -=tiketPrices[2];
		}
	}
}
