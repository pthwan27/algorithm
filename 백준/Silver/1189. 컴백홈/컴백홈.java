import java.util.*;
import java.io.*;

public class Main {
	
	static int R, C, K;
	
	static char[][] map;
	static boolean[][] isSelected;
	
	static int result = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputs = in.readLine().split(" ");
		
		R = Integer.parseInt(inputs[0]);
		C = Integer.parseInt(inputs[1]);
		K = Integer.parseInt(inputs[2]);
		
		map = new char[R][C];
		isSelected = new boolean[R][C];
		
		for(int r = 0; r < R; r++) {
			String inputStr = in.readLine();
			for(int c = 0; c < C; c++) {
				map[r][c] = inputStr.charAt(c);
			}
		}
		isSelected[R-1][0] = true;
		dfs(1, R-1, 0);
		
		System.out.println(result);
	}
	
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, -1, 0, 1};
	
	private static void dfs(int cnt, int r, int c) {
		if(r == 0 && c == C-1 && cnt == K) {
			result++;
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nextR = r + dr[i];
			int nextC = c + dc[i];
			
			if(isIn(nextR, nextC) && !isSelected[nextR][nextC]) {
				isSelected[nextR][nextC] = true;
				dfs(cnt + 1, nextR, nextC);
				
				isSelected[nextR][nextC] = false;
			}
		}
	}
	
	public static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != 'T';
	}
}
