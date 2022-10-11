package com.swea.algorithm.week1010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_5656_벽돌깨기 {
	static class Boom {
		int r, c, power;

		public Boom(int r, int c, int power) {
			super();
			this.r = r;
			this.c = c;
			this.power = power;
		}

	}

	static int N, W, H;

	static int[][] map;

	static int[] nums;
	static boolean[] visited;
	
	static int totalCount;
	
	static int tempCount;

	static int minResult;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());

			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			totalCount = 0;
			tempCount = 0;
			minResult = Integer.MAX_VALUE;
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(in.readLine());
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if(map[r][c] > 0) totalCount++;
				}
			}

			// 중복순열 N개
			// N개로 구슬 떨어트리면서 벽돌 부수기 
			// -> C고정 R돌면서 확인
			// 	-> 구슬 터지면 연쇄작용 만들어야함

			// 벽돌 땡기기(중력작용)

			nums = new int[N];
			visited = new boolean[W];

			perm(0);
			
			sb.append("#" + tc + " " + minResult + "\n");
		}
		System.out.println(sb);
	}

	private static void perm(int cur) {
		if (cur == N) {
			int[][] newMap = new int[H][W];
			tempCount = totalCount;
			newMap = copy(map);
			drop(nums, newMap);
			
			minResult = Math.min(minResult, tempCount);
			return;
		}

		for (int i = 0; i < W; i++) {
			nums[cur] = i;
			perm(cur + 1);
		}
	}

	private static void drop(int[] nums, int[][] newMap) {
		for (int idx = 0; idx < nums.length; idx++) {
			int c = nums[idx];
			for (int r = 0; r < H; r++) {
				//깨야하는 벽을 만났을 때
				if (newMap[r][c] > 0) {
					//연쇄작용 일어나지 않음
					if (newMap[r][c] == 1) {
						newMap[r][c] = 0;
						tempCount--;
						break;
					} else {
						newMap = boom(new Boom(r, c, newMap[r][c]), newMap);
						newMap = gravity(newMap);
						break;
					}
				}
			}
		}
	}
	static int[][] dArr = new int[][] {{-1,0}, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private static int[][] boom(Boom boom, int[][] boomMap) {
		Queue<Boom> bfsQueue = new ArrayDeque<>();
		bfsQueue.add(boom);		
		boomMap[boom.r][boom.c]= 0;
		tempCount--;
		
		while (!bfsQueue.isEmpty()) {
			Boom nextBoom = bfsQueue.poll();

			int r = nextBoom.r;
			int c = nextBoom.c;
			int power = nextBoom.power;			

			for (int i = 0; i < 4; i++) {
				for (int a = 1; a < power; a++) {
					int dr = r + (dArr[i][0] * a);
					int dc = c + (dArr[i][1] * a);

					if (isIn(dr, dc)) {
						if (boomMap[dr][dc] > 0) {
							if (boomMap[dr][dc] == 1) {
								boomMap[dr][dc] = 0;
								tempCount--;
								
							} else {
								bfsQueue.add(new Boom(dr, dc, boomMap[dr][dc]));
								boomMap[dr][dc] = 0;
								tempCount--;
							}
						}
					}
				}
			}
		}
		return boomMap;
	}
	private static int[][] gravity(int[][] newMap) {
		Deque<Integer> queue = new ArrayDeque<>();
		for(int c = 0; c < W; c++) {
			for(int r = 0; r < H; r++) {
				if(newMap[r][c] > 0) {					
					queue.offer(newMap[r][c]);					
				}
				newMap[r][c] = 0;
			}
			
			int r = H-1;
			while(!queue.isEmpty()) {
				newMap[r--][c] = queue.pollLast();
			}
		}
		return newMap;
	}
	
	private static boolean isIn(int dr, int dc) {
		return dr >= 0 && dc >= 0 && dr < H && dc < W;
	}

	private static int[][] copy(int[][] map) {
		int[][] newMap = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
		return newMap;
	}
	
	private static void print(int[][] map2) {
		for (int[] arr : map2) {
			System.out.println(Arrays.toString(arr));
		}
	}	

}
