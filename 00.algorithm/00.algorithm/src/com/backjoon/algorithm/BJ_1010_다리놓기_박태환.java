package com.backjoon.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1010_다리놓기_박태환 {

	/**
	 * 사이트 -> 다리를 짓기에 적합한 곳
	 * 서쪽에는 N개의 사이트, 동쪽에는 M개의 사이트
	 * N <= M
	 * N개의 다리를 지으려고할 때 다리를 지울 수 있는 경우의 수
	 * *다리는 겹칠 수 없다.*
	 * 
	 * mCn 이 다리를 놓을 수 있는 조합의 수.
	 * 
	 * @param park
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	static int N;
	static int M;
	
	//nCr 값들을 저장해둠	
	static int dpArr[][] = new int[31][31];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer("");
		int T = Integer.parseInt(br.readLine());
		dp(30, 30);
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			sb.append(dp(M,N) + "\n");
		}
		System.out.println(sb);
	}

	private static int dp(int n, int m) {
		
		//이미 계산된 경우
		if(dpArr[n][m] > 0) {
			return dpArr[n][m];
		}
		//nC0 이거나 1C1, 2C2 -> 0
		else if(n == m || m == 0) {
			return dpArr[n][m] = 1;
		}
		
		// nCr = n-1Cr-1 + n-1Cr
		else {
			return dpArr[n][m] = dp(n-1, m-1) + dp(n-1, m);
		}		
	}

//	public static int factorial(int N){
//		if(N == 0) {
//			return 1;
//		}
//		return N * factorial(N-1);
//	}

}
