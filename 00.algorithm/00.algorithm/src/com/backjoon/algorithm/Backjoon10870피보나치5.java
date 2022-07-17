package com.backjoon.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Backjoon10870피보나치5 {
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 규칙
		// Fn = Fn-1 + Fn-2 (n ≥ 2)
		// n=17 -> 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987,
		// 1597
		// 재귀함수 활용
		// ex ) n = 5 -> f(4) + f(3)
		// f(3) + f(3) + f(2)
		// f(3) + f(2) + f(2) + f(1)
		// f(2) + f(2) + f(1) + f(2) + f(1)
		// f(2) + f(1) + f(2) + f(1) + f(1) + f(0)
		// '''''' 5가 나옴

		int n = Integer.parseInt(br.readLine());

		System.out.println(function(n));
	}

	private static int function(int i) {
		if (i == 0) {
			return 0;
		} else if (i == 1) {
			return 1;
		} else
			;
		return function(i - 1) + function(i - 2);
	}
}
