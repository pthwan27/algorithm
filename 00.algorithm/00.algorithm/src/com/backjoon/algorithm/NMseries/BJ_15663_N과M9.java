package com.backjoon.algorithm.NMseries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_15663_N과M9 {

	static int N;
	static int M;

	static ArrayList<Integer> inputArr;

	static int[] nums;
	static boolean[] isSelected;

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		inputArr = new ArrayList<>();
		st = new StringTokenizer(in.readLine());

		for (int idx = 0; idx < N; idx++) {
			inputArr.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(inputArr);
		
		nums = new int[M];
		
		isSelected = new boolean[N];
		comb(0);

		System.out.println(sb);
	}

	// HashSet을 쓰지말고, 정렬을 해뒀기 때문에 중복을 제거하면서 들어올 수 있도록 해보자!
	private static void comb(int cur) {
		if (cur == M) {	
			for(int i = 0; i < nums.length; i++) {
				sb.append(nums[i] + " ");
			}
			sb.append("\n");
			return;
		}
		int past = -1;
		for (int i = 0; i < N; i++) {
			int now = inputArr.get(i); //새로 들어오는 값 넣기
			if (isSelected[i] || now == past) // 새로들어온 값과, 지난 번 값이 같은건지 확인 -> 중복되지 않게 하기 위해
				continue;
			
			past = now; //새로 들어온 값이면 새로운 값 넣어주고, 다음 재귀로
			isSelected[i] = true;
			nums[cur] = inputArr.get(i);

			comb(cur + 1);
			isSelected[i] = false;

		}
	}
}
