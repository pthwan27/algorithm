import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            dp[i][0] = Integer.parseInt(st.nextToken()); // 무게
            dp[i][1] = Integer.parseInt(st.nextToken()); // 비용
            // dp[i][2] = dp[i][0]; // 누적 무게
        }
        // 2중 배열 정렬
        Arrays.sort(dp, (o1, o2) -> o1[1] == o2[1]? o2[0]-o1[0] : o1[1]-o2[1]);
        // System.out.println(Arrays.deepToString(dp));
        int minCost = Integer.MAX_VALUE;
        int totalVolume = dp[0][0];
        int totalCost = dp[0][1];
        if (dp[0][0] >= M) {
            minCost = Math.min(minCost, dp[0][1]);
        }
        for (int i = 1; i < N; i++) {
            totalVolume += dp[i][0];
            if (dp[i-1][1] != dp[i][1]) {
                totalCost = dp[i][1];
            } else {
                totalCost += dp[i][1];
            }
            if (totalVolume >= M) {
                minCost = Math.min(minCost, totalCost);
            }
        }
        if (totalVolume < M) {
            System.out.println(-1);
        } else {
            System.out.println(minCost);
        }
    }
}