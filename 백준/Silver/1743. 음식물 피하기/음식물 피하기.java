import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	static int[][] map;
	static boolean[][] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		K = Integer.parseInt(inputs[2]);

		map = new int[N + 1][M + 1];
		isSelected = new boolean[N + 1][M + 1];

		for (int i = 0; i < K; i++) {
			inputs = in.readLine().split(" ");
			int r = Integer.parseInt(inputs[0]);
			int c = Integer.parseInt(inputs[1]);

			map[r][c] = 1;
		}
		int maxCount = Integer.MIN_VALUE;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= M; c++) {
				if (map[r][c] == 1 && !isSelected[r][c]) {
					isSelected[r][c] = true;
					maxCount = Math.max(maxCount, bfs(r, c));
				}
			}
		}

		System.out.println(maxCount);
	}

	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, -1, 0, 1 };

	public static int bfs(int r, int c) {
		Queue<int[]> bfsQ = new LinkedList<>();

		bfsQ.offer(new int[] { r, c });

		int count = 1;

		while (!bfsQ.isEmpty()) {
			int[] cur = bfsQ.poll();

			for (int i = 0; i < 4; i++) {
				int nextR = cur[0] + dr[i];
				int nextC = cur[1] + dc[i];

				if (isIn(nextR, nextC) && !isSelected[nextR][nextC]) {
					count++;
					isSelected[nextR][nextC] = true;

					bfsQ.offer(new int[] { nextR, nextC });
				}
			}
		}
		return count;
	}

	public static boolean isIn(int r, int c) {
		return r >= 1 && r <= N && c >= 1 && c <= M && map[r][c] == 1;
	}

}
