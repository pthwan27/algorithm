
import java.io.*;
import java.util.*;

public class Main {

	static int C, N;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		C = Integer.parseInt(inputs[0]);
		N = Integer.parseInt(inputs[1]);

		int[] dp = new int[100 + C];

		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int i = 0; i < N; i++) {
			inputs = in.readLine().split(" ");

			int cost = Integer.parseInt(inputs[0]);
			int custom = Integer.parseInt(inputs[1]);

			for (int a = custom; a < 100 + C; a++) {
				if (dp[a - custom] != Integer.MAX_VALUE) {
					int prev = dp[a - custom];

					dp[a] = Math.min(prev + cost, dp[a]);
				}
			}
		}
		
		int result = Integer.MAX_VALUE;
		
		for(int i = C; i < 100+C; i++) {
			result = Math.min(dp[i], result);			
		}
		
		System.out.println(result);
	}
}
