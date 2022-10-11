package com.backjoon.algorithm.week1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_12891_DNA비밀번호 {
	static int S = 0;
	static int P = 0;

	static char[] strChar;
	static String temp = "";
	
	static int Adna;
	static int Cdna;
	static int Gdna;
	static int Tdna;	
	
	// 문자열 길이 S / 사용할 부분 문자열 길이 P
	// S에서 , P 자리만큼 문자열을 뽑았을 때,
	// ‘A’, ‘C’, ‘G’, ‘T’ 가 주어진 숫자만큼 다 들어가 있어야한다.

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		//주어진 문자열 길이
		S = Integer.parseInt(st.nextToken());

		//비밀번호로 사용할 문자열 길이
		P = Integer.parseInt(st.nextToken());

		//문자열 담아둔 char 배열
		strChar = in.readLine().toCharArray();
		
		//문자열을 순차적으로 파악하기때문에 queue를 사용. ( 하나 빼고, 하나 넣고, 반복 )
		Queue<Character> strQue = new LinkedList<Character>();
		st = new StringTokenizer(in.readLine());
		
		//문자열에 들어가 있어야 하는 DNA 알파벳  갯수 (입력받는 것들)
		Adna = Integer.parseInt(st.nextToken());
		Cdna = Integer.parseInt(st.nextToken());
		Gdna = Integer.parseInt(st.nextToken());
		Tdna = Integer.parseInt(st.nextToken());

		//문자열에 있는 DNA 알파벳  갯수 (몇개가 있는지 파악)
		int Acnt = 0;
		int Ccnt = 0;
		int Gcnt = 0;
		int Tcnt = 0;
		
		int result = 0;
		
		//문자열 길이가 5이고, 비밀번호 길이가 3이라면, 3번 돌 수 있음
		//1 2 3 4 5 -> 123 , 234, 345
		for (int i = 0; i <= S - P; i++) {
			if (i == 0) {//맨 처음엔 Queue에 P만큼 값을 넣어줌.				
				for (int j = 0; j < P; j++) {
					switch (strChar[j]) {
					case 'A':
						Acnt++;
						break;
					case 'C':
						Ccnt++;
						break;
					case 'G':
						Gcnt++;
						break;
					case 'T':
						Tcnt++;
						break;
					}
					strQue.offer(strChar[j]);
				}
			} 
			else // 처음 큐에 값을 넣어주고 나서부터는 앞에걸 빼고, 하나씩 넣으면서 확인 
			{
				//하나 빼기
				switch (strQue.poll()) {
				case 'A':
					Acnt--;
					break;
				case 'C':
					Ccnt--;
					break;
				case 'G':
					Gcnt--;
					break;
				case 'T':
					Tcnt--;
					break;
				}

				//다음거 넣기
				switch (strChar[P-1 + i]) {
				case 'A':
					Acnt++;
					strQue.offer(strChar[P-1 + i]);
					break;
				case 'C':
					Ccnt++;
					strQue.offer(strChar[P-1 + i]);
					break;
				case 'G':
					Gcnt++;
					strQue.offer(strChar[P-1 + i]);
					break;
				case 'T':
					Tcnt++;
					strQue.offer(strChar[P-1 + i]);
					break;
				default:
					strQue.offer(strChar[P-1 + i]);
					break;
				}
			}
			//주어진 갯수 만큼 들어와 있는지 확인
			result += pwCheck(Acnt, Ccnt, Gcnt, Tcnt);
		}		
		System.out.println(result);
	}

	private static int pwCheck(int acnt, int ccnt, int gcnt, int tcnt) {
		if(Adna - acnt <= 0 && Cdna - ccnt <= 0 && Gdna - gcnt <= 0 && Tdna - tcnt <= 0) {
			return 1;
		}
		return 0;		
	}
}
