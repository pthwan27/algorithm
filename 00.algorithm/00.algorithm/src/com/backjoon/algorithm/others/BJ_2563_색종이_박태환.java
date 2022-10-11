package com.backjoon.algorithm.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2563_색종이_박태환 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(in.readLine());		
		
		int count = 0;
		int [][]area = new int[100][100];
		for (int tc = 0; tc < N; tc++) {
			st = new StringTokenizer(in.readLine());
			//도화지 왼쪽 변과, 색종이 왼쪽 변 사이의 거리
			//도화지 아래 변과, 색종이 아래쪽 변 사이의 거리
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			
			for(int i = X; i < X+10; i++) {
				for(int j = Y; j < Y+10; j++) {
					area[i][j] = 1;
				}
			}			
		}
		
		for(int i = 0; i < area.length; i++) {
			for(int j = 0; j <area[i].length; j++) {
				if(area[i][j] == 1) {
					count++;
				}
			}
		}			
		System.out.println(count);

	}

}
