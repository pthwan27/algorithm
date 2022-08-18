package com.jungol.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class JO_2217_DNA조합2 {
	static int N;
	static ArrayList<String> dnaList;

	static boolean[] isSelected;
	static int[] nums;
	
	static int minLen = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		dnaList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String inputStr = in.readLine();
			dnaList.add(inputStr);
		}

		isSelected = new boolean[N];
		nums = new int[N];

		DNApermutation(0);
		
		System.out.println(minLen);
	}

	private static void DNApermutation(int cur) {
		if (cur == N) {
			ArrayList<String> dnaPermList = new ArrayList<>();
			for (int i = 0; i < nums.length; i++) {
				dnaPermList.add(dnaList.get(nums[i]));
			}

			minLen = Math.min(minLen, findMinLen(dnaPermList));
			
			return;
		}

		for (int i = 0; i < N; i++) {
			if (isSelected[i])
				continue;

			isSelected[i] = true;
			nums[cur] = i;
			DNApermutation(cur + 1);

			isSelected[i] = false;
		}
	}

	private static int findMinLen(ArrayList<String> dnaPermList) {
		String temp = dnaPermList.get(0);
		String result = "";

		for (int i = 1; i < dnaPermList.size(); i++) {

			int len = Math.min(temp.length(), dnaPermList.get(i).length());
			int idx = 0;

			for (int j = 0; j <= len; j++) {
				String sub1 = temp.substring(temp.length() - j, temp.length());
				String sub2 = dnaPermList.get(i).substring(0, j);
				if (sub1.equals(sub2))
					idx = j;
			}

			result = temp.concat(dnaPermList.get(i).substring(idx, dnaPermList.get(i).length()));

			temp = result;
		}
		
		
		return result.length();
	}

}
