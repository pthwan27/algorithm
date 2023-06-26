import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] inputArr;

	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		inputArr = new int[N];
		result = 0;

		if (N > 2) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int i = 0; i < N; i++) {
				inputArr[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(inputArr);

			for (int i = 0; i < N; i++) {
				int start = 0;
				int end = N - 1;

				while (true) {
					if(i == start) {
						start++;
					}
					else if(i == end) {
						end--;
					}
					
					if (start == end) {
						break;
					}

					if (inputArr[i] == inputArr[start] + inputArr[end]) {
						result++;
						break;
					} else if (inputArr[i] < inputArr[start] + inputArr[end]) {
						end--;
					} else if (inputArr[i] > inputArr[start] + inputArr[end]) {
						start++;
					}
				}
			}
		}

		System.out.println(result);
	}
}
