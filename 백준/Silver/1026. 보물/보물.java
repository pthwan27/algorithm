import java.io.*;
import java.util.*;

public class Main {
	static int N;

	static class number {
		int num, pos;

		public number(int n, int p) {
			num = n;
			pos = p;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());

		int[] A = new int[N];
		int[] B = new int[N];

		String[] aInput = in.readLine().split(" ");
		String[] bInput = in.readLine().split(" ");

		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(aInput[i]);
			B[i] = Integer.parseInt(bInput[i]);
		}

		Arrays.sort(A);
		Arrays.sort(B);
		
		int result = 0;
		for(int i = 0; i < N; i++) {
			result += (A[i] * B[N - 1 - i]);
		}
		
		System.out.println(result);
	}

}
