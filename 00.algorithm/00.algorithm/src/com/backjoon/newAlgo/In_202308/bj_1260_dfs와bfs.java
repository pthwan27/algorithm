package com.backjoon.newAlgo.In_202308;

import java.io.*;
import java.util.*;

public class bj_1260_dfs와bfs {

	static int N, M, V;

	static ArrayList<Integer>[] map;

	static boolean[] isSelected;
	
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");
		
		sb = new StringBuilder();
		
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		V = Integer.parseInt(inputs[2]);

		map = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			map[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			inputs = in.readLine().split(" ");

			int s = Integer.parseInt(inputs[0]);
			int e = Integer.parseInt(inputs[1]);

			map[s].add(e);
			map[e].add(s);
		}
		for(int i = 0; i <= N; i++) {
			Collections.sort(map[i]);
		}

		isSelected = new boolean[N + 1];
		dfs(V);
		sb.append("\n");
		

		isSelected = new boolean[N + 1];
		bfs(V);

		System.out.println(sb.toString());
	}

	public static void dfs(int start) {
		isSelected[start] = true;
		sb.append(start + " ");		
		
		for (int i = 0; i < map[start].size(); i++) {
			int cur = map[start].get(i);
			if (!isSelected[cur]) {
				dfs(cur);
			}
		}
	}

	public static void bfs(int start) {
		Queue<Integer> bfsQ = new LinkedList<>();
		
		bfsQ.offer(start);
		
		isSelected[start] = true;
		
		while(!bfsQ.isEmpty()) {
			int cur = bfsQ.poll();
			sb.append(cur + " ");		
			
			for(int i = 0; i < map[cur].size(); i++) {
				if(!isSelected[map[cur].get(i)]) {
					isSelected[map[cur].get(i)] = true;
					bfsQ.offer(map[cur].get(i));
				}
			}
		}
	}

}
