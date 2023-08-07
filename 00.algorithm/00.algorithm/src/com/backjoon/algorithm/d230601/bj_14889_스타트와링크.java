package com.backjoon.algorithm.d230601;

import java.io.*;
import java.util.*;


public class bj_14889_스타트와링크 {

	static int n;
	static int[][] abilityArr;
	static boolean[] isSelected;
	
	static int minSum = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char a = 'A';
		System.out.println(a);
		n = Integer.parseInt(in.readLine());
		abilityArr = new int[n][n];

		for (int r = 0; r < n; r++) {
			StringTokenizer st = new StringTokenizer(in.readLine());

			for (int c = 0; c < n; c++) {
				abilityArr[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		isSelected = new boolean[n];

		//조합 만들기
		comb(0, 0);
		

		System.out.println(minSum);
	}

	private static void comb(int cur, int count) {
		if (count == n / 2) {
			calc();
			return;
		}

		for (int i = cur; i < n; i++) {
			if (!isSelected[cur]) {
				isSelected[i] = true;
				comb(i + 1, count + 1);
				isSelected[i] = false;
			}
		}
	}

	private static void calc() {
		int linkTeam = 0;
		int startTeam = 0;
		
		for(int r = 0; r < n; r++) {
			for(int c= 0; c < n; c++) {
				//링크팀
				if(isSelected[r]) {
					if(isSelected[c]) {
						linkTeam += abilityArr[r][c];
					}
				}
				
				//스타트팀
				else {
					if(!isSelected[c]) {
						startTeam += abilityArr[r][c];
					}					
				}
			}
		}		
		minSum = Math.min(minSum, Math.abs(linkTeam-startTeam));
		}
}
