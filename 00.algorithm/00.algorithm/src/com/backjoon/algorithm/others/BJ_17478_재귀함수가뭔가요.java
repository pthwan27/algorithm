package com.backjoon.algorithm.others;

import java.util.Scanner;

public class BJ_17478_재귀함수가뭔가요 {

	public static void main(String[] args) {		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int c = 0;
		
		// 처음한번만 나오기때문에 따로 출력
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");		
		
		//C를 이용해 탈출할 수 있도록 하고, "____" 를 출력할수 있도록 함
		solution(c, N);				
	}

	private static void solution(int c, int n) {
		String str = "____";
		StringBuilder sb = new StringBuilder();
		
		//"____"가 재귀함수 만큼 보이도록
		for(int i = 0; i < c; i++) {
			sb.append(str);
		}
		
		//재귀함수를 빠져나갈 조건
		if(c == n) {
			System.out.println(sb +"\"재귀함수가 뭔가요?\"");
			System.out.println(sb +"\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			System.out.println(sb +"라고 답변하였지.");
			
			// C==N이면 탈출!
			return;
		}	

		//아닐경우 반복되는 패턴이 실행되고
		System.out.println(sb + "\"재귀함수가 뭔가요?\"");
		System.out.println(sb +"\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
		System.out.println(sb +"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
		System.out.println(sb +"그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
		
		
		//재귀함수 호출
		solution(c+1, n);
		
		
		System.out.println(sb +"라고 답변하였지.");		
	}
}
