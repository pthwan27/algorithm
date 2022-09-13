package com.backjoon.algorithm.week0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_25186_INFP두람2 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] inputArr = new int[N];

		StringTokenizer st = new StringTokenizer(in.readLine());
		String result = "Happy";

		long sum = 0;
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(st.nextToken());
			inputArr[i] = temp;
			max = Math.max(max, temp);
			sum += inputArr[i];
		}
		if (N == 1 && inputArr[0] == 1) {
			result = "Happy";
		}
		else {
			if(max > sum/2) {
				result = "Unhappy";
			}
		}
		
		System.out.println(result);
	}
}
