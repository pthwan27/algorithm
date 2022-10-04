package com.swea.algorithm.week0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_4008_숫자만들기_np {

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	static int N;

	static ArrayList<Integer> numList;
	static ArrayList<Integer> operList;

	static int[] output;
	static boolean[] isSelelcted;

	static int minResult;
	static int maxResult;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(in.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			numList = new ArrayList<>();
			operList = new ArrayList<>();

			//숫자 갯수
			N = Integer.parseInt(in.readLine());

			StringTokenizer st = new StringTokenizer(in.readLine());

			//연산자 입력
			for (int i = 0; i < 4; i++) {
				int cnt = Integer.parseInt(st.nextToken());
				operList.add(cnt);
			}

			st = new StringTokenizer(in.readLine());

			for (int i = 0; i < N; i++) {
				numList.add(Integer.parseInt(st.nextToken()));
			}

			isSelelcted = new boolean[N - 1];
			output = new int[N - 1];

			maxResult = Integer.MIN_VALUE;
			minResult = Integer.MAX_VALUE;

			func(0);

			sb.append("#").append(tc).append(" ").append(maxResult - minResult).append("\n");
		}
		System.out.println(sb);
	}

	private static void func(int cur) {
		if (cur == N - 1) {			
			System.out.println(Arrays.toString(output));
			int result = calculation();
			maxResult = Math.max(maxResult, result);
			minResult = Math.min(minResult, result);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (operList.get(i) > 0) {
				operList.set(i, operList.get(i)-1);
				output[cur] = i;
				func(cur+1);
				operList.set(i, operList.get(i)+1);
			}
		}
	}
	private static int calculation() {
		int start = calc(numList.get(0), output[0], numList.get(1));
 		int sum = 0;
        for (int i = 1; i < output.length; i++) {
            sum = calc(start, output[i], numList.get(i + 1));
            start = sum;
        }
        return start;
    }
	

	//계산 함수
	private static int calc(int n1, int oper, int n2) {
		switch (oper) {
		case 0:
			return n1 + n2;
		case 1:
			return n1 - n2;
		case 2:
			return n1 * n2;
		case 3:
			return n1 / n2;
		}
		return 0;
	}
}
