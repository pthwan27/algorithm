package com.swea.algorithm.week1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class SW_2115_벌꿀채취 {
	static class HoneySum implements Comparable<HoneySum> {
		int r, c, sum, benefit;

		public HoneySum(int r, int c) {
			this.r = r;
			this.c = c;

			int temp = 0;
			int temp2 = 0;
			for (int i = c; i < c + M; i++) {
				temp += map[r][i];
				temp2 += Math.pow(map[r][i], 2);
			}
			this.sum = temp;
			this.benefit = temp2;
		}

		public HoneySum(int r, int c, int sum, int benefit) {
			super();
			this.r = r;
			this.c = c;
			this.sum = sum;
			this.benefit = benefit;
		}

		@Override
		public String toString() {
			return "HoneySum [r=" + r + ", c=" + c + ", sum=" + sum + ", benefit=" + benefit + "]";
		}

		@Override
		public int compareTo(HoneySum o) {
			return o.benefit - this.benefit;
		}

	}

	//N -> 크기(map -> 정사각형)

	//M -> 꿀 담을 통의 갯수
	//C -> 꿀을 담을 수 있는 양
	static int N, M, C;
	static int[][] map;

	static int result;

	static ArrayList<HoneySum> honeySumList;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			result = Integer.MIN_VALUE;

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			//만들 수 있는 꿀통들 
			honeySumList = new ArrayList<>();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c <= N - M; c++) {
					honeySumList.add(new HoneySum(r, c));
				}
			}

			//한 통의 최대 값 구해놓기.
			for (int i = 0; i < honeySumList.size(); i++) {
				if (honeySumList.get(i).sum > C) {
					int r, c, sum, maxbenefit;

					r = honeySumList.get(i).r;
					c = honeySumList.get(i).c;
					sum = honeySumList.get(i).sum;
					maxbenefit = maxBenefit(honeySumList.get(i));

					honeySumList.set(i, new HoneySum(r, c, sum, maxbenefit));
				}				
			}

//			for (int i = 0; i < honeySumList.size(); i++) {
//				System.out.println(honeySumList.get(i));
//			}

			//2개조합 구하기		
			int[] nums = new int[2];
			comb(0, 0, nums);

			sb.append("#"+tc+ " " + result + "\n");
		}
		System.out.println(sb);

	}

	static int maxbenefit = 0;

	//maxBenefit -> 한 꿀통에서 나올 수있는 최대 값 찾기
	private static int maxBenefit(HoneySum honeySum) {
		HoneySum A = honeySum;
		maxbenefit = 0;

		int[] Aarr = new int[M];
		for (int i = A.c, idx = 0; i < A.c + M; i++, idx++) {
			Aarr[idx] = map[A.r][i];
		}

		findMaxComb(0, 0, 0, Aarr);

		return maxbenefit;
	}

	private static void findMaxComb(int start, int sum, int benefit, int[] arr) {
		if (sum > C) {
			return;
		}

		if (sum <= C) {
			maxbenefit = Math.max(maxbenefit, benefit);
		}

		for (int i = start; i < M; i++) {
			sum += arr[i];
			benefit += Math.pow(arr[i], 2);
			findMaxComb(i+1, sum, benefit, arr);
			sum -= arr[i];
			benefit -= Math.pow(arr[i], 2);
		}

	}

	//꿀통 비교하면서, 두개의 합이 가장 큰 것 찾기
	private static void comb(int cur, int start, int[] nums) {
		if (cur == 2) {
			//두개의 꿀통 비교하면서 찾기
			result = Math.max(result, calc(nums));
			return;
		}
		for (int i = start; i < honeySumList.size(); i++) {
			nums[cur] = i;
			comb(cur + 1, i+1, nums);
		}
	}

	private static int calc(int[] nums) {
		HoneySum A = honeySumList.get(nums[0]);
		HoneySum B = honeySumList.get(nums[1]);

		//꿀통의 범위가 겹치는 경우
		if (A.r == B.r && A.c + M > B.c) {
			return -1;
		}
		return A.benefit + B.benefit;
	}

}
