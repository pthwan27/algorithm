package com.backjoon.algorithm.week1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14502_연구소_박태환 {
	//0과 2 (공간, 바이러스 클래스)
	static class Space {
		int r, c;

		public Space(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static class Virus {
		int r, c;

		public Virus(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	// 가로 ,세로, 결과
	static int N, M, maxResult;
	static int[][] map;

	// 공간 담아둔 list ( 여기서 3개 뽑음 )
	static ArrayList<Space> spaceList;
	
	// 총 공간에서 3개를 뽑기위해 
	static int spaceSize;
	
	// bfs에서 사용할 것
	static ArrayList<Virus> virusList;
	
	// 조합 3개 담을 배열
	static int[] output;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		spaceList = new ArrayList<>();
		virusList = new ArrayList<>();

		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < M; c++) {
				int nextNum = Integer.parseInt(st.nextToken());

				if (nextNum == 0) {
					spaceList.add(new Space(r, c));
				} else if (nextNum == 2) {
					virusList.add(new Virus(r, c));
				}

				map[r][c] = nextNum;
			}
		}
		//입력끝
		
		spaceSize = spaceList.size();
		output = new int[3];

		maxResult = Integer.MIN_VALUE;
		makeWall(0, 0);
		//로직 끝

		System.out.println(maxResult);
	}

	//벽 세울 곳 찾는 함수
	private static void makeWall(int cnt, int start) {
		if (cnt == 3) {
			int[][] newMap = copy();
			
			maxResult = Math.max(maxResult, bfs(output, newMap));
			return;
		}

		for (int i = start; i < spaceSize; i++) {
			output[cnt] = i;
			makeWall(cnt + 1, i + 1);
		}
	}

	static int[] dx = new int[] { -1, 0, 1, 0 };
	static int[] dy = new int[] { 0, -1, 0, 1 };

	//BFS
	private static int bfs(int[] output, int[][] newMap) {
		//map 을 1 로 바꾸기 ( 새로 생기는 벽 )
		for (int i = 0; i < output.length; i++) {
			int r = spaceList.get(output[i]).r;
			int c = spaceList.get(output[i]).c;

			newMap[r][c] = 1;
		}
		
		
		
		Queue<Virus> queue = new LinkedList<Virus>();

		for (int i = 0; i < virusList.size(); i++) {
			queue.offer(virusList.get(i));
		}

		while (!queue.isEmpty()) {
			Virus virus = queue.poll();
			int r = virus.r;
			int c = virus.c;

			for (int i = 0; i < 4; i++) {
				int dr = r + dx[i];
				int dc = c + dy[i];

				if (dr >= 0 && dc >= 0 && dr < N && dc < M && newMap[dr][dc] == 0) {
					newMap[dr][dc] = 2;
					queue.offer(new Virus(dr, dc));
				}
			}

		}

		// 공간 갯수 반환
		return Counting(newMap);
	}

	private static int[][] copy() {
		int[][] newMap = new int[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				newMap[r][c] = map[r][c];
			}
		}
		return newMap;
	}

	private static int Counting(int[][] newMap) {
		int count = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (newMap[r][c] == 0) {
					count++;
				}

			}
		}
		return count;
	}
}
