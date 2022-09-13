package com.backjoon.algorithm.week0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_2437_저울 {

	static int N;
	static ArrayList<Integer> weightArr;

	static boolean[] isSelected;
	
	static HashSet<Integer> reusltSet;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		weightArr = new ArrayList<>();
		reusltSet = new HashSet<>();		
		
		StringTokenizer st = new StringTokenizer(in.readLine());

		for (int i = 0; i < N; i++) {			
			weightArr.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(weightArr);
		
		isSelected = new boolean[N];
		solve(0);
		
		ArrayList<Integer> resultList = new ArrayList<>(reusltSet);
		
		int idx = 0;
		while(true) {
			if(idx >= resultList.size()) {
				break;
			}
			if(resultList.get(idx) != idx) {
				break;
			}
			idx++;
		}		
		System.out.println(idx);
	}

	private static void solve(int cur) {
		if (cur == N) {
			int sum = 0;
			for(int i = 0; i < isSelected.length; i++) {
				if(isSelected[i]) {
					sum+=weightArr.get(i);
				}
			}
			reusltSet.add(sum);
			return;
		}
		
		isSelected[cur] = true;
		solve(cur + 1);
		
		isSelected[cur] = false;
		solve(cur + 1);
	}
}
