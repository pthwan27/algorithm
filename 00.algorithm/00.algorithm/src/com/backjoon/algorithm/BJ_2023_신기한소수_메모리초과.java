package com.backjoon.algorithm;

import java.util.Scanner;

public class BJ_2023_신기한소수_메모리초과 {

	
	static boolean outCheck = true;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
	
		boolean[] primeNumArr = new boolean[(int) Math.pow(10, N)];
		primeNumArr[0] = true;
		primeNumArr[1] = true;
		for (int i = 2; i < (int) Math.pow(10, N); i++) {
			for (int j = 2; i*j < (int) Math.pow(10, N); j++) {
				primeNumArr[i * j] = true;
			}
		}
				
		for (int i = (int) Math.pow(10, N-1); i < (int) Math.pow(10, N); i++) {
			outCheck = true;
			primeCheck(i, N-1, primeNumArr);
			if(outCheck) {
				sb.append(i + "\n");
			}
		}
		System.out.println(sb);
	}

	private static void primeCheck(int i, int n, boolean[] primeNumArr) {
		//왼쪽부터 1자리씩, i / (N-1) -> i / (N-2),  N == 0
		if(outCheck == false) {
			return;
		}		
		
		if(n == 0) {
			if(primeNumArr[(int) (i / Math.pow(10, n))] == true) {
				outCheck = false;
				return;
			}
			else {
				return;
			}
		}
		
		if(primeNumArr[(int) (i / Math.pow(10, n))] == true) {
			outCheck = false;
			return;
		}else {
			primeCheck(i, n-1, primeNumArr);
		}
	}
}
