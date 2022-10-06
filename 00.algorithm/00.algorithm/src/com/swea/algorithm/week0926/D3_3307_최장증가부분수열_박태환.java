package com.swea.algorithm.week0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_3307_최장증가부분수열_박태환 {
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(in.readLine());
			
			int[] inputArr = new int[N];
			int[] dp = new int[N];
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			
			for(int i = 0; i < N; i++) {
				inputArr[i] = Integer.parseInt(st.nextToken());
			}
			//입력 끝
			
			int result = 1;
			for(int a = 0; a < N; a++) {
				dp[a] = 1;
				
				for(int b = 0; b < a; b++) {
					if(inputArr[a] > inputArr[b]) {//순열 만들 수 있으면 dp값변경
						dp[a] = Math.max(dp[a],dp[b]+1);						
					}
				}
				
				//LIS 값 갱신
				result = Math.max(result, dp[a]);
			}
			
			sb.append("#"+tc + " " + result +"\n");
		}
		System.out.println(sb);		
	}
}
