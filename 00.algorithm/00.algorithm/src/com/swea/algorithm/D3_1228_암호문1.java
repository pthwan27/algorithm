package com.swea.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_1228_암호문1 {

	// 원본 암호문의 길이를 받고 -> int N
	// 원본 암호문을 받고 -> " "로 구분되어 있음.
	// 암호 처리 횟수 -> int C
	// 명령어 -> I / 암호를 추가할 위치 /
	static ArrayList<Integer> pwList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = 10;

		for (int tc = 1; tc <= 10; tc++) {
			pwList = new ArrayList<>();

			int N = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine());
			// 원본 암호문 담기
			while (st.hasMoreTokens()) {
				pwList.add(Integer.parseInt(st.nextToken()));
			}

			int C = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < C; i++) {
				// I
				char input = st.nextToken().charAt(0);
				// 들어갈 위치
				int inputPosition = Integer.parseInt(st.nextToken());
				// 몇개
				int inputNums = Integer.parseInt(st.nextToken());
				// 숫자들
				int[] Nums = new int[inputNums];
				for (int j = 0; j < Nums.length; j++) {
					Nums[j] = Integer.parseInt(st.nextToken());
					pwList.add(inputPosition+j, Nums[j]);
				}			
			}

			sb.append("#" + tc + " ");
			for(int i = 0; i < 10; i++) {
				sb.append(pwList.get(i) + " ");
			}			
			System.out.println(sb);
			sb = new StringBuilder();
		}
		in.close();
	}

}
