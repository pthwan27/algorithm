package com.swea.algorithm.week0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_헌터_박태환 {
	static class Node {
		int r, c, num;

		public Node(int r, int c, int num) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}

	static ArrayList<Node> monsterlist;
	static ArrayList<Node> customerlist;

	static boolean[] isMonSelected;
	static boolean[] isCusSelected;

	static Node[] selectedList;

	static int N;
	static int[][] map;

	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(in.readLine());

			map = new int[N][N];

			monsterlist = new ArrayList<>();
			customerlist = new ArrayList<>();

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());

					if (map[r][c] > 0) {
						Node node = new Node(r, c, map[r][c]);
						monsterlist.add(node);
					}

					if (map[r][c] < 0) {
						Node node = new Node(r, c, Math.abs(map[r][c]));
						customerlist.add(node);
					}
				}
			}
			isMonSelected = new boolean[monsterlist.size() + 1];
			isCusSelected = new boolean[customerlist.size() + 1];

			selectedList = new Node[monsterlist.size() + customerlist.size() + 1];
			result = Integer.MAX_VALUE;
			dfs(0, 0, 0, 0);
			sb.append("#"+tc+" ").append(result).append("\n");
		}
		System.out.println(sb);

	}

	private static void dfs(int cur, int dis, int r, int c) {
		if (dis >= result) return;
		if (cur == customerlist.size() + monsterlist.size()) {
			result = Math.min(result, dis);
			return;
		}

		for (Node monster : monsterlist) {
			int d = getDistance(r, c, monster.r, monster.c);
			
			//이미 선택된 몬스터라면 pass
			if(isMonSelected[monster.num]) continue;
			
			isMonSelected[monster.num] = true;
			dfs(cur+1, dis + d, monster.r, monster.c);
			
			isMonSelected[monster.num] = false;

		}

		for (Node customer : customerlist) {
			int d = getDistance(r, c, customer.r, customer.c);
			
			if(isCusSelected[customer.num] || !isMonSelected[customer.num]) continue;
			
			isCusSelected[customer.num] = true;
			dfs(cur+1, dis + d, customer.r, customer.c);
			
			isCusSelected[customer.num] = false;
		}
	}

	static int getDistance(int r, int c, int r2, int c2) {
		return Math.abs(r-r2) + Math.abs(c-c2);		
	}
}
