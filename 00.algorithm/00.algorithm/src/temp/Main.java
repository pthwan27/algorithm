package temp;

import java.util.*;
import java.io.*;

public class Main {
	static ArrayList<Integer>[] map;
	static boolean[] isSelected;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		int M = Integer.parseInt(in.readLine());
		
		
		map = new ArrayList[N+1];
		isSelected = new boolean[N+1];
		
		for(int i = 0; i <= N; i++) {
			map[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			String[] inputs = in.readLine().split(" ");
			
			int start = Integer.parseInt(inputs[0]);
			int end = Integer.parseInt(inputs[1]);
			
			map[start].add(end);
			map[end].add(start);
		}
		
		int result = bfs(0);
		
		System.out.println(result);
	}	
	
	public static int bfs(int count) {
		Queue<Integer> bfsQ = new LinkedList<>();
		
		bfsQ.offer(1);
		isSelected[1] = true;
		
		while(!bfsQ.isEmpty()) {
			int cur = bfsQ.poll();
			
			for(int i = 0; i < map[cur].size(); i++) {
				int next = map[cur].get(i);
				if(!isSelected[next]) {
					count++;
					isSelected[next] = true;
					bfsQ.offer(next);
				}
			}
		}
		
		return count;
		
	}
}