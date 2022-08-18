package com.backjoon.algorithm.week0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3109_빵집_박태환 {
	static int R, C;
	static char[][] map;
	static boolean[][] isSelected;

	static int result = 0;

	// 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선
	static int[] dx = new int[] { -1, 0, 1 };
	static int[] dy = new int[] { 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		isSelected = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String inputStr = in.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = inputStr.charAt(j);
				if (map[i][j] == 'x') {
					isSelected[i][j] = true;
				}
			}
		}

		for (int r = 0; r < R; r++) {
			isSelected[r][0] = true;
			if(findEnd(r, 0)) result++;
		}
		System.out.println(result);
	}
	
	private static boolean findEnd(int r, int c) {
//		print(isSelected);		
		for (int dir = 0; dir < 3; dir++) {
			int goR = r + dx[dir];
			int goC = c + dy[dir];
			if (goR >= 0 && goR < R && goC >= 0 && goC < C) {
				if (!isSelected[goR][goC] && map[goR][goC] == '.') {
					isSelected[goR][goC] = true;
					// 종료됐을 때
					if (goC == C - 1) {						
						return true;
					}					
					//이동한 것들도 빠져나옴
					if(findEnd(goR, goC)){
						return true;
					}					
				} 
			}
		}
		//이동하지못하면
		return false;
	}
	
	private static void print(boolean[][] isSelected2) {
		for (int r = 0; r < isSelected2.length; r++) {
			for (int c = 0; c < isSelected2[r].length; c++) {
				if (isSelected2[r][c])
					System.out.print("1 ");
				else
					System.out.print("0 ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
