package com.swea.algorithm.week0926;

import java.util.Scanner;

public class LAB_02_막대색칠하기_박태환 {
	static int[] dpArr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		dpArr = new int[n+1];
		dpArr[2] = 5;
		dpArr[1] = 2;
		
		for(int i = 3; i < n+1; i++) {
			//dpArr[i-1] * 2 -> -1길이에 1길이 막대 2개 붙이기
			//dpArr[i-2] -> -2길이 (빨간색)
			dpArr[i] = dpArr[i-1]*2 + dpArr[i-2];
		}
		
		System.out.println(dpArr[n]);
	}
}
