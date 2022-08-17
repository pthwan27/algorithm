package com.backjoon.algorithm.week0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2839_설탕배달_박태환 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());		
		int threeCount = 0;
		while(true) {
			if(N % 5 == 0) {
				System.out.println(N / 5 + threeCount);
				break;
			}
			else if(N <= 0) {
				System.out.println(-1);
				break;
			}
			
			N -= 3;
			threeCount++;
		}
	}
}
