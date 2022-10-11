package com.backjoon.algorithm.week1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_1541_잃어버린괄호_박태환 {

	/**
	 * 잃어버린 괄호 괄호를 식에 추가해 최소값을 찾기 첫째 줄에 식이 주어진다.
	 * 
	 * sol - > 1. -가 오면 다음 -가 올때까지 괄호로 묶기 2. -가 왔는데 다음 연산자가 안나오고 끝나면 괄호 닫고 끝내기
	 * 
	 * @param 박태환
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), "-");
		ArrayList<Integer> list = new ArrayList<>();
		while(st.hasMoreTokens()) {	
			int sum =0;
			StringTokenizer inst = new StringTokenizer(st.nextToken(),"+");
			while(inst.hasMoreTokens()) {
				sum += Integer.parseInt(inst.nextToken());				
			}			
			list.add(sum);
		}
		
		int result = list.get(0);
		for(int idx = 1; idx < list.size(); idx++) {
			
			result -= list.get(idx);		
		}
		
		System.out.println(result);		
	}

}
