import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = in.readLine().split(" ");

		int N = Integer.parseInt(inputs[0]);
		int K = Integer.parseInt(inputs[1]);

		int[] studentArr = new int[N];
		int[] diffArr = new int[N - 1];

		inputs = in.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			studentArr[i] = Integer.parseInt(inputs[i]);
			if (i > 0) {
				diffArr[i - 1] = studentArr[i] - studentArr[i - 1];
			}
		}

		Arrays.sort(diffArr);

		int result = 0;
		for (int i = 0; i < (N - 1) - (K - 1); i++) {
			result += diffArr[i];
		}

		System.out.println(result);
	}

}
