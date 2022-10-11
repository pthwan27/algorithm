package com.backjoon.algorithm.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_3040_백설공주와일곱난쟁이_박태환 {

	static int[] arr = new int[9];
	static int[] numbers = new int[7];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		comb(0, 0);
	}

	private static void comb(int cur, int start) {
		if (cur == 7) {
			if (Sum(numbers) == 100) {
				for (int i = 0; i < numbers.length; i++) {
					System.out.println(numbers[i]);
				}
			}
			return;
		}

		for (int r = start; r < 9; r++) {
			numbers[cur] = arr[r];
			comb(cur + 1, r + 1);
		}
	}

	private static int Sum(int[] numbers) {
		int sum = 0;
		for (int i = 0; i < numbers.length; i++) {
			sum += numbers[i];
		}
		return sum;
	}
}
