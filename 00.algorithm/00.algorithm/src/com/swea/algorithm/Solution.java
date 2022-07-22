package com.swea.algorithm;

import java.util.Arrays;
import java.util.Scanner;

class Solution {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		int T = sc.nextInt();
		
		
		//한번만 바꾸는 거라면, 자릿수가 제일 중요!		
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			
			max = N;
			min = N;
			
			String numStr = String.valueOf(N);			
			char[] numChar = numStr.toCharArray();	
			char[] originNumChar = numStr.toCharArray();	
			for(int i = 0; i < numStr.length(); i++) {
				for(int j = i + 1; j < numStr.length(); j++){										
					//숫자 한쌍 위치 바꾸기
					char c1 = numChar[i];
					numChar[i] = numChar[j];
					numChar[j] = c1;
					
					int changeNum = Integer.parseInt(String.valueOf(numChar));
					//첫번째 숫자가 0이면 안되고, 최대값, 최소값 찾기
					if(numChar[0] != '0' && changeNum > max ) {
						max = changeNum;
					}
					
					if(numChar[0] != '0' && changeNum < min) {
						min = changeNum;
					}					
					
					//원래대로 돌려놓기
					numChar = Arrays.copyOf(originNumChar, numChar.length);
				}
			}			
			System.out.printf("#%d %d %d%n", tc, min, max);
		}
	}
}
