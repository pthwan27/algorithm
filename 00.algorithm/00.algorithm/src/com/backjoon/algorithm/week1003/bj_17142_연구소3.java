package com.backjoon.algorithm.week1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_17142_연구소3 {
	static class Virus {
		int r, c, count;

		public Virus(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}
	}

	static int[][] map;
	static ArrayList<Virus> virusList;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		
		virusList = new ArrayList<>();
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(in.readLine());
			for (int c = 0; c < N; c++) {
				int nextNum =Integer.parseInt(st.nextToken()); 
				if(nextNum == 2) {
					Virus virus = new Virus(r, c, 0);
					virusList.add(virus);
				}
				map[r][c] = nextNum;
			}
		}
		
		int virusSize = virusList.size();
		
		
	}
}
