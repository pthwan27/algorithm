package com.backjoon.algorithm.week0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_25186_INFP두람 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] inputArr = new int[N];

		StringTokenizer st = new StringTokenizer(in.readLine());
		String result = "Happy";
		if (N > 1) {
			int start = Integer.parseInt(st.nextToken());
			inputArr[0] = start;

			int idx = 1;
			while (st.hasMoreTokens()) {
				int nextNum = Integer.parseInt(st.nextToken());
				inputArr[idx] = nextNum;
				if (start == nextNum) {
					result = "Unhappy";
					break;
				}
				start = nextNum;
				idx++;
			}

			if (inputArr[0] == inputArr[N - 1]) {
				result = "Unhappy";
			}
		}

		System.out.println(result);
	}
}
