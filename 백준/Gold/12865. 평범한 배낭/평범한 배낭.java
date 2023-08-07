import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int K;
	static int[][] dp;
	static int[] w;
	static int[] v;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp =new int[N+1][K+1];
		
		w = new int[N+1];
		v = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c<= K; c++) {
				dp[r][c] = dp[r-1][c];
				
				if(c-w[r] >= 0) {
					dp[r][c] = Math.max(dp[r-1][c-w[r]]+v[r], dp[r-1][c]);
				}
				
			}
		}
		
		System.out.println(dp[N][K]);		
	}
}