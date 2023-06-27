import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
	1 2 3 4 5 
	2 4 6 8 10
	3 6 9 12 15 
	4 8 12 16 20 
	5 10 15 20 25

 */
public class Main {
	static int N;
	static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		K = Integer.parseInt(in.readLine());

		int low = 1;
		int high = K;

		int result = 0;
		
		//이분탐색
		while (low <= high) {
			int mid = (low + high) / 2;

			int cnt = 0;

			//mid 보다 작거나 같은 수는 몇개인지
			for (int i = 1; i <= N; i++) {
				cnt += Math.min(mid / i, N);
			}
			if (cnt < K) {
				low = mid + 1;
			} else {
				result = mid;
				high = mid - 1;
			}
		}
		
		System.out.println(result);

	}

}
