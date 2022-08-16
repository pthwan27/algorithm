package com.backjoon.algorithm.NMseries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_15652_N과M4 {
	
	static int N;
	static int[] nums;
	
	static boolean[] isSelected;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N  = Integer.parseInt(st.nextToken());
		int M  = Integer.parseInt(st.nextToken());
		
		
		nums = new int[M];
	
		comb(0,0, M);
		System.out.println(sb);
	}

	private static void comb(int cur, int start,int m) {
		if(cur == m) {
			for(int i = 0; i < m; i++) {
				sb.append(nums[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start; i < N; i++) {			
			nums[cur] = i+1;
			comb(cur+1, i, m);			
		}
		
	}

}
