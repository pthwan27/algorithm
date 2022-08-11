package com.ssafy.algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class PermutationBitMaskingTest {
	static int N, R, totalCnt;
	static int[] numbers, input;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		totalCnt = 0;
		
		input = new int[N];
		numbers = new int[R];
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		perm(0, 0);
		System.out.println("총 경우의 수 : " + totalCnt);
	}
//cnt : 직전짜기 뽑은 순엻에 포함된 수의 개수, cnt+1 번째 해당하는 순열에 포함될 수를 뽑기
	// falg : 선택된 수들의 상태를 비트로 표현하고 있는 비트열
	private static void perm(int cnt, int flag) {
		if(cnt == R) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;			
		}
		for (int i = 0; i < N; i++) {
			//시도ㅓ하는 수가 선택되었는지 판단
			if((flag & 1 << i) != 0) continue;
			//앞쪽에서 선택되지 않았다면 수를 사용
			numbers[cnt] = input[i];
			//다음 수 뽀ㅃ으러 가기
			perm(cnt+1, flag | 1<<i);
			
		}
		
	}
	

}
