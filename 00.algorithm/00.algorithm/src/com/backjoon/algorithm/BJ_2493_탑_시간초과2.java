package com.backjoon.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_2493_탑_시간초과2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());		

		// 배열에 input을 담고
		int[] TopArr = new int[N];
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			TopArr[i] = Integer.parseInt(st.nextToken());
		}

		// 스택에 배열의 역순으로 담음
		Stack<Integer> TopStack = new Stack<>();
		for (int i = N - 1; i >= 0; i--) {
			TopStack.add(TopArr[i]);
		}		
		
		int [] outArr = new int[N];
		
		// 출력할 위치를 알기 위해
		int count = 0;
		int max = -1;
		
		int maxIdx = -1;

		// 9
		// 10 6 1 1 1 2 7 5 9
		
		
		// 왼쪽으로 쏜다 -> 왼쪽이 높으면 쏠 수 있고 쏜 곳의 번호를 출력한다, 왼쪽이 자신보다 낮으면 0 출력.
		//
		// 1. 첫번째는 0이다.
		// 2. 왼쪽이 자신보다 작을 때, 최대값이 자신보다 크거나 같으면 그 위치 출력
		// 3. 왼쪽이 자신보다 크면, 그 위치 출력
		// 4. 자신이 최대값보다 크다면 0 출력
		while (!TopStack.isEmpty()) {
			if (count == 0) {
				sb.append(0 + " ");
				max = TopStack.peek();
				outArr[count] = TopStack.pop();				
				maxIdx = count;
				count++;
			} else {
				// 4. 최대값 보다 클 때.
				if (TopStack.peek() > max) {
					sb.append(0 + " ");
					max = TopStack.peek();
					outArr[count] = TopStack.pop();
					maxIdx = count;
					count++;
				}
				// 최대값 보다는 작을 때.
				else {
					for(int i = count-1; i >= maxIdx; i--) {
						if(outArr[i] > TopStack.peek()) {
							sb.append(i+1 + " ");
							outArr[count] = TopStack.pop();
							count++;
							break;
						}
					}					
				}				
			}
		}
		System.out.println(sb);
	}

}
