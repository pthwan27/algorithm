package com.swea.algorithm.week1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1952_수영장2 {
	static int minResult;

	static int dayPass;
	static int monthPass;
	static int threeMonthPass;
	static int yearPass;

	static int[] plans;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int i = 1; i <= T; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			dayPass = Integer.parseInt(st.nextToken());
			monthPass = Integer.parseInt(st.nextToken());
			threeMonthPass = Integer.parseInt(st.nextToken());
			yearPass = Integer.parseInt(st.nextToken());

			plans = new int[12];

			st = new StringTokenizer(in.readLine());
			for (int a = 0; a < plans.length; a++) {
				plans[a] = Integer.parseInt(st.nextToken());
			}
			//입력 끝

			minResult = yearPass;
			DFS(0, 0);

			sb.append("#" + i + " " + minResult + "\n");
		}
		System.out.println(sb);
	}

	//3개월권은 10월이 마지막으로 끊을 수 있는 달이다
	private static void DFS(int cur, int sum) {
		if (cur > 11) {
			minResult = Math.min(sum, minResult);
			return;
		}

		if (cur < 10) {
			DFS(cur + 3, sum + threeMonthPass);
		}
		DFS(cur + 1, sum + monthPass);

		DFS(cur + 1, sum + (dayPass * plans[cur]));

	}

}
