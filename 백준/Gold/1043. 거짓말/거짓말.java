import java.util.*;
import java.io.*;

public class Main {

	static int N, M;

	static int[] parent;

	static ArrayList<Integer> tList;
	static ArrayList<Integer>[] partyList;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		inputs = in.readLine().split(" ");
		tList = new ArrayList<>();

		for (int i = 0; i < Integer.parseInt(inputs[0]); i++) {
			tList.add(Integer.parseInt(inputs[i + 1]));
		}

		if (tList.size() == 0) {
			System.out.println(M);
			return;
		}

		parent = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			parent[i] = i;
		}

		partyList = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			partyList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			inputs = in.readLine().split(" ");
			int num = Integer.parseInt(inputs[0]);

			int x = Integer.parseInt(inputs[1]);
			partyList[i].add(x);

			for (int a = 1; a < num; a++) {
				int y = Integer.parseInt(inputs[a + 1]);
				union(x, y);
				partyList[i].add(y);
			}
		}

		int cnt = 0;

		for (int i = 0; i < M; i++) {
			boolean flag = true;
			for (int num : partyList[i]) {
				if (tList.contains(find(parent[num]))) {
					flag = false;
					break;
				}
			}
			if (flag) {
				cnt++;
			}

		}
		System.out.println(cnt);
	}

	static int find(int idx) {
		if (parent[idx] == idx) {
			return idx;
		}
		return find(parent[idx]);
	}

	static void union(int x, int y) {
		int root_x = find(x);
		int root_y = find(y);
		if (tList.contains(root_y)) {
			int tmp = root_x;
			root_x = root_y;
			root_y = tmp;
		}
		parent[root_y] = root_x;
	}

}
