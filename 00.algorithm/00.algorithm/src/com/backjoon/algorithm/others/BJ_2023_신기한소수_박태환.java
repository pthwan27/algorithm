package com.backjoon.algorithm.others;

import java.util.Scanner;

/**
 * @author 박태환
 * key, ★가지치기 / 배열사용 X
 */
public class BJ_2023_신기한소수_박태환 {	
	
	static int N = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		N = sc.nextInt();
		
		String numStr = "";
		
		//N자리 까지 소수찾기 시작! 
		primeNum(0,numStr);				
	}

	private static void primeNum(int c, String numstr) {
		//N 자리수까지 소수라면 출력되고 빠져나가게 함
		// ex 23일 때 c+1로 와서 c== 2이기 때문에 다음자리 검사하지 않고 빠져나감
		if(N == c) {
			System.out.println(numstr);
			return;
		}
		
		// 1. 처음엔 한자리 수가 소수인지 확인한다.
		// 2. 첫번째(왼쪽에서 첫번째 숫자)가 소수라면 
		// ex 0 소수 X, 1 소수 X ,2 -> 소수
		// 3. 다음 자릿수 검사, 
		// ex 20 X, 21 X, 22 X, 23 -> 소수
		// 4. 이런식으로 검사를 하게 되면 , 가지치기가 된다. 
		// ex 소수가 아닌것들은 다음것을 검사하지 않게 됌.
		for(int i = 0; i <=9; i++) {
			String temp = numstr + i;
			
			if(isPrime(Integer.parseInt(temp))){
				primeNum(c+1, temp);
			}
		}		
	}
	
	// 소수판별 함수, 0과 1은 소수 아님!
	public static boolean isPrime(int num) {
		if(num == 1 || num == 0) return false;
			
		int sqrtN = (int) Math.sqrt(num);
		for(int i = 2; i <= sqrtN; i++) {
			if(num % i == 0) return false;
		}
		return true;
	}
}
