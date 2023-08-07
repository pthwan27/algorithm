import java.io.*;
import java.util.*;

public class Main {
	static int n, q;

	static ArrayList<int[]>[] graph;
	
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(in.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			graph[start].add(new int[] { end, weight });
			graph[end].add(new int[] { start, weight });
		}

		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(in.readLine());

			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			bfs(k, v);
		}
		
		System.out.println(sb);
	}

	private static void bfs(int k, int v) {
		Queue<Integer> queue = new LinkedList<>();
		int cnt = 0;
		boolean[] visit = new boolean[n + 1];
		visit[v] = true;

		queue.offer(v);

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int[] next : graph[cur]) {
				// 연결되어있는 곳이 체크한적 없고 USADO가 k이상일 때
				if(!visit[next[0]] && next[1] >= k) {
					queue.offer(next[0]);
					visit[next[0]] = true;
					cnt++;
				}
			}
		}
		sb.append(cnt).append("\n");		
	}
}