import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int maxDist = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);

		int[] dis = new int[N + 1];
		int[][] dp = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			dis[i] = Integer.parseInt(in.readLine());
		}

		for (int i = 1; i <= N; i++) {
			//쉴 때
			dp[i][0] = dp[i - 1][0];

			//뛸 때
			for (int a = 1; a <= M; a++) {
				dp[i][a] = dp[i - 1][a - 1] + dis[i];
			}

			//지침지수가 0이 되기 전에는 다시 달릴 수가 없다.
			if (i > 1) {
				for(int a = 1; a <= M && a <= i; a++) {					
					dp[i][0] = Math.max(dp[i][0], dp[i-a][a]);
				}
			}
		}
		
		System.out.println(dp[N][0]);

	}
}
