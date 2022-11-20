package com.swea.algorithm.week1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_3238_이항계수구하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());

			long N = Long.parseLong(st.nextToken());
			long R = Long.parseLong(st.nextToken());
			int divNum = Integer.parseInt(st.nextToken());

			long[] fact = new long[divNum];
			fact[0] = 1;
			for (int i = 1; i < divNum; i++) {
				fact[i] = (fact[i - 1] * i) % divNum;
			}
			long result = 1;
			
			//뤼카 정리
			while(N > 0 || R > 0) {
				int x = (int) (N % divNum);
				int y = (int) (R % divNum);
				
				if(x < y) {
					result = 0;
					break;
				}
			
			
			//페르마 정리  
			result = (result * fact[x]) % divNum;
			result = (result * fermat((fact[x-y] * fact[y]) % divNum , divNum-2, divNum)) % divNum;
			N = N/divNum;
			R = R/divNum;
			
			}
			System.out.println("#" + tc + " " + result);
		}

	}

	private static long fermat(long b, int e,int p) {
		if (e == 1)
			return b;

		long temp = fermat(b, e / 2, p);
		temp = (temp * temp) % p;

		if (e % 2 == 0)
			return temp;
		else
			return (temp * b) % p;
	}

}
