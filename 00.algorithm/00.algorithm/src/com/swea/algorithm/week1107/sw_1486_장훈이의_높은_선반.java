package com.swea.algorithm.week1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class sw_1486_장훈이의_높은_선반 {
	static int N, B;
	static int diff;

	static int[] keyArr;
	static boolean[] nArr;
	
	static int minResult = 9999;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			keyArr = new int[N];

			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				keyArr[i] = Integer.parseInt(st.nextToken());
			}

			nArr = new boolean[N];
			minResult = 9999;
			subSet(0);
			
			sb.append("#" +tc + " " +minResult + "\n");
		}
		System.out.println(sb);		
	}

	private static void subSet(int cur) {
		if (cur == N) {
			int total = 0;
			for(int i =0 ; i < cur; i++) {
				if(!nArr[i]) continue;
				total+= keyArr[i];
			}
			
			if(total < B) return;
			else {
				minResult = Math.min(minResult, total - B);
			}
			return;
		}

		nArr[cur] = true;
		subSet(cur + 1);

		nArr[cur] = false;
		subSet(cur + 1);
	}

}
