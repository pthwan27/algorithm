import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] inputArr;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());

		inputArr = new int[N + 1];
		dp = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			inputArr[i] = Integer.parseInt(in.readLine());
		}
		for (int i = 1; i <= N; i++) {
			if (i == 1) {
				dp[i] = inputArr[i];
			} else if (i == 2) {
				dp[i] = Math.max(inputArr[i - 2] + inputArr[i], inputArr[i - 1] + inputArr[i]);
			} else if (i == 3) {
				dp[i] = Math.max(dp[1] + inputArr[i], inputArr[i - 1] + inputArr[i]);
			} else {
				dp[i] = Math.max(dp[i - 2] + inputArr[i], dp[i - 3] + inputArr[i - 1] + inputArr[i]);
			}
		}
		System.out.println(dp[N]);
	}
}