
import java.io.*;
import java.util.*;

public class Main {
	static List<Node>[] list;
	static int[] dist;
	static int n, m, k, x;

	static class Node implements Comparable<Node> {
		int num;
		int weight;

		public Node(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node node) {
			return weight - node.weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());

		//최단거리 저장
		dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		list = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(in.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			list[start].add(new Node(end, 1));
		}

		dijkstra(x);
		
		for(int i = 1; i < dist.length; i++) {
			if(dist[i] == k) {
				sb.append(i).append("\n");
			}
		}
		if(sb.length() == 0) {
			sb.append(-1);
		}
		
		System.out.println(sb);
	}

	private static void dijkstra(int startNum) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		boolean isVisited[] = new boolean[n+1];
		dist[startNum] = 0;
		
		queue.offer(new Node(startNum, 0));
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int num = node.num;
			
			if(isVisited[num]) continue;
			
			isVisited[num] = true;
			
			for(Node n : list[num]) {
				if(!isVisited[n.num] && dist[n.num] > (n.weight + dist[num]) ) {
					dist[n.num]= n.weight + dist[num];
					queue.offer(new Node(n.num, dist[n.num]));
				}
			}
		}
	}
}
