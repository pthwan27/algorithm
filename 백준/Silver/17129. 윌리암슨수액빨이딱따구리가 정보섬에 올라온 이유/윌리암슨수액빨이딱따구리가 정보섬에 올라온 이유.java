import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int n, m;
	static int[][] map;
	static int[][] isVisited;

	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, -1, 0, 1 };

	static Node startNode;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = in.readLine().split(" ");

		n = Integer.parseInt(inputs[0]);
		m = Integer.parseInt(inputs[1]);

		map = new int[n][m];
		isVisited = new int[n][m];

		startNode = new Node(0, 0);

		for (int r = 0; r < n; r++) {
			String inputStr = in.readLine();
			for (int c = 0; c < m; c++) {
				int info = inputStr.charAt(c) - '0';
				map[r][c] = info;
				if (info == 2) {
					startNode = new Node(r, c);
					isVisited[r][c] = 1;
				}
			}
		}

		int foodNum = bfs();
		if (foodNum == 0) {
			System.out.println("NIE");
		} else {
			System.out.println("TAK");
			System.out.println(foodNum);
		}
	}

	private static int bfs() {
		Queue<Node> bfsQ = new LinkedList<Node>();
		bfsQ.offer(startNode);
		while (!bfsQ.isEmpty()) {
			Node curNode = bfsQ.poll();
			int curR = curNode.r;
			int curC = curNode.c;

			for (int i = 0; i < 4; i++) {
				int nextR = curR + dr[i];
				int nextC = curC + dc[i];

				if (isOk(nextR, nextC) && isVisited[nextR][nextC] == 0) {
					if (map[nextR][nextC] != 0 && map[nextR][nextC] != 2) {
						return isVisited[curR][curC];
					}
					isVisited[nextR][nextC] = isVisited[curR][curC] + 1; 
					bfsQ.offer(new Node(nextR, nextC));
				}
			}
		}
		return 0;
	}

	private static boolean isOk(int nextR, int nextC) {
		return nextR >= 0 && nextR < n && nextC >= 0 && nextC < m && map[nextR][nextC] != 1;
	}
}