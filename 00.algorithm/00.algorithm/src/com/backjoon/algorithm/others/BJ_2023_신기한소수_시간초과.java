package com.backjoon.algorithm.others;

import java.util.Scanner;

public class BJ_2023_신기한소수_시간초과 {

	static boolean outCheck = true;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();

		boolean primecheck = true;
		for (int i = (int) Math.pow(10, N - 1); i < (int) Math.pow(10, N); i++) {
			primecheck = true;
			int count = N;
			while (count > 0) {
				int num = (int) (i / Math.pow(10, count-1));
				if (num > 1) {
					for (int j = 2; j <= Math.sqrt(num); j++) {
						if (num % j == 0) {
							primecheck = false;
							break;
						}
					}
				}
				else {
					primecheck = false;
				}				
				
				if (primecheck == false) {
					break;
				} else {
					count--;
				}
			}
			if(primecheck) {
				sb.append(i + "\n");
			}

		}
		System.out.println(sb);
	}
}
