package com.swea.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Queue를 이용한 문제
 * @author parks
 */
public class D3_1225_암호생성기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> pwQue = new LinkedList<Integer>();
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= 10; tc++) {
			pwQue.clear();
			sb = new StringBuilder();
			int T = Integer.parseInt(in.readLine());			
			st = new StringTokenizer(in.readLine());
			
			for(int i = 0; i < 8; i++) {
				pwQue.add(Integer.parseInt(st.nextToken()));
			}
			boolean check = true;
			while(true) {
				for(int i = 1; i <= 5; i++) {
					int temp = pwQue.poll();
					temp = temp-i;
					if(temp > 0) {
						pwQue.add(temp);						
					}else {
						pwQue.add(0);
						check = false;
						break;
					}					
				}
				if(check == false) break;
				
			}

			while(!pwQue.isEmpty()) {
				sb.append(pwQue.poll()+ " ");
			}
			System.out.println("#"+tc + " " +sb);
			
		}
		
	}
}
