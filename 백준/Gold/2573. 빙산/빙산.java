import java.io.*;
import java.util.*;

public class Main {
	static class ice {
		int r, c, h;

		public ice(int r, int c, int h) {
			this.r = r;
			this.c = c;
			this.h = h;
		}
	}

	static int N, M;
	static int[][] map;

	static ArrayList<ice> iceList;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		map = new int[N][M];

		iceList = new ArrayList<>();

		for (int r = 0; r < N; r++) {
			inputs = in.readLine().split(" ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(inputs[c]);

				if (map[r][c] > 0) {
					iceList.add(new ice(r, c, map[r][c]));
				}
			}
		}

		int iceCount = 0;
		int yearCount = 0;

		while (iceCount < 2) {
			if (iceList.size() == 0) {
				System.out.println(0);
				return;
			}

			map = melting();
			yearCount++;

			iceCount = countingArea();
		}

		System.out.println(yearCount);
	}

	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, 1, 0, -1 };

	static boolean[][] isSelected;

	public static int countingArea() {
		isSelected = new boolean[N][M];
		int count = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] > 0 && !isSelected[r][c]) {
					isSelected[r][c] = true;
					bfs(r, c);
					count++;
				}
			}
		}

		return count;
	}

	private static void bfs(int r, int c) {
		Queue<int[]> bfsQ = new LinkedList<>();

		bfsQ.offer(new int[] { r, c });

		while (!bfsQ.isEmpty()) {
			int[] cur = bfsQ.poll();

			for (int i = 0; i < 4; i++) {
				int nextR = cur[0] + dr[i];
				int nextC = cur[1] + dc[i];

				if (isIn(nextR, nextC) && !isSelected[nextR][nextC] && map[nextR][nextC] > 0) {
					isSelected[nextR][nextC] = true;
					bfsQ.offer(new int[] { nextR, nextC });
				}
			}
		}
	}

	public static int[][] melting() {
		int[][] newMap = copyMap(map);

		for (int i = 0; i < iceList.size(); i++) {
			ice cur = iceList.get(i);

			for (int a = 0; a < 4; a++) {
				int nextR = cur.r + dr[a];
				int nextC = cur.c + dc[a];

				if (isIn(nextR, nextC)) {
					if (map[nextR][nextC] == 0 && newMap[cur.r][cur.c] > 0) {
						newMap[cur.r][cur.c]--;

						if (newMap[cur.r][cur.c] == 0) {
							iceList.set(i, new ice(cur.r, cur.c, 0));
						} else {
							iceList.set(i, new ice(cur.r, cur.c, newMap[cur.r][cur.c]));
						}

					}
				}
			}
		}

		int i = 0;
		while (i < iceList.size()) {
			if (iceList.get(i).h == 0) {
				iceList.remove(i);
			} else {
				i++;
			}

		}

		return newMap;
	}

	public static boolean isIn(int nR, int nC) {
		return nR >= 0 && nR < N && nC >= 0 && nC < M;
	}

	public static int[][] copyMap(int[][] map) {
		int[][] newMap = new int[N][M];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				newMap[r][c] = map[r][c];
			}
		}

		return newMap;
	}
}
