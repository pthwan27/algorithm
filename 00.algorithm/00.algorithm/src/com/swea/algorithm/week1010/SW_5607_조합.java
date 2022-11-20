package com.swea.algorithm.week1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_5607_조합 {

	static final int DIVNUM = 1234567891;


	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());

			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());

			long fact[] = new long[N+1];
			fact[0] = 1;
			for (int i = 0; i++ < N;) {
				fact[i] = (fact[i-1] * i) % DIVNUM;
			}
			
			//나눌 수 구하기
			long a = (fact[R] * fact[N-R]) % DIVNUM;
			
			//페르마 소정리로 a^-1과 동일한 a^p-2구하기
			long b = fermat(a, DIVNUM-2);
			
			//nCr -> n! * a^p-2 값 출력
			System.out.println("#"+tc+" " + fact[N] * b % DIVNUM);
		}

	}

	private static long fermat(long a, int i) {
		if(i== 0) return 1;
		
		long temp = fermat(a , i / 2);
		long pm = (temp * temp) % DIVNUM;
		
		if( i % 2 == 0) return pm;
		else return (pm * a) % DIVNUM;
	}


}
