package com.backjoon.algorithm.week0926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17471_게리맨더링 {

	static int N;
	static int[] people;

	static int[][] graph;

	static int[] nums;

	static int minResult;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());

		people = new int[N + 1];

		StringTokenizer st = new StringTokenizer(in.readLine());

		// 인구 수 저장
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		// 1은 연결되어있음
		graph = new int[N + 1][N + 1];
		// 인접행렬
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(in.readLine());
			int c = Integer.parseInt(st.nextToken());

			for (int i = 0; i < c; i++) {
				int to = Integer.parseInt(st.nextToken());
				graph[r][to] = 1;
				graph[to][r] = 1;
			}
		}

		minResult = 99999;
		for (int i = 1; i <= N / 2; i++) {
			nums = new int[i];
			comb(0, 1, i);
		}

		System.out.println(minResult == 99999 ? -1 : minResult);

	}

	private static void comb(int cur, int start, int end) {
		if (cur == end) {
			// 연결되어있는지 확인..
			int result = isConnect(nums);
			minResult = Math.min(result, minResult);
			return;
		}

		for (int i = start; i <= N; i++) {
			nums[cur] = i;
			comb(cur + 1, i, end);
		}
	}

	private static int isConnect(int[] nums) {
		ArrayList<Integer> A = new ArrayList<>();
		ArrayList<Integer> B = new ArrayList<>();

		boolean[] check = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			for (int a = 0; a < nums.length; a++) {
				check[nums[a]] = true;
			}
		}

		for (int i = 1; i <= N; i++) {
			if (check[i] == true)
				A.add(i);
			else
				B.add(i);
		}

		if (check(A, B)) {
			return calc(A, B);
		} else {
			return 99999;
		}

	}

	private static int calc(ArrayList<Integer> a, ArrayList<Integer> b) {
		int aSum = 0;
		int bSum = 0;
		for (int i = 0; i < a.size(); i++) {
			aSum += people[a.get(i)];
		}

		for (int i = 0; i < b.size(); i++) {
			bSum += people[b.get(i)];
		}

		return Math.abs(aSum - bSum);
	}

	private static boolean check(ArrayList<Integer> a, ArrayList<Integer> b) {
//		System.out.println(a);
//		System.out.println(b);

		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] isConnected = new boolean[N + 1];
		
		queue.offer(a.get(0));
		isConnected[a.get(0)] = true;

		while (!queue.isEmpty()) {
			int nowPos = queue.poll();
			for (int i = 1; i <= N; i++) {
				// 연결하는 곳이 A가 아니면 Pass
				if(!a.contains(nowPos)){
					continue;
				}
				
				// 연결된 곳이 있다면 Queue에 추가 + a 구역에 있는것들만 추가
				if (graph[nowPos][i] > 0 && !isConnected[i] && a.contains(i)) {
					isConnected[i] = true;
					queue.offer(i);
				}
			}
		}

		queue.offer(b.get(0));
		isConnected[b.get(0)] = true;
		
		while (!queue.isEmpty()) {
			int nowPos = queue.poll();
			for (int i = 1; i <= N; i++) {
				// 연결하는 곳이 B가 아니면 Pass
				if(!b.contains(nowPos)){
					continue;
				}
				
				// 연결된 곳이 있다면 Queue에 추가 + b 구역에 있는것들만 추가
				if (graph[nowPos][i] > 0 && !isConnected[i] && b.contains(i)) {
					isConnected[i] = true;
					queue.offer(i);
				}
			}
		}

		for (int i = 1; i < isConnected.length; i++) {
			if(isConnected[i] == false) {
				return false;
			}			
		}

		return true;
	}

}
