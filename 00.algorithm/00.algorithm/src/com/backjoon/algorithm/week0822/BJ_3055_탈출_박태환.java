package com.backjoon.algorithm.week0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3055_탈출_박태환 {
	static class Water {
		int r, c;

		public Water(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static class S {
		int r, c, count;

		public S(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}
	}

	static int r, c;
	static char[][] map;
	static Queue<Water> waterQueue = new LinkedList<>();

	static S s;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];

		for (int a = 0; a < r; a++) {
			String inputStr = in.readLine();
			for (int b = 0; b < c; b++) {
				map[a][b] = inputStr.charAt(b);
				//물 위치 저장
				if (inputStr.charAt(b) == '*') {
					waterQueue.offer(new Water(a, b));
				}
				//현재 고슴도치 위치 저장
				if (inputStr.charAt(b) == 'S') {
					s = new S(a, b, 0);
				}
			}
		}

		int result = 0;
		result = bfs();
		
		if(result == -1) {
			System.out.println("KAKTUS");
		}else {			
			System.out.println(result);
		}
	}

	private static int bfs() {
		final int[] dx = new int[] { 1, 0, -1, 0 };
		final int[] dy = new int[] { 0, -1, 0, 1 };

		Queue<S> sQueue = new LinkedList<>();
		boolean[][] isVisited = new boolean[r][c];
		isVisited[s.r][s.c] = true;

		sQueue.offer(s);

		while (!sQueue.isEmpty()) {
			//한 턴에 진행되는 물 확산 진행
			int size = waterQueue.size();
			while (size-- > 0) {
				Water tempWater = waterQueue.poll();
				int r = tempWater.r;
				int c = tempWater.c;

				for (int i = 0; i < 4; i++) {
					int goR = r + dx[i];
					int goC = c + dy[i];
					if (isIn(goR, goC)) {
						if (map[goR][goC] == '.' || map[goR][goC] == 'S') {
							map[goR][goC] = '*';
							waterQueue.add(new Water(goR, goC));
						}
					}
				}
			}

			//고슴도치가 갈 수 있는 경로 탐색 bfs
			size = sQueue.size();
			while (size-- > 0) {
				S tempS = sQueue.poll();
				
				int r = tempS.r;
				int c = tempS.c;
				
				int count = tempS.count;

				if (map[r][c] == 'D') {
					return count;
				}			

				for (int i = 0; i < 4; i++) {
					int goR = r + dx[i];
					int goC = c + dy[i];
					
					//방문하지않았고, 갈 수 있다면 ㄱㄱ
					if (isIn(goR, goC) && map[goR][goC] != '*' && !isVisited[goR][goC]) {
						// 도착 했을 때
						if (map[goR][goC] == 'D') {
							sQueue.offer(new S(goR, goC, count+1));
						}
						// 빈 곳인 경우
						if (map[goR][goC] == '.') {
							sQueue.offer(new S(goR, goC, count+1));
							isVisited[goR][goC] = true;
						}
					}
				}
			}
		}
		//도착하지 못하면
		return -1;
	}

	private static boolean isIn(int goR, int goC) {
		if (goR >= 0 && goR < r && goC >= 0 && goC < c) {
			return true;
		}
		return false;
	}

}
