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

	static char[][] map;
	static ArrayList<Node> tNode;

	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, -1, 0, 1 };

	static boolean isPossible;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());

		map = new char[N][N];
		tNode = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			String[] inputs = in.readLine().split(" ");
			for (int c = 0; c < N; c++) {
				char cur = inputs[c].charAt(0);
				map[r][c] = cur;

				if (cur == 'T') {
					tNode.add(new Node(r, c, map[r][c]));
				}
			}
		}

		// 선생님 바로 옆칸에 학생이 있으면 검사를 절대 못피함.
		for (int i = 0; i < tNode.size(); i++) {
			int r = tNode.get(i).r;
			int c = tNode.get(i).c;

			for (int a = 0; a < 4; a++) {
				int nr = r + dr[a];
				int nc = c + dc[a];

				if (isIn(nr, nc) && map[nr][nc] == 'S') {
					System.out.println("NO");
					return;
				}
			}
		}

		isPossible = false;
		dfs(0,0, 0);

		System.out.println(isPossible ? "YES" : "NO");
	}

	public static void dfs(int dr, int dc, int cnt) {
		if (isPossible)
			return;

		if (cnt == 3) {
			// 여기서 선생님들 감시망에 있는지 확인
			if (check()) {
				isPossible = true;
			}
			return;
		}

		 for (int r = dr; r < N; r++) {
		        for (int c = (r == dr ? dc : 0); c < N; c++) {
		            if (map[r][c] == 'X') {
		                map[r][c] = 'O';
		                dfs(r, (c + 1 == N) ? 0 : c + 1, cnt + 1);  // 다음 칸이나 다음 행으로 이동
		                map[r][c] = 'X';
		            }
			}
		}
	}

	private static boolean check() {
		for (int i = 0; i < tNode.size(); i++) {
			Node cur = tNode.get(i);

			for (int a = 0; a < 4; a++) {
				int nextR = cur.r;
				int nextC = cur.c;

				switch (a) {
				case 0:
					nextR = cur.r + dr[a];
					nextC = cur.c;
					while (isIn(nextR, nextC)) {
						if (map[nextR][nextC] == 'S') {
							return false;
						} else if (map[nextR][nextC] == 'O' || map[nextR][nextC] == 'T') {
							break;
						} else {
							nextR += dr[a];
						}
					}
					break;
				case 1:
					nextR = cur.r;
					nextC = cur.c + dc[a];
					while (isIn(nextR, nextC)) {
						if (map[nextR][nextC] == 'S') {
							return false;
						} else if (map[nextR][nextC] == 'O' || map[nextR][nextC] == 'T') {
							break;
						} else {
							nextC += dc[a];
						}
					}
					break;
				case 2:
					nextR = cur.r + dr[a];
					nextC = cur.c;

					while (isIn(nextR, nextC)) {
						if (map[nextR][nextC] == 'S') {
							return false;
						} else if (map[nextR][nextC] == 'O' || map[nextR][nextC] == 'T') {
							break;
						} else {
							nextR += dr[a];
						}
					}
					break;
				case 3:
					nextR = cur.r;
					nextC = cur.c + dc[a];

					while (isIn(nextR, nextC)) {
						if (map[nextR][nextC] == 'S') {
							return false;
						} else if (map[nextR][nextC] == 'O' || map[nextR][nextC] == 'T') {
							break;
						} else {
							nextC += dc[a];
						}
					}
					break;
				}

			}
		}
		return true;
	}

	public static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

}
