package hwalgo19_광주_5반_박태환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10971_외판원순회2 {
	static boolean[] isVisited;
	static int N;
	static int[][] map;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		map = new int[N+1][N+1];

		for (int r = 1; r < N+1; r++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int c = 1; c < N+1; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		//모든 방문했는지 확인하기 위해
		isVisited = new boolean[N+1];
		for (int i = 1; i < N+1; i++) {
			find(i, i, 0, 0);
		}

		System.out.println(min);
	}

	private static void find(int start, int now, int count, int sum) {
		//시작점으로 돌아오고, 모든 도시를 방문했을 때 return;
		if (count == N  && start == now) {
			min = Math.min(min, sum);
			return;
		}

		for (int i = 1; i < N+1; i++) {
			if (map[now][i] == 0)
				continue;

			if (!isVisited[i] && map[now][i] > 0) {
				isVisited[i] = true;
				sum += map[now][i];
				// 연결되어 있는 곳으로;
				find(start, i, count + 1, sum);
				//백트래킹
				sum -= map[now][i];
				isVisited[i] = false;
			}
		}

	}

}
