package com.backjoon.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Backjoon17478재귀함수가뭔가요 {
		
	public static void main(String[] args) throws Exception, IOException {
		//문제
		//재귀함수를 이용한 문제
		//함수 내에 같은 함수를 실행하고, 종료 조건을 만들어야함
		//문제의 패턴 파악( 문자열이 어떻게 이루어졌는지)
		//종료조건 -> 0~ N까지 실행하고, N이 되었을 때 종료되도록
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		
		//function(n이 되었을 때만 다르게 답변하도록 하기 위한 숫자, "____" 을 출력하기 위한 숫자, N) 
		function(0, 0, N);		
	}

	private static void function(int c, int i, int n) {
		String temp = "";
	
		//"____"가 점점 증가하면서 나오도록
		for (int j= 0; j < i; j++) {
			temp += "____";
		}
		
		//c가 N이랑 같아질 때 재귀함수를 종료시킴.
		// 그러면 "라고 답변하였지" 가 출력되면서 최근꺼부터 실행됌.
		if(c == n) {
			System.out.println(temp+"\"재귀함수가 뭔가요?\"");
			System.out.println(temp+"\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			System.out.println(temp + "라고 답변하였지.");
			return;
		}
		
		System.out.println(temp+"\"재귀함수가 뭔가요?\"");
		System.out.println(temp+"\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\r\n" + 
				temp +"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\r\n" + 
				temp +"그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");	
		
		
		function(c+1,i+1,n);
		
		System.out.println(temp + "라고 답변하였지.");
	}
}
