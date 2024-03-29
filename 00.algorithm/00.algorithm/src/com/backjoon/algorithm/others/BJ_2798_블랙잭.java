package com.backjoon.algorithm.others;

import java.util.Scanner;
 
public class BJ_2798_블랙잭 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int M = in.nextInt();
		
		int [] cardarr = new int[N];
		
		int maxSum = Integer.MIN_VALUE;
		int tempSum = Integer.MIN_VALUE;
		
		for(int i = 0; i < N; i++) {
			cardarr[i] = in.nextInt();
		}	
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0 ; k < N; k++) {
					if(i != j && i != k && j != k ) {
						tempSum = cardarr[i] + cardarr[j] +cardarr[k];						

						if(tempSum <= M && tempSum > maxSum) {
							maxSum = tempSum;
						}
					}					
				}
			}			
		}
		System.out.println(maxSum);
	
	}
 
}