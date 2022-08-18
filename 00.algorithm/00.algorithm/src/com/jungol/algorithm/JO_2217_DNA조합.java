package com.jungol.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JO_2217_DNA조합 {	
	static int N;
	static ArrayList<char[]> dnaList;
	
	
	static boolean[] isSelected;
	static int[] nums;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		dnaList = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			String inputStr = in.readLine();
			char[] temp = new char[inputStr.length()]; 
			for(int j = 0, size = inputStr.length(); j < size; j++) {			
				temp[j] = inputStr.charAt(j);
			}
			dnaList.add(temp);
		}
		
		
		isSelected = new boolean[N];
		nums = new int[N];
		
		DNApermutation(0);
	}
	private static void DNApermutation(int cur) {
		if(cur == N) {
			ArrayList<char[]> dnaPermList = new ArrayList<>();
			for(int i = 0; i <nums.length; i++) {
				dnaPermList.add(dnaList.get(nums[i]));
			}
			
			findMinLen(dnaPermList);
			return;			
		}
		
		for(int i = 0; i < N; i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			nums[cur] = i;
			DNApermutation(cur+1);
			
			isSelected[i] = false;
		}
	}
	
	private static void findMinLen(ArrayList<char[]> dnaPermList) {
		char[] temp = dnaPermList.get(0);
		int count = 0;
		for(int i = 1; i < dnaPermList.size(); i++) {
			while(true) {
				int a = temp.length-1;
				int b = 0;
				if(temp[a] != dnaPermList.get(i)[b]) {
					break;
				}
				
				if(temp[a] == dnaPermList.get(i)[b]) {
					count++;
				}
				
				System.out.println(count);
				
				a--;
				b++;
			}
		}
		
	}

}
