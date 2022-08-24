package com.backjoon.algorithm.week0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1759_암호만들기_박태환 {

	static char[] alphaArr;

	static char[] combChar;

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		//알파벳 담아둔 배열
		alphaArr = new char[C];

		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < C; i++) {
			alphaArr[i] = st.nextToken().charAt(0);
		}
		//미리 정렬해둠
		Arrays.sort(alphaArr);
		combChar = new char[L];
		
		//조합뽑기
		comb(0, 0, L, C);
		
		System.out.println(sb);
	}

	private static void comb(int cur, int start, int N, int C) {
		if (cur == N) {
			char[] temp = combChar;
			//출력해도 되는지 검사(aeiou 1개이상, 나머지 2개이상 들어가있는지)
			if (IsPossible(temp)) {
				for(int i = 0, size = temp.length; i < size; i++) {
					sb.append(temp[i]);
				}
				sb.append("\n");
			}
			return;
		}

		for (int i = start; i < C; i++) {
			combChar[cur] = alphaArr[i];
			comb(cur + 1, i + 1, N, C);
		}

	}

	//aeiou 1개이상, 나머지 2개이상 들어가있는지 확인
	private static boolean IsPossible(char[] temp) {
		int aeiouCnt = 0;
		int otherCnt = 0;
		
		for (int i = 0, size = temp.length; i < size; i++) {
			if (temp[i] == 'a' || temp[i] == 'e' || temp[i] == 'i' || temp[i] == 'o' || temp[i] == 'u') {
				aeiouCnt++;
			}else {
				otherCnt++;
			}
		}
		
		if(aeiouCnt < 1 || otherCnt < 2) {
			return false;
		}else {
			return true;
		}
		
	}

}
