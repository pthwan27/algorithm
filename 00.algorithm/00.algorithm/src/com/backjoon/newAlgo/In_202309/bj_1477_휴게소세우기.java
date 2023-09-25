package com.backjoon.newAlgo.In_202309;

import java.io.*;
import java.util.*;

public class bj_1477_휴게소세우기 {

	static int N, M, L;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		L = Integer.parseInt(inputs[2]);

		int[] restArea = new int[N + 2];

		restArea[N + 1] = L;

		inputs = in.readLine().split(" ");

		for (int i = 1; i <= N; i++) {
			restArea[i] = Integer.parseInt(inputs[i - 1]);
		}

		Arrays.sort(restArea);

		int left = 1;
		int right = L - 1;

		while (left <= right) {
			int mid = (left + right) / 2;
			int sum = 0;

			for (int i = 1; i < restArea.length; i++) {
				sum += (restArea[i] - restArea[i - 1] - 1) / mid;
			}

			if (sum > M) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(left);
	}

}
