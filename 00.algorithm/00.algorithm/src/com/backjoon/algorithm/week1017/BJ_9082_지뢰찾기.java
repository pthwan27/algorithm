package com.backjoon.algorithm.week1017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_9082_지뢰찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(in.readLine());
			
			int[] numArr = new int[N];
			
			String inputStr = in.readLine();
			for(int i = 0; i < N; i++) {
				numArr[i] = inputStr.charAt(i)-'0';
			}
			in.readLine();//두 번째 줄은 안받음.			

			int count = 0;
			for(int i = 0; i < N; i++) {
				//첫번째 칸
				if(i == 0) {
					if(numArr[i] != 0 && numArr[i+1] != 0) {
						numArr[i]-=1;
						numArr[i+1]-=1;
						
						count++;
					}
				}
				
				//마지막 칸
				else if ( i == N-1) {
					if(numArr[N-1] != 0 && numArr[N-2] != 0) {
						numArr[N-1]-=1;
						numArr[N-2]-=1;
						
						count++;
					}
				}
				
				//그 외
				else {
					if(numArr[i-1] != 0 && numArr[i] != 0 && numArr[i+1] != 0) {
						numArr[i-1]-=1;
						numArr[i]-=1;
						numArr[i+1]-=1;
						
						count++;
					}					
				}
			}
			
			System.out.println(count);
			
		}
	}
}
