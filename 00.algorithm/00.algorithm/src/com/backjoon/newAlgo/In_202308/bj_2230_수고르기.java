package com.backjoon.newAlgo.In_202308;

import java.util.*;
import java.io.*;

public class bj_2230_수고르기 {

	static int N, M;
	
	static int minDiff = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		
		int[] arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(in.readLine());
		}
		
		Arrays.sort(arr);
		
		int start = 0, end = 0;
		
		while(start <= end && end < N) {
			int diff = arr[end] - arr[start];
			
			if(diff >= M) {
				minDiff = Math.min(diff , minDiff);
				start++;
			}else {
				end++;				
			}
		}
		
		System.out.println(minDiff);
	}
}
