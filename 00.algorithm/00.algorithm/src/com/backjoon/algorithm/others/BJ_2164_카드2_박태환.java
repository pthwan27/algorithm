package com.backjoon.algorithm.others;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Queue를 이용한 문제
 * @author parks
 */
public class BJ_2164_카드2_박태환 {
		public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		Queue<Integer> cardQ = new LinkedList<>();		
		
		for(int i = 1; i <= N; i++) {
			cardQ.add(i);			
		}		
		// 입력 끝		
		
		boolean check = false;
		while(cardQ.size() > 1) {
			// 가져올 값을 저장할 변수
			int temp = 0;
			
			// check를 통해 제일 위에 있는 카드 버리는 경우와, 
			// 아래에 있는 걸 위로 옮기는 경우를 구분하여 처리
			if(check == false) {
				// 제일 위에 있는 카드 버리기.
				cardQ.remove();
				check = true;
			} else {
				// 아래에 있는 걸 위로 옮기는 경우				
				cardQ.add(cardQ.poll());				
				check = false;				
			}			
		}		
		System.out.println(cardQ.peek());				
	}
}
