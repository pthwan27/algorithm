package com.backjoon.newAlgo.In_202308;

import java.io.*;
import java.util.*;

public class bj_1260_dfs와bfs {

	static int N, M, V;
	
	static ArrayList<Integer>[] map;
	
	static String dfsAnswer = "";
	static String bfsAnswer = "";
	
	static boolean[] dfsSelected;
	static boolean[] bfsSelected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputs = in.readLine().split(" ");
		
		N = Integer.parseInt(inputs[0]);		
		M = Integer.parseInt(inputs[1]);
		V = Integer.parseInt(inputs[2]);

		map = new ArrayList[N+1];
		
		for(int i = 0; i <= N; i++) {
			map[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			inputs = in.readLine().split(" ");
			
			int s = Integer.parseInt(inputs[0]);
			int e = Integer.parseInt(inputs[1]);
			
			map[s].add(e);
			map[e].add(s);
		}
		
		dfsSelected = new boolean[N+1];
		bfsSelected = new boolean[N+1];
		
		dfsSelected[V] = true;
		bfsSelected[V] = true;
		
		dfsAnswer += V;
		bfsAnswer += V;
		
		dfs(V);
		bfs(V);
		
		System.out.println(dfsAnswer + "\n" + bfsAnswer);
	}
	
	public static void dfs(int start) {
		
		for(int i = 0; i < map[start].size(); i++) {
			int cur = map[start].get(i);
			if(!dfsSelected[cur]) {
				dfsAnswer += cur+" ";
				dfs(cur);			
			}
		}		
	}
	
	public static void bfs(int start) {
		
	}

}
