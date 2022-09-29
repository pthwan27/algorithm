package com.swea.algorithm.week0926;

import java.util.Scanner;

public class LAB_01_아파트색칠하기_박태환 {
	static int[] dpArr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		dpArr = new int[n+1];
		dpArr[0] = 0;
		dpArr[1] = 2;
		dpArr[2] = 3;
		
		for(int i = 3; i < n+1; i++) {
			dpArr[i] = dpArr[i-1]+dpArr[i-2];
		}
		
		System.out.println(dpArr[n]);		
	}
}
