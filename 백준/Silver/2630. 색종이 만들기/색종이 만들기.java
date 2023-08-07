import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb;
	
	static int[][] map;
	
	static int wCnt, bCnt;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		sb = new StringBuilder();
		
		int n = Integer.parseInt(in.readLine());
		
		map = new int[n][n];
		for(int r = 0; r < n; r++) {
			st = new StringTokenizer(in.readLine());
			for(int c = 0; c < n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		wCnt = 0;
		bCnt = 0;
		divMap(0, 0, n);
		
		System.out.println(wCnt + "\n" + bCnt);
	}

	private static void divMap(int r, int c, int n) {
		if(isPossible(r,c,n)) {
			if(map[r][c] == 1) {
				bCnt++;
			}else {
				wCnt++;
			}
			return;
		}
		int size = n/2;
		
		divMap(r, c, size);
		divMap(r, c + size , size);
		divMap(r + size, c , size);
		divMap(r + size, c + size , size);
	}

	private static boolean isPossible(int r, int c, int size) {
		int start = map[r][c];
		
		for(int x = r; x < r + size; x++) {
			for(int y = c; y < c + size; y++) {
				if(map[x][y] != start) {
					return false;
				}
			}
		}
		return true;
	}
}
