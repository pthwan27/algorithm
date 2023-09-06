package com.backjoon.newAlgo.In_202309;

import java.io.*;
import java.util.*;

public class bj_21921_블로그 {

	static int N, X;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		N = Integer.parseInt(inputs[0]);
		X = Integer.parseInt(inputs[1]);

		int[] nums = new int[N];

		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int sum = 0;
		int max = 0;
		int cnt = 0;

		int start = 0;
		int end = X - 1;

		while (end < N) {
			if (start == 0) {
				for (int i = 0; i <= end; i++) {
					max += nums[i];
				}
				sum = max;

				if (max > 0) {
					cnt = 1;
				}
			} else {
				sum = sum - nums[start - 1] + nums[end];

				if (sum > max) {
					max = sum;
					cnt = 1;
				} else if (sum == max) {
					cnt++;
				} else {

				}

			}

			start++;
			end++;
		}

		System.out.println(max == 0 ? "SAD" : max + "\n" + cnt);
	}

}
