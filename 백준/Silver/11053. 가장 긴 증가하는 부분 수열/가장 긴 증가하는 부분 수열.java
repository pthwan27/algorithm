
import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int[] inputArr;
	
	static int[] dp;
	
	static int MaxLen = Integer.MIN_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());

		inputArr = new int[N];
		
		//각 위치의 가장 긴 증가하는 부분수열 갯수 저장
		dp = new int[N];
		for (int i = 0; i < N; i++) {
			inputArr[i] = Integer.parseInt(st.nextToken());
			dp[i] = -1;
		}
		
		
		for(int i = 0; i < N; i++) {
			if(dp[i] == -1 ) {
				dp[i] = 1;
			}
			
			for(int j = i; j > 0; j--) {
				if(inputArr[j-1] < inputArr[i]) {
					dp[i] = Math.max(dp[j-1] + 1, dp[i]);
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			MaxLen = Math.max(dp[i], MaxLen);
		}
		
		System.out.println(MaxLen);

	}
}