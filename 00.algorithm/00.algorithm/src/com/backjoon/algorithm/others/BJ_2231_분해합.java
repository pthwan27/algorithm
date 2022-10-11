package com.backjoon.algorithm.others;

import java.util.Scanner;

public class BJ_2231_분해합 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int MIN_VALUE = Integer.MAX_VALUE;

		// N의 길이
		int Nlen = String.valueOf(N).length();

		// 확인할 숫자의 길이
		int len = 0;
		int c = 0;

		// 각 자리의 값
		int digitValue = -1;
		int digitSum = 0;
		int constNum = -1;

		
		// 각 자릿수의 최대값은 9
		// 6자리라 해도 각 자리수의 합의 최대값은 54
		// 현재숫자 + 현재숫자의 각 자릿수의 합이 N이여야 하기 때문에
		// 생성자가 있다면 N -(자릿수 * 9) 부터 N 중에 생성자가 있어야함. 

		// 생성자가 없다면 -1이 나오고, 출력시 0이 나오도록 함
		for (int i = N - (Nlen * 9); i < N; i++) {
			
			//각 숫자의 길이수를 알아내고		
			len = String.valueOf(i).length();
			digitSum = 0;
			c = 0;
			
			//N-(자릿수*9)가 음수일경우에는 다음것으로 넘어가도록
			if (i < 0) {
				continue;
			}
			
			//길이 수 만큼 반복하면서 각 자릿수를 더함
			while (len > 0) {

				char temp = String.valueOf(i).charAt(c);
				digitValue = temp - '0';
				digitSum += digitValue;
				len--;
				c++;

			}
			
			// 생성자를 찾으면 빠져나옴 , 낮은 수를 찾는 것이기  때문에 더 이상 찾을 필요 없음.
			if (i + digitSum == N) {
				constNum = i;
				break;
			}
		}
		
		if (constNum == -1) {
			constNum = 0;
		}
		System.out.println(constNum);
	}
}