package com.swea.algorithm.week0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_낚시터 {
	static class GateInfo {
		int gate, nums;

		public GateInfo(int gate, int nums) {
			super();
			this.gate = gate;
			this.nums = nums;
		}
	}

	static int N;
	static GateInfo[] gates;
	static boolean[] isSelected;

	static int[] D;

	static int fisherCnt;

	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			// 낚시터 개수
			N = Integer.parseInt(in.readLine());
			gates = new GateInfo[3];
			isSelected = new boolean[3];
			D = new int[N + 1];

			for (int i = 0; i < 3; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				gates[i] = new GateInfo(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			result = Integer.MAX_VALUE;
			DFS(0, 0);	
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
		
	}

	private static void DFS(int count, int sum) {
		if(sum >= result) return;
		
		if (count == 3) {
			result = Math.min(result,sum);
			return;
		}

		for (int i = 0; i < 3; i++) {
			if (isSelected[i])
				continue;

			// 게이트 선택
			isSelected[i] = true;

			DFS(count+1, sum + inFisher(i, gates[i].nums, true));
			outFisher(i);
			// 짝수일 경우 오른쪽부터하는 경우도 검사
			if (gates[i].nums % 2 == 0) {
				DFS(count+1, sum + inFisher(i, gates[i].nums, false));
				outFisher(i);
			}


			// 게이트 선택 해제
			isSelected[i] = false;
		}
	}

	private static void outFisher(int idx) {
		//낚시꾼 수만큼만 되게 수정해야함
		
		for(int i = 1; i <= N; i++) {
			if(D[i] == gates[idx].gate) {
				D[i] = 0;
			}
		}
		
	}

	private static int inFisher(int idx, int nums, boolean flag) {
		int distance = 0;
		int tempSum = 0;
		fisherCnt = 0;
		while (fisherCnt < nums) {
			tempSum += flag ? left(idx, distance) : right(idx, distance);
			if (fisherCnt == nums)
				break;
			tempSum += flag ? right(idx, distance) : left(idx, distance);
			
			distance++;
		}

		return tempSum;
	}

	private static int left(int idx, int distance) {		
		int left = gates[idx].gate - distance;
		if ( left > 0 && D[left] == 0) {
			D[left] = gates[idx].gate;
			fisherCnt++;
			return distance+1;			
		}

		return 0;
	}

	private static int right(int idx, int distance) {
		int right = gates[idx].gate + distance;
		if ( right <= N && D[right] == 0) {
			D[right] = gates[idx].gate;
			fisherCnt++;
			return distance+1;		
		}
		return 0;
	}

}
