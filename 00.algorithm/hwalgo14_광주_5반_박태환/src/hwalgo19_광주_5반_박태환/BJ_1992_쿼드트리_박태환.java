package hwalgo19_광주_5반_박태환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1992_쿼드트리_박태환 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());

		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			String temp = in.readLine();
			for (int c = 0; c < N; c++) {
				map[r][c] = temp.charAt(c) - '0';
			}
		}
		divMap(0, 0, N);
		System.out.println(sb);
	}

	private static void divMap(int x, int y, int size) {
		if (isPossible(x, y, size)) {
			sb.append(map[x][y]);
			return;
		}

		int divSize = size / 2;

		sb.append("(");
		// 왼쪽위 -> 오른쪽 위 -> 왼쪽 아래 -> 오른쪽 아래 순서로
		divMap(x, y, divSize);
		divMap(x, y + divSize, divSize);
		divMap(x + divSize, y, divSize);
		divMap(x + divSize, y + divSize, divSize);

		sb.append(")");

	}

	// 압축가능한지 확인하는 함수
	private static boolean isPossible(int x, int y, int size) {
		int start = map[x][y];
		
		// 시작값과 이후 값들 비교
		for (int r = x; r < x+size; r++) {
			for (int c = y; c < y+size; c++) {
				if (start != map[r][c])
					return false;
			}
		}
		return true;
	}

}
