package com.backjoon.algorithm.others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_2493_탑_박태환 {
	static Stack<int[]> TopStack;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());		
		
		//stack 에 넣을 때, Map<탑 높이, 몇번째순서>인지 넣음.		
		TopStack = new Stack<int[]>();
	
		
		int topHeight = -1;		
		int max= -1;		
		int idx = 1;
		
		// Stack에서 
		// 1. 처음에는 값을 넣고 0 출력
		// 
		// 2. peek을 한 값보다 입력할 값이 더 크면 , top을 하나 빼고, push
		// 3. peek보다 작은 값이 들어오면, peek 위치 출력하고 push
		
		st = new StringTokenizer(in.readLine());
		
		while(st.hasMoreTokens()){
			topHeight = Integer.parseInt(st.nextToken());
			
			
			if(idx == 1) {									
				sb.append(0 + " ");
				TopStack.push(new int[]{topHeight, idx});
				idx++;
				
				//있어야 하나?
				max = topHeight;				
			}
			//처음이 아닐 때.
			else{
				//들어온값이 최대값 보다 클 때.
				if(topHeight > max) {
					sb.append(0 + " ");
					TopStack.clear();
					TopStack.push(new int[]{topHeight, idx});
					idx++;
					
					max = topHeight;
					
				}
				//들어온 값이 최대값 보다 작을 때
				else {
					//최대값 보다 작고, 맨 위의 값보다 작을 때
					if(TopStack.peek()[0] > topHeight) {
						sb.append(TopStack.peek()[1] + " ");
						TopStack.push(new int[] { topHeight, idx });
						idx++;
					}
					else {
						// 맨 위 값이 들어오는 값보다 작은 경우에는
						// 들어오는 값보다 큰 값이 나올때까지 pop해준다.
						while(TopStack.peek()[0] < topHeight) {
							TopStack.pop();
						}
						sb.append(TopStack.peek()[1] + " ");					
						TopStack.push(new int[]{topHeight, idx});
						idx++;
					
					}
				}				
			}				
		}
		System.out.println(sb);		
	}
}
