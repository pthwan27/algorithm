import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());
		int[] dp = new int[N + 2];
		Arrays.fill(dp, 0);

		int[][] taskArr = new int[N + 2][2];

		for (int i = 1; i <= N; i++) {
			String[] inputTask = in.readLine().split(" ");

			taskArr[i] = new int[] { Integer.parseInt(inputTask[0]), Integer.parseInt(inputTask[1]) };
		}

		int maxBenefit = -1;

		for (int i = 1; i <= N; i++) {
			int nextDay = i + taskArr[i][0];

			if (nextDay <= N + 1) {
				dp[nextDay] = Math.max(taskArr[i][1] + dp[i], dp[nextDay]);
			}
			dp[i + 1] = Math.max(dp[i], dp[i + 1]);
		}
		System.out.println(dp[N + 1]);
	}
}
