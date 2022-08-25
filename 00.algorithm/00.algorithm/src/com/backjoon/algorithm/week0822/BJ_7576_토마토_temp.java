package com.backjoon.algorithm.week0822;

/*
창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다. 
보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다. 
하나의 토마토의 인접한 곳은 왼쪽, 오른쪽, 앞, 뒤 네 방향에 있는 토마토를 의미한다. 
*/

/* 
1주변에 사방으로 확장되듯이 0을 탐색하는 알고리즘 -> BFS
sol)
입력 - 토마토맵 초기화
토마토 위치를 Queue에 넣어서 하나씩 빼면서 전체 영역을 모두 탐색할 때까지 진행
최소 몇 번만에 완료되었는지 출력
토마토 맵에 0이 있다면 모든 탐색이 되지 않았으므로 -1 출력
*/
//

import java.io.*;
import java.util.*;

public class BJ_7576_토마토_temp {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력단
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		int col = Integer.parseInt(stk.nextToken());
		int row = Integer.parseInt(stk.nextToken());
		int[][] tomatoMap = new int[row][col];

		// 맵초기화
		for (int i = 0; i < row; i++) {
			tomatoMap[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		// 토마토 위치 특정
		Queue<int[]> que = new ArrayDeque<int[]>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (tomatoMap[i][j] == 1) {
					que.add(new int[] { j, i, 0 }); // y,x point
				}
			}
		}
		// 특정 위치로부터 bfs 진행
		boolean[][] visited = new boolean[row][col]; // 방문 여부를 검사할 배열
		int[][] delta = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } };
		int[] p = new int[3];

		int minRank = 0;
		int rank = 0;
		int x, y, dx, dy;
		while (!que.isEmpty()) {
			p = que.poll();
			x = p[0];
			y = p[1];
			rank = p[2] + 1;
			
			for (int i = 0; i < 4; i++) {
				dx = x + delta[i][0];
				dy = y + delta[i][1];
				if ((dx >= 0 && dx < col) && (dy >= 0 && dy < row) && (tomatoMap[dy][dx] == 0) && !visited[dy][dx]) {
					if (rank > minRank)
						minRank = rank;
					visited[dy][dx] = true;
					tomatoMap[dy][dx] = 1; // 익음
					que.add(new int[] { dx, dy, rank});
				}
			}
		}
		// 토마토 예외처리
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (tomatoMap[i][j] == 0)
					minRank = -1;
			}
		}
		System.out.print(minRank);
	}
}