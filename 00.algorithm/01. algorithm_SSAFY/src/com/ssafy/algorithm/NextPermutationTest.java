package com.ssafy.algorithm;

import java.util.Arrays;
import java.util.Scanner;


//숙제: 옛날에 짯던 재귀 nPn 10P10으로 기존 재귀 순열/네퍼순열 시간차이 확인 (출력 불필요)

public class NextPermutationTest { // nPn

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] input = new int[N];

		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}

		// 전처리 : 순열에 쓰일 수들을 오름차순 정렬
		Arrays.sort(input);
		do {
			System.out.println(Arrays.toString(input)); // 순열 완성
		}
		while(np(input));

	}

	// numbers 배열의 상태를 근거로 다음 순열 생성, 다음 순열 존재하면 true, 아니면 false
	public static boolean np(int[] numbers) {		
		int N = numbers.length;
		//1. 꼭대기 찾기		
		int i = N-1;
		
		while(i > 0 && numbers[i-1] >= numbers[i]) --i;
		
		//다음 순열을 만들 수 없는 이미 가장 큰 순열의 상태일 떄
		if(i == 0) {
			return false;
		}
		
		//2. 꼭대기의 바로 앞자리(i-1)의 값을 크게 만들 교환 값 뒤쪽에서 찾기
		int j = N-1;
		while(numbers[i-1] >= numbers[j]) j--;
		
		//3. i-1 위치값과 j위치값 교환
		swap(numbers, i-1, j);
		
		//4. i위치부터 맨 뒤까지의 수열을 가장 작은 형태의 오름차순으로 변경
		int k = N-1;
		//i 는 꼭대기, k 는 맨 뒤
		while(i < k) swap(numbers, i++, k--);
		
		return true;		
	}
	public static void swap(int[] numbers, int i, int j) {
		int tmp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = tmp;
	}

}
