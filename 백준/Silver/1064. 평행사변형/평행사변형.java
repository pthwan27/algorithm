import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int[] A = new int[2];
		int[] B = new int[2];
		int[] C = new int[2];

		String[] inputStr = in.readLine().split(" ");

		A[0] = Integer.parseInt(inputStr[0]);
		A[1] = Integer.parseInt(inputStr[1]);

		B[0] = Integer.parseInt(inputStr[2]);
		B[1] = Integer.parseInt(inputStr[3]);

		C[0] = Integer.parseInt(inputStr[4]);
		C[1] = Integer.parseInt(inputStr[5]);

		double AB = Math.hypot(B[0] - A[0], B[1] - A[1]);
		double AC = Math.hypot(C[0] - A[0], C[1] - A[1]);
		double BC = Math.hypot(C[0] - B[0], C[1] - B[1]);

		if ((C[1] - A[1]) * (B[0] - A[0]) == (B[1] - A[1]) * (C[0] - A[0])) {
			System.out.println(-1);
			return;
		} else {
			double case1 = AB * 2 + AC * 2;
			double case2 = BC * 2 + AC * 2;
			double case3 = BC * 2 + AB * 2;

			double max = Math.max(case1, Math.max(case2, case3));
			double min = Math.min(case1, Math.min(case2, case3));

			System.out.println(max - min);
		}
	}
}
