package com.backjoon.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2493_탑_시간초과 {
	static int [] TopArr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine());
		TopArr = new int[N+1];		
		
		//왼쪽으로 쏜다 -> 왼쪽이 높으면 쏠 수 있고 쏜 곳의 번호를 출력한다, 왼쪽이 자신보다 낮으면 0 출력.
		// 
		// 1. 첫번째는 0이다.
		// 2. 왼쪽이 자신보다 작을 때, 최대값이 자신보다 크거나 같으면 그 위치 출력
		// 3. 왼쪽이 자신보다 크면, 그 위치 출력
		// 4. 자신이 최대값보다 크다면 0 출력
		st = new StringTokenizer(in.readLine());		
		
		int max = -1;
		int maxIdx = 0;
		
		for(int i = 1; i <= N; i++) {
			TopArr[i] = Integer.parseInt(st.nextToken());
			if(i==1) {
				//1번
				max = TopArr[i];
				sb.append(0 + " ");
			}
			else {
				//4번, 최대값보다 크다면 0 출력하고, 최대값 , 위치 값 넣기
				if(max < TopArr[i]) {
					max = TopArr[i];
					maxIdx = i;
					
					sb.append(0 + " ");
				}
				//최대값 보다 작을 때.
				else {
					//3번 왼쪽이 자신보다 크다면, 그 곳 출력하고
					if(TopArr[i] < TopArr[i-1]) {
						sb.append(i-1 + " ");
					} 
					//2번 아닌 경우에는 , 최대값이 있는 곳 까지 탐색하며 자신보다 큰 값이 있는지 확인해야함
					else {
						//
						for(int j = i-1; j >= maxIdx; j--) {
							if(TopArr[i] < TopArr[j]) {
								sb.append(j + " ");
								break;
							}
							if(TopArr[j] == max) {
								sb.append(maxIdx+ " ");
								break;
							}
						}
					}
				}
			}
		}
		System.out.println(sb);	
	}

}
