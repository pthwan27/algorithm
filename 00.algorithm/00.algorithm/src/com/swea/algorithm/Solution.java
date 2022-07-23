package com.swea.algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		int [] kyuCard = new int[9]; //규영이		
		int [] InCard = new int[9]; //인영이
		
		boolean [] CardCheck = new boolean[18]; 
		
		for(int tc= 0; tc < T; tc++) {
			for(int i = 0; i < 9; i++) {
				kyuCard[i] = sc.nextInt();
				CardCheck[kyuCard[i]] = true;
			}
			
			int count = 0;
			for(int i = 0; i < 17; i++) {				
				if(CardCheck[i] == false) {
					InCard[count++] = i;
				}
			}			
		}
		
		
	}
}
