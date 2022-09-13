package com.backjoon.algorithm.week0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_2437_저울2 {

	static int N;
	static ArrayList<Integer> weightArr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		weightArr = new ArrayList<>();	
		
		StringTokenizer st = new StringTokenizer(in.readLine());

		for (int i = 0; i < N; i++) {			
			weightArr.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(weightArr);
		
		int sum = 0;
		
		for(int i = 0; i < weightArr.size(); i++) {
			if(sum+1 < weightArr.get(i)) {
				break;
			}
			sum += weightArr.get(i);
		}
		System.out.println(sum+1);
		
	}
}
