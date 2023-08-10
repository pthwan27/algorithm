import java.io.*;
import java.util.*;

public class Main {
	static int N;
	
	static int[][] map;
	static boolean[][] isSelected;

	static ArrayList<Integer> answerList;
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(in.readLine());
		
		answerList = new ArrayList<>();
		
		map = new int[N][N];
		isSelected = new boolean[N][N];
		
		for(int r = 0; r < N; r++) {
			String inputStr = in.readLine();
			for(int c = 0; c < N; c++) {
				map[r][c] = inputStr.charAt(c) - '0';
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] == 1 && !isSelected[r][c]) {
					isSelected[r][c] = true;
					answerList.add(bfs(r, c));			
				}
			}
		}
		
		Collections.sort(answerList);
		sb.append(answerList.size()).append("\n");
		
		for(int i = 0; i< answerList.size(); i++) {
			sb.append(answerList.get(i)).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static int[] dr = new int[] {-1,0,1,0};
	static int[] dc = new int[] {0,-1,0,1};
	public static int bfs(int r, int c) {
		Queue<int[]> bfsQ = new LinkedList<>();
		int count = 1;
		
		bfsQ.offer(new int[] {r,c});
	
		while(!bfsQ.isEmpty()) {
			int[] cur = bfsQ.poll();
			for(int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if(isIn(nr, nc) && !isSelected[nr][nc]) {
					isSelected[nr][nc] = true;
					bfsQ.offer(new int[] {nr, nc});
					count++;
				}
			}
		}
		
		return count;
	
	}
	public static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 1;
	}

}
