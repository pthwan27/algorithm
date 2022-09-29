package com.backjoon.algorithm.week0926;

import java.util.Scanner;

public class BJ_1463_1로만들기_dfs {
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		dfs(N, 0);
		System.out.println(result);
	}

	private static void dfs(int n, int count) {
		//가지치기
		if (result < count) {
			return;
		}
		
		//기저조건
		if (n == 1) {
			result = Math.min(result, count);
			return;
		}
		
		//3으로 나눠질경우
		if (n % 3 == 0) {
			dfs(n / 3, count + 1);
		}
		//2로 나눠질경우
		if (n % 2 == 0) {
			dfs(n / 2, count + 1);
		}
		//나눠지지 않을 때
		dfs(n - 1, count + 1);
	}
}
