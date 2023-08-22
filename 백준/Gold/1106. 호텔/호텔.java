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
		for (int a = 0; a < N; a++) {
			inputs = in.readLine().split(" ");

			int cost = Integer.parseInt(inputs[0]);
			int people = Integer.parseInt(inputs[1]);

			for (int b = people; b < 100 + C; b++) {
				if (dp[b - people] != Integer.MAX_VALUE) {
					int prev = dp[b - people];
					dp[b] = Math.min(dp[b], cost + prev);
				}
			}
		}

		int minResult = Integer.MAX_VALUE;

		for (int i = C; i < 100 + C; i++) {
			minResult = Math.min(minResult, dp[i]);
		}
		
		System.out.println(minResult);
	}
}
