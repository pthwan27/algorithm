package com.backjoon.newAlgo.In_202308;

import java.io.*;
import java.util.*;

public class bj_18428_감시피하기 {

	static int N;

	static class Node {
		int r, c;
		char info;

		public Node(int r, int c, char info) {
			this.r = r;
			this.c = c;
			this.info = info;
		}

	}

	static Node[][] map;
	static ArrayList<Node> tNode;

	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, -1, 0, 1 };

	static boolean isPossible;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());

		map = new Node[N][N];
		tNode = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			String[] inputs = in.readLine().split(" ");
			for (int c = 0; c < N; c++) {
				char cur = inputs[c].charAt(0);
				map[r][c] = new Node(r, c, cur);
				
				if(cur == 'T') {
					tNode.add(map[r][c]);
				}
			}
		}
		
		// 선생님 바로 옆칸에 학생이 있으면 검사를 절대 못피함.
		for(int i = 0; i < tNode.size(); i++) {
			int r = tNode.get(i).r;
			int c = tNode.get(i).c;
			
			for(int a = 0; a < 4; a++) {
				int nr = r + dr[a];
				int nc = c + dc[a];
				
				if(isIn(nr,nc) && map[nr][nc].info == 'S') {
					System.out.println("NO");
					return;
				}
			}
		}
		
		dfs(0);
	}
	public static void dfs(int cnt) {
		if(cnt == 3) {
			//여기서 선생님들 감시망에 있는지 확인
		}
	}

	public static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

}
