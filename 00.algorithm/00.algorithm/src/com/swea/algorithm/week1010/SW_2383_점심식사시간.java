package com.swea.algorithm.week1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SW_2383_점심식사시간 {

	static int[] match;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = 10;
		match = new int[10];
		
		dfs(0);

	}

	private static void dfs(int depth) {
		if (depth == 10) {
			System.out.println(Arrays.toString(match));

			return;
		}
		for (int i = 0; i < 2; i++) {
			match[depth] = i;
			dfs(depth + 1);
		}
	}
}
