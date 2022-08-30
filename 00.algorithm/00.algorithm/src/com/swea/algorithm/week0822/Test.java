package com.swea.algorithm.week0822;

import java.util.Arrays;
import java.util.Scanner;

public class Test {
	private static int[] numbers;
	private static int[] input;
	private static int N;
	private static int R;
	private static boolean[] isSelected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N =sc.nextInt();
		R =sc.nextInt();
		
		input = new int[N];
		numbers = new int[R];
		isSelected = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		perm(0);
		System.out.println();
		comb(0, 0);
		System.out.println();
		powerSet(0);
	}
	
	public static void perm(int cnt) {
		if(cnt == R) {
			print(numbers);

			return;
			
		}
		
		for(int i = 0; i < N; i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] =true;
			numbers[cnt] = input[i];
			perm(cnt+1);
			
			isSelected[i] = false;			
		}
	}
	
	public static void comb(int cnt, int start) {
		if(cnt == R){
			print(numbers);

			return;
		}
		
		for(int i = start; i < N; i++) {
			numbers[cnt] = input[i];
			comb(cnt+1, i+1);
		}		
	}
	
	public static void powerSet(int cnt) {
		if(cnt == R) {
			print(numbers);
			return;
			
		}
		
		for(int i = 0; i < N; i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] =true;
			numbers[cnt] = input[i];
			perm(cnt+1);
			
			isSelected[i] = false;	
			perm(cnt+1);
		}
	}
	
	public static void print(int [] temp) {
		System.out.println(Arrays.toString(temp));;
	}
	

}
