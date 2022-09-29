package com.backjoon.algorithm.week0926;

import java.util.Arrays;
import java.util.Scanner;

public class BJ_1463_1로만들기 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[] dpArr = new int[N+1];
		Arrays.fill(dpArr, 2000000);
		dpArr[1] = 0;
		for(int i = 1; i < dpArr.length-1; i++) {
			dpArr[i+1] = Math.min(dpArr[i+1],dpArr[i]+1);
			if(i*2 < dpArr.length) {
				dpArr[i*2] = Math.min(dpArr[i*2],dpArr[i]+1);				
			}
			
			if(i*3 < dpArr.length) {
				dpArr[i*3] = Math.min(dpArr[i*3],dpArr[i]+1);				
			}
		}
		
		System.out.println(dpArr[N]);
	}
}
