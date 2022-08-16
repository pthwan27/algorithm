package com.backjoon.algorithm.studyQuest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BJ_1929_소수구하기_시간비교 {

	public static void main(String[] args) throws IOException {
		 long startTime = System.nanoTime();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int M = 1; //첫번째 숫자
		int N = 1; //두번째 숫자

		StringBuilder sb = new StringBuilder(); //빠른 출력을 위해 StringBuiler 사용

		for(int i=M; i<=N; i++) { //첫번째 숫자부터 두번째 숫자까지 반복
			if(isPrime(i)) { //소수인지 판별
				sb.append(i).append("\n"); //소수가 맞다면 StringBuilder에 추가
			}
		}

		System.out.println(sb); //출력
		
		long endTime = System.nanoTime();
		 Double elapsedTime = (endTime - startTime) / 1000000.0;
        System.out.println(elapsedTime);
	}


	//소수 판별을 위한 isPrime 함수
	public static boolean isPrime(int N) {		
		if(N == 1) { //1인 경우는
			return false; //소수가 아니므로 false 반환
		} else { //다른 경우에서
			for(int i=2; i<(int)Math.sqrt(N)+1; i++) { //2부터 입력받은 숫자의 루트+1까지 반복
				if(N % i == 0) { //나눴을 때  0이라면 소수가 아니므로 false 반환
					return false;
				}
			}
			return true; //나눴을 때 0인 경우가 없다면 소수이므로 true 반환

		}
	}
	


}