package com.swea.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_1233_사칙연산유효성검사_박태환 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = 10;

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			boolean check = true;
			
			for (int i = 0; i < N; i++) {
				if(check == false) {
					st = new StringTokenizer(br.readLine());
					continue;
				}
				st = new StringTokenizer(br.readLine());
				if (i < (N / 2)) {
					if (st.countTokens() < 4) {
						check = false;
					} else {
						st.nextToken();
						String operStr = st.nextToken();
						if (operStr.equals("+") || operStr.equals("-") || operStr.equals("*") || operStr.equals("/")) {
							continue;
						} else {
							check = false;
						}
					}
				}
				else {
					st.nextToken();
					String operStr = st.nextToken();
					if (operStr.equals("+") || operStr.equals("-") || operStr.equals("*") || operStr.equals("/")) {
						check = false;
					} else {						
						continue;
					}
				}
			}
			System.out.printf("#%d %d\n", tc, check ? 1 : 0);			
		}
	}
}
