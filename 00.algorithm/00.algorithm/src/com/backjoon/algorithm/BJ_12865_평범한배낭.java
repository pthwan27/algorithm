package com.backjoon.algorithm;

import java.util.Scanner;

public class BJ_12865_평범한배낭 {	
	//준서가 여행에 필요하다 생각하는 물건 N
	//물건은 무게 W, 가치 V 를 가진다.
	//해당물건을 배낭에 넣어 가져가면 V만큼 즐길 수 있다.
	//준서에게는 K만큼의 배낭이 있다.
	//이 때 가치의 최대값은 ?
	
	//첫줄 입력데이터 N, K
	//두 번째 줄부터 N개만큼 각 물건의 W, V를 받는다	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//N -> 물건의 개수
		//K -> 버틸수 있는 무게
		int N = sc.nextInt();
		int K = sc.nextInt();
		

		int [] weightArr = new int[N];
		int [] valueArr = new int[N];
		
		
		for(int index = 0; index < N; index++) {
			weightArr[index] = sc.nextInt();	
			valueArr[index] = sc.nextInt();		
		}
		
		//물건을 하나 뽑고 -> 무게가 넘치지 않는지 확인  value추가
		// 또 하나 뽑고 -> 확인 value추가		
		//무게가 넘치지 않는지 확인 초과일때 return, 초과가 아니면 다음꺼 더해보기
		
		int result = 0;
		
		for(int i = 0; i< N; i++) {
			for(int j = 0; j < N; j++) {
				if(weightArr[j] < K);
			}
		}

	}

	

}
