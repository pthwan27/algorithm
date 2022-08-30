package com.swea.algorithm.week0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_낚시터2 {

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
	static GateInfo[] selectedGate;

	static int result = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 낚시터 개수
			N = Integer.parseInt(in.readLine());

			gates = new GateInfo[3];
			isSelected = new boolean[3];
			selectedGate = new GateInfo[3];

			// 입구는 3개로 고정.
			for (int i = 0; i < 3; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());

				int gate = Integer.parseInt(st.nextToken());
				int nums = Integer.parseInt(st.nextToken());

				gates[i] = new GateInfo(gate, nums);
			}
			result = Integer.MAX_VALUE;
			perm(0);
			sb.append("#"+ tc + " " + result+"\n");			
		}
		System.out.println(sb);
	}

	private static void perm(int cur) {
		if (cur == 3) {
			enterGate(new int[N + 1], 0, selectedGate, 0);
			return;
		}

		for (int i = 0; i < 3; i++) {
			if (isSelected[i])
				continue;

			isSelected[i] = true;

			selectedGate[cur] = gates[i];
			perm(cur + 1);

			isSelected[i] = false;
		}
	}

	private static void enterGate(int[] distArr, int cur, GateInfo[] gateArr, int sum) {
		if (cur == 3) {
			result = Math.min(result, sum);
			return;
		}

		int[] d = distArr.clone();
		int tempSum = sum;
		
		int distance = 0;
		int count = 0;
		int gate = gateArr[cur].gate;
		int nums = gateArr[cur].nums;
		boolean flag = nums % 2 == 0 ? true : false;

		// 짝수는 왼오, 오왼 두번 검사
		if (flag) {
			while (count < nums) {
				// 왼쪽
				int left = gate - distance;
				if (left > 0 && distArr[left] == 0 ) {
					distArr[left] = gate;
					sum += distance + 1;
					count++;
				}

				if (count == nums)
					break;

				// 오른쪽
				int right = gate + distance;
				if (right <= N && distArr[right] == 0 ) {
					distArr[right] = gate;
					sum += distance + 1;
					count++;
				}
				distance++;
			}
			enterGate(distArr, cur + 1, gateArr, sum);
			
			distArr = d.clone();
			sum = tempSum;
			distance = 0;
			count = 0;
			gate = gateArr[cur].gate; 
			nums = gateArr[cur].nums; 
			
			while (count < nums) {
				// 오른쪽
				int right = gate + distance;
				if (right <= N &&distArr[right] == 0) {
					distArr[right] = gate;
					sum += distance + 1;
					count++;
				}

				if (count == nums)
					break;
				// 왼쪽
				int left = gate - distance;
				if (left > 0 && distArr[left] == 0 ) {
					distArr[left] = gate;
					sum += distance + 1;
					count++;
				}				
				distance++;				
			}
			enterGate(distArr, cur + 1, gateArr, sum);
			
			
		}

		// 홀수는 한번만
		else {
			while (count < nums) {
				// 왼쪽
				int left = gate - distance;
				if (left > 0 && distArr[left] == 0 ) {
					distArr[left] = gate;
					sum += distance + 1;
					count++;
				}

				if (count == nums)
					break;

				// 오른쪽
				int right = gate + distance;
				if (right <= N &&distArr[right] == 0) {
					distArr[right] = gate;
					sum += distance + 1;
					count++;
				}
				distance++;
			}
			enterGate(distArr, cur + 1, gateArr, sum);
		}
	}
}
